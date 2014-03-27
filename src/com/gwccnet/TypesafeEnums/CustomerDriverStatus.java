/*
 * Created on Nov 30, 2006
 */
package com.gwccnet.TypesafeEnums;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;

import com.gwccnet.utilites.Exception.DataConversionException;
import com.gwccnet.utilities.CommonUtils;

/**
 * @author rlacyx0
 */
public final class CustomerDriverStatus implements Comparable
{
	@XmlAttribute
	private final String status;
	@XmlAttribute
	private final String persistentTypeCode;
	
	/*
	 * Only adding this method for JAXB until these can be converted to
	 * real enums.  It should normally not be used.
	 */
	public CustomerDriverStatus()
	{	
		this("", "");
	}
	
	private CustomerDriverStatus(String status, String persistentTypeCode)
	{
		this.status = status;
		this.persistentTypeCode = persistentTypeCode;
		
		statusList.add(this);
	}
	
	private static final List statusList = new ArrayList();
		
	public static final CustomerDriverStatus EMPLOYED = new CustomerDriverStatus("EMPLOYED", "EMP");
	public static final CustomerDriverStatus PENDING_HIRE = new CustomerDriverStatus("PENDING", "PND");
	public static final CustomerDriverStatus REJECTED = new CustomerDriverStatus("REJECTED", "RJC");
	public static final CustomerDriverStatus TERMINATED = new CustomerDriverStatus("NO LONGER EMPLOYED", "TRM");
	public static final CustomerDriverStatus UNKNOWN = new CustomerDriverStatus("UNKNOWN", "");
	
	public static final CustomerDriverStatus getActiveInstanceEx(String queryString) throws DataConversionException
	{
		if(CommonUtils.isNullOrEmpty(queryString))
		{
			String msg = "No employment status value received.";
			throw new DataConversionException(msg);
		}
		
		CustomerDriverStatus driverStatus = CustomerDriverStatus.getInstance(queryString);
		
		if(driverStatus != CustomerDriverStatus.EMPLOYED && 
		   driverStatus != CustomerDriverStatus.PENDING_HIRE)
		{
			String msg = "The employment status " + queryString + " is not in a recognized format.  " +
			             "Please enter as EMPLOYED or PENDING.";
			throw new DataConversionException(msg);
		}
		
		return driverStatus;
	}
	
	public static final CustomerDriverStatus getInstance(String queryString)
	{
		Iterator i = statusList.iterator();
		
		while(i.hasNext())
		{
			CustomerDriverStatus driverStatus = (CustomerDriverStatus) i.next();
			
			if(driverStatus.equals(queryString))
			{
				return driverStatus;
			}
		}

		return CustomerDriverStatus.UNKNOWN;
	}
	
	public static final String getString(CustomerDriverStatus instance)
	{
		return instance.toString();
	}
	
	public final String toPersistentTypeCode()
	{
		return persistentTypeCode;
	}
	
	public final String toString()
	{
		return status;
	}
	
	public final boolean equals(Object object)
	{
		if(object instanceof CustomerDriverStatus)
		{
			return (this == object);
		}
		else if(object instanceof String)
		{
			String queryString = ((String) object).trim();
			
			if(queryString.equalsIgnoreCase(this.status) || queryString.trim().equalsIgnoreCase(this.persistentTypeCode))
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
		if(object instanceof CustomerDriverStatus)
		{
			return this.status.compareTo(((CustomerDriverStatus) object).status);
		}
		else if(object instanceof String)
		{
			return this.status.compareTo((String) object);
		}
		else
		{
			return 0;
		}
	}
}
