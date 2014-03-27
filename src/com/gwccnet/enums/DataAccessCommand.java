package com.gwccnet.enums;

public enum DataAccessCommand 
{
	CREATE("CREATE"),
	READ("READ"),
	UPDATE("UPDATE"),
	DELETE("DELETE"),
	UNKNOWN("UNKNOWN");
	
	private String displayName;
	
	private DataAccessCommand(String displayName)
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
	
	public static final DataAccessCommand fromValue(String value)
	{
		for(DataAccessCommand dataAccessCommand : DataAccessCommand.values())
		{
			if(dataAccessCommand.displayName.equalsIgnoreCase(value) ||
			   dataAccessCommand.name().equalsIgnoreCase(value))
			{
				return dataAccessCommand;
			}	
		}
		
		return DataAccessCommand.UNKNOWN;
	}
}
