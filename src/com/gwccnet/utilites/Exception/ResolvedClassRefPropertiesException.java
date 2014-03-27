/*
 * ResolvedClassRefPropertiesException.java
 * 
 * Created by rlacyx0
 * 
 * Created on July 19, 2006
 *
 * Copyright 2006 Great West Casualty Company
 */
 
package com.gwccnet.utilites.Exception;

import java.io.IOException;

/**
 * Exception to be used in conjunction with the ResolvedClassRefProperties class.
 */

public class ResolvedClassRefPropertiesException extends IOException 
{
	private static final long serialVersionUID = 5318686515891912293L;

	/**
	 * Constructor comment.
	 */
	ResolvedClassRefPropertiesException()
	{
	}
	
	/**
	 * Constructor comment.
	 * 
	 * @param msg - Message providing information about the cause of the exception.
	 */
	
	public ResolvedClassRefPropertiesException(String msg)
	{
		super(msg);
	}
}
