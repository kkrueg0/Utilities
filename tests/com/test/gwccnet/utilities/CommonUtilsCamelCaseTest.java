package com.test.gwccnet.utilities;

import java.util.Arrays;
import java.util.Collection;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.gwccnet.utilities.CommonUtils;

@RunWith(value = Parameterized.class)
public class CommonUtilsCamelCaseTest 
{
	private String input;
	private String expectedOutput;
	
	public CommonUtilsCamelCaseTest(String input, String expectedOutput) 
	{
		this.input = input;
		this.expectedOutput = expectedOutput;
	}
	
	@SuppressWarnings("unchecked")
	@Parameters
	public static Collection data()
	{
		Collection data = Arrays.asList(new Object[][] {
			{"EFFECTIVE_DATE", "effectiveDate"},
			{"effectiveDate", "effectivedate"},
			{"Effective Date", "effectiveDate"},
			{"Effective _ Date", "effectiveDate"},
			{"Effective          _Date", "effectiveDate"},
			{"effective Date", "effectiveDate"}
		});
		
		return data;
	}
	
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
	public void testCamelCase()
	{
		String actualOutput = CommonUtils.camelCase(this.input);
		
		Assert.assertEquals("Expected " + this.expectedOutput + " but received " + actualOutput, this.expectedOutput, actualOutput);
	}
}
