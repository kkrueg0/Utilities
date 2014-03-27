/*
 * Created on Nov 30, 2006
 */
package com.gwccnet.TypesafeEnums;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author rlacyx0
 */
public final class ClaimantRepresentativeTypes implements Comparable
{
	private final String name;
	private final String description;
	
	/*
	 * Only adding this method for JAXB until these can be converted to
	 * real enums.  It should normally not be used.
	 */
	public ClaimantRepresentativeTypes()
	{	
		this("", "");
	}
	
	private ClaimantRepresentativeTypes(String name, String description)
	{
		this.name = name;
		this.description = description;
		
		repTypeList.add(this);
	}
	
	private static final List repTypeList = new ArrayList();
		
	public static final ClaimantRepresentativeTypes ATTORNEY = new ClaimantRepresentativeTypes("ATY", "Attorney");
	public static final ClaimantRepresentativeTypes POWER_OF_ATTORNEY = new ClaimantRepresentativeTypes("PWR", "Power Of Attorney");
	public static final ClaimantRepresentativeTypes GAURDIAN = new ClaimantRepresentativeTypes("GRD", "Guardian/Conservator");
	public static final ClaimantRepresentativeTypes OTHER = new ClaimantRepresentativeTypes("OTR","Other");
	
	public static final ClaimantRepresentativeTypes getInstance(String queryString)
	{
		Iterator i = repTypeList.iterator();
		
		while(i.hasNext())
		{
			ClaimantRepresentativeTypes driverStatus = (ClaimantRepresentativeTypes) i.next();
			
			if(driverStatus.equals(queryString))
			{
				return driverStatus;
			}
		}

		return ClaimantRepresentativeTypes.OTHER;
	}

	public static final String getString(ClaimantRepresentativeTypes instance)
	{
		return instance.toString();
	}
	
	public final String toDescription()
	{
		return description;
	}
	
	public final String toName()
	{
		return name;
	}
	
	public final boolean equals(Object object)
	{
		if(object instanceof ClaimantRepresentativeTypes)
		{
			return (this == object);
		}
		else if(object instanceof String)
		{
			String queryString = ((String) object).trim();
			
			if(queryString.equalsIgnoreCase(this.name) || queryString.trim().equalsIgnoreCase(this.description))
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
		if(object instanceof ClaimantRepresentativeTypes)
		{
			return this.name.compareTo(((ClaimantRepresentativeTypes) object).name);
		}
		else if(object instanceof String)
		{
			return this.name.compareTo((String) object);
		}
		else
		{
			return 0;
		}
	}

}
