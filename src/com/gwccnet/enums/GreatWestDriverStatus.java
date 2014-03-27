package com.gwccnet.enums;

public enum GreatWestDriverStatus 
{
	ACTIVE("ACTIVE", "A"),
	INCOMPLETE("INCOMPLETE", "I"),
	PROBATION("PROBATION", "P"),
	REJECTED("REJECTED", "R"),
	UNKNOWN("UNKNOWN", "U");	
	
	private String displayName;
	private String persistentTypeCode;
	
	private GreatWestDriverStatus(String displayName, String persistentTypeCode)
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
	
	public static final GreatWestDriverStatus fromValue(String value)
	{
		if(value!=null){
			value = value.trim();
		}
		for(GreatWestDriverStatus requestStatus : GreatWestDriverStatus.values())
		{
			if(requestStatus.displayName.equalsIgnoreCase(value) || 
			   requestStatus.persistentTypeCode.equalsIgnoreCase(value) ||
			   requestStatus.name().equalsIgnoreCase(value))
			{
				return requestStatus;
			}
		}
		
		return GreatWestDriverStatus.UNKNOWN;
	}
}