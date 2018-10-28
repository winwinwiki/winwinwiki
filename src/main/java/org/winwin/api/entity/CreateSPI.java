package org.winwin.api.entity;

import lombok.Data;

@Data
public class CreateSPI {

	Long programId;
	Long spiId;
	String tagType;
	String value;
}
