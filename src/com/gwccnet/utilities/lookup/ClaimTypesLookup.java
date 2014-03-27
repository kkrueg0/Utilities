/*
 * Created on Oct 12, 2006
 *
 * Created by laleyn0
 * 
 * Copyright 2006 Great West Casualty Company
 */
package com.gwccnet.utilities.lookup;

import java.util.Hashtable;
import java.util.Map;

/**
 * @author laleyn0
 *
 * This list can be replaced with a call to the persistent layer to read
 * the claim types from the database. 10/12/06 such table does not exist.
 */
public class ClaimTypesLookup extends TypesLookup {

	protected static Map typesMap = null; // each subclass must have it
	protected static ClaimTypesLookup instance = null;
	protected synchronized void load()
	{
		typesMap = new Hashtable(5);
		typesMap.put("M", "Physical Damage / Cargo");
		typesMap.put("L", "Liability"); 
		typesMap.put("W", "Workers Compensation");
		typesMap.put("P", "Property");
		typesMap.put("O", "Occupational Accident");
	}

	/**
	 * @return an instance
	 */
	public static ClaimTypesLookup getInstance() 
	{
		if (instance == null) instance = new ClaimTypesLookup();
		return instance;
	}

	/* (non-Javadoc)
	 * @see com.gwccnet.WebEnhancement.utilities.lookup.TypesLookup#getTypesMap()
	 */
	protected Map getTypesMap() 
	{
		return typesMap;
	}

	/* (non-Javadoc)
	 * @see com.gwccnet.WebEnhancement.utilities.lookup.TypesLookup#setTypesMap(java.util.Map)
	 */
	protected void setTypesMap(Map map) 
	{
		typesMap = map;
	}
}
