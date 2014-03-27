package com.gwccnet.enums;

public enum PolicyStatus 
{
	PENDING_ISSUE("PENDING ISSUE"),
	CANCELLED("CANCELLED"),
	PENDING_CANCEL("PENDING CANCEL"),
	EXPIRED("EXPIRED"),
	ACTIVE("ACTIVE"),
	UNKNOWN("UNKNOWN");	
	
	private String displayName;
	
	private PolicyStatus(String displayName)
	{
		this.displayName = displayName;
	}
	
	public final String value()
	{
		return this.displayName;
	}
	public final String toString()
	{
		return this.displayName;
	}
	
	public static final PolicyStatus fromValue(String value)
	{
		if(value!=null){
			value = value.trim();
		}
		for(PolicyStatus policyStatus : PolicyStatus.values())
		{
			if(policyStatus.displayName.equalsIgnoreCase(value) || 
				policyStatus.name().equalsIgnoreCase(value))
			{
				return policyStatus;
			}
		}
		
		return PolicyStatus.UNKNOWN;
	}
}