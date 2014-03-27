package com.gwccnet.web.utilities.XMLHttpRequest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;

import com.gwccnet.utilities.CommonUtils;
import com.gwccnet.xmlutil.domtools.DOMTools;

public class XMLHttpRequest
{	
	private HttpURLConnection conn = null;
	private byte[] lastResponse = null;
	
	boolean throwExceptionOnBadStatus = true;
	
	public XMLHttpRequest()
	{	
	}
	
	public Map<String, List<String>> getAllResponseHeaders()
	  throws XMLHttpRequestException
	{
		try
		{
			if(conn == null)
			{
				throw new XMLHttpRequestInvalidConnectionStateException("Cannot get response headers on a null connection");
			}
		
			return conn.getHeaderFields();
		}
		catch(Throwable t)
		{
			handleThrowable(t);
		}
		
		return null;
	}
	
	public String getResponseHeader(String key)
	  throws XMLHttpRequestException
	{
		try
		{
			if(conn == null)
			{
				throw new XMLHttpRequestInvalidConnectionStateException("Cannot get response header on a null connection");
			}
		
			return conn.getHeaderField(key);
		}
		catch(Throwable t)
		{
			handleThrowable(t);
		}
		
		return null;
	}

	public void setRequestHeader(String key, String value)
	  throws XMLHttpRequestException
	{
		try
		{
			if(conn == null)
			{
				throw new XMLHttpRequestInvalidConnectionStateException("Cannot set request header on a null connection");
			}
		
			conn.setRequestProperty(key, value);
		}
		catch(Throwable t)
		{
			handleThrowable(t);
		}
	}
	
	public void open(String requestMethod, String requestURL)
		throws XMLHttpRequestException
	{
		try
		{
			open(requestMethod, requestURL, "", -1, -1);
		}
		catch(Throwable t)
		{
			handleThrowable(t);
		}
	}
	
	public void open(String requestMethod, String requestURL, int timeout)
		throws XMLHttpRequestException
	{
		try
		{
			open(requestMethod, requestURL, "", -1, timeout);
		}
		catch(Throwable t)
		{
			handleThrowable(t);
		}
	}
	
	public void open(String requestMethod, String requestURL, String proxyHostName, int proxyPort)
		throws XMLHttpRequestException
	{
		try
		{
			open(requestMethod, requestURL, proxyHostName, proxyPort, -1);
		}
		catch(Throwable t)
		{
			handleThrowable(t);
		}
	}
	
	public void open(String requestMethod, String requestURL, String proxyHostName, int proxyPort, int timeout) 
		throws XMLHttpRequestException
	{
		try
		{
			if(conn != null)
			{
				throw new XMLHttpRequestInvalidConnectionStateException(this.getClass().getName() + " cannot be reused after open is invoked; create another instance");
			}
			
			conn = null;
						
			if(CommonUtils.isNullOrEmpty(requestMethod))
			{
				throw new XMLHttpRequestOpenConnectionException("No request method specified");
			}
			
			if(!requestMethod.equalsIgnoreCase("POST") && !requestMethod.equalsIgnoreCase("GET"))
			{
				throw new XMLHttpRequestOpenConnectionException("Invalid request type: " + requestMethod);
			}
			
			if(CommonUtils.isNullOrEmpty(requestURL))
			{
				throw new XMLHttpRequestOpenConnectionException("No request URL specified");
			}
			
			Proxy proxy = null;
			
			if(!CommonUtils.isNullOrEmpty(proxyHostName))
			{
				try
				{
					InetSocketAddress inetSocket = new InetSocketAddress(proxyHostName, proxyPort);
					proxy = new Proxy(Proxy.Type.HTTP, inetSocket);
				}
				catch(Throwable t)
				{
					throw new XMLHttpRequestOpenConnectionException("Problem accessing proxy server: " + proxyHostName + ":" + proxyPort, t);
				}
			}
			
			URL url;
			
			try
			{
				url = new URL(requestURL);
			} 
			catch(MalformedURLException e)
			{
				throw new XMLHttpRequestOpenConnectionException("URL " + requestURL + " was malformed", e);
			}

			/* The return from openConnection might actually resolve to
			 * HttpsURLConnection or some other related subclass of HttpURLConnection.
			 */
			
			try
			{
				if(proxy != null)
				{
					conn = (HttpURLConnection) url.openConnection(proxy);
				}
				else
				{
					conn = (HttpURLConnection) url.openConnection();
				}
			}
			catch(IOException e)
			{
				throw new XMLHttpRequestOpenConnectionException("Problem opening connection to " + requestURL, e);
			}
			
			try
			{
				conn.setRequestMethod(requestMethod.toUpperCase());
			}
			catch(ProtocolException e)
			{
				throw new XMLHttpRequestOpenConnectionException("Could not set protocol to " + requestMethod, e);
			}
			
			setAllowCachedResponse(false);
			
			if(timeout >= 0)
			{
				conn.setConnectTimeout(timeout);
				conn.setReadTimeout(timeout);
			}
		}
		catch(Throwable t)
		{
			handleThrowable(t);
		}
	}
	
