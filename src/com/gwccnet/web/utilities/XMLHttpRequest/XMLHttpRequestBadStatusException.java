package com.gwccnet.web.utilities.XMLHttpRequest;

public class XMLHttpRequestBadStatusException extends XMLHttpRequestException
{
	private static final long serialVersionUID = -4796433355096758315L;
	
	private static final String exceptionMessage = "Bad HTTP return code: ";
	
	public XMLHttpRequestBadStatusException(int returnCode)
	{
		super(exceptionMessage + returnCode);
	}
	
	public XMLHttpRequestBadStatusException(String msg, Throwable cause)
	{
		super(msg, cause);
	}
	
	public XMLHttpRequestBadStatusException(int returnCode, Throwable cause)
	{
		super(exceptionMessage + returnCode, cause);
	}
}
