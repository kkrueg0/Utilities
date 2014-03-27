package com.gwccnet.enums;

public enum PolicyType 
{
	TRUCK("TRUCK", "TR"),
	WORK_COMP("WORK COMP", "WC"),
	INLAND_MARINE("INLAND MARINE", "IM"),
	SURETY_BOND("SURETY BOND", "BD"),
	BUSINESS_AUTO("BUSINESS AUTO", "BA"),
	BACKUP("BACKUP", "BU"),
	GENERAL_LIABILITY("GENERAL LIABILITY", "GL"),
	CARGO("CARGO", "CG"),
	UMBRELLA("UMBRELLA", "UM"),
	CRIME("CRIME", "CR"),
	COMMERCIAL_MULTI_PERIL("COMMERCIAL MULTI-PERIL", "CM"),
	OCCUPATIONAL_ACCIDENT("OCCUPATIONAL ACCIDENT", "OC"),
	UNKNOWN("UNKNOWN", "");
	
	private String displayName;
	private String persistentTypeCode;
	
	private PolicyType(String displayName, String persistentTypeCode)
	{
		this.displayName = displayName;
		this.persistentTypeCode = persistentTypeCode;
	}
	
	public final String value()
	{
		return this.displayName;
	}
	
	public final String toString()
	{
		return this.displayName;
	}
	
	public final String toPersistentTypeCode()
	{
		return this.persistentTypeCode;
	}
	
	public static final PolicyType fromValue(String value)
	{
		for(PolicyType policyType : PolicyType.values())
		{
			if(policyType.displayName.equalsIgnoreCase(value) ||
				policyType.name().equalsIgnoreCase(value) ||
				policyType.persistentTypeCode.equalsIgnoreCase(value))
			{
				return policyType;
			}
		}
		
		return PolicyType.UNKNOWN;
	}
}
