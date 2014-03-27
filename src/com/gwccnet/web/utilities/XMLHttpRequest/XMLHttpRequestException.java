package com.gwccnet.web.utilities.XMLHttpRequest;

public class XMLHttpRequestException extends Exception
{
	private static final long serialVersionUID = -416220850578448881L;

	public XMLHttpRequestException(String msg, Throwable cause)
	{
		super(msg, cause);
	}
	
	public XMLHttpRequestException(String msg)
	{
		super(msg);
	}
		
	public XMLHttpRequestException()
	{
		super();
	}
}
