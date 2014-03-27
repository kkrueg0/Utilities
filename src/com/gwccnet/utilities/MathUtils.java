package com.gwccnet.utilities;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;

public class MathUtils
{
	public static final MathContext MC = new MathContext(34, RoundingMode.HALF_UP);
	public static final BigDecimal BD_ZERO = new BigDecimal("0");
	public static final BigDecimal BD_ONE = new BigDecimal("1");
	public static final BigDecimal BD_100 = new BigDecimal("100");
	public static final BigDecimal BD_1000 = new BigDecimal("1000");
	
	public static BigDecimal roundToDollar(BigDecimal bd)
	{
		return bd.setScale(0, RoundingMode.HALF_UP);
	}
	
	public static BigDecimal truncateToDollar(BigDecimal bd)
	{
		return bd.setScale(0, RoundingMode.DOWN);
	}
	
	public static String bigDecimalToString(BigDecimal bd){
		if(bd==null)
		{
			return "";
		}
		else
		{
			return bd.toPlainString();
		}
	}
	
	public static final BigDecimal max(BigDecimal bd1, BigDecimal bd2)
	{
		return bd1.max(bd2);
	}
	
	public static final BigDecimal min(BigDecimal bd1, BigDecimal bd2)
	{
		return bd1.min(bd2);
	}
		
	/*
	 * Attempts to calculate the natural log of a BigDecimal.
	 * Note that the BigDecimal is converted to a double.  This
	 * might result in a slight loss of accuracy.  Unfortunately, 
	 * BigDeciaml doesn't natively support log operations.
	 */
	public static final BigDecimal ln(BigDecimal bd)
	{
		return new BigDecimal(Math.log(bd.doubleValue()));
	}
	
	public static final BigDecimal round(BigDecimal bd, int decimalPlaces)
	{
		return bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
	}
	
	public static final boolean equals(BigDecimal bd1, BigDecimal bd2)
	{
		return (bd1.compareTo(bd2) == 0);
	}
	
	public static final BigDecimal trunc(BigDecimal bd)
	{
		return new BigDecimal(bd.intValue());
	}
	
	/*
	 * Find the integer median value of a sorted array.
	 * If the array has an odd number of elements, the middle element is returned
	 * If the array has an even number of elements, the average of the two middle elements is returned
	 * If the array is null a NullPointerException is thrown
	 * If the array is empty a ArithmeticException is thrown
	 */
	public static final Integer findIntegerMedianFromArray(ArrayList<Integer> array)
		throws ArithmeticException, NullPointerException 
	{
		if (array == null) 
		{
			throw new NullPointerException("Cannot find median of a null ArrayList");
		}
		
		if (array.size() == 0) 
		{
			throw new ArithmeticException("Cannot find median of an empty ArrayList");
		}
		
		int middleIndex = array.size() / 2;
		
		if (array.size() % 2 == 1)
		{
			return array.get(middleIndex);
		}
		
		return ((array.get(middleIndex) + array.get(middleIndex - 1)) / 2);
	}
}
