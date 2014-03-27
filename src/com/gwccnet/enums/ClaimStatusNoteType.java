package com.gwccnet.enums;

public enum ClaimStatusNoteType 
{
	//TODO Find out what the correct values are.
	LIABILITY("LIABILITY", "L"),
	DELETED("DELETED", "D"),
	PROTECTED("PROTECTED", "X"),
	UNKNOWN("UNKNOWN", "");
	
	private String displayName;
	private String persistentTypeCode;
	
	private ClaimStatusNoteType(String displayName,String persistentTypeCode)
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
	
	public static final ClaimStatusNoteType fromValue(String value)
	{
		for(ClaimStatusNoteType claimStatusNoteType : ClaimStatusNoteType.values())
		{
			if(claimStatusNoteType.displayName.equalsIgnoreCase(value) ||
					claimStatusNoteType.persistentTypeCode.equalsIgnoreCase(value) ||
					claimStatusNoteType.name().equalsIgnoreCase(value))
				{
					return claimStatusNoteType;
				}
		}
		
		return ClaimStatusNoteType.UNKNOWN;
	}
}
