package com.gwccnet.enums;
//test
public enum AccidentStatus
{
	INCOMPLETE("INCOMPLETE", "I", "1"),
	REJECTED("REJECTED", "R", "2"),
	COMPLETE("COMPLETE", "C", "3"),
	UNKNOWN("", "", " ");
	
	private String displayName;
	private String abbreviation;
	private String persistentTypeCode;
	
	private AccidentStatus(String displayName, String abbreviation, String persistentTypeCode)
	{
		this.displayName = displayName;
		this.abbreviation = abbreviation;
		this.persistentTypeCode = persistentTypeCode;
	}
	
	public final String value()
	{
		return this.displayName;
	}
	
	public final String getAbbreviation()
	{
		return this.abbreviation;
	}
	
	public final String toPersistentTypeCode()
	{
		return this.persistentTypeCode;
	}
	
	public final String toString()
	{
		return this.displayName;
	}
	
	public static final AccidentStatus fromValue(String value)
	{
		for(AccidentStatus enumValue : AccidentStatus.values())
		{
			if(enumValue.displayName.equalsIgnoreCase(value) || 
			   enumValue.abbreviation.equalsIgnoreCase(value) ||
			   enumValue.persistentTypeCode.equalsIgnoreCase(value) ||
			   enumValue.name().equalsIgnoreCase(value))
			{
				return enumValue;
			}
		}
		
		return AccidentStatus.UNKNOWN;
	}
}
