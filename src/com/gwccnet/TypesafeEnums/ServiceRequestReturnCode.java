/*
 * Created on March 23, 2006
 */
package com.gwccnet.TypesafeEnums;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author rlacyx0
 */
public class ServiceRequestReturnCode implements Comparable
{
	private final String returnCode;
	
	private ServiceRequestReturnCode(String returnCode)
	{
		this.returnCode = returnCode;
		
		returnCodeList.add(this);
	}
	
	private static final List returnCodeList = new ArrayList();
	
	public static final ServiceRequestReturnCode SUCCESS = new ServiceRequestReturnCode("SUCCESS");
	public static final ServiceRequestReturnCode FAILURE = new ServiceRequestReturnCode("FAILURE");
	public static final ServiceRequestReturnCode NOT_FOUND = new ServiceRequestReturnCode("NOT_FOUND");
	public static final ServiceRequestReturnCode UNKNOWN = new ServiceRequestReturnCode("UNKNOWN");
		
	public static final ServiceRequestReturnCode getInstance(String queryString)
	{
		Iterator i = returnCodeList.iterator();
		
		while(i.hasNext())
		{
			ServiceRequestReturnCode aReturnCode = (ServiceRequestReturnCode) i.next();
			
			if(aReturnCode.equals(queryString))
			{
				return aReturnCode;
			}
		}

		return ServiceRequestReturnCode.UNKNOWN;
	}
	
	public static final String getString(ServiceRequestReturnCode instance)
	{
		return instance.toString();
	}
		
	public final String toString()
	{
		return returnCode;
	}
	
	public final boolean equals(Object object)
	{
		if(object instanceof Gender)
		{
			return (this == object);
		}
		else if(object instanceof String)
		{
			String queryString = ((String) object).trim();
			
			if(queryString.equalsIgnoreCase(this.returnCode))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public final int hashCode()
	{
		return super.hashCode();
	}
	
	public final int compareTo(Object object)
	{
		if(object instanceof ServiceRequestReturnCode)
		{
			return this.returnCode.compareTo(((ServiceRequestReturnCode) object).returnCode);
		}
		else if(object instanceof String)
		{
			return this.returnCode.compareTo((String) object);
		}
		else
		{
			return 0;
		}
	}
}
