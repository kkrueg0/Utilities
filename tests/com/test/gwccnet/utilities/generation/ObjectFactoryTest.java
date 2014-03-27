package com.test.gwccnet.utilities.generation;

import junit.framework.Assert;

import org.junit.Test;

import com.gwccnet.utilities.generation.ObjectFactoryGenerator;


public class ObjectFactoryTest {
	@Test
	public void testGenerateEmptyPath() {
		ObjectFactoryGenerator obf = new ObjectFactoryGenerator();
		obf.setFilePath("C:\\temp\\ObjectFactory.java");
		obf.setPackageToScan("com.gwccnet.test");
		
		try {
			obf.generate();
			
			Assert.fail("Expected exception due to empty path to scan.");
		} catch(Exception e) {
		}
	}
	
	@Test
	public void testGenerateEmptyFilePath() {
		ObjectFactoryGenerator obf = new ObjectFactoryGenerator();
		obf.setPackageToScan("com.gwccnet.test");
		
		try {
			obf.generate();
			
			Assert.fail("Expected exception due to empty path to scan.");
		} catch(Exception e) {
		}
	}
	
	@Test
	public void testGenerateSuccess() throws Exception {
		ObjectFactoryGenerator obf = new ObjectFactoryGenerator();
		obf.setPackageToScan("com.gwccnet.web.utilities");
		obf.setFilePath("C:\\temp\\ObjectFactory.java");
		
		obf.generate();
	}
}
