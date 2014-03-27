package com.gwccnet.web.utilities.XMLHttpRequest;

public class XMLHttpRequestReceiveDataException extends XMLHttpRequestException
{
	private static final long serialVersionUID = 3600642687364090759L;
	
	private static final String exceptionMessage = "Error receiving data";
	
	public XMLHttpRequestReceiveDataException()
	{
		super(exceptionMessage);
	}
	
	public XMLHttpRequestReceiveDataException(String msg)
	{
		super(msg);
	}
	
	public XMLHttpRequestReceiveDataException(String msg, Throwable cause)
	{
		super(msg, cause);
	}
	
	public XMLHttpRequestReceiveDataException(Throwable cause)
	{
		super(exceptionMessage, cause);
	}
}
