package com.test.gwccnet.utility;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gwccnet.utility.ToolBox;

public class ToolBoxIntegerStringConversionTest 
{
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
	public void testIntegerToString1()
	{
		String expectedResult = "10";
		
		int i = 10;
		
		String result = ToolBox.integerToString(i);
		
		Assert.assertEquals(expectedResult, result);
	}
	
	@Test
	public void testIntegerToString2()
	{
		String expectedResult = "10";
		
		String result = ToolBox.integerToString(10);
		
		Assert.assertEquals(expectedResult, result);
	}
	
	@Test
	public void testIntegerToString3()
	{
		String expectedResult = "10";
		
		Integer i = 10;
		
		String result = ToolBox.integerToString(i);
		
		Assert.assertEquals(expectedResult, result);
	}
	
	@Test
	public void testIntegerToString4()
	{
		String expectedResult = "";
		
		Integer i = null;
		
		String result = ToolBox.integerToString(i);
		
		Assert.assertEquals(expectedResult, result);
	}
	
	@Test
	public void testPadNumberLeft1()
	{
		int i = 10;
		String expected = "0010";
		
		String actual = ToolBox.padNumberLeft(i, 4);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testPadNumberLeft2()
	{
		Integer i = 10;
		String expected = "0010";
		
		String actual = ToolBox.padNumberLeft(i, 4);
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testPadNumberLeft3()
	{
		Integer i = null;
		String expected = "";
		
		String actual = ToolBox.padNumberLeft(i, 4);
		
		Assert.assertEquals(expected, actual);
	}
}
