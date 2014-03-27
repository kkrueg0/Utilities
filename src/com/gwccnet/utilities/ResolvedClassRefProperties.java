/*
 * ResolvedClassRefProperties.java
 * 
 * Created by rlacyx0
 * 
 * Created on July 19, 2006
 *
 * Copyright 2006 Great West Casualty Company
 */
 
package com.gwccnet.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import com.gwccnet.utilites.Exception.ResolvedClassRefPropertiesException;

/**
 * This class is an extension of the Properties class.  If an entry in the
 * .properties file is prefixed with a special character ('%' by default)
 * the entry is treated as if it is a fully-qualified field name.  An
 * attempt is made to use the reflection API to obtain the 'actual' .properties
 * file entry.  This is useful as a means to cross-reference entries from
 * interfaces in the com.gwccnet.WebEnhancement.config package.
 */

public class ResolvedClassRefProperties extends Properties  
{	
	private static final long serialVersionUID = -2595301696931865435L;
	
	private final char mRefMarker;
	
	/**
	 * Constructor.  Sets the prefix character to '%'.  Entries that
	 * begin with this character will be treated as field names.
	 *
	 */
	
	public ResolvedClassRefProperties()
	{
		super();
		mRefMarker = '%';
	}
	
	/**
	 * Constructor.  Allows a choice for the prefix character before
	 * the properties file is loaded.
	 * 
	 * @param refMarker
	 */
	public ResolvedClassRefProperties(char refMarker)
	{
		super();
		mRefMarker = refMarker;
	}
	
	/**
	 * Loads the .properties file into a temporary Properties instance.
	 * Attempts to resolve each fully-qualified field name via reflection and
	 * build the 'real' properties from these resolved field references.
	 * 
	 * @param InputStream - The stream from which to load the properties file.
	 */
	
	public void load(InputStream inStream) throws IOException
	{
		try
		{
			clear();
			Properties tempProperties = new Properties();

			tempProperties.load(inStream);	
			Enumeration propertyNames = tempProperties.propertyNames();
			while(propertyNames.hasMoreElements())
			{
				String name = (String) propertyNames.nextElement();
				String value = tempProperties.getProperty(name);
			
				if(value != null)
				{
					setProperty(resolveClassRef(name), resolveClassRef(value));
				}
			}
		}
		finally
		{
			if(inStream != null)
			{
				inStream.close();
				inStream = null;
			}
		}
	}
		
	/**
	 * Receives a string parameter.  If the parameter begins with the 
	 * designated marker, the parameter is treated as a fully-qualified field
	 * name and the reflection API is used to obtain its true value.
	 * 
	 * @param ref - The String to be resolved.
	 * @return - The resolved String.
	 * @throws ResolvedClassRefPropertiesException
	 */
	
	private String resolveClassRef(String ref) throws ResolvedClassRefPropertiesException
	{
		if(ref != null && ref.length() > 0)
		{
			if(ref.charAt(0) == mRefMarker)
			{
				int lastPeriodPos = ref.lastIndexOf('.');
				if(lastPeriodPos < ref.length() - 1)
				{
					String className = ref.substring(1, lastPeriodPos);
					String fieldName = ref.substring(lastPeriodPos + 1);
					
					try
					{
						return (String) Class.forName(className).getField(fieldName).get(null);
					}
					catch(Exception e)
					{	
						e.printStackTrace();
						throw new ResolvedClassRefPropertiesException("Failure to resolve reference: " + ref);
					}
				}
			}
			else
			{
				return ref;
			}
		}

		return null;
	}
}
