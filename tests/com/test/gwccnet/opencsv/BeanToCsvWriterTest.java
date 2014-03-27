package com.test.gwccnet.opencsv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Collections;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import au.com.bytecode.opencsv.CSVWriter;

import com.gwccnet.opencsv.AnnotatedBeanHeaderColumnMappingStrategy;
import com.gwccnet.opencsv.BeanToCsvWriter;
import com.gwccnet.opencsv.TestBean;

public class BeanToCsvWriterTest {
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
	public void testWriteSuccess1() throws Exception {
		BeanToCsvWriter<TestBean> writer = new BeanToCsvWriter<TestBean>();
		
		TestBean testBean = new TestBean();
		testBean.setName1(null);
		testBean.setName2("Tassadar");
		testBean.setAddress1("777 Juicy Fruit");
		testBean.setAddress2("PO 24601");
		testBean.setCity("Orlando");
		testBean.setZip(51106);
		
		AnnotatedBeanHeaderColumnMappingStrategy<TestBean> mappingStrat = new AnnotatedBeanHeaderColumnMappingStrategy<TestBean>(TestBean.class);
		String fileName = System.getProperty("java.io.tmpdir") + "csv_test.csv";
		CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fileName)), ',', CSVWriter.NO_QUOTE_CHARACTER, System.getProperty("line.separator"));
		
		writer.write(csvWriter, mappingStrat, Collections.singletonList(testBean));
	}

}
