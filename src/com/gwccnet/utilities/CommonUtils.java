package com.gwccnet.utilities;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.WordUtils;

import com.gwccnet.utility.ToolBox;


/**
 * Insert the type's description here.
 * Creation date: (4/11/2002 2:07:37 PM)
 * @author: Blake
 */
public class CommonUtils 
{
	private static int INPUT_STREAM_BUFFER_SIZE = 100000;
	
	public static void printStackTrace(Object o, Throwable t)
	{
		 System.out.println(o.getClass().getName() + ": " + t.getMessage());
		 t.printStackTrace(System.out);
	}
	
	public static boolean validateSSN(String ssn)
	{
		String validatePattern = "^[\\s]*[\\d]{3}[\\s]*[-]?[\\s]*[\\d]{2}[\\s]*[-]?[\\s]*[\\d]{4}[\\s]*$";
		
		return ssn.matches(validatePattern);
	}
	
	public static String parseSSN(String ssn)
	{
		String parsePattern = "[^\\d]";
		
		return ssn.replaceAll(parsePattern, "");
	}
	
	public static String formatSSN(Integer ssn)
	{
		if(ssn == null)
		{
			return "";
		}
		
		return CommonUtils.formatSSN(ssn.intValue());
	}
	
	public static String formatSSN(int ssn)
	{
		return CommonUtils.formatSSN(ToolBox.padNumberLeft(ssn, 9));
	}
	
	public static String formatSSN(String ssn)
	{
		if(!CommonUtils.validateSSN(ssn))
		{
			return "";
		}
		
		String numbersOnly = CommonUtils.parseSSN(ssn);
				
		return numbersOnly.substring(0, 3) + "-" + numbersOnly.substring(3,5) + 
			   "-" + numbersOnly.substring(5);
	}
	
	public static String maskSSN(Integer ssn)
	{
		if(ssn == null)
		{
			return "";
		}
		
		return CommonUtils.maskSSN(ssn.intValue());
	}
	
	public static String maskSSN(int ssn)
	{
		return CommonUtils.maskSSN(ToolBox.padNumberLeft(ssn, 9));
	}
	
	public static String maskSSN(String ssn)
	{
		if(!CommonUtils.validateSSN(ssn))
		{
			return "";
		}
		
		String numbersOnly = CommonUtils.parseSSN(ssn);
						
		return "XXX-XX-" + numbersOnly.substring(5);
	}
	
	public static String hideSSN(Integer ssn)
	{
		if(ssn == null)
		{
			return "";
		}
		
		return CommonUtils.hideSSN(ssn.intValue());
	}
	
	public static String hideSSN(int ssn)
	{
		return hideSSN(ToolBox.padNumberLeft(ssn, 9));
	}
	
	public static String hideSSN(String ssn)
	{
		if(!CommonUtils.validateSSN(ssn))
		{
			return "";
		}
		
		return "XXX-XX-XXXX";
	}
	public static boolean validateBusTaxID(String busTaxID)
	{
		String validatePattern = "^[\\s]*[\\d]{2}[\\s]*[-]?[\\s]*[\\d]{7}[\\s]*$";
		
		return busTaxID.matches(validatePattern);
	}
	
	public static String parseBusTaxID(String busTaxID)
	{
		String parsePattern = "[^\\d]";
		
		return busTaxID.replaceAll(parsePattern, "");
	}
	
	public static String formatBusTaxID(Integer busTaxID)
	{
		if(busTaxID == null)
		{
			return "";
		}
		
		return CommonUtils.formatBusTaxID(busTaxID.intValue());
	}
	
	public static String formatBusTaxID(int busTaxID)
	{
		return CommonUtils.formatBusTaxID(ToolBox.padNumberLeft(busTaxID, 9));
	}
	
	public static String formatBusTaxID(String busTaxID)
	{
		if(!CommonUtils.validateBusTaxID(busTaxID))
		{
			return "";
		}
		
		String numbersOnly = CommonUtils.parseBusTaxID(busTaxID);
				
		return numbersOnly.substring(0, 2) + "-" + numbersOnly.substring(2);
	}
	
