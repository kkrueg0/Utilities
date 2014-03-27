package com.gwccnet.web.utilities;

public class HTMLUtils 
{
	public static String getTableRowStyle(int rowNum)
	{
		if((rowNum % 2) == 0)
		{
			return "drvr_row_2";
		}
		else
		{
			return "drvr_row_1";
		}
	}
}
