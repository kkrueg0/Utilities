package com.gwccnet.enums;

public enum ReportType 
{
	MVR_USAGE_BY_REGION_REPORT("MVR USAGE BY REGION", "R"),
	MVR_USAGE_BY_COMPANY_REPORT("MVR USAGE BY COMPANY", "C"),
	UNKNOWN("UNKNOWN", "");
	
	public static final String MVR_USAGE_BY_COMPANY_AND_REGION_PERSISTENT_TYPE_CODE = "A";
	
	private String displayName;
	private String persistentTypeCode;
	
	private ReportType(String displayName, String persistentTypeCode)
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
	
	public static final ReportType fromValue(String value)
	{
		for(ReportType reportType : ReportType.values())
		{
			if(reportType.displayName.equalsIgnoreCase(value) ||
			   reportType.name().equalsIgnoreCase(value) ||
			   reportType.persistentTypeCode.equalsIgnoreCase(value))
			{
				return reportType;
			}
		}
		
		return ReportType.UNKNOWN;
	}
}