	public static String maskBusTaxID(Integer busTaxID)
	{
		if(busTaxID == null)
		{
			return "";
		}
		
		return CommonUtils.maskBusTaxID(busTaxID.intValue());
	}
	
	public static String maskBusTaxID(int busTaxID)
	{
		return CommonUtils.maskBusTaxID(ToolBox.padNumberLeft(busTaxID, 9));
	}
	
	public static String maskBusTaxID(String busTaxID)
	{
		if(!CommonUtils.validateBusTaxID(busTaxID))
		{
			return "";
		}
		
		String numbersOnly = CommonUtils.parseBusTaxID(busTaxID);
						
		return "XX-" + numbersOnly.substring(2);
	}
	
	public static String hideBusTaxID(Integer busTaxID)
	{
		if(busTaxID == null)
		{
			return "";
		}
		
		return CommonUtils.hideBusTaxID(busTaxID.intValue());
	}
	
	public static String hideBusTaxID(int busTaxID)
	{
		return hideBusTaxID(ToolBox.padNumberLeft(busTaxID, 9));
	}
	
	public static String hideBusTaxID(String busTaxID)
	{
		if(!CommonUtils.validateBusTaxID(busTaxID))
		{
			return "";
		}
		
		return "XX-XXXXXXX";
	}	
	public static String formatPhoneNumber(String phoneNumber)
	{
		if(!isNullOrEmpty(phoneNumber))
		{
			phoneNumber = phoneNumber.trim();
			if(phoneNumber.length()==7 && phoneNumber.matches("\\d{7}+"))
			{
				phoneNumber = phoneNumber.substring(0,3) + "-" + phoneNumber.substring(3);
			}
			else if(phoneNumber.length()==10 && phoneNumber.matches("\\d{10}+"))
			{
				phoneNumber = phoneNumber.substring(0,3) + "-" + phoneNumber.substring(3,6) + "-" + phoneNumber.substring(6);
			}
			else if(phoneNumber.length()==11 && phoneNumber.matches("\\d{11}+"))
			{
				phoneNumber = phoneNumber.substring(0,1) + "-" + phoneNumber.substring(1,4) + "-" + phoneNumber.substring(4,7) + "-" + phoneNumber.substring(7);
			}
		}
		return phoneNumber;
	}
	
	public static String formatDollarAmount(double amount)
	{
		String result = "";
		
		try
		{
			Format dollarFormat = new DecimalFormat("#,##0.00");
			result = dollarFormat.format(amount);
		}
		catch(Exception e)
		{
		}
		
		return result;
	}
	
	public static String formatAsDecimal(BigDecimal value)
	{
		return CommonUtils.formatCheckForNull(value, "###,###,##0.00");
	}
	
	public static String formatAsDecimalCurrency(BigDecimal value)
	{
		return CommonUtils.formatCheckForNull(value, "$###,###,##0.00");
	}
	
	public static String formatAsInteger(Double value)
	{
		BigDecimal bigDecimalValue = null;
		
		if(value != null)
		{
			bigDecimalValue = new BigDecimal(value);
		}
		else
		{
			bigDecimalValue = new BigDecimal(0);
		}
		
		return CommonUtils.formatCheckForNull(bigDecimalValue, "###,###,##0");
	}
	
	public static String formatAsInteger(Integer value)
	{
		BigDecimal bigDecimalValue = null;
		
		if(value != null)
		{
			bigDecimalValue = new BigDecimal(value);
		}
		else
		{
			bigDecimalValue = new BigDecimal(0);
		}
		
		return CommonUtils.formatCheckForNull(bigDecimalValue, "###,###,##0");
	}
	
	public static String formatAsInteger(BigDecimal value)
	{
		return CommonUtils.formatCheckForNull(value, "###,###,##0");
	}
	
	public static String formatAsIntegerCurrency(BigDecimal value)
	{
		return CommonUtils.formatCheckForNull(value, "$###,###,##0");
	}
	
