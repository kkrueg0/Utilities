package com.gwccnet.enums;

public enum ImUserJobType 
{
	CLAIMS_ADJUSTOR("CLAIMS ADJUSTOR", "ADJ"),
	CLAIMS_ASSISTANT("CLAIMS ASSISTANT", "AST"),
	SUBROGATION_ATTORNEY("SUBROGATION ATTORNEY", "ATT"),
	AUDITOR("AUDITOR", "AUD"),
	DATA_ENTRY("DATA ENTRY", "D_E"),
	DATA_PROCESSING("DATA PROCESSING", "D_P"),
	IT_PERSON("IT PERSON", "I_T"),
	INDEXER("INDEXER", "INX"),
	OFFICE_CLAIMS_PROCESSOR("OFFICE CLAIMS PROCESSOR", "OCP"),
	STENOGRAPHER("STENOGRAPHER", "STN"),
	SUPERVISOR("SUPERVISOR", "SUP"),
	VICE_PRESIDENT_CLAIMS("VICE PRESIDENT - CLAIMS", "VPC"),
	ASSISTANT_VICE_PRESIDENT("ASSISTANT VICE PRESIDENT", "AVP"),
	ASSISTANT_TO_ATTORNEY("ASSISTANT TO ATTORNEY", "AXT"),
	UNKNOWN("UNKNOWN", "");
	
	private String displayName;
	private String persistentTypeCode;
	
	private ImUserJobType(String displayName,String persistentTypeCode)
	{
		this.displayName = displayName;
		this.persistentTypeCode = persistentTypeCode;
	}
	
	public final String Value()
	{
		return this.displayName;
	}
	
	public final String toPersistentTypeCode()
	{
		return this.persistentTypeCode;
	}
	
	public final String toString()
	{
		return this.displayName;
	}
	
	public static final ImUserJobType fromValue(String value)
	{
		for(ImUserJobType imUserJobType : ImUserJobType.values())
		{
			if(imUserJobType.displayName.equalsIgnoreCase(value) ||
					imUserJobType.persistentTypeCode.equalsIgnoreCase(value) ||
					imUserJobType.name().equalsIgnoreCase(value))
				{
					return imUserJobType;
				}
		}
		
		return ImUserJobType.UNKNOWN;
	}
}
