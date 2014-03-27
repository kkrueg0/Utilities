package com.gwccnet.web.utilities.XMLHttpRequest;

public class XMLHttpRequestInvalidConnectionStateException extends XMLHttpRequestException
{
	private static final long serialVersionUID = -6717750498901179396L;
	
	private static final String exceptionMessage = "Invalid connection state";
	
	public XMLHttpRequestInvalidConnectionStateException()
	{
		super(exceptionMessage);
	}
	
	public XMLHttpRequestInvalidConnectionStateException(String msg)
	{
		super(msg);
	}
	
	public XMLHttpRequestInvalidConnectionStateException(String msg, Throwable cause)
	{
		super(msg, cause);
	}
	
	public XMLHttpRequestInvalidConnectionStateException(Throwable cause)
	{
		super(exceptionMessage, cause);
	}
}