	public static String formatCheckForNull(BigDecimal value, String formatString)
	{
		if(value == null)
		{
			value = new BigDecimal(0);
		}
			
		return CommonUtils.format(value, formatString);
	}
	
	public static String format(BigDecimal value, String formatString)
	{
		String result = "";
		
		try
		{
			Format format = new DecimalFormat(formatString);
			result = format.format(value);
		}
		catch(Exception e)
		{
		}
		
		return result;
	}
	
	public static String buildFullName(String firstName,
									   String middleName,
									   String lastName,
									   String nameSuffix)
	{
		firstName = firstName.trim();
		middleName = middleName.trim();
		lastName = lastName.trim();
		nameSuffix = nameSuffix.trim();
		
		String fullName = lastName;
					
		fullName = fullName + ", ";
								
		if(!CommonUtils.isNullOrEmpty(firstName))
		{
			fullName = fullName + firstName + " ";
		}
			
		if(!CommonUtils.isNullOrEmpty(middleName))
		{
			fullName = fullName + middleName + " ";
		}
			
		if(!CommonUtils.isNullOrEmpty(nameSuffix))
		{
			fullName = fullName + nameSuffix;
		}
			
		fullName = fullName.trim();
		
		if(fullName.equals(","))
		{
			fullName = "";
		}
		
		return fullName;
	}
	
	public static String[] parseFullName(String fullName)
	{
		//last, first, middle, suffix
		
		String[] parsedName = {"", "", "", ""};
		
		if(!CommonUtils.isNullOrEmpty(fullName))
		{
			String[] nameParts = fullName.split(",");
				
			if(nameParts.length >= 1)
			{
				parsedName[0] = new String(nameParts[0].trim());
			}
			
			if(nameParts.length >= 2)
			{				
				String[] firstMiddleSuffix = nameParts[1].trim().split(" ");
					
				if(firstMiddleSuffix.length >= 1)
				{
					parsedName[1] = firstMiddleSuffix[0].trim();
				}
					
				if(firstMiddleSuffix.length >= 2)
				{
					parsedName[2] = firstMiddleSuffix[1].trim();
				}
					
				if(firstMiddleSuffix.length >= 3)
				{
					parsedName[3] = firstMiddleSuffix[2].trim();
				}
			}
		}
		
		return parsedName;	
	}
	