	/* Note that the acutaly physical connection is established when conn.getOutputStream
	 * or conn.getInputStream is invoked (whichever comes first).
	 */
	
	public void send()
		throws XMLHttpRequestException
	{
		try
		{
			parseResponse();
		}
		catch(Throwable t)
		{
			handleThrowable(t);
		}
	}
	
	public void send(String data)
		throws XMLHttpRequestException
	{
		try
		{
			if(conn == null)
			{
				throw new XMLHttpRequestInvalidConnectionStateException("Cannot send data on a null connection");
			}
		
			conn.setDoOutput(true);
			
			try
			{
				OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
				wr.write(data);
				wr.flush();
				wr.close();
			}
			catch(IOException e)
			{
				throw new XMLHttpRequestSendDataException(e);
			}
			
			parseResponse();
		}
		catch(Throwable t)
		{
			handleThrowable(t);
		}
	}
	
	private void parseResponse()
		throws XMLHttpRequestException
	{
		try
		{
			if(conn == null)
			{
				throw new XMLHttpRequestInvalidConnectionStateException("Cannot receive data on a null connection");
			}
						
			try
			{
				parseInputStream(conn.getInputStream());
			}
			catch(Throwable t)
			{
				/* Certain 'bad' HTTP return codes will throw exceptions;
				 * 404, for example.  We still want to get the data the server
				 * sent back, even if it is only an error page.  The decision
				 * to throw an exception or not is deferred until later.
				 */
				InputStream is = conn.getErrorStream();
				if(is != null)
				{
					parseInputStream(conn.getErrorStream());
				}
				else
				{
					handleThrowable(t);
				}
			}
			
			if(getStatus() != HttpURLConnection.HTTP_OK)
			{
				if(getThrowExceptionOnBadStatus())
				{
					throw new XMLHttpRequestBadStatusException(getStatus());
				}
			}
		}
		catch(Throwable t)
		{			
			handleThrowable(t);
		}
	}
	
	public void send(String nameKey, Document document)
		throws XMLHttpRequestException
	{
		String data = null;
	
		try
		{
			if(CommonUtils.isNullOrEmpty(nameKey))
			{
				throw new XMLHttpRequestSendDataException("Empty key");
			}
			
			if(document == null)
			{
				throw new XMLHttpRequestSendDataException("No document specified");
			}
			
			try
			{
				data = URLEncoder.encode(nameKey, "UTF-8") + "=" + URLEncoder.encode(DOMTools.getXMLString(document, "UTF-8"), "UTF-8");
			}
			catch(IOException e)
			{
				throw new XMLHttpRequestSendDataException("Could not encode data");
			}
			
			send(data);
		}
		catch(Throwable t)
		{
			handleThrowable(t);
		}
	}
	
	public StringBuilder getResponseAsStringBuilder()
		throws XMLHttpRequestException
	{
		try
		{
			if(conn == null)
			{
				throw new XMLHttpRequestInvalidConnectionStateException("Cannot get response on a null connection");
			}
		
			if(lastResponse == null)
			{
				throw new XMLHttpRequestReceiveDataException("The last response is null; no data to return");
			}
			
			return new StringBuilder(getResponseAsString());
		}
		catch(Throwable t)
		{
			handleThrowable(t);
		}
		
		return null;
	}
	
	public byte[] getResponseAsByteArray()
	{
		return lastResponse;
	}
	
	public String getResponseAsString()
		throws XMLHttpRequestException
	{
		try
		{
			if(conn == null)
			{
				throw new XMLHttpRequestInvalidConnectionStateException("Cannot get response on a null connection");
			}
		
			if(lastResponse == null)
			{
				throw new XMLHttpRequestReceiveDataException("The last response is null; no data to return");
			}
			
			return new String(lastResponse);
		}
		catch(Throwable t)
		{
			handleThrowable(t);
		}
		
		return null;
	}
	
	public Document getResponseAsXML() 
		throws XMLHttpRequestException
	{
		try
		{
			if(conn == null)
			{
				throw new XMLHttpRequestInvalidConnectionStateException("Cannot get response on a null connection");
			}
		
			if(lastResponse == null)
			{
				throw new XMLHttpRequestReceiveDataException("The last response is null; no data to return");
			}
			
			Document document = null;
		
			try
			{
				document = DOMTools.getDOMDocument(getResponseAsString());
			}
			catch(Throwable t)
			{
				throw new XMLHttpRequestReceiveDataException("Response does not appear to be an XML document", t);
			}
		
			return document;
		}
		catch(Throwable t)
		{
			handleThrowable(t);
		}
		
		return null;
	}
	
