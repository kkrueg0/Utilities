/*
 * Created on Feb 21, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.gwccnet.utilities.lookup;

import java.util.List;

/**
 * @author rlacyx0
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface CsvToDtoNameMappingFactory
{
	public CsvToDtoNameMapping getInstance(String queryString);
	
	public List getElementNames();
	
	public List getCsvColumnHeadingNames();
	
	public List getDisplayNames();
	
	public List getRequiredElements();
}
