package com.gwccnet.enums;

public enum CorrespondenceType 
{
	REJECTED("REJECTED", "REJ"),
	PROBATION("PROBATION", "PRB"),
	UNKNOWN("UNKNOWN", "");
	
	private String displayName;
	private String persistentTypeCode;
	
	private CorrespondenceType(String displayName,String persistentTypeCode)
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
	
	public static final CorrespondenceType fromValue(String value)
	{
		for(CorrespondenceType correspondenceType : CorrespondenceType.values())
		{
			if(correspondenceType.displayName.equalsIgnoreCase(value) ||
					correspondenceType.persistentTypeCode.equalsIgnoreCase(value) ||
					correspondenceType.name().equalsIgnoreCase(value))
				{
					return correspondenceType;
				}
		}
		
		return CorrespondenceType.UNKNOWN;
	}
}
