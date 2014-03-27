package com.gwccnet.enums;

public enum PolicyNoteDepartment 
{
	CLAIMS("CLAIMS", "CL"),
	MANAGEMENT("MANAGEMENT", "MT"),
	SAFETY("SAFETY", "SF"),
	SIRRACCOUNTING("SIRR ACCOUNTING", "SR"),
	UNDERWRITING("UNDERWRITING", "UW"),
	UNKNOWN("UNKNOWN", "");
	
	private String displayName;
	private String persistentTypeCode;
	
	private PolicyNoteDepartment(String displayName, String persistentTypeCode)
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
	
	public static final PolicyNoteDepartment fromValue(String value)
	{
		for(PolicyNoteDepartment enumValue : PolicyNoteDepartment.values())
		{
			if(enumValue.displayName.equalsIgnoreCase(value) || 
			   enumValue.persistentTypeCode.equalsIgnoreCase(value) ||
			   enumValue.name().equalsIgnoreCase(value))
			{
				return enumValue;
			}
		}
		
		return PolicyNoteDepartment.UNKNOWN;
	}
}