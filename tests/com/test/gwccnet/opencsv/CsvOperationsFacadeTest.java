package com.test.gwccnet.opencsv;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.gwccnet.opencsv.CsvOperationsFacade;
import com.gwccnet.opencsv.CsvWriteOptions;
import com.gwccnet.opencsv.TestBean;

public class CsvOperationsFacadeTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testWriteCsvSuccess1() throws Exception {
		TestBean testBean = new TestBean();
		testBean.setName1(null);
		testBean.setName2("Tassadar");
		testBean.setAddress1("777 Juicy Fruit");
		testBean.setAddress2("PO 24601");
		testBean.setCity("Orlando");
		testBean.setZip(51106);
		
		CsvOperationsFacade csvOperations = new CsvOperationsFacade();
		CsvWriteOptions options = new CsvWriteOptions();
		options.setIncludeHeaderRow(false);
		String fileName = System.getProperty("java.io.tmpdir") + "csv_test.csv";
		csvOperations.writeCsv(testBean, TestBean.class, fileName, options);
	}

}
