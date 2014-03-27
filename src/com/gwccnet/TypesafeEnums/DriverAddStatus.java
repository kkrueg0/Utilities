/*
 * Created on March 1, 2007
 */
package com.gwccnet.TypesafeEnums;
/**************************************************************
 * Change History
 **************************************************************
 * @author elowex0
 **************************************************************
 * Elise Lowe - 5/15/07
 * 65 - Driver Lists to UWE
 * Separate Driver Added - to New or Existing Driver Added
 **************************************************************/
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class DriverAddStatus implements Comparable
{
	private final String status;
	
	/*
	 * Only adding this method for JAXB until these can be converted to
	 * real enums.  It should normally not be used.
	 */
	public DriverAddStatus()
	{	
		this("");
	}
	
	private DriverAddStatus(String status)
	{
		this.status = status;
		statusList.add(this);
	}
	
	private static final List statusList = new ArrayList();
		
	public static final DriverAddStatus ALREADY_TIED = new DriverAddStatus("Already Tied");
	public static final DriverAddStatus LICENSE_ADDED = new DriverAddStatus("Already Tied, License Added");
	public static final DriverAddStatus LICENSE_DRIVER_ADDED = new DriverAddStatus("Driver Added, License Added");
	public static final DriverAddStatus MATCHES = new DriverAddStatus("Multiple Matches");
	public static final DriverAddStatus EXISTING_DRIVER_ADDED = new DriverAddStatus("Existing Driver Added");
	public static final DriverAddStatus NEW_DRIVER_ADDED = new DriverAddStatus("New Driver Added");
	public static final DriverAddStatus DRIVER_ADDED_ADD_LICENSE_FAILED = new DriverAddStatus("Driver Added, Add License Failed");
	public static final DriverAddStatus ADD_DRIVER_FAILED  = new DriverAddStatus("Add Driver Failed");
	public static final DriverAddStatus ADD_LICENSE_FAILED = new DriverAddStatus("Add License Failed");
	public static final DriverAddStatus INFO_CHANGED  = new DriverAddStatus("Info Changed");
	public static final DriverAddStatus ALREADY_TIED_INFO_CHANGED  = new DriverAddStatus("Already Tied, "+INFO_CHANGED);
	public static final DriverAddStatus LICENSE_EXISTS_INFO_CHANGED  = new DriverAddStatus("License Exists, "+INFO_CHANGED);
	public static final DriverAddStatus DRIVER_EXISTS_INFO_CHANGED  = new DriverAddStatus("Driver Exists, "+INFO_CHANGED);
	public static final DriverAddStatus DRIVER_NOT_PROCESSED = new DriverAddStatus("Not Processed Yet");
	public static final DriverAddStatus UNKNOWN = new DriverAddStatus("Unknown");
	
	public static final DriverAddStatus getInstance(String queryString)
	{
		Iterator i = statusList.iterator();
		
		while(i.hasNext())
		{
			DriverAddStatus driverStatus = (DriverAddStatus) i.next();
			
			if(driverStatus.equals(queryString))
			{
				return driverStatus;
			}
		}

		return DriverAddStatus.UNKNOWN;
	}
	
	public final String toString()
	{
		return status;
	}
	
	public final boolean equals(Object object)
	{
		if(object instanceof DriverAddStatus)
		{
			return (this == object);
		}
		else if(object instanceof String)
		{
			String queryString = ((String) object).trim();
			
			if(queryString.equalsIgnoreCase(this.status))
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
		if(object instanceof DriverAddStatus)
		{
			return this.status.compareTo(((DriverAddStatus) object).status);
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
