package com.gwccnet.web.utilities;

public class BreadCrumb implements Comparable
{
	private String url;
	private String description;
	@Override
	public boolean equals(Object o)
	{
		boolean equals = false;
		if (o instanceof BreadCrumb)
		{
			BreadCrumb obj = (BreadCrumb)o;
			equals = this.getDescription().equals(obj.getDescription());
		}
		else if (o instanceof String)
		{
			equals = this.getDescription().equals((String)o);
		}
		return equals;
	}
	public BreadCrumb(String url, String description)
	{
		super();
		this.url = url;
		this.description = description;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public BreadCrumb()
	{
		// TODO Auto-generated constructor stub
	}
	public int compareTo(Object o)
	{
		int res = 0;
		if (o instanceof BreadCrumb)
		{
			BreadCrumb obj = (BreadCrumb)o;
			res = this.getDescription().compareTo(obj.getDescription());
		}
		else if (o instanceof String)
		{
			res = this.getDescription().compareTo((String)o);
		}
		return res;
	}

}
