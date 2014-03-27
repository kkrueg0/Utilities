/**
 * DoublePropertyEditor.java
 * 
 * Jan 10, 2008
 * 
 * Copyright 2008 Great West Casualty Company
 * 
 * @author kkrueg0
 * 
 * Comments:
 */
package com.gwccnet.utilities.propertyEditors;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditorSupport;


public class DoublePropertyEditor extends PropertyEditorSupport
{

	public DoublePropertyEditor()
	{
	}

	public void addPropertyChangeListener(PropertyChangeListener arg0)
	{

	}

	public String getAsText()
	{
		Object value = getValue();
		if (value != null && value instanceof Double)
		{
			return ((Double)value).toString();
		}
		else
		{
			return "0.0";
		}
	}

	public Component getCustomEditor()
	{
		return null;
	}

	public String getJavaInitializationString()
	{
		return null;
	}

	public String[] getTags()
	{
		return null;
	}

	public Object getValue()
	{
		return (super.getValue() != null?super.getValue():new Double(0));
	}

	public boolean isPaintable()
	{
		return false;
	}

	public void paintValue(Graphics arg0, Rectangle arg1)
	{

	}

	public void removePropertyChangeListener(PropertyChangeListener arg0)
	{

	}

	public void setAsText(String val) throws IllegalArgumentException
	{
		//Remove dollar signs and commas
		val = val.replaceAll(",|\\$", "");
	
		if (val == null || val.length() == 0)
		{
			val = "0";
		}
		setValue(new Double(val));

	}

	public void setValue(Object val)
	{
		if (val == null)
		{
			val = new Double(0);
		}
		super.setValue(val);
	}

	public boolean supportsCustomEditor()
	{
		return false;
	}

}
