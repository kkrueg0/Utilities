package com.gwccnet.opencsv;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.HeaderColumnNameMappingStrategy;

public class BidirectionalHeaderColumnNameMappingStrategy<T> extends
		HeaderColumnNameMappingStrategy<T>
{
	@SuppressWarnings("unchecked")
	protected Comparator sortComp = new BeanComparator("position");
	protected List<ColumnMapping> columnMappings = new ArrayList<ColumnMapping>();
	protected boolean exportOnly;
	protected boolean strictHeaderNames = false;
	public BidirectionalHeaderColumnNameMappingStrategy(Class<T> type)
	{
		super();
		setType(type);
		// TODO Auto-generated constructor stub
	}
	public boolean isExportOnly()
	{
		return exportOnly;
	}
	public void setExportOnly(boolean exportOnly)
	{
		this.exportOnly = exportOnly;
	}
	@Override
	 public void captureHeader(CSVReader reader) throws IOException {
	        header = reader.readNext();
	        if (isStrictHeaderNames())
	        {
	        	for (int i = 0; i < header.length;++i)
	        	{
	        		if (header[i] != null)
	        		{
	        			header[i] = header[i].replaceAll("[^\\w\\s]", "");
	        		}
	        	}
	        }
	 }
	
	public String[] getHeaders()
	{
		return header;
	}
	public ColumnMapping addColumnMapping(String columnName,String fieldName)
    {
    	ColumnMapping cm = new ColumnMapping(columnName,fieldName,columnMappings.size());
		this.columnMappings.add(cm);
		return cm;
    }
    protected String getColumnName(int col) 
    {
        return getPropertyName(header[col]);
    }
    
    public String getPropertyName(String columnName)
    {
    	String fieldName = null;
    	for (ColumnMapping mapping:columnMappings)
    	{
    		if (mapping.getColumnName().equalsIgnoreCase(columnName))
    		{
    			fieldName = mapping.getPropertyName();
    			break;
    		}
    	}
    	return fieldName;
    }
    public String getColumnName(String fieldName)
    {
    	String colName = null;
    	for (ColumnMapping mapping:columnMappings)
    	{
    		if (mapping.getPropertyName().equalsIgnoreCase(fieldName))
    		{
    			
    			colName = mapping.getColumnName();
    			break;
    		}
    	}
    	return colName;
    }
    
    @SuppressWarnings("unchecked")
	public void sortColumnMappings()
    {
    	if(this.sortComp != null)
    	{
    		Collections.sort(columnMappings, sortComp);
    	}
    }
        
    public String[] getColumnNames()
    {
    	
    	//String[] cols = new String[columnMappings.size()];
    	List<String> cols = new ArrayList<String>();
    	ColumnMapping cm = null;
    	for (int i = 0;i < columnMappings.size();++i)
    	{
    		cm = columnMappings.get(i);
    		if (!exportOnly || (exportOnly && cm.isExported()))
    		{
    			cols.add(cm.getColumnName());
    		}
    	}
    	
    	String[] cols2 = new String[cols.size()];
    	return cols.toArray(cols2);
    }
    public List<ColumnMapping> getSortedColumnMappings()
    {
    	sortColumnMappings();
    	return columnMappings;
    }
    public List<ColumnMapping> getColumnMappings()
    {
    	return this.columnMappings;
    }
    public PropertyDescriptor getPropertyDescriptor(String name) throws IntrospectionException
    {
    	return this.findDescriptor(name);
    }
	public boolean isStrictHeaderNames() {
		return strictHeaderNames;
	}
	public void setStrictHeaderNames(boolean strictHeaderNames) {
		this.strictHeaderNames = strictHeaderNames;
	}
    
}
