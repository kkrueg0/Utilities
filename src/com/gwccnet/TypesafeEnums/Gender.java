/*
 * Created on Dec 4, 2006
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
public class Gender implements Comparable
{
	@XmlAttribute
	private final String gender;
	@XmlAttribute
	private final String persistentTypeCode;
	
	/*
	 * Only adding this method for JAXB until these can be converted to
	 * real enums.  It should normally not be used.
	 */
	public Gender()
	{	
		this("", "");
	}
	
	private Gender(String gender, String persistentTypeCode)
	{
		this.gender = gender;
		this.persistentTypeCode = persistentTypeCode;
		
		genderList.add(this);
	}
	
	private static final List genderList = new ArrayList();
	
	public static final Gender MALE = new Gender("MALE", "M");
	public static final Gender FEMALE = new Gender("FEMALE", "F");
	public static final Gender UNKNOWN = new Gender("UNKNOWN", "");
	
	public static final Gender getKnownInstanceEx(String queryString) throws DataConversionException
	{
		if(CommonUtils.isNullOrEmpty(queryString))
		{
			String msg = "No gender value received.";
			throw new DataConversionException(msg);
		}
		
		Gender aGender = Gender.getInstance(queryString);
		
		if(aGender == Gender.UNKNOWN)
		{
			String msg = "The gender " + queryString + " is not valid.  Please enter one " +
			             "of the following: MALE, M, FEMALE, F.";
			throw new DataConversionException(msg);	
		}
		
		return aGender;
	}
	
	public static final Gender getInstance(String queryString)
	{
		Iterator i = genderList.iterator();
		
		while(i.hasNext())
		{
			Gender aGender = (Gender) i.next();
			
			if(aGender.equals(queryString))
			{
				return aGender;
			}
		}

		return Gender.UNKNOWN;
	}
	
	public static final String getString(Gender instance)
	{
		return instance.toString();
	}
	
	public final String toPersistentTypeCode()
	{
		return persistentTypeCode;
	}
	
	public final String toString()
	{
		return gender;
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
			
			if(queryString.equalsIgnoreCase(this.gender) || queryString.trim().equalsIgnoreCase(this.persistentTypeCode))
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
		if(object instanceof Gender)
		{
			return this.gender.compareTo(((Gender) object).gender);
		}
		else if(object instanceof String)
		{
			return this.gender.compareTo((String) object);
		}
		else
		{
			return 0;
		}
	}
}