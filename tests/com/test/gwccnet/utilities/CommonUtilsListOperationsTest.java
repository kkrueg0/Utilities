package com.test.gwccnet.utilities;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.gwccnet.utilities.CommonUtils;
import com.gwccnet.utility.ToolBox;

public class CommonUtilsListOperationsTest {
	
	protected List<String> buildList(int num) {
		List<String> list = new ArrayList<String>();
		
		for(int i = 1; i <= num; i++) {
			list.add(ToolBox.integerToString(i));
		}
		
		return list;
	}
	
	@Test
	public void testSubdivideListSuccess1() {
		List<String> list = this.buildList(27);
		int size = 5;
		
		List<List<String>> newList = CommonUtils.subdivideList(list, size);
		
		Assert.assertEquals(6, newList.size());
		Assert.assertEquals(5, newList.get(0).size());
		Assert.assertEquals(2, newList.get(5).size());
	}

	@Test
	public void testSubdivideListSuccess2() {
		List<String> list = this.buildList(27);
		int size = 30;
		
		List<List<String>> newList = CommonUtils.subdivideList(list, size);
		
		Assert.assertEquals(1, newList.size());
		Assert.assertEquals(27, newList.get(0).size());
	}
	
	@Test
	public void testSubdivideListSuccess3() {
		List<String> list = this.buildList(27);
		int size = 1;
		
		List<List<String>> newList = CommonUtils.subdivideList(list, size);
		
		Assert.assertEquals(27, newList.size());
		Assert.assertEquals(1, newList.get(0).size());
	}
	
	@Test
	public void testSubdivideListFailure1() {
		List<String> list = this.buildList(27);
		int size = 0;
		
		try {
			List<List<String>> newList = CommonUtils.subdivideList(list, size);
			
			Assert.assertEquals(27, newList.size());
			Assert.assertEquals(1, newList.get(0).size());
			
			Assert.fail("expected exception");
		} catch(Exception e) {
		}
	}
	
	@Test
	public void testSubdivideListFailure2() {
		List<String> list = this.buildList(27);
		int size = -1;
		
		try {
			List<List<String>> newList = CommonUtils.subdivideList(list, size);
			
			Assert.assertEquals(27, newList.size());
			Assert.assertEquals(1, newList.get(0).size());
			
			Assert.fail("expected exception");
		} catch(Exception e) {
		}
	}
}
