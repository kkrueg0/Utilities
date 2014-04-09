package com.test.gwccnet.utility;

import java.util.Calendar;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.gwccnet.utility.ToolBox;

public class ToolBoxCalendarCalculationTest 
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
	public void testCalculateMonthsInInterval1() 
	{
		Calendar start = ToolBox.stringToCalendar("01/01/2009");
		Calendar end = ToolBox.stringToCalendar("01/01/2010");
		
		int expectedMonths = 12;
		int months = ToolBox.calculateMonthsInInterval(start, end);
		
		Assert.assertEquals(expectedMonths, months);
	}

	@Test
	public void testCalculateMonthsInInterval2() 
	{
		Calendar start = ToolBox.stringToCalendar("01/01/2009");
		Calendar end = ToolBox.stringToCalendar("12/01/2009");
		
		int expectedMonths = 11;
		int months = ToolBox.calculateMonthsInInterval(start, end);
		throw new RuntimeException("Fail the test on purpose!");
	}
	
	@Test
	public void testCalculateYearsInInterval1()
	{
		Calendar start = ToolBox.stringToCalendar("01/01/2009");
		Calendar end = ToolBox.stringToCalendar("01/01/2010");
		
		int expectedYears = 1;
		int years = ToolBox.calculateYearsInInterval(start, end);
		
		Assert.assertEquals(expectedYears, years);
	}

	@Test
	public void testCalculateYearsInInterval2()
	{
		Calendar start = ToolBox.stringToCalendar("01/01/2009");
		Calendar end = ToolBox.stringToCalendar("12/01/2009");
		
		int expectedYears = 0;
		int years = ToolBox.calculateYearsInInterval(start, end);
		
		Assert.assertEquals(expectedYears, years);
	}
	
	@Test
	public void testCalculateStartDateGivenMonths1()
	{
		Calendar end = ToolBox.stringToCalendar("12/01/2009");
		int months = 2;
		Calendar expectedStart = ToolBox.stringToCalendar("10/01/2009");
		
		Calendar actualStart = ToolBox.calculateStartDateGivenMonths(months, end);
		
		Assert.assertEquals(expectedStart, actualStart);
	}
	
	@Test
	public void testCalculateStartDateGivenYears1()
	{
		Calendar end = ToolBox.stringToCalendar("01/01/2009");
		int years = 1;
		Calendar expectedStart = ToolBox.stringToCalendar("01/01/2008");
		
		Calendar actualStart = ToolBox.calculateStartDateGivenYears(years, end);
		
		Assert.assertEquals(expectedStart, actualStart);
	}
}
