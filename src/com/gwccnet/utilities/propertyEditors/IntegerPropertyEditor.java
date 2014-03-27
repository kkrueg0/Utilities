/**
 * IntegerPropertyEditor.java
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


public class IntegerPropertyEditor extends PropertyEditorSupport
{

	public IntegerPropertyEditor()
	{
		// TODO Auto-generated constructor stub
	}

	public void addPropertyChangeListener(PropertyChangeListener arg0)
	{
		// TODO Auto-generated method stub

	}

	public String getAsText()
	{
		Object value = getValue();
		if (value != null)// && value instanceof Integer)
		{
			return ((Integer)value).toString();
		}
		else
		{
			return null;
		}
	}

	public Component getCustomEditor()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String getJavaInitializationString()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public String[] getTags()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Object getValue()
	{
		return super.getValue();
	}

	public boolean isPaintable()
	{
		// TODO Auto-generated method stub
		return false;
	}

	public void paintValue(Graphics arg0, Rectangle arg1)
	{
		// TODO Auto-generated method stub

	}

	public void removePropertyChangeListener(PropertyChangeListener arg0)
	{
		// TODO Auto-generated method stub

	}

	public void setAsText(String val) throws IllegalArgumentException
	{
		//Remove commas, periods, and dollar signs
		val = val.replaceAll(",|\\.|\\$", "");
		if (val.length() > 0)
		{
			setValue(new Integer(val));
		}
		else
		{
			setValue(null);
		}

	}

	public void setValue(Object arg0)
	{
		super.setValue(arg0);
	}

	public boolean supportsCustomEditor()
	{
		// TODO Auto-generated method stub
		return false;
	}

}
