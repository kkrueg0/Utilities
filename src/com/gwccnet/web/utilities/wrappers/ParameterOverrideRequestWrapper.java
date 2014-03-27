/*
 * ParameterOverrideRequestWrapper.java
 * 
 * Created by rlacyx0
 * 
 * Created on July 19, 2006
 *
 * Copyright 2006 Great West Casualty Company
 */
 
package com.gwccnet.web.utilities.wrappers;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Provides basic functionality for implementing a request wrapper that is
 * primarily intended as a means to override request parameters.  The default
 * behavior for concrete subclasses will be to use the parameters from the
 * original request object.  If a subclass wants to supply its own parameter
 * mapping, it should implement the buildParamMapping method and call
 * setParamMapping.
 */

public abstract class ParameterOverrideRequestWrapper extends HttpServletRequestWrapper 
{
	private Map<String, String[]> mParamMapping;
	
	/**
	 * Constructor comment.
	 * 
	 * @param req
	 */
	
	public ParameterOverrideRequestWrapper(HttpServletRequest req)
	{
		super(req);
		mParamMapping = new Hashtable<String, String[]>();
		mParamMapping.putAll((Map<String, String[]>) super.getParameterMap());
	}
	
	/**
	 * Subclasses should call this method when the
	 * parameter list needs updating.
	 * 
	 * @throws Throwable
	 */
	protected final void setParamMapping() throws Throwable
	{
		mParamMapping = buildParamMapping();
	}
	
	/**
	 * Subclasses must implement this method in order to 
	 * determine what the list of parameters will be.
	 * 
	 * @return Map
	 * @throws Throwable
	 */
	protected abstract Map<String, String[]> buildParamMapping() throws Throwable;
	
	/**
	 * Find the first value associated with the key.
	 * 
	 * @return String
	 * @throws throwable
	 */
	
	public String getParameter(String name)
	{
		String[] paramValues = (String []) mParamMapping.get(name);
		
		if(paramValues != null && paramValues.length > 0)
		{
			return paramValues[0];
		}
		
		return null;
	}
	
	/**
	 * Find the names of all keys.
	 * 
	 * @return Enumeration
	 */
	
	public Enumeration getParameterNames()
	{		
		return Collections.enumeration(mParamMapping.keySet());
	}
	
	/** Returns a copy of the parameter mapping stored internally.
	 * 
	 *  @return Map
	 */
	
	public Map getParameterMap()
	{
		Map<String, String[]> paramMapCpy = new Hashtable<String, String[]>();
		
		Set keys = mParamMapping.keySet();
		Iterator iter = keys.iterator();
		
		while(iter.hasNext())
		{
			String name = (String) iter.next();
			
			String[] values = (String []) mParamMapping.get(name);
			String[] valuesCpy = new String[values.length];
			System.arraycopy(values, 0, valuesCpy, 0, values.length);
			
			paramMapCpy.put(name, valuesCpy);
		}
		
		return paramMapCpy;
	}
	
	/** Returns a an array of all values associated with the given key.
	 * 
	 *  @return String[]
	 */
	
	public String[] getParameterValues(String name)
	{
		return (String[]) mParamMapping.get(name);
	}
}
