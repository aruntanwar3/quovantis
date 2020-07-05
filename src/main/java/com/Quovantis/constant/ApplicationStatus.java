package com.Quovantis.constant;

public enum ApplicationStatus {
 APPLIED("applied"), INVITED("invited"), REJECTED("rejected"),HIRED("hired");
	
	private String enumValue;
	public String getEnumValue() {
		return this.enumValue;
	}

	private ApplicationStatus(String enumValue) {
		this.enumValue = enumValue;
	}
}
