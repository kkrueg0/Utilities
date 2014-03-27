package com.gwccnet.enums;

public enum PolicyNoteSecurity 
{
	PUBLIC("PUBLIC", "1"),
	CORPORATE("CORPORATE", "2"),
	PRIVATE("PRIVATE", "3"),
	UNKNOWN("UNKNOWN", "");
	
	private String displayName;
	private String persistentTypeCode;
	
	private PolicyNoteSecurity(String displayName, String persistentTypeCode)
	{
		this.displayName = displayName;
		this.persistentTypeCode = persistentTypeCode;
	}
	
	public final String value()
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
	
	public static final PolicyNoteSecurity fromValue(String value)
	{
		for(PolicyNoteSecurity enumValue : PolicyNoteSecurity.values())
		{
			if(enumValue.displayName.equalsIgnoreCase(value) || 
			   enumValue.persistentTypeCode.equalsIgnoreCase(value) ||
			   enumValue.name().equalsIgnoreCase(value))
			{
				return enumValue;
			}
		}
		
		return PolicyNoteSecurity.UNKNOWN;
	}
}