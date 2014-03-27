package com.gwccnet.enums;

public enum WorkflowItemType 
{
	RENEWAL("RENAP", "Renewal Application"),
	NEW("NEWAP", "New Application"),
	ENDORSEMENT("PENDR", "Endorsement"),
	UNKNOWN("", "");
	
	private String displayName;
	private String persistentTypeCode;
	
	private WorkflowItemType(String persistentTypeCode, String displayName)
	{
		this.displayName = displayName;
		this.persistentTypeCode = persistentTypeCode;
	}
	
	public final String value()
	{
		return this.displayName;
	}
	
	public final String getPersistentTypeCode()
	{
		return persistentTypeCode;
	}
	
	public final String toString()
	{
		return this.displayName;
	}
	
	public static final WorkflowItemType fromValue(String value)
	{
		String testValue = value.replaceAll("\\W", "");
		
		for(WorkflowItemType workStatus : WorkflowItemType.values())
		{
			if(workStatus.displayName.equalsIgnoreCase(testValue) || 
			   workStatus.persistentTypeCode.equalsIgnoreCase(testValue) ||
			   workStatus.name().equalsIgnoreCase(testValue))
			{
				return workStatus;
			}
		}
		
		return WorkflowItemType.UNKNOWN;
	}
}
