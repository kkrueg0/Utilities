package com.gwccnet.enums;

public enum DriverAccountTransferOption
{
	MOVE_DRIVER("MOVE DRIVER", "move"),
	COPY_DRIVER("COPY DRIVER", "copy"),
	UNKNOWN("UNKNOWN", "");
	
	private String displayName;
	private String shortName;
	
	private DriverAccountTransferOption(String displayName, String shortName)
	{
		this.displayName = displayName;
		this.shortName = shortName;
	}
	
	public final String Value()
	{
		return this.displayName;
	}
		
	public final String toString()
	{
		return this.displayName;
	}
	
	public final String getShortName()
	{
		return this.shortName;
	}
	
	public static final DriverAccountTransferOption fromValue(String value)
	{
		for(DriverAccountTransferOption driverAccountTransferOption : DriverAccountTransferOption.values())
		{
			if(driverAccountTransferOption.displayName.equalsIgnoreCase(value) ||
			   driverAccountTransferOption.shortName.equalsIgnoreCase(value) ||
			   driverAccountTransferOption.name().equalsIgnoreCase(value))
			{
				return driverAccountTransferOption;
			}
		}
		
		return DriverAccountTransferOption.UNKNOWN;
	}
}
