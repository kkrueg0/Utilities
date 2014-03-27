package com.gwccnet.opencsv;

public class ColumnMapping
{
	protected String columnName;
	protected String propertyName;
	protected boolean exported = true;
	protected String formatString;
	protected int position;
	
	public boolean isExported()
	{
		return exported;
	}
	
	public void setExported(boolean exported)
	{
		this.exported = exported;
	}
	
	public String getColumnName()
	{
		return columnName;
	}
	
	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}
	
	public String getPropertyName()
	{
		return propertyName;
	}
	
	public void setPropertyName(String propertyName)
	{
		this.propertyName = propertyName;
	}
	
	public String getFormatString() 
	{
		return formatString;
	}
	
	public void setFormatString(String formatString) 
	{
		this.formatString = formatString;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) 
	{
		this.position = position;
	}
	
	public ColumnMapping(String columnName, String propertyName)
	{
		super();
		this.columnName = columnName;
		this.propertyName = propertyName;
	}
	
	public ColumnMapping(String columnName, String propertyName, int position)
	{
		super();
		this.columnName = columnName;
		this.propertyName = propertyName;
		this.position = position;
	}
}