	public static boolean areCalendarsEqual(Calendar lhs, Calendar rhs)
	{
		if(lhs == null && rhs == null)
		{
			return true;
		}
		
		if((lhs == null && rhs != null) || (lhs != null && rhs == null))
		{
			return false;
		}
		
		if(lhs.getTimeInMillis() == rhs.getTimeInMillis())
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Copies a Calendar object.  Returns null if the input Calendar was null
	 * and a copy of the Calendar otherwise.
	 * @param rhs
	 * @return a copy of rhs
	 */
	public static Calendar copyCalendar(Calendar rhs)
	{
		if(rhs == null)
		{
			return null;
		}
		
		Calendar copy = Calendar.getInstance();
		copy.setTimeInMillis(rhs.getTimeInMillis());
		
		return copy;
	}
	
	public static <T extends Object> List<T> convertSetToList(Set<T> rhs)
	{
		List<Object> setAsList = new ArrayList<Object>();
		
		if(!CommonUtils.isNullOrEmpty(rhs))
		{
			for(T setElement : rhs)
			{
				setAsList.add(setElement);
			}
		}
		
		return (List<T>) setAsList;
	}
	
	public static <T> List<List<T>> subdivideList(List<T> oldList, int maxSubdivisionSize) {
		List<List<T>> newList = new ArrayList<List<T>>();
		
		if(maxSubdivisionSize <= 0) {
			throw new UnsupportedOperationException("maxSubdivisionSize must be positive");
		}
		
		if(!CommonUtils.isNullOrEmpty(oldList)) {
			List<T> subList = new ArrayList<T>();
			
			for(int i = 0; i < oldList.size(); i++) {
				if(subList.size() >= maxSubdivisionSize) {
					newList.add(subList);
					subList = new ArrayList<T>();
				}
				
				subList.add(oldList.get(i));
			}
			
			if(!CommonUtils.isNullOrEmpty(subList)) {
				newList.add(subList);
			}
		}
		
		return newList;
	}
	
	public static <T extends Object> List<T> convertSetToListAsCopy(Set<T> rhs)
	{
		List<T> setAsList = CommonUtils.convertSetToList(rhs);
		List<T> copy = CommonUtils.copyList(setAsList);
		
		return copy;
	}
	
	/**
	 * Makes a deep copy of a list.  **IMPORTANT** The class represented
	 * by T **MUST** have a public copy constructor.  If not, the reflection
	 * calls to get a new instance of the object via the Constructor object
	 * will fail with exceptions.  The method eats these exceptions and throws
	 * an unchecked UnsupportedOperationException.  You have been warned.
	 * @param <T>
	 * @param rhs
	 * @return a copy of rhs
	 */
	public static <T extends Object> List<T> copyList(List<T> rhs)
	{
		List<Object> copy = new ArrayList<Object>();
		
		try
		{		
			if(!CommonUtils.isNullOrEmpty(rhs))
			{
				Class aClass = rhs.get(0).getClass();
				Constructor constructor = aClass.getConstructor(aClass);
				
				for(T listElement : rhs)
				{
					if(listElement != null)
					{
						copy.add((Object)constructor.newInstance(listElement));
					}
					else
					{
						copy.add(null);
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			throw new UnsupportedOperationException(e.getMessage());
		}
		
		return (List<T>) copy;
	}
	
	/**
	 * Makes a copy of an object.  **IMPORTANT** The class represented by T **MUST** have
	 * a public copy constructor.  If not, the reflection calls to get a new instance of the
	 * object via the Constructor object will fail with exceptions.  The method eats these
	 * exceptions and throws an unchecked UnsupportedOperationException.  You have been warned.
	 * @param <T>
	 * @param rhs
	 * @return a copy of rhs
	 */
	public static <T extends Object> T copyObject(T rhs)
	{
		T copy = null;
		
		try
		{
			if(rhs != null)
			{
				Class aClass = rhs.getClass();
				Constructor constructor = aClass.getConstructor(aClass);
				
				copy = (T) constructor.newInstance(rhs);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			throw new UnsupportedOperationException(e.getMessage());
		}
		
		return copy;
	}
	
	public static Boolean checkForNull(Boolean aBoolean)
	{
		if(aBoolean == null)
		{
			return new Boolean(false);
		}
		else
		{
			return aBoolean;
		}
	}
	
	public static String checkForNullUCase(String aString)
	{
		return CommonUtils.checkForNull(aString).toUpperCase();
	}
	
	public static String checkForNullTrimUCase(String aString)
	{
		return CommonUtils.checkForNull(aString).trim().toUpperCase();
	}
	
	public static String checkForNullLCase(String string)
	{
		return CommonUtils.checkForNull(string).toLowerCase();
	}
	
	/**
	 * Basic null string checker.  Feed a string to it, if null
	 * you get an empty string ("") or the string itself (if not
	 * null), but you don't wind up with null strings.
	 * 
	 * **NOTE** This is a copy of the method from the GwccHttpServlet.
	 * This method doesn't belong there.  We should probably convert
	 * all the calls to use this one eventually.
	 */
	public static String checkForNull(String aString)
	{
		if (aString == null)
		{
			return "";
		}
		else
		{
			return aString;
		}
	}
	
	public static String checkForNullTrimLCase(String string)
	{
		return CommonUtils.checkForNullLCase(string).trim();
	}
	
	public static String checkForNullTrim(String aString)
	{
		return CommonUtils.checkForNull(aString).trim();
	}
	
	public static Integer checkForNull(Integer anInteger)
	{
		if(anInteger == null)
		{
			return new Integer(0);
		}
		else
		{
			return anInteger;
		}
	}
		
	public static <T extends Object> List<T> checkForNull(List<T> list)
	{
		if(list == null)
		{
			return new ArrayList<T>();
		}
		else
		{
			return list;
		}
	}
	
	public static <T extends Object> Set<T> checkForNull(Set<T> set)
	{
		if(set == null)
		{
			return new HashSet<T>();
		}
		else
		{
			return set;
		}
	}
	
	public static boolean isNullorEmpty(Map map)
	{
		return ((map == null) || map.isEmpty());
	}
		
	public static boolean isNullOrEmpty(byte[] byteArray)
	{
		return ((byteArray == null) || byteArray.length == 0);
	}
	
	public static boolean isNullOrEmpty(int[] intArray)
	{
		return ((intArray == null) || intArray.length == 0);
	}
	
	/**
	 * Insert the method's description here.
	 * Creation date: (4/11/2002 2:08:33 PM)
	 * @return boolean
	 * @param aString java.lang.String
	 */
	public static boolean isNullOrEmpty(Object[] objects) {
		return ((objects == null) || objects.length == 0);
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/11/2002 2:08:33 PM)
	 * @return boolean
	 * @param aString java.lang.String
	 */
	public static boolean isNullOrEmpty(String aString) {
		return ((aString == null) || aString.trim().length() == 0);
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/11/2002 2:08:33 PM)
	 * @return boolean
	 * @param anIter java.util.Iterator
	 */
	public static boolean isNullOrEmpty(Iterator anIter) {
		return ((anIter == null) || (! anIter.hasNext()));
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (4/11/2002 2:08:33 PM)
	 * @return boolean
	 * @param aList java.util.List
	 */
	public static boolean isNullOrEmpty(List aList) {
		return ((aList == null) || aList.isEmpty());
	}
	
	public static boolean isNullOrEmpty(Set set)
	{
		return ((set == null) || set.isEmpty());
	}
	
	public static <T extends Enum> boolean isEnumMemberInList(List<T> list, T value)
	{
		if(!CommonUtils.isNullOrEmpty(list))
		{
			for(Enum enumMember : list)
			{
				if(enumMember == value)
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static boolean isInteger(String value)
	{
		boolean result = true;
		
		try
		{
			Integer.parseInt(value);
		}
		catch(Exception e)
		{
			result = false;
		}
		
		return result;
	}
	
	public static boolean isBoolean(String value)
	{
		boolean result = true;
		
		if(!CommonUtils.checkForNull(value).equalsIgnoreCase("false") &&
		   !CommonUtils.checkForNull(value).equalsIgnoreCase("true"))
		{
			result = false;
		}
			
		return result;
	}
	
	public static <T extends Enum> List<Enum> removeEnumMemberFromList(List<T> list, T value)
	{
		List<Enum> newList = new ArrayList<Enum>();
		
		if(!CommonUtils.isNullOrEmpty(list))
		{
			for(Enum enumMember : list)
			{
				if(enumMember != value)
				{
					newList.add(enumMember);
				}
			}
		}
		
		return newList;
	}
	
	public static <T extends Enum, U extends Enum> int countMembersInIntersection(List<T> list1, List<U> list2)
	{
		int intersectionCount = 0;
		
		if(!CommonUtils.isNullOrEmpty(list1) && !CommonUtils.isNullOrEmpty(list2))
		{
			for(Enum list1Member : list1)
			{
				for(Enum list2Member : list2)
				{
					if(list1Member == list2Member)
					{
						intersectionCount++;
						break;
					}
				}
			}
		}
		
		return intersectionCount;
	}
	
	public static String removeSpecialChars(String text)
	{
		String tempText = text;	
		tempText = tempText.replaceAll("&","");
		tempText = tempText.replaceAll("<","");
		tempText = tempText.replaceAll(">","");
		tempText = tempText.replaceAll("/","");
		
		return tempText;
	
	}
	
	/**
	 * Return string with max length of charLimit 
	 * @param string
	 * @param charLimit
	 * @return
	 */
	public static String limitStringLength(String string, int charLimit){
		return limitStringLength(string, charLimit, "");
	}
	
	public static String limitStringLength(String string, int charLimit, String suffix)
	{
		if(CommonUtils.isNullOrEmpty(string) || charLimit <= 0)
		{
			return "";
		}
		
		if(suffix == null)
		{
			suffix = "";
		}
		
		if(string.length() <= charLimit)
		{
			return string;
		}
		
		String newString = string.substring(0, charLimit) + suffix;
		return newString;
	}
	
	public static String capitalizeWord(String word)
	{
		if(CommonUtils.isNullOrEmpty(word))
		{
			return "";
		}
		
		if(word.length() == 1)
		{
			return word.toUpperCase();
		}
		
		return (word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase());
	}
	
	public static String readInputStreamIntoString(InputStream is)
		throws Throwable
	{
		String resultString = "";
		
		byte[] resultBuffer = CommonUtils.readInputStreamIntoByteArray(is);
		
		if(!CommonUtils.isNullOrEmpty(resultBuffer))
		{
			resultString = new String(resultBuffer);
		}
		
		return resultString;
	}
	
	public static byte[] readInputStreamIntoByteArray(InputStream is)
		throws Throwable
	{
		return CommonUtils.readInputStreamIntoByteArray(is, CommonUtils.INPUT_STREAM_BUFFER_SIZE);
	}
	
	public static byte[] readInputStreamIntoByteArray(InputStream is, int bufferSize)
		throws Throwable
	{
		BufferedInputStream bis = new BufferedInputStream(is);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		byte[] buffer = new byte[bufferSize];
			
		while(true)
		{
			int amountRead = bis.read(buffer);
			
			if(amountRead == -1)
			{
				break;
			}
			else
			{
				bos.write(buffer, 0, amountRead);
			}
		}
	
		byte[] result = bos.toByteArray();
		
		bis.close();
		
		return result;
	}
	
	public static void printProperties(Object object, String fileName)
	{
		try
		{
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName));
			PrintStream ps = new PrintStream(bos);
			
			printProperties(object, ps);
			
			ps.flush();
			ps.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void printProperties(Object object, PrintStream ps)
	{
		try
		{			
			Map<String, Object> map = PropertyUtils.describe(object);
			
			Set<String> keys = map.keySet();
			
			for(String key : keys)
			{
				Object value = map.get(key);
				
				if(value instanceof List)
				{
					List listValues = (List) value;
					
					ps.println("Begin list " + key);
					
					for(Object listElement : CommonUtils.checkForNull(listValues))
					{
						CommonUtils.printProperties(listElement, ps);
					}
					
					ps.println("End list " + key);
				}
				else
				{
					ps.println(key + " : " + value);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void printProperties(Object object)
	{
		CommonUtils.printProperties(object, System.out);
	}
	
	public static String camelCase(String inputString)
	{
		String separatorChar = "[_|\\s]";
	
		return CommonUtils.camelCase(inputString, separatorChar);
	}
	
	public static String camelCase(String inputString, String separator)
	{
		String resultString = "";
		
		String[] inputStringPieces = inputString.split(separator);
		
		for(int i = 0; i < inputStringPieces.length; i++)
		{
			if(i == 0)
			{
				resultString += CommonUtils.checkForNullTrim(inputStringPieces[i]).toLowerCase();
			}
			else
			{
				resultString += CommonUtils.capitalizeWord(CommonUtils.checkForNullTrim(inputStringPieces[i]));
			}
		}
		
		return resultString;
	}
	
	//Tests whether a date is on or after a start date and before an end date (if end date is not null)
	public static boolean isDateWithinRange(Calendar date, Calendar startDate, Calendar endDate)
		throws NullPointerException
	{
		if (date == null || startDate == null)
		{
			throw new NullPointerException();
		}
		
		if (date.after(startDate) || date.equals(startDate))
		{
			if (endDate == null || date.before(endDate))
			{
				return true;
			}
		}
		
		return false;
	}
	/**
	 * returns a string in Mixed Case
	 * @param String input
	 * @return String 
	 */
	public static String convertToProperCase(String input)
	{
		return(WordUtils.capitalize(input.toLowerCase()));
	}
}
