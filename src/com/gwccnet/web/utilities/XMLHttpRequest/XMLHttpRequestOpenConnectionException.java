package com.gwccnet.web.utilities.XMLHttpRequest;

public class XMLHttpRequestOpenConnectionException extends XMLHttpRequestException
{
	private static final long serialVersionUID = 5249678575839594076L;
	
	private static final String exceptionMessage = "Error opening connection";
	
	public XMLHttpRequestOpenConnectionException()
	{
		super(exceptionMessage);
	}
	
	public XMLHttpRequestOpenConnectionException(String msg)
	{
		super(msg);
	}
	
	public XMLHttpRequestOpenConnectionException(String msg, Throwable cause)
	{
		super(msg, cause);
	}
	
	public XMLHttpRequestOpenConnectionException(Throwable cause)
	{
		super(exceptionMessage, cause);
	}
}
