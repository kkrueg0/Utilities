package com.gwccnet.enums;

public enum ClaimType 
{
	LIABILITY_CLAIM("LIABILITY CLAIM", "L"),
	PHYSICAL_DAMAGE_CLAIM("PHYSICAL DAMAGE CLAIM", "M"),
	OCC_ACC_CLAIM("OCC/ACC CLAIM", "O"),
	PROPERTY_CLAIM("PROPERTY CLAIM", "P"),
	WORKERS_COMP_CLAIM("WORKERS COMP CLAIM", "W"),
	UNKNOWN("UNKNOWN", "");
	
	private String displayName;
	private String persistentTypeCode;
	
	private ClaimType(String displayName,String persistentTypeCode)
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
	
	public static final ClaimType fromValue(String value)
	{
		for(ClaimType claimType : ClaimType.values())
		{
			if(claimType.displayName.equalsIgnoreCase(value) ||
					claimType.persistentTypeCode.equalsIgnoreCase(value) ||
					claimType.name().equalsIgnoreCase(value))
				{
					return claimType;
				}
		}
		
		return ClaimType.UNKNOWN;
	}
}
