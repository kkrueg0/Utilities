package com.test.gwccnet.utility;

import junit.framework.Assert;

import org.junit.Test;

import com.gwccnet.utility.ToolBox;


public class ToolBoxDoubleToStringConversionTest {
	
	@Test
	public void testDoubleToFormattedString1() {
		Double d = null;
		int decimals = 2;
		
		String result = ToolBox.doubleToFormattedString(d, decimals);
		String expectedResult = "";
		
		Assert.assertEquals(expectedResult, result);
	}
	
	@Test
	public void testDoubleToFormattedString2() {
		Double d = new Double(10.5);
		int decimals = 2;
		
		String result = ToolBox.doubleToFormattedString(d, decimals);
		String expectedResult = "10.50";
		
		Assert.assertEquals(expectedResult, result);
	}
	
	@Test
	public void testDoubleToFormattedString3() {
		Double d = new Double(10);
		int decimals = 2;
		
		String result = ToolBox.doubleToFormattedString(d, decimals);
		String expectedResult = "10.00";
		
		Assert.assertEquals(expectedResult, result);
	}
	
	@Test
	public void testDoubleToFormattedString4() {
		Double d = new Double(1000000000);
		int decimals = 2;
		
		String result = ToolBox.doubleToFormattedString(d, decimals);
		String expectedResult = "1,000,000,000.00";
		
		Assert.assertEquals(expectedResult, result);
	}
	
	@Test
	public void testDoubleToFormattedString5() {
		Double d = new Double(0);
		int decimals = 2;
		
		String result = ToolBox.doubleToFormattedString(d, decimals);
		String expectedResult = "0.00";
		
		Assert.assertEquals(expectedResult, result);
	}
	
	@Test
	public void testDoubleToFormattedString6() {
		Double d = new Double(10);
		int decimals = 0;
		
		String result = ToolBox.doubleToFormattedString(d, decimals);
		String expectedResult = "10";
		
		Assert.assertEquals(expectedResult, result);
	}
}
