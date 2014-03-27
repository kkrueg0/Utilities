package com.gwccnet.web.utilities.XMLHttpRequest;

public class XMLHttpRequestSendDataException extends XMLHttpRequestException
{
	private static final long serialVersionUID = -782800378936170543L;
	
	private static final String exceptionMessage = "Error sending data";
	
	public XMLHttpRequestSendDataException()
	{
		super(exceptionMessage);
	}
	
	public XMLHttpRequestSendDataException(String msg)
	{
		super(msg);
	}
	
	public XMLHttpRequestSendDataException(String msg, Throwable cause)
	{
		super(msg, cause);
	}
	
	public XMLHttpRequestSendDataException(Throwable cause)
	{
		super(exceptionMessage, cause);
	}
}
