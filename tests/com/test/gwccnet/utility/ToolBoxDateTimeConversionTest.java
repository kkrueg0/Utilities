package com.test.gwccnet.utility;

import java.sql.Timestamp;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.gwccnet.utility.ToolBox;

public class ToolBoxDateTimeConversionTest 
{
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception 
	{
	}

	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception 
	{
	}

	@Test
	public void testSqlTimestampToBullDB2String() 
	{
		String expectedDB2Timestamp = "2009-02-01-10.30.40.123456789";
		
		String timestampValue = "2009-02-01 10:30:40.123456789";
		Timestamp tm = Timestamp.valueOf(timestampValue);
		
		String actualTimestampString = ToolBox.sqlTimestampToFullDB2String(tm);
		
		Assert.assertEquals(expectedDB2Timestamp, actualTimestampString);
	}
	
	@Test
	public void testFullDB2StringToSqlTimestamp()
	{
		String db2String = "2009-02-01-10.30.40.123456789";
		Timestamp expectedTm = Timestamp.valueOf("2009-02-01 10:30:40.123456789");
		
		Timestamp actualTm = ToolBox.fullDB2StringToSqlTimestamp(db2String);
		
		Assert.assertEquals(expectedTm.toString(), actualTm.toString());
	}
}
