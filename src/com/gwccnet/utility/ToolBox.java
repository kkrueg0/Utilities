package com.gwccnet.utility;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;

import org.apache.commons.beanutils.PropertyUtils;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Months;
import org.joda.time.Years;

import com.gwccnet.utilities.CommonUtils;

/**
 * Class of general purpose utilities.
 */
public class ToolBox {

	private static final long MILLISECONDS_IN_DAY = 86400000L;  //1000*3600*24
	private static final long MILLISECONDS_IN_YEAR = 31536000000L;  //1000*3600*24*365
	
	//String Constants
	private static final String INPUT_APOSTROPHE = "'";
	private static final String INPUT_DOUBLE_QUOTE = "\"";
	
	//Escaped Constants
	private static final String ESCAPED_APOSTROPHE = "\\\\'"; 
	private static final String ESCAPED_DOUBLE_QUOTE = "\\\\\"";
	
	private static final String ESCAPE_CHAR = "\\";
	
	public static String toCommaDelimitedString(Object ... args)
	{
		List<String> newArgs = new ArrayList<String>();
		
		if(!CommonUtils.isNullOrEmpty(args))
		{
			for(Object arg : args)
			{
				newArgs.add(arg.toString());
			}
		}
		
		return ToolBox.toCommaDelimitedString(newArgs.toArray(new String[] {}));
	}
	public static String[] beanToStringArray(Object bean,List<String> orderedPropertyList)
	{
		String[] values = new String[orderedPropertyList.size()];
		try
		{			
			Map<String, Object> map = PropertyUtils.describe(bean);
			int i = 0;
			for(String key : orderedPropertyList)
			{
				Object value = map.get(key);
				if (value == null)
				{
					values[i] = "";
				}
				else if(value instanceof List)
				{
					values[i] = key + "(List)";
				}
				else
				{
					values[i] = value.toString();
				}
				++i;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return values;
	}
	public static String toCommaDelimitedString(String ... args)
	{
		return ToolBox.toDelimitedString(",", args);
	}
	
	public static String toDelimitedString(String delimiter, String ... args)
	{
		StringBuilder sb = new StringBuilder();
		
		if(!CommonUtils.isNullOrEmpty(args))
		{
			for(int i = 0; i < args.length; i++)
			{
				sb.append(args[i]);
				
				if((i + 1) != args.length)
				{
					sb.append(delimiter);
				}
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * Converts a string of hex digits into a byte array.
	 * @param hexString
	 * @return bytes[]
	 */
	public static byte[] getByteArrayFromHexString(String hexString)
	{
		byte[] bytes = null; 
		
		if(!CommonUtils.isNullOrEmpty(hexString) && (hexString.length() % 2) == 0)
		{
			bytes = new byte[hexString.length() / 2];
			int byteCounter = 0;
		
			for(int i = 0; i < hexString.length() - 1; i += 2)
			{
				try
				{
					BigInteger bi = new BigInteger(hexString.substring(i, i + 2), 16);
					bytes[byteCounter++] = bi.byteValue();
				}
				catch(Exception e)
				{
				}
			}
		}
		
		return bytes;
	}
	/**
	 * Returns a string of hex digits 
	 * <p>
	 * @param bytes array of bytes to be convered to hex
	 * <p>
	 * @param bytes[]
	 * @return Hex string representing the input byte array.
	 * @roseuid 3C96C54D0195
	 */
	public static String getHexString(byte bytes[]) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < bytes.length; i++) {
			Byte b = new Byte(bytes[i]);
			sb.append(getHexDigit(b.intValue()));
		}

		return sb.toString().toUpperCase();
	}

	/**
	 * Returns hex string representation of a single byte
	 * <p>
	 * @param int integer value to be convered to hex
	 * <p>
	 * @param i
	 * @return Hex string representing the input value.
	 * @roseuid 3C96C54D01A9
	 */
	public static String getHexDigit(int i) {
		StringBuilder sb = new StringBuilder();

		String value = Integer.toHexString(i);
		
		if (value.length() > 2) {
			sb.append(value.substring(value.length() - 2));
		}
		else {
			sb.append(value);
		}

		while (sb.length() < 2)
			sb.insert(0, '0');

		return sb.toString().toUpperCase();
	}

	/**
	 * Helper method for making a string a fixed length.  Truncates
	 * strings that are too long.  Pads strings that are too short with 
	 * trailing blanks.
	 * <p>
	 * @param str    string to be padded
	 * @param length of the padded/truncated output string
	 * <p>
	 * @return padded/truncated string
	 * @roseuid 3C96C54D01BD
	 */
	public static String padString(String str, int length) {
		StringBuilder sb = new StringBuilder(str);
		sb.setLength(length);

		// replace any nulls w/ blanks
		for (int i = 0; i < length; i++) {
			if (sb.charAt(i) == '\0')
				sb.setCharAt(i, ' ');
		}

		return sb.toString();
	}

	public static String padStringRight(String str, int length) {
		return padStringRight(str, length, ' ');

	}

	public static String padStringRight(String str, int length, char c) {
		StringBuilder sb = new StringBuilder();

		if (str.length() > length) {
			sb.append(str.substring(str.length() - length));
		}
		else {
			sb.append(str);
		}

		// add trailing blanks until desired length
		while (sb.length() < length)
			sb.append(c);

		return sb.toString();

	}

	/**
	 * Helper method for making a string a fixed length.  Truncates
	 * strings that are too long taking the right most characters.  
	 * Pads strings that are too short with leading blanks.
	 * <p>
	 * @param str    string to be padded
	 * @param length of the padded/truncated output string
	 * <p>
	 * @return padded/truncated string
	 * @roseuid 3C96C54D01D1
	 */
	public static String padStringLeft(String str, int length) {
		return padStringLeft(str, length, ' ');

	}

	/**
	 * Helper method for making a number a fixed string length.  Truncates
	 * numbers that are too long taking the right most characters.  
	 * Pads numbers that are too short with leading zeros.
	 * <p>
	 * @param num    number to be padded
	 * @param length of the padded/truncated output string
	 * <p>
	 * @return padded/truncated string
	 * @roseuid 3C96C54D01EF
	 */
	public static String padNumberLeft(int num, int length) {
		Integer Num = new Integer(num);
		return padStringLeft(Num.toString(), length, '0');
	}

	/**
	 * Same as the version that takes a primative int except that
	 * it does null checking on an Integer object.
	 * 
	 * @param num
	 * @param length
	 * @return
	 */
	public static String padNumberLeft(Integer num, int length) {
		if(num == null) {
			return "";
		} else {
			return ToolBox.padNumberLeft(num.intValue(), length);
		}
	}
	
	/**
	 * Helper method for making a string a fixed length.  Truncates
	 * strings that are too long taking the right most characters.  
	 * Pads strings that are too short with leading specified character.
	 * <p>
	 * @param num    number to be padded
	 * @param length of the padded/truncated output string
	 * @param c      padding character
	 * <p>
	 * @param str
	 * @return padded/truncated string
	 * @roseuid 3C96C54D0203
	 */
	public static String padStringLeft(String str, int length, char c) {
		StringBuilder sb = new StringBuilder();

		if (str.length() > length) {
			sb.append(str.substring(str.length() - length));
		}
		else {
			sb.append(str);
		}

		// add leading blanks until desired length
		while (sb.length() < length)
			sb.insert(0, c);

		return sb.toString();

	}

	/**
	 * Remove leading whitespace from the input string.  Whitespace
	 * is any character less than or equal to '\u0020'.
	 * <p>
	 * @param str Trim string source.
	 * <p>
	 * @return String with leading whitespace removed.  null if input string
	 * is null.
	 * @roseuid 3C96C54D0221
	 */
	/*
	public static String trimLeadingWhitespace(String str) {
		String retValue = str;

		if (str != null) {
			int len = str.length();

			byte[] chars = str.getBytes();

			int nonWhiteIndex = -1;

			// Find the index of the first non-white character
			for (int c = 0; c < len; c++) {
				if (chars[c] > '\u0020') {
					nonWhiteIndex = c;
					break;
				}
			}

			if (nonWhiteIndex < 0) {
				retValue = "";
			}
			else if (nonWhiteIndex > 0) {
				retValue = str.substring(nonWhiteIndex, len);
			}
		}

		return retValue;

	}
	*/

	/**
	 * Remove trailing whitespace from the input string.  Whitespace
	 * is any character less than or equal to '\u0020'.
	 * <p>
	 * @param str Trim string source.
	 * <p>
	 * @return String with trailing whitespace removed.  null if input string
	 * is null.
	 * @roseuid 3C96C54D0236
	 */
	/*
	public static String trimTrailingWhitespace(String str) {
		String retValue = str;

		if (str != null) {
			int len = str.length();

			byte[] chars = str.getBytes();

			int nonWhiteIndex = -1;

			// Find the index of the last non-white character
			for (int c = len - 1; c > -1; c--) {
				if (chars[c] > '\u0020') {
					nonWhiteIndex = c;
					break;
				}
			}

			if (nonWhiteIndex < 0) {
				retValue = "";
			}
			else if (nonWhiteIndex < len - 1) {
				retValue = str.substring(0, nonWhiteIndex + 1);
			}
		}

		return retValue;

	}
	*/

	/**
	 * Remove all whitespace from the input string.  Whitespace
	 * is any character less than or equal to '\u0020'.
	 * <p>
	 * @param str Compress string source.
	 * <p>
	 * @return String with all whitespace removed.  null if input string
	 * is null.
	 * @roseuid 3C96C54D0253
	 */
	/*
	public static String removeWhitespace(String str) {
		String retValue = str;

		if (str != null) {
			int len = str.length();

			byte[] chars = str.getBytes();

			int idx = 0;

			// Squeeze the whitespace characters out of the array
			for (int c = 0; c < len; c++) {
				if (chars[c] > '\u0020') {
					chars[idx++] = chars[c];
				}
			}

			// Note idx is the length of the squashed string

			if (idx > 0) {
				retValue = new String(chars, 0, idx);
			}
			else {
				retValue = "";
			}
		}

		return retValue;

	}
	*/

	/**
	 * Remove the specified character from the input string.
	 * <p>
	 * @param str Compress string source.
	 * <p>
	 * @return String with specified character removed.  null if input string
	 * is null.
	 */
	public static String removeCharacter(String str, char character) {
		String retValue = str;

		if (str != null) {
			int len = str.length();

			byte[] chars = str.getBytes();

			int idx = 0;

			// Squeeze the specified character out of the array
			for (int c = 0; c < len; c++) {
				if (chars[c] != character) {
					chars[idx++] = chars[c];
				}
			}

			// Note idx is the length of the squashed string

			if (idx > 0) {
				retValue = new String(chars, 0, idx);
			}
			else {
				retValue = "";
			}
		}

		return retValue;

	}
	
	public static Integer stringSsnToIntegerSsn(String ssn)
	{
		if(!CommonUtils.validateSSN(ssn))
		{
			return null;
		}
		
		return new Integer(CommonUtils.parseSSN(ssn));
	}
	
	public static String integerSsnToStringSsn(Integer ssn)
	{
		return CommonUtils.formatSSN(ssn);
	}
	
	public static String integerTaxIDToStringBusTaxID(Integer taxID)
	{
		return CommonUtils.formatBusTaxID(taxID);
	}
	
	public static String longToString(long rhs)
	{
		return new Long(rhs).toString();
	}
	
	public static long stringToLong(String s) 
	{
		long value = 0;

		try 
		{
			Long longNum = new Long(getNumbersOnly(s));
			value = longNum.longValue();
		}
		catch (Exception e) 
		{
		}

		return value;
	}
	
	public static short stringToShort(String s)
	{
		short value = 0;
		
		try
		{
			Short shortNum = new Short(getNumbersOnly(s));
			value = shortNum.shortValue();
		}
		catch (Exception e)
		{
		}
		
		return value;
	}
	
	public static String integerToString(Integer i) {
		if(i == null) {
			return "";
		} else {
			return ToolBox.integerToString(i.intValue());
		}
	}
	
	public static String integerToString(int i) {
		Integer integer = new Integer(i);
		String string = integer.toString();

		return string;
	}

	public static int stringToInteger(String s) {
		int value = 0;

		try {
			Integer integer = new Integer(getNumbersOnly(s));
			value = integer.intValue();
		}
		catch (Exception e) {
		}

		return value;
	}

	public static int doubleToInteger(double d)
	{
		return ToolBox.stringToInteger(ToolBox.doubleToString(d, 0));
	}
	
	public static String doubleToFormattedString(Double d, int decimals) {
		if(d == null) {
			return "";
		}
		
		return ToolBox.doubleToFormattedString(d.doubleValue(), decimals);
	}
	
	public static String doubleToFormattedString(double d, int decimals) {
		String formatString = "%,." + ToolBox.integerToString(decimals) + "f";
		
		return String.format(formatString, d);
	}
	
	public static String doubleToString(double d, int decimals) {

		String baseFormat = "#######################0";
		if (decimals > 0) {
			baseFormat += ".";

			for (int i = 0; i < decimals; i++)
				baseFormat += "0";
		}

		DecimalFormat df = new DecimalFormat(baseFormat);
		String string = df.format(d);
		return string;
	}
	
	public static double stringToDouble(String s) {
		double value = 0.0;

		try {
			value = (new Double(removeCharacter(s,','))).doubleValue();
		}
		catch (Exception e) {
		}

		return value;
	}

	public static boolean charToBoolean(char c)
	{
		return ToolBox.stringToBoolean(Character.toString(c));
	}
	
	public static boolean stringToBoolean(String s) {
		boolean status = false;

		if (s == null)
			return status;

		//String cleanString = (ToolBox.removeWhitespace(s)).toUpperCase();
		String cleanString = s.toUpperCase();

		if (cleanString != null && cleanString.length() > 0) {
			status = (    cleanString.startsWith("Y")
					   || cleanString.startsWith("T")
					   || cleanString.startsWith("X")
					   || cleanString.equals("ON")
					   || cleanString.equals("PASS"));
		}

		return status;
	}
	
	public static int booleanToInteger(boolean flag)
	{
		return (flag) ? 1 : 0;
	}
	
	public static boolean integerToBoolean(int integer)
	{
		if(integer == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public static boolean byteToBoolean(byte aByte)
	{
		if(aByte == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public static String byteToString(byte aByte)
	{
		return new String(new byte[] {aByte} );
	}
	
	public static byte booleanToByte(boolean aBoolean)
	{
		if(aBoolean)
		{
			return (byte) 1;
		}
		else
		{
			return (byte) 0;
		}
	}
	
	public static boolean areDatesOneYearApart(Calendar start, Calendar end)
	{
		Calendar testStart = Calendar.getInstance();
		testStart.setTimeInMillis(start.getTimeInMillis());
		testStart.add(Calendar.YEAR, 1);
		
		if(ToolBox.calendarToString(testStart).equals(ToolBox.calendarToString(end)))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static Calendar calculateStartDateGivenMonths(int months, Calendar endDateCal)
	{
		DateTime endDate = new DateTime(endDateCal.getTimeInMillis());
		DateTime startDate = endDate.minusMonths(months);
		
		return startDate.toCalendar(Locale.getDefault());
	}
	
	public static Calendar calculateStartDateGivenMonths(int months)
	{
		DateTime endDate = new DateTime();
		DateTime startDate = endDate.minusMonths(months);
		
		return startDate.toCalendar(Locale.getDefault());
	}
	
	public static Calendar calculateStartDateGivenYears(int years, Calendar endDateCal)
	{
		DateTime endDate = new DateTime(endDateCal.getTimeInMillis());
		DateTime startDate = endDate.minusYears(years);
		
		return startDate.toCalendar(Locale.getDefault());
	}
	
	public static Calendar calculateStartDateGivenYears(int years)
	{
		DateTime endDate = new DateTime();
		DateTime startDate = endDate.minusYears(years);
		
		return startDate.toCalendar(Locale.getDefault());
	}
	
	public static int calculateMonthsInInterval(Calendar start, Calendar end)
	{
		Interval interval = new Interval(start.getTimeInMillis(), end.getTimeInMillis());
		
		return Months.monthsIn(interval).getMonths();
	}
	
	public static int calculateYearsInInterval(Calendar start, Calendar end)
	{
		Interval interval = new Interval(start.getTimeInMillis(), end.getTimeInMillis());
		
		return Years.yearsIn(interval).getYears();
	}
	
	public static int calculateAgeInMonths(Calendar start)
	{
		if(start.getTimeInMillis() >= Calendar.getInstance().getTimeInMillis())
		{
			return 0;
		}
		
		return ToolBox.calculateMonthsInInterval(start, Calendar.getInstance());
	}
	
	public static int calculateAgeInYears(Calendar start)
	{
		if(start.getTimeInMillis() >= Calendar.getInstance().getTimeInMillis())
		{
			return 0;
		}
		
		return ToolBox.calculateYearsInInterval(start, Calendar.getInstance());
	}
	
	public static Calendar dateToCalendar(Date date)
	{
		try
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(date.getTime());
			return calendar;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	@Deprecated
	public static double dateDiffInDays(Calendar beginDate, Calendar endDate)
	{
		long dateRange = endDate.getTimeInMillis() - beginDate.getTimeInMillis(); 
		
		return ((double)dateRange) / ToolBox.MILLISECONDS_IN_DAY;
	}
	
	//Does not take into account leap years!!!! Assumes 365 days in a year.
	//Use something else if more accuracy is required.
	@Deprecated
	public static double dateDiffInYears(Calendar beginDate, Calendar endDate)
	{
		long dateRange = endDate.getTimeInMillis() - beginDate.getTimeInMillis();
	
		return ((double)dateRange) / ToolBox.MILLISECONDS_IN_YEAR;
	}
	
	@Deprecated
	public static Calendar addYearsToDate(Calendar beginDate, double years)
	{
		int wholeYears = (int)years;
		beginDate.add(Calendar.YEAR, wholeYears);
		
		double partialYear = years - wholeYears;
		int months = (int)Math.floor(partialYear*12 + .5);
		beginDate.add(Calendar.MONTH, months);
		
		return beginDate;
	}
	
	public static String stringToUppercaseString(String s)
	{
		if(CommonUtils.isNullOrEmpty(s))
		{
			return s;	
		}
		
		return s.trim().toUpperCase();
	}
	
	public static String sqlTimestampToString(java.sql.Timestamp timestamp)
	{
		try
		{
			return ToolBox.calendarToString(ToolBox.sqlTimestampToCalendar(timestamp));
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public static String sqlTimestampToString(java.sql.Timestamp timestamp, String format)
	{
		String s = timestamp.toString();
		s = s.replaceAll(":", ".");
		s = s.replaceAll(" ", "-");
		return s;
	}
	
	public static String sqlDateToString(java.sql.Date date)
	{
		try
		{
			return ToolBox.calendarToString(ToolBox.sqlDateToCalendar(date));
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public static String sqlTimeToString(java.sql.Time time, String format)
	{
		try
		{
			return ToolBox.calendarToString(ToolBox.sqlDateToCalendar(time), format);
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public static Calendar sqlTimestampToCalendar(java.sql.Timestamp timestamp)
	{
		try
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(timestamp.getTime());
			return calendar;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public static Calendar sqlDateToCalendar(java.sql.Date date)
	{
		try
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(date.getTime());
			return calendar;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public static Calendar sqlDateToCalendar(java.sql.Time time)
	{
		try
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(time.getTime());
			return calendar;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public static Calendar stringToCalendar(String s, String format){
		Calendar calendar = null;

		try {
			Date date = new SimpleDateFormat(format).parse(s);
			calendar = Calendar.getInstance();
			calendar.setTime(date);
		}
		catch (Exception e) {
		}

		return calendar;
	}
	
	
	public static Calendar stringToCalendar(String s) {

		try {
			return stringToCalendar(s, "MM/dd/yyyy");
		}
		catch (Exception e) {
		}

		return null;
	}
	
	public static String stringTimestampToCalendarString(String s)
	{
		if(s == null)
		{
			return null;	
		}
		
		Calendar calendar = ToolBox.stringTimestampToCalendar(s);
		
		if(calendar == null)
		{
			return null;
		}
		
		return ToolBox.calendarToString(calendar);
	}
	
	public static Calendar stringTimestampToCalendar(String s)
	{
		if(s == null)
		{
			return null;
		}
		
		java.sql.Date date = ToolBox.stringTimestampToSqlDate(s);
		java.sql.Time time = ToolBox.stringTimestampToSqlTime(s);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime() + time.getTime());
		
		return calendar;
	}
	
	//parameter s is in timestamp format yyyy-MM-dd-HH.mm.ss.SSSSSS
	public static java.sql.Date stringTimestampToSqlDate(String s){
		if(s==null){
			return null;
		}
		java.sql.Date date = java.sql.Date.valueOf(s.substring(0, 10));
		return date;
	}
	
	//parameter s is in timestamp format yyyy-MM-dd-HH.mm.ss.SSSSSS
	public static Time stringTimestampToSqlTime(String s){
		if(s==null){
			return null;
		}
		s = s.substring(11,19).replaceAll("\\.", ":");
		java.sql.Time time = java.sql.Time.valueOf(s);
		return time;
	}

	public static String calendarToString(Calendar date) {
		String dateString = "";

		try {
			// Gets from Calendar to String through SimpleDateFormat
			if (date != null)
				dateString = (new SimpleDateFormat("MM/dd/yyyy").format(date.getTime())).toString();
		}
		catch (Exception e) {
		}

		return dateString;
	}

	public static String calendarToString(Calendar date, String datePattern) 
	{
		String dateString = "";

		try 
		{
			// Gets from Calendar to String through SimpleDateFormat
			if(date != null)
			{
				dateString = (new SimpleDateFormat(datePattern).format(date.getTime())).toString();
			}
		}
		catch(Exception e) 
		{
		}

		return dateString;
	}
	
	public static String calendarToShortString(Calendar date) {
		String dateString = "";

		try {
			// Gets from Calendar to String through SimpleDateFormat
			if (date != null)
				dateString = (new SimpleDateFormat("MM/dd/yy").format(date.getTime())).toString();
		}
		catch (Exception e) {
		}

		return dateString;
	}

	public static String calendarToMediumString(Calendar date)
	{
		String dateString = "";

		try 
		{
			// Gets from Calendar to String through SimpleDateFormat
			if(date != null)
			{
				dateString = (new SimpleDateFormat("MMMMM d, yyyy").format(date.getTime())).toString();
			}
		}
		catch(Exception e) 
		{
		}
		
		return dateString;
	}
	public static String calendarToMonthAndYearString(Calendar date)
	{
		String dateString = "";

		try 
		{
			// Gets from Calendar to String through SimpleDateFormat
			if(date != null)
			{
				dateString = (new SimpleDateFormat("MMMMM yyyy").format(date.getTime())).toString();
			}
		}
		catch(Exception e) 
		{
		}
		
		return dateString;
	}

	public static Timestamp fullDB2StringToSqlTimestamp(String db2String)
	{
		Timestamp tm = null;
		
		try
		{
			String[] pieces1 = db2String.split("-");
			String[] pieces2 = pieces1[3].split("\\.");
			
			String trialString = 
				pieces1[0] + "-" + pieces1[1] + "-" + pieces1[2] + " " +
				pieces2[0] + ":" + pieces2[1] + ":" + pieces2[2] + ".";
			
			if(pieces2.length > 3)
			{
				trialString += pieces2[3];
			}
			else
			{
				trialString += "000000";
			}
			
			tm = Timestamp.valueOf(trialString);
		}
		catch(Exception e)
		{
		}
		
		return tm;
	}
	
	public static String sqlTimestampToFullDB2String(Timestamp timestamp)
	{
		String fmtString = "";
		
		try
		{
			if(timestamp != null)
			{
				//Timestamp format is yyyy-mm-dd hh:mm:ss.fffffffff
				String rawString = timestamp.toString();
				
				fmtString = 
					rawString.substring(0, 4) + "-" + rawString.substring(5, 7) + "-" + rawString.substring(8, 10) + "-" +
					rawString.substring(11, 13) + "." + rawString.substring(14, 16) + "." + rawString.substring(17, 19) + "." + rawString.substring(20);					
			}
		}
		catch(Exception e)
		{
		}
		
		return fmtString;
	}
	
	public static String calendarToDB2String(Calendar date)
	{
		String dateString = "";
		
		try 
		{
			// Gets from Calendar to String through SimpleDateFormat
			if(date != null)
			{
				dateString = (new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss").format(date.getTime())).toString();
			}
		}
		catch(Exception e) 
		{
		}
		
		return dateString;	
	}
	
	public static String sqlTimestampToDB2String(Timestamp timestamp)
	{
		return ToolBox.calendarToDB2String(ToolBox.sqlTimestampToCalendar(timestamp));
	}
	
	public static Date CalendarDate(Calendar calendar)
	{
		if(calendar == null)
		{
			return null;
		}
		else
		{
			return new Date(calendar.getTimeInMillis());
		}
	}
	
	public static java.sql.Date CalendarToSqlDate(Calendar calendar)
	{
		if(calendar == null)
		{
			return null;
		}
		else
		{
			return new java.sql.Date(calendar.getTimeInMillis());
		}
	}
	
	public static java.sql.Timestamp calendarToSqlTimestamp(Calendar calendar)
	{
		if(calendar == null){
			return null;
		}else{
			return new java.sql.Timestamp(calendar.getTimeInMillis());
		}
	}
	
	public static String calendarDateAndSqlTimeToString(Calendar date, java.sql.Time time){
		String s;
		if(date!=null){
			s = ToolBox.calendarToString(date, "yyyy-MM-dd");
			if(time!=null){
				s += "-" + ToolBox.sqlTimeToString(time, "HH.mm.ss.SSSSSS");
			}else{
				s += "-00.00.00.000000"; 
			}
		}else if (time!=null){
			s = "0000-00-00-"+ToolBox.sqlTimeToString(time, "HH.mm.ss.SSSSSS");
		}else{
			s = "0000-00-00-00.00.00.000000" ;
		}
		return s;
	}
	
	static public String calendarToJulianString(Calendar date)
	{
		String julianDate = "";
		try {
			if(date!=null){
				julianDate = 
					ToolBox.padNumberLeft(date.get(Calendar.YEAR), 4) + 
					ToolBox.padNumberLeft(date.get(Calendar.DAY_OF_YEAR), 3);
				
			}
		}
		catch(Exception e){			
		}
		return julianDate;
	}

	static public double  calendarToJulian(Calendar date) {
		double julianDate = 0;
		try {

			if (date != null) {
				julianDate = 
					dayMonthYearToJulian(
						date.get(Calendar.DAY_OF_MONTH), 
						date.get(Calendar.MONTH)+1, 
						date.get(Calendar.YEAR));
			}
		}
		catch (Exception e) {
		}

		return julianDate;

	}

	static public double dayMonthYearToJulian(int di, int mi, int yi) {

		double d = (new Double(di)).doubleValue();
		double m = (new Double(mi)).doubleValue();
		double y = (new Double(yi)).doubleValue();

		double julianDate = 367.0*y;

		julianDate -= Math.floor(7.0*(y+Math.floor((m+9.0)/12.0))/4.0);
		julianDate -= Math.floor(3.0*(Math.floor((y+(m-9.0)/7.0)/100.0)+1.0)/4.0);
		julianDate += Math.floor(275.0*m/9.0);
		julianDate += d;
		julianDate += 1721028.5;
		julianDate += 12.0/24.0; // time is noon on the date

		return julianDate;

	}

/**
 * Returns Yes if boolean is true and No if false
 */
public static String booleanToString(boolean flag)
{
	if (flag)
	{return "Yes";}
	else
	{return "No";} 
}

public static String booleanToShortString(boolean flag)
{
	if(flag)
	{
		return "Y";
	}
	else
	{
		return "N";
	}
}

public static char booleanToChar(boolean flag)
{
	return ToolBox.booleanToShortString(flag).charAt(0);
}

/**
 * Returns numeric element only of a SSN
 */
public static String getNumbersOnly(String number)
{
	// check if null or empty, return empty string
	if (number == null || number.equals(""))
	{
		return "";
	}
	
	// number will be "full" so return numbers only, no dashes or other separators
	StringBuilder sb = new StringBuilder();
	// loop as long as there are characters
	for (int i=0; i < number.length(); i++)
	{
		//get character at position
		char c = number.charAt(i);
		// check if digit, if digit add to sb
		if ( Character.isDigit(c))
		{
		  sb.append(c);
		}
	}
	// pad with leading zeros
	return sb.toString();
}


/**
 * Return string with no single apostrophe.
 */

	public static String replaceSingleApostrophe(String inString,int apostropheIndex)
	{
		char apostropheChar = '\'';
		String apostropheTwo = "\'\'";
		String resultString = "";
		int[] apostrophePositions = new int[25];
		
		int startIndex = 0, apostropheCount = 0; 

		// find all apostrophes
		while(apostropheIndex >= 0)
		{
			//record the position of the appostrophe.
			apostrophePositions[apostropheCount++] = apostropheIndex;
						
			// The apostrophe is not the last character in the string
			if(apostropheIndex < inString.length())
			{
				// This is not the first apostrophe.
				if(apostropheCount > 1)
				{
					// Check for consecutive apostrophes.
					if((apostrophePositions[apostropheCount - 2] + 1) == (apostrophePositions[apostropheCount - 1]))
					{
						// do nothing for consecutive apostrophe
					}
					else
					{
						resultString += inString.substring(startIndex,apostropheIndex) + apostropheTwo;
					}
					startIndex = apostropheIndex + 1;		
				}			
				
			}
			else
			{
				// Apostrophe is the last character.
				break;
			}
			//find the next apostrophe.
			apostropheIndex = inString.indexOf(apostropheChar,startIndex);
		}
		resultString += inString.substring(startIndex);
		return(resultString);
	}

	public static List<Integer> IntegerListFromDelimitedString(String delimitedString, String regex)
	{
		List<Integer> integerList = new Vector<Integer>();
		String[] valueList = delimitedString.split(regex);
		
		for(int i = 0; i < valueList.length; ++i)
		{
			try
			{
				integerList.add(new Integer(Integer.parseInt(valueList[i])));
			}
			catch(Exception e)
			{
				//Not using ToolBox API because it defaults to 0 if an
				//exception occurs parsing the integer.  Don't want to
				//add it to the list if not numeric.
			}
		}
		
		return integerList;
	}
	
	public static String getSqlInClauseFromIntegerList(List integerList)
	{
		int[] intArray = ToolBox.IntegerListToIntArray(integerList);
		return ToolBox.getSqlInClauseFromIntArray(intArray);
	}
	
	public static String getSqlInClauseFromIntArray(int[] values)
	{
		StringBuilder inClause = new StringBuilder();
		
		for(int i = 0; i < values.length; i++) 
		{
			inClause.append('?');
			if(i < values.length - 1)
			{
				inClause.append(',');
			}
		}
		
		return inClause.toString();
	}
	
	public static Map<String, String> searchResultEnumToAttributeMap(NamingEnumeration<SearchResult> nameEnum) 
	{
		Map<String, String> attrMap = new Hashtable<String, String>();
		
		try 
		{
			if(nameEnum != null && nameEnum.hasMore()) // process the first element only
			{
				SearchResult sr = nameEnum.next();
				Attributes attrs = sr.getAttributes();
				
				for(NamingEnumeration ne = attrs.getAll(); ne.hasMoreElements();)
				{
					//looking for the searched-for attribute
					Attribute attr = (Attribute) ne.next();
					attrMap.put(attr.getID(), attr.get().toString());
				}
			}
		} 
		catch(NamingException e) 
		{
			// do nothing - as if the attribute not found
		}
		
		return attrMap;
	}
	
	public static List<Integer> stringListToIntegerList(List<String> stringList)
	{
		List<Integer> integerList = new ArrayList<Integer>();
		
		for(String stringElement : stringList)
		{
			integerList.add(ToolBox.stringToInteger(stringElement));
		}
		
		return integerList;
	}
	
	public static String[] stringListToStringArray(List stringList)
	{
		String[] stringArray = new String[stringList.size()];
		
		for(int i = 0; i < stringList.size(); i++)
		{
			if(stringList.get(i) instanceof String)
			{
				stringArray[i] = (String) stringList.get(i);
			}
			else
			{
				stringArray[i] = "";
			}
		}
		
		return stringArray;
	}
	
	public static List<String> stringArrayToStringList(String[] stringArray)
	{
		List<String> stringList = new ArrayList<String>();
		
		for(int i = 0; i < stringArray.length; i++)
		{
			stringList.add(stringArray[i]);
		}
		
		return stringList;
	}
	
	public static List<Integer> intArrayToIntegerList(int[] intArray)
	{
		List<Integer> intObjList = new ArrayList<Integer>();
		
		for(int i = 0; i < intArray.length; i++)
		{
			intObjList.add(new Integer(intArray[i]));
		}
		
		return intObjList;
	}
	
	public static int[] IntegerListToIntArray(List IntegerList)
	{
		int[] intArray = new int[IntegerList.size()];
		
		for(int i = 0; i < IntegerList.size(); i++)
		{
			if(IntegerList.get(i) instanceof Integer)
			{
				Integer intObject = (Integer) IntegerList.get(i);
				intArray[i] = intObject.intValue();
			}
			else
			{
				intArray[i] = 0;
			}
		}
		
		return intArray;
	}

	/**
	 * @param calendar
	 * @param separator
	 * @return
	 */
	public static String timeToString(Calendar calendar, String separator)
	{
		if (calendar == null) calendar = Calendar.getInstance();
		if (separator == null) separator = "";
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);
		String time = "";
		if (hour < 10) time += "0";
		time += hour + separator;
		if (min < 10) time += "0";
		time += min + separator;
		if (sec < 10) time += "0";
		time += sec;
		return time;	
	}
	
	/**
	 * Creates a new file if one does not exists and writes a string to it
	 * Does not write to an existing file 
	 * @param source
	 * @param filePath
	 * @return 0 if file already existed, -1 if could not write, or length 
	 */
	public static long writeToNewFile(String text, String filePath)
	{
		File out = new File(filePath);
		if (out.exists()) return 0;
		return writeToFile(text, filePath, false);
	}
	
	/**
	 * Creates a file if one did not exist, and writes the string
	 * @param text
	 * @param path
	 * @param append
	 * @return
	 */
	public static long writeToFile(String text, String path, boolean append)
	{
		try 
		{
//			FileOutputStream fos = new FileOutputStream(path, append);
//			BufferedWriter w = 
//					new BufferedWriter(new OutputStreamWriter(fos, "Cp1252"));
//			w.write(text);
//			w.flush();
//			w.close();  
			FileWriter fw = new FileWriter(path, append);
			fw.write(text);
			fw.close();
//			PrintWriter writer = new PrintWriter(fw);
//			writer.write(text);
//			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return -1;
		}
		return text.length();
	}
	
	/**
	 * Reads properties file
	 * @param fileName
	 * @return
	 */
	public static Properties readPropertiesFile(String fileName) 
	{
		Properties propFile = new Properties();
		InputStream is = null;
		
		try 
		{  
			is = ToolBox.getResourceAsStream(fileName);
			propFile.load(is); 
		} 
		catch(Exception e)
		{  
			e.printStackTrace();
			propFile = null;
		}
		finally
		{
			if(is != null)
			{
				try
				{
					is.close();
				}
				catch(Exception e)
				{
					//Not much can be done at this point
				}
			}
		}
		
		if(propFile == null)
		{
			System.out.println("Failed to load properties file for " + fileName);
		}
		
		return propFile;
	}
	
	public static InputStream getResourceAsStream(String resourceName)
	{
		InputStream is = null;
		
		if(!CommonUtils.isNullOrEmpty(System.getProperty("GWC_PROPERTIES")))
		{
			String absoluteResourceName = 
				System.getProperty("GWC_PROPERTIES") + System.getProperty("file.separator") + resourceName;

			try
			{
				is = new BufferedInputStream(new FileInputStream(absoluteResourceName));
			}
			catch(java.io.FileNotFoundException e)
			{
				//It is normal to not find an explicit file if the file is embedded
				//somewhere in the classpath of an ear application.
			}
		}
		
		if(is != null)
		{
			return is;
		}
				
		is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName);
		
		if( is != null)
		{
			return is;
		}
		
		is = ToolBox.class.getClassLoader().getResourceAsStream(resourceName);
				
		if(is == null)
		{
			System.out.println("Could not find any resource matching " + resourceName + " - returning null");
		}
		
		//We could also check all the parent class loaders, but if the server runtime
		//is behaving, it should already be doing this.
		
		return is;
	}
	
	public static int getStringLength(String string)
	{
		return CommonUtils.checkForNull(string).trim().length();
	}
	
	/**
	 * Breaks a string into chunks.
	 * @param stringToBreakIntoChunks
	 * @param chunkSize
	 * @return A containing the string chunks.
	 */
	public static List<String> chunkifyString(String stringToBreakIntoChunks, int chunkSize)
	{
		List<String> chunks = new ArrayList<String>();
		
		if(!CommonUtils.isNullOrEmpty(stringToBreakIntoChunks) && chunkSize > 0)
		{
			try
			{
				/*Convert the String object to a byte array.*/
				
				byte[] stringAsBytes = stringToBreakIntoChunks.getBytes();
				
				/*Iterate over the byte array, progressing by the chunk size.*/
				for(int i = 0; i < stringAsBytes.length; i += chunkSize)
				{
					/* If this is the last time through the loop, then just
					 * grab the remainder of the array and add it to the chunks.
					 * Otherwise, grab only the junk size.
					 */
					if((i + chunkSize) >= stringAsBytes.length)
					{
						chunks.add(new String(stringAsBytes, i, (stringAsBytes.length - i)));
					}
					else
					{
						chunks.add(new String(stringAsBytes, i, chunkSize));
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return chunks;
	}
	
	public static String stringListToNewlineDelimitedString(List<String> stringList)
	{
		String result = "";
		
		if(!CommonUtils.isNullOrEmpty(stringList))
		{
			for(int i = 0; i < stringList.size(); i++)
			{
				//Get rid of embedded newlines, but leave other leading / trainling whitespace.
				result += CommonUtils.checkForNull(stringList.get(i)).replaceAll("\n", "");
				
				if(i < (stringList.size() - 1))
				{
					result += System.getProperty("line.separator");
				}
			}
		}
		
		return result;
	}
	
	public static String javaScriptEscape(String Input)
	{	
		if (Input.contains(INPUT_APOSTROPHE))
		{
			Input = Input.replaceAll(INPUT_APOSTROPHE, ESCAPED_APOSTROPHE);
		}
		if (Input.contains(INPUT_DOUBLE_QUOTE))
		{
			Input = Input.replaceAll(INPUT_DOUBLE_QUOTE, ESCAPED_DOUBLE_QUOTE);
		}
		return Input;
	}	
	
	public static String javaScriptUnescape(String Input)
	{
		if (Input.contains(ESCAPE_CHAR))
		{
			Input.replaceAll(ESCAPE_CHAR, "");
		}
		return Input;
	}
	
    public static void runSystemCommand(String command) {

        String s = null;

        try 
        {
            Process p = Runtime.getRuntime().exec(command);
            
            BufferedReader stdInput = new BufferedReader(new 
                 InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new 
                 InputStreamReader(p.getErrorStream()));

            while ((s = stdInput.readLine()) != null) 
            {
            	System.out.println("Here is the standard output of the command:\n");
                System.out.println(s);
            }
            
            while ((s = stdError.readLine()) != null) 
            {
            	System.out.println("Here is the standard error of the command (if any):\n");
                System.out.println(s);
            }
        }
        catch(IOException e) 
        {
            System.out.println("exception executing command: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /*
     * This method is intended to build a resource path given a Java package.
     * The getResourceAsStream method is not enough in this case.  It only
     * searches the top of the classpath - you need to help it out with this method.
     * 
     * For example,
     * 
     * ToolBox.getResourceAsStream(ToolBox.buildResourcePath(...))
     */
	public static String buildResourcePath(Package pkg, String fileName) {
		String resourcePath = pkg.getName().replaceAll("\\.", "/") + "/" + fileName;
		
		return resourcePath;
	}
	public static List<String> sliceStringOnWordBoundary(String text, int maxLen)
    {
	    List<String> result = new ArrayList<String>();
	    String wordDelimiters = " .,?!" + System.getProperty("line.separator");
		if(!CommonUtils.isNullOrEmpty(text))
		{
		    if(text.length() > maxLen)
		    {
		        int pos = 0; //start position of the next line to be added
		        int textLen = text.length();
		        while(pos <= textLen)
	            {
		            if(pos + maxLen > textLen)
		            {//the rest of the text fits on the line
		                result.add(text.substring(pos));
		                pos += maxLen; //stops while loop
		            }
		            else
		            {//get a line of full words up to textMax characters
    	                String line = text.substring(pos, pos + maxLen);
    	                for (int j = maxLen - 1; j >= 0; j--)
                        {
    	                    if(j == 0)
    	                    {//all line is one word - no delimiters found
                                result.add(line);
                                pos += maxLen;
    	                    }
    	                    else if(wordDelimiters.contains("" + line.charAt(j)))
                            {
                                result.add(line.substring(0, j + 1));
                                pos += j + 1;
                                break;
                            }
                        }
		            }
	            }
		    }
		    else
		    {
	            result.add(text);
		    }
		}
        return result;
    }
	
	/**
	 * Convert date to string formatted for DB2 date "yyyy-MM-dd"
	 * @param date
	 * @return
	 */
	public static String dateToDB2DateString(Date date){
		String dateString = "";
		
		try 
		{
			// Gets from Calendar to String through SimpleDateFormat
			if(date != null)
			{
				dateString = (new SimpleDateFormat("yyyy-MM-dd").format(date.getTime())).toString();
			}
		}
		catch(Exception e) 
		{
		}
		
		return dateString;	
	}
	
	/**
	 * Convert date to string formatted for DB2 timestamp "yyyy-MM-dd-HH.mm.ss"
	 * @param date
	 * @return
	 */
	public static String dateToDB2TimestampString(Date date)
	{
		String dateString = "";
		
		try 
		{
			// Gets from Calendar to String through SimpleDateFormat
			if(date != null)
			{
				dateString = (new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss").format(date.getTime())).toString();
			}
		}
		catch(Exception e) 
		{
		}
		
		return dateString;	
	}
}
