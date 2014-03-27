package com.gwccnet.opencsv;

import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import au.com.bytecode.opencsv.CSVWriter;

import com.gwccnet.utilities.CommonUtils;
import com.gwccnet.utilities.propertyDescriptors.BeanInformationService;
import com.gwccnet.utilities.propertyDescriptors.CachingBeanInformationServiceImpl;

public class BeanToCsvWriter<T>
{
	//testing
	public static final String EMPTY_STRING = "";
	protected BeanInformationService beanInfo = new CachingBeanInformationServiceImpl();
	private boolean includeHeaderRow = true;
	private boolean disallowNullStrings = false;
	
	public void write(CSVWriter writer,BidirectionalHeaderColumnNameMappingStrategy<T> strategy,List<T> beans) throws Exception
	{
		if(this.getIncludeHeaderRow()) 
		{
			String[] currentRow =strategy.getColumnNames();
			writer.writeNext(currentRow);
		}
		
		for (T bean:beans)
		{
			writer.writeNext(getBeanValues(strategy,bean));
		}
		writer.close();
	}
	public String[] getBeanValues(BidirectionalHeaderColumnNameMappingStrategy<T> strategy,T bean) throws Exception
	{
		List<ColumnMapping> mappings = strategy.getColumnMappings();
		String[] values = new String[mappings.size()];
		int i = 0;

		for (ColumnMapping mapping:mappings)
		{
			if (mapping.isExported())
			{
				//Find the property descriptor for the current header
				PropertyDescriptor desc = strategy.getPropertyDescriptor(mapping.getPropertyName());
				if (desc == null)
				{
					//If no property descriptor is found, set the field value to empty string
					values[i] = EMPTY_STRING;
				}
				else
				{
					
					//If there is an explicit format choice, use it.
					//Otherwise, if a property editor exists for the property, use it to set
					//the value of the field.
					if(!CommonUtils.isNullOrEmpty(mapping.getFormatString()))
					{
						Object obj = this.getRawValue(desc, bean);
						
						if(obj != null) 
						{
							values[i] = String.format(mapping.getFormatString(), obj);
						}
					}
					else 
					{
						PropertyEditor editor = beanInfo.getPropertyEditor(desc);
						if (editor != null)
						{
							editor.setValue(getRawValue(desc,bean));
							values[i] = editor.getAsText();
						}
						else
						{
							values[i] = getRawStringValue(desc,bean);
						}
					}
				}
				
				if(this.getDisallowNullStrings())
				{
					if(values[i] == null || values[i].equalsIgnoreCase("NULL")) 
					{
						values[i] = EMPTY_STRING;
					}
				}
				
				++i;
			}
		}
		return values;
	}
	
	protected Object getRawValue(PropertyDescriptor desc,T bean) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		return desc.getReadMethod().invoke(bean, null);
	}
	
	protected String getRawStringValue(PropertyDescriptor desc,T bean) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		String rawValue= EMPTY_STRING;
		//Use reflection to set the value of the field.
		Object obj = getRawValue(desc,bean);
		//TODO handle more types
		if (desc.getPropertyType().equals(String.class))
		{
			rawValue = (String)obj;
		}
		else if (desc.getPropertyType().equals(Integer.class))
		{
			rawValue = String.valueOf((Integer)obj);
		}
		return rawValue;
	}
	
	public boolean getIncludeHeaderRow()
	{
		return includeHeaderRow;
	}
	
	public void setIncludeHeaderRow(boolean includeHeaderRow) 
	{
		this.includeHeaderRow = includeHeaderRow;
	}
	
	public boolean getDisallowNullStrings() 
	{
		return disallowNullStrings;
	}
	
	public void setDisallowNullStrings(boolean disallowNullStrings) 
	{
		this.disallowNullStrings = disallowNullStrings;
	}
}
