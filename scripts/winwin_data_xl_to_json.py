#!/usr/bin/python

import sys, getopt
import json
import os
import re
import pandas as pd

#Get Command Line Arguments
def main(argv):
    input_file = ''
    output_file = ''
    format = ''
    es_url = None
    help_string = os.path.basename(__file__) + ' -i <path to inputfile> -o <path to outputfile> -f <dump/pretty> -u <es-url>'
    try:
        opts, args = getopt.getopt(argv,"hi:o:f:u:",["ifile=","ofile=","format=","es-url="])
    except getopt.GetoptError:
        print help_string
        sys.exit(2)
    for opt, arg in opts:
        if opt == '-h':
            print help_string
            sys.exit()
        elif opt in ("-i", "--ifile"):
            input_file = arg
        elif opt in ("-o", "--ofile"):
            output_file = arg
        elif opt in ("-f", "--format"):
            format = arg
        elif opt in ("-u", "--es-url"):
            es_url = arg

    if es_url:
        format = "dump"
    process_excel(input_file, output_file, format, es_url)

#Process Excel File
def process_excel(file, json_file, format, es_url):
    csv_rows = []
    upload_command = None
    if es_url:
        upload_command = "curl -XPOST {}/_bulk --data-binary \@{} -H 'Content-Type: application/json'".format(es_url, json_file)
    xl = pd.ExcelFile(file)
    first_sheet = xl.sheet_names[0]
    df = xl.parse(first_sheet) # assume data is in first sheet
    columns = df.columns
    row_count = 0
    for _, row in df.iterrows():
        row_count = row_count + 1
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
                spi_primary_field = re.search('Primary( \S*)( \(Auto\))*', field_name, re.IGNORECASE)
                spi_field = re.search('SPI( \S*)( \d+)*( \(Auto\))*', field_name, re.IGNORECASE)
                sdg_field = re.search('SDG( \S*)( \d+)*( \(Auto\))*', field_name, re.IGNORECASE)
                ntee_field = re.search('NTEE( \S*)( \(Auto\))*', field_name, re.IGNORECASE)
                headquarters_field = re.search('Headquarters( \S*)( \(Auto\))*', field_name, re.IGNORECASE)
                if spi_primary_field:
                    tag_id = "1"
                    spi_primary_field_name = spi_primary_field.group(1).strip()
                    if tag_id not in SPI:
                        SPI[tag_id] = {}
                    SPI[tag_id][spi_primary_field_name] = field_value
                    SPI[tag_id]["Primary"] = True
                elif spi_field:
                    tag_id = spi_field.group(2) or "1"
                    tag_id = tag_id.strip()
                    spi_field_name = spi_field.group(1).strip()
                    if tag_id not in SPI:
                        SPI[tag_id] = {}
                    SPI[tag_id][spi_field_name] = field_value
                elif sdg_field:
                    tag_id = sdg_field.group(2) or "1"
                    tag_id = tag_id.strip()
                    sdg_field_name = sdg_field.group(1).strip()
                    if tag_id not in SDG:
                        SDG[tag_id] = {}
                    SDG[tag_id][sdg_field_name] = field_value
                elif ntee_field:
                    ntee_field_name = ntee_field.group(1).strip()
                    NTEE[ntee_field_name] = field_value
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
            es_meta_row = { "index" : { "_index": "winwin", "_type" : "program", "_id" : program['ID'] } }
            csv_rows.append(es_meta_row)
            csv_rows.append(program)
        if es_url and row_count % 1000 == 0:
            write_json(csv_rows, json_file, format)
            os.system(upload_command)
            print "\nuploaded {} records".format(row_count)
            csv_rows = []
    write_json(csv_rows, json_file, format)
    if es_url and len(csv_rows) > 0:
        os.system(upload_command)
        print "\nuploaded {} records".format(row_count)
        os.remove(json_file)
    else:
        print "\processed {} records".format(row_count)

def map_to_array(inputMap):
    outputArray = []
    for key, value in inputMap.iteritems():
        outputArray.append(value)
    return outputArray

#Convert csv data into json and write it
def write_json(data, json_file, format):
    with open(json_file, "w") as f:
        for entry in data:
            if format == "pretty":
                f.write(json.dumps(entry, sort_keys=True, indent=4, separators=(',', ': ')))
            else:
                f.write(json.dumps(entry))
            f.write("\n")

if __name__ == "__main__":
   main(sys.argv[1:])
