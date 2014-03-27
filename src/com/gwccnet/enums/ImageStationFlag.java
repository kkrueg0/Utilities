package com.gwccnet.enums;

public enum ImageStationFlag 
{
	IS_IMAGE_STATION("IS IMAGE STATION", "Y"),
	IS_NOT_IMAGE_STATION("IS NOT IMAGE STATION", "N"),
	UNKNOWN("UNKNOWN", "");
	
	private String displayName;
	private String persistentTypeCode;
	
	private ImageStationFlag(String displayName, String persistentTypeCode)
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
	
	public static final ImageStationFlag fromValue(String value)
	{
		for(ImageStationFlag enumValues : ImageStationFlag.values())
		{
			if(enumValues.displayName.equalsIgnoreCase(value) || 
			   enumValues.persistentTypeCode.equalsIgnoreCase(value) ||
			   enumValues.name().equalsIgnoreCase(value))
			{
				return enumValues;
			}
		}
		
		return ImageStationFlag.UNKNOWN;
	}
}
