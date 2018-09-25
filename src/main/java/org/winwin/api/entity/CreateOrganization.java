package org.winwin.api.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class CreateOrganization {
	String name;
	String description;
    Long totalAssets;
    String sector;
    String sectorLevel;
    String priority;
    boolean isTaggingReady;
    String tagStatus;
    String website;
    
    public static void main(String[] args) throws JsonProcessingException {
		CreateOrganization org = new CreateOrganization();
		org.setName("WinWin");
		org.setDescription("Non Profit");
		org.setTotalAssets(100000L);
		org.setSector("PUBLIC");
		org.setSectorLevel("Public Level");
		org.setPriority("LOW");
		org.setTaggingReady(false);
		org.setTagStatus("NOT WORKED");
		org.setWebsite("www.winwin.org");
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(org));
	}
}