	public int getStatus()
		throws XMLHttpRequestException
	{
		try
		{
			if(conn == null)
			{
				throw new XMLHttpRequestInvalidConnectionStateException("Cannot get status on a null connection");
			}
			
			try
			{
				return conn.getResponseCode();
			}
			catch(IOException e)
			{
				throw new XMLHttpRequestReceiveDataException("Problem getting status", e);
			}
		}
		catch(Throwable t)
		{
			handleThrowable(t);
		}
		
		return 0;
	}
	
	public String getStatusText()
		throws XMLHttpRequestException
	{
		try
		{
			if(conn == null)
			{
				throw new XMLHttpRequestInvalidConnectionStateException("Cannot get status description on a null connection");
			}
			
			try
			{
				return conn.getResponseMessage();
			}
			catch(IOException e)
			{
				throw new XMLHttpRequestReceiveDataException("Problem getting status description", e);
			}
		}
		catch(Throwable t)
		{
			handleThrowable(t);
		}
		
		return null;
	}
	
	public int getContentLength() 
		throws XMLHttpRequestException
	{
		try
		{
			if(conn == null)
			{
				throw new XMLHttpRequestInvalidConnectionStateException("Cannot get content length on a null connection");
			}
			
			return conn.getContentLength();
		}
		catch(Throwable t)
		{
			handleThrowable(t);
		}
		
		return 0;
	}
	
	public String getContentEncoding()
		throws XMLHttpRequestException
	{
		try
		{
			if(conn == null)
			{
				throw new XMLHttpRequestInvalidConnectionStateException("Cannot get content encoding on a null connection");
			}
			
			return conn.getContentEncoding();
		}
		catch(Throwable t)
		{
			handleThrowable(t);
		}
		
		return null;
	}
	
	public String getContentType()
		throws XMLHttpRequestException
	{
		try
		{
			if(conn == null)
			{
				throw new XMLHttpRequestInvalidConnectionStateException("Cannot get content type on a null connection");
			}
			
			return conn.getContentType();
		}
		catch(Throwable t)
		{
			handleThrowable(t);
		}
		
		return null;
	}
	
	public void setAllowCachedResponse(boolean flag)
		throws XMLHttpRequestException
	{
		try
		{
			if(conn == null)
			{
				throw new XMLHttpRequestInvalidConnectionStateException("Cannot modify cache policy on a null connection");
			}
			
			conn.setUseCaches(flag);
		}
		catch(Throwable t)
		{
			handleThrowable(t);
		}
	}
	
	public boolean getAllowCachedResponse()
		throws XMLHttpRequestException
	{
		try
		{
			if(conn == null)
			{
				throw new XMLHttpRequestInvalidConnectionStateException("Cannot query cache policy on a null connection");
			}
			
			return conn.getUseCaches();
		}
		catch(Throwable t)
		{
			handleThrowable(t);
		}
		
		return false;
	}
	
	public boolean getThrowExceptionOnBadStatus()
		throws XMLHttpRequestException
	{
		try
		{
			return throwExceptionOnBadStatus;
		}
		catch(Throwable t)
		{
			handleThrowable(t);
		}
		
		return false;
	}
	
	public void setThrowExceptionOnBadStatus(boolean throwExceptionOnBadStatus)
		throws XMLHttpRequestException
	{
		try
		{
			this.throwExceptionOnBadStatus = throwExceptionOnBadStatus;
		}
		catch(Throwable t)
		{
			handleThrowable(t);
		}
	}
	
	public void close()
		throws XMLHttpRequestException
	{
		try
		{
			if(conn == null)
			{
				throw new XMLHttpRequestInvalidConnectionStateException("Cannot close a null connection");
			}
		
			conn.disconnect();
		}
		catch(Throwable t)
		{
			handleThrowable(t);
		}
	}
	
	
	private void parseInputStream(InputStream is)
		throws XMLHttpRequestException
	{
		try
		{	
			try
			{
				lastResponse = CommonUtils.readInputStreamIntoByteArray(is);
			}
			catch(IOException e)
			{
				throw new XMLHttpRequestReceiveDataException(e);
			}
		}
		catch(Throwable t)
		{
			handleThrowable(t);
		}
	}
	
	private void handleThrowable(Throwable t)
		throws XMLHttpRequestException
	{
		if(t instanceof XMLHttpRequestException)
		{
			throw (XMLHttpRequestException) t;
		}
		else
		{
			throw new XMLHttpRequestException(this.getClass().getName() + ".handleThrowable() - " + t.getMessage(), t.getCause());
		}
	}
}
