package com.gwccnet.opencsv;

import com.gwccnet.utilities.propertyEditors.StringPropertyEditor;

public class TestBean
{
	@CsvColumnHeader(isExported=true, columnName = "FirstName", position = -2)
	private String name1;
	@CsvColumnHeader
	String name2;
	@CsvColumnHeader(isExported=true, columnName = "Address1")
	String address1;
	@CsvColumnHeader(isExported=true, columnName = "Address2")
	String address2;
	@CsvColumnHeader(isExported=true, columnName = "City")
	String city;
	@CsvColumnHeader(isExported=true, columnName = "State")
	@PropertyEditor(editorClass=StringPropertyEditor.class)
	String state;
	@CsvColumnHeader(isExported=true, columnName = "ZipCode", formatString="%07d", position = -1)
	int zip;

	public String getName1()
	{
		return name1;
	}

	public void setName1(String name1)
	{
		this.name1 = name1;
	}

	public String getName2()
	{
		return name2;
	}

	public void setName2(String name2)
	{
		this.name2 = name2;
	}

	public String getAddress1()
	{
		return address1;
	}

	public void setAddress1(String address1)
	{
		this.address1 = address1;
	}

	public String getAddress2()
	{
		return address2;
	}

	public void setAddress2(String address2)
	{
		this.address2 = address2;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public int getZip()
	{
		return zip;
	}

	public void setZip(int zip)
	{
		this.zip = zip;
	}

	@Override
	public String toString()
	{
		return name1.trim() + "\n"
		+ name2.trim() + "\n"
		+ address1.trim() + "\n"
		+ address2.trim() + "\n"
		+ city.trim() + ", " + state.trim() + "  " + zip;
	}

}
