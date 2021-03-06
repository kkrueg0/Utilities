/*
 * Created on Nov 09, 2006
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
 * The document type code/description list is loaded from DB2 table DOC_DESC
 */
public class GwccRegionsLookup extends TypesLookup 
{
	protected static Map typesMap = null; // each subclass must have it
	protected static GwccRegionsLookup instance = null;
	
	public static final String SQL =
		"SELECT STATE_CODE , CLAIM_REGN_CODE  " + 
		"FROM GWC.CMN_STATE_BASE ORDER BY STATE_NAME";
	
	protected synchronized void load()
	{// call to the persistent layer
		try {
			typesMap = TypeLookupAccess.selectTypes(SQL);
		} catch (Throwable e) {
			typesMap = new Hashtable(); 
			e.printStackTrace();
		}
	}
	
	/**
	 * @return
	 */
	public static GwccRegionsLookup getInstance() 
	{
		if (instance == null) instance = new GwccRegionsLookup();
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
