#!/usr/bin/python

import datetime
import sys, getopt
import json
import os
import re
import pandas as pd

#Get Command Line Arguments
def main(argv):
    input_file = ''
    format = ''
    es_url = None
    delete_index = False 
    help_string = os.path.basename(__file__) + ' -d -i <path to inputfile> -u <elastic search url>'
    try:
        opts, args = getopt.getopt(argv,"hdi:f:u:",["inputfile=","format=","url="])
    except getopt.GetoptError:
        print help_string
        sys.exit(2)
    for opt, arg in opts:
        if opt == ("-h", "--help"):
            print help_string
            sys.exit()
        elif opt in ("-i", "--inputfile"):
            input_file = arg
        elif opt in ("-f", "--format"):
            format = arg
        elif opt in ("-u", "--url"):
            es_url = arg
        elif opt in ("-d", "--deleteindex"):
            delete_index = True

    if delete_index:
        print "\ndeleting records\n"
        os.system(get_delete_command(es_url))

    if es_url:
        format = "dump"
    process_excel(input_file, format, es_url)

def is_float(input):
  try:
    num = float(input)
  except ValueError:
    return False
  return True

def get_delete_command(es_url):
    return "curl -XDELETE {0}".format(es_url)

def get_post_command(es_url, json_file):
    return "curl -XPOST {0}/_bulk --data-binary \@{1} -H 'Content-Type: application/json'".format(es_url, json_file)

#Process Excel File
def process_excel(file, format, es_url):
    csv_rows = []
    xl = pd.ExcelFile(file)
    first_sheet = xl.sheet_names[0]
    df = xl.parse(first_sheet) # assume data is in first sheet
    columns = df.columns
    row_count = 0
    batch_number = 0
    output_file_prefix = datetime.datetime.today().strftime('%Y-%m-%d-%H-%M-%S')
    for _, row in df.iterrows():
        SPI = {}
        SDG = {}
        NTEE = {}
        headquarters = {}
        program = {}
        for i in range(len(columns)):
            field_name = columns[i].strip()
            field_value = row[field_name]
            if pd.isna(field_value):
                continue
            else:
                if isinstance(field_value, basestring):
                    field_value = field_value.strip()
                    if field_value in ["", "TBD"]:
                        continue
                    if field_name in ["Open or Closed Data Set", "Sector", "Sector' (Auto)", "Sector Level"]:
                        field_value = field_value.title()
                    if field_name in ["Revenue or Budget", "Assets", "GM Donated Amount", "NAICS Code"]:
                        field_value = field_value.replace("$", "").replace(",", "")
                        if not is_float(field_value):
                            print "leaving out cell:{} with value:{}".format(field_name, field_value)
                            continue
                if field_name in ["EIN", "Headquarters Zip Code", "Organization"]:
                    field_value = unicode(field_value)
 
                spi_primary_field = re.search('Primary( \S*)( \(Auto\))*', field_name, re.IGNORECASE)
                spi_field = re.search('SPI( \S*)( \d+)*( \(Auto\))*', field_name, re.IGNORECASE)
                sdg_field = re.search('SDG( \S*)( \d+)*( \(Auto\))*', field_name, re.IGNORECASE)
                ntee_field = re.search('NTEE( \S*)( \(Auto\))*', field_name, re.IGNORECASE)
                headquarters_field = re.search('Headquarters( \S*)( \(Auto\))*', field_name, re.IGNORECASE)
                if field_name in ["Resources", "Data Set"]:
                    program[field_name] = [x.strip() for x in field_value.split(',')]
                elif spi_primary_field:
                    tag_id = "1"
                    spi_primary_field_name = spi_primary_field.group(1).strip()
                    if tag_id not in SPI:
                        SPI[tag_id] = {}
                    SPI[tag_id][spi_primary_field_name] = field_value.title()
                    SPI[tag_id]["Primary"] = True
                elif spi_field:
                    tag_id = spi_field.group(2) or "1"
                    tag_id = tag_id.strip()
                    spi_field_name = spi_field.group(1).strip()
                    if tag_id not in SPI:
                        SPI[tag_id] = {}
                    SPI[tag_id][spi_field_name] = field_value.title()
                elif sdg_field:
                    tag_id = sdg_field.group(2) or "1"
                    tag_id = tag_id.strip()
                    sdg_field_name = sdg_field.group(1).strip()
                    if tag_id not in SDG:
                        SDG[tag_id] = {}
                    SDG[tag_id][sdg_field_name] = str(field_value).title()
                elif ntee_field:
                    ntee_field_name = ntee_field.group(1).strip()
                    NTEE[ntee_field_name] = field_value.title()
                elif headquarters_field:
                    headquarters_field_name = headquarters_field.group(1).strip()
                    headquarters[headquarters_field_name] = field_value
                else:
                    program[field_name] = field_value
            program["SPI"] = map_to_array(SPI)
            program["SDG"] = map_to_array(SDG)
            program["NTEE"] = NTEE
            program["Headquarters"] = headquarters

        if 'ID' in program:
            row_count = row_count + 1
            es_meta_row = { "index" : { "_index": "winwin", "_type" : "program", "_id" : program['ID'] } }
            csv_rows.append(es_meta_row)
            csv_rows.append(program)

        if es_url and row_count % 1000 == 0:
            batch_number = batch_number + 1
            json_file = write_json(csv_rows, format, output_file_prefix, batch_number)
            os.system(get_post_command(es_url, json_file))
            print "\nuploaded {} records".format(row_count)
            csv_rows = []

    # process remaining rows
    if row_count % 1000 != 0:
        batch_number = batch_number + 1
        json_file = write_json(csv_rows, format, output_file_prefix, batch_number)
        if es_url:
            os.system(get_post_command(es_url, json_file))
            print "\nuploaded {} records".format(row_count)

    print "\nprocessed {} records".format(row_count)

def map_to_array(inputMap):
    outputArray = []
    for key, value in inputMap.iteritems():
        outputArray.append(value)
    return outputArray

#Convert csv data into json and write it
def write_json(data, format, output_file_prefix, batch_number):
    json_file = '{0}-{1}.json'.format(output_file_prefix, batch_number)
    print "\nuoutput file json_file\n".format(json_file)
    with open(json_file, "w") as f:
        for entry in data:
            if format == "pretty":
                f.write(json.dumps(entry, sort_keys=True, indent=4, separators=(',', ': ')))
            else:
                f.write(json.dumps(entry))
            f.write("\n")
    return json_file

if __name__ == "__main__":
   main(sys.argv[1:])
