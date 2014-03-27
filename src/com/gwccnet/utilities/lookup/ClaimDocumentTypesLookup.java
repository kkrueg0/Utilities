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
 * The document type code/description list is loaded from DB2 table DOC_DESC
 */
public class ClaimDocumentTypesLookup extends TypesLookup 
{
	protected static Map typesMap = null; // each subclass must have it
	protected static ClaimDocumentTypesLookup instance = null;
	
	public static final String CLAIM_DOCUMENT_TYPES_LOOKUP_SQL =
		"Select TYPE_CODE, DESC_TEXT from GWC.DOC_DESC";
	
	protected synchronized void load()
	{// call to the persistent layer
		try {
			typesMap = TypeLookupAccess.selectTypes(CLAIM_DOCUMENT_TYPES_LOOKUP_SQL);
		} catch (Throwable e) {
			typesMap = new Hashtable(); 
			e.printStackTrace();
		}
	}
	
	/**
	 * @return
	 */
	public static ClaimDocumentTypesLookup getInstance() 
	{
		if (instance == null) instance = new ClaimDocumentTypesLookup();
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
