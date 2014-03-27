/*
 * Created on Oct 12, 2006
 *
 * Created by laleyn0
 * 
 * Copyright 2006 Great West Casualty Company
 */
package com.gwccnet.utilities.lookup;

import java.util.Map;
import java.util.Hashtable;
import java.util.Properties;

import com.gwccnet.utility.ToolBox;

/**
 * @author laleyn0
 *
 * The list of document type codes is loaded from an xml file per user type/claim type
 */
public class ClaimDocumentTypesListLookup extends TypesLookup 
{
	public static final String MIN_EXTERNAL_DOCUMENT_TYPE = "MIN_EXTERNAL_DOCUMENT_TYPE";
	public static final String MIN_INTERNAL_DOCUMENT_TYPE = "MIN_INTERNAL_DOCUMENT_TYPE";
	public static final String MAX_EXTERNAL_DOCUMENT_TYPE = "MAX_EXTERNAL_DOCUMENT_TYPE";
	public static final String MAX_INTERNAL_DOCUMENT_TYPE = "MAX_INTERNAL_DOCUMENT_TYPE";

	private static final String DOCUMENT_TYPE = "DOCUMENT_TYPE_";
	private static final String CLAIM_DOC_UPLOAD_CONFIG = "claimDocumentUpload.properties";

	protected static ClaimDocumentTypesListLookup instance = null;
	protected static Map typesMap = null; // each subclass must have it
	protected static Map allTypes = null;
	
	public Map getTypes(String userTypeCode, String claimTypeCode)
	{
		if (allTypes == null)load();//should be ==
		
		// return if could not load	or no user type	
		if (allTypes == null || userTypeCode == null) return new Hashtable();

		Map userDocTypes = (Map)allTypes.get(userTypeCode);

		// return if user was not listed for any doc types
		if (userDocTypes == null) return new Hashtable();
		
		// for this user - doc types for all claim types requested
		if (claimTypeCode == null || claimTypeCode.trim().equals("")) 
		{
			claimTypeCode = "ALL";
		} 
		Hashtable result = (Hashtable)userDocTypes.get(claimTypeCode);		
		return result == null? new Hashtable():(Map)result.clone();
	}
	
	/**
	 * @return an instance
	 */
	public static ClaimDocumentTypesListLookup getInstance() 
	{
		if (instance == null) instance = new ClaimDocumentTypesListLookup();
		return instance;
	}

	/* (non-Javadoc)
	 * @see com.gwccnet.WebEnhancement.utilities.lookup.TypesLookup#getTypesMap()
	 */
	protected Map getTypesMap() 
	{
		return typesMap;
	}

	/* (non-Javadoc) TODO delete
	 * @see com.gwccnet.WebEnhancement.utilities.lookup.TypesLookup#setTypesMap(java.util.Map)
	 */
	protected void setTypesMap(Map map) 
	{
		typesMap = map;
	}

	protected synchronized void load()
	{
		// document code/description
		typesMap = ClaimDocumentTypesLookup.getInstance().getTypes();
		allTypes = new Hashtable(); // user type / Map userDocTypes

		// claim type / Map userClaimDocTypes
		Map userDocTypes = null; 

		// document code/description for this user and this claim type
		Map userClaimDocTypes = null; 

		Properties config = ToolBox.readPropertiesFile(CLAIM_DOC_UPLOAD_CONFIG);	
		if (config == null) // config file is not found - just return empty map
		{	//throw new GwccStopException("Missing config file " + CLAIM_DOC_UPLOAD_CONFIG);
			return;
		}
		// get the properties for doc type lists
		int minExternal = ToolBox.stringToInteger(config.getProperty(MIN_EXTERNAL_DOCUMENT_TYPE));
		int minInternal = ToolBox.stringToInteger(config.getProperty(MIN_INTERNAL_DOCUMENT_TYPE));
		int maxExternal = ToolBox.stringToInteger(config.getProperty(MAX_EXTERNAL_DOCUMENT_TYPE));
		int maxInternal = ToolBox.stringToInteger(config.getProperty(MAX_INTERNAL_DOCUMENT_TYPE));
		
		// loop for each document type
		for (int iDocCode = Math.min(minExternal, minInternal); 
			iDocCode <= Math.max(maxExternal, maxInternal); iDocCode++)
		{
			// key pattern: DOCUMENT_TYPE_101
			String docCode = (new Integer(iDocCode)).toString();
			String codeUses = config.getProperty(DOCUMENT_TYPE + docCode);
			if (codeUses == null) continue;

			//parse DOCUMENT_TYPE_101=LMOPW,IAT,IAD
			int firstCommaIndex = codeUses.indexOf(',');
			// if no comma - read to the end of line
			if (firstCommaIndex < 0) firstCommaIndex = codeUses.length();

			String sClaimTypes = codeUses.substring(0, firstCommaIndex); // LMOPW

			String sUserTypes = "";
			if (firstCommaIndex < codeUses.length()) 
			{
				sUserTypes = codeUses.substring(firstCommaIndex + 1); // ,IAT,IAD
			}
			String[] userTypes = sUserTypes.split(",");
			
			// add to the maps
			for(int i = -1; i < userTypes.length; i++)
			{//Map for this userType
				
				// add all doc types to EMP user 				
				String userType = (i < 0)? "EMP": userTypes[i];  
				userDocTypes = (Map)allTypes.get(userType); 
				if (userDocTypes == null)
				{// first time - create a Map and add to alltypes
					userDocTypes = new Hashtable();
					allTypes.put(userType, userDocTypes);
				} 

				for(int j = -1; j < sClaimTypes.length(); j++)
				{// Map for this userType/claimType
			
					// add to all-claim-types				
					String claimType = j < 0? "ALL": sClaimTypes.substring(j, j+1);
				
					userClaimDocTypes = (Map)userDocTypes.get(claimType); 
					if (userClaimDocTypes == null)
					{// first time - create a Map and add to this userDocTypes
						userClaimDocTypes = new Hashtable();
						userDocTypes.put(claimType, userClaimDocTypes);
					}
					if(typesMap.get(docCode) != null)
					{
						userClaimDocTypes.put(docCode, typesMap.get(docCode));			
					}
				} // end-of claim type for the user type
			} // end-of user type
		}// end-of doc type
	}	
}
