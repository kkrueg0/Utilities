/*
 * Created on Dec 27, 2006
 */
package com.gwccnet.TypesafeEnums;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author rlacyx0
 */
public class GlobalNavigationTab implements Comparable
{
	private final String tab;
	
	private GlobalNavigationTab(String tab)
	{
		this.tab = tab;
		
		tabList.add(this);
	}
	
	private static final List tabList = new ArrayList();
	
	public static final GlobalNavigationTab POLICY_NAVIGATION_TAB = new GlobalNavigationTab("POLICY_NAVIGATION_TAB");
	public static final GlobalNavigationTab CLAIMS_NAVIGATION_TAB = new GlobalNavigationTab("CLAIMS_NAVIGATION_TAB");
	public static final GlobalNavigationTab ACCIDENT_NAVIGATION_TAB = new GlobalNavigationTab("ACCIDENT_NAVIGATION_TAB");
	public static final GlobalNavigationTab DRIVER_NAVIGATION_TAB = new GlobalNavigationTab("DRIVER_NAVIGATION_TAB");
	public static final GlobalNavigationTab FUNCTIONS_NAVIGATION_TAB = new GlobalNavigationTab("FUNCTIONS_NAVIGATION_TAB");
	public static final GlobalNavigationTab UNKNOWN = new GlobalNavigationTab("UNKNOWN");
	
	public static final GlobalNavigationTab getInstance(String queryString)
	{
		Iterator i = tabList.iterator();
		
		while(i.hasNext())
		{
			GlobalNavigationTab aTab = (GlobalNavigationTab) i.next();
			
			if(aTab.equals(queryString))
			{
				return aTab;
			}
		}

		return GlobalNavigationTab.UNKNOWN;
	}
	
	public static final String getString(GlobalNavigationTab instance)
	{
		return instance.toString();
	}
	
	public final String toString()
	{
		return tab;
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
			
			if(queryString.equalsIgnoreCase(this.tab))
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
		if(object instanceof GlobalNavigationTab)
		{
			return this.tab.compareTo(((GlobalNavigationTab) object).tab);
		}
		else if(object instanceof String)
		{
			return this.tab.compareTo((String) object);
		}
		else
		{
			return 0;
		}
	}
}