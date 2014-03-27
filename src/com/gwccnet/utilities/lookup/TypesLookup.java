/*
 * TypesLookup.java
 * 
 * Created by laleyn0
 * 
 * Created on October 12, 2006
 *
 * Copyright 2006 Great West Casualty Company
 */
package com.gwccnet.utilities.lookup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Abstract Class to get a Dictionary of code/description types.
 * Only one instance to exist per Java Virtual Machine (JVM).
 */
public abstract class TypesLookup {	
	
	/**
	 * Handle one instance of the Map for all
	 */
//	protected static Map typesMap = null; // each subclass must have it
//	private static TypesLookup instance = null; // each subclass must have it
	
	/**
	 * Made protected to keep only one instance.
	 */
	protected TypesLookup() {
	}
	
	/**
	 * Return the shell copy of the Map.
	 * @return Map object - copy
	 */
	public Map getTypes() 
	{
		if (getTypesMap() == null) load();
		Map types = getTypesMap();
		return  types == null? new Hashtable():(Map)((Hashtable)types).clone();
	}

	/**
	 * @param key
	 * @return value object
	 */
	public Object getValue(Object key) 
	{
		if (getTypesMap() == null) load();
		Map types = getTypesMap();
		return types == null? "": types.get(key);
	}

	/**
	 * @param key
	 * @return string representation of the value
	 */
	public String getStringValue(Object key) 
	{
		Object value = getValue(key);
		return value instanceof String? value.toString(): "";
	}

	/**
	 * @return the type codes 
	 */
	public static Iterator getCodes(boolean sort, Map types) 
	{
		if (types == null) return null;
		Iterator iter = null;
		if (sort)
		{
			List arraylist = new ArrayList( types.keySet());
			Collections.sort(arraylist);
			List list = Collections.unmodifiableList(arraylist);

			iter = list.iterator();
		}
		else
		{
			iter = types.keySet().iterator();
		}
		return iter;
	}


	/**
	 * @return the type codes 
	 */
	public Iterator getCodes(boolean sort) 
	{
		if (getTypesMap() == null) load();		
		return getCodes(sort, getTypesMap());
	}

	/**
	 * @return the type codes sorted by value
	 */
	public static Iterator getCodesSortedByValue(Map types) 
	{
		if (types == null) return null;
		ArrayList arraylist = new ArrayList( types.keySet());
		// sort by Comparator keys by values
		Collections.sort(arraylist, new StringValueComparator(types));
		List list = Collections.unmodifiableList(arraylist);
		return list.iterator();
	}

	/**
	 * @return the type codes sorted by value
	 */
	public Iterator getCodesSortedByValue() 
	{
		if (getTypesMap() == null) load();		
		return getCodesSortedByValue(getTypesMap());
	}

	/**
	 * loads the content of the Map from the persistent layer
	 */
	protected abstract void load();

	/**
	 * sets the Map to null to enforce the load on teh next get call
	 */
	public void reload()
	{
		setTypesMap(null);
		load();
	}
		
	/**
	 * @return
	 */
	protected abstract Map getTypesMap();

	/**
	 * @param map
	 */
	protected abstract void setTypesMap(Map map) ;

	/*
	 * 
	 * @author laleyn0
	 *
	 * Class that compares Map.Entry objects by comparing their values
	 */
	protected static class StringValueComparator implements Comparator
	{
		private Map map;
		
		public StringValueComparator(Map map)
		{
			this.map = map;
		}
		
		public int compare(Object o1, Object o2) 
		{
			return ((String)map.get(o1)).compareTo((String)(map.get(o2)));
		} 
	}
}

