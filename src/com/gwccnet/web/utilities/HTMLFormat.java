package com.gwccnet.web.utilities;

import com.gwccnet.utilities.CommonUtils;

/**
 * HTMLFormat formats Strings into a HTML safe format
 * @author: Justin L Rosenberg (rosenberg@us.ibm.com)
 */
public class HTMLFormat {
	
	/**
	 * This method is intended to do the exact same thing as the standard format method
	 * except that there is guaranteed to be at least one character present (in this
	 * case, a space that will translate to &nbsp;).  This is necessary to maintain
	 * consistent borders on tables.  There are also CSS ways of doing this, but this is standard
	 * practice and probably easier.
	 * @param aString
	 * @return formatted String
	 */
	public static String formatForTableCell(String aString)
	{
		return CommonUtils.isNullOrEmpty(aString) ? HTMLFormat.format(" ") : HTMLFormat.format(aString);
	}
	
/**
 * This function looks through a String and changes any
 * characters that can cause problems in HTML and makes them
 * HTML format compliant.
 * @return java.lang.String HTML formatted string
 * @param aString java.lang.String The String to be formatted
 */
public static String format(String aString) 
{
	StringBuilder aStringBuilder = new StringBuilder("");
	String next = new String();
	int stringLength = aString.length();
	
	for(int i = 0; i <  stringLength; i++)
	{
		switch(aString.charAt(i))
		{
			case ' ':
				next = "&nbsp;";
				break;
			case '!':
				next = "&#033;";
				break;
			case '"':
				next = "&#034;";
				break;
			case '#':
				next = "&#035;";
				break;
			case '$':
				next = "&#036;";
				break;
			case '%':
				next = "&#037;";
				break;
			case '&':
				next = "&#038;";
				break;
			case '\'':
				next = "&#039;";
				break;
			case '(':
				next = "&#040;";
				break;
			case ')':
				next = "&#041;";
				break;
			case '*':
				next = "&#042;";
				break;
			case '+':
				next = "&#043;";
				break;
			case ',':
				next = "&#044;";
				break;
			case '-':
				next = "&#045;";
				break;
			case '/':
				next = "&#047;";
				break;
			case ':':
				next = "&#058;";
				break;
			case ';':
				next = "&#059;";
				break;
			case '<':
				next = "&#060;";
				break;
			case '=':
				next = "&#061;";
				break;
			case '>':
				next = "&#062;";
				break;
			case '@':
				next = "&#064;";
				break;
			case '[':
				next = "&#091;";
				break;
			case '\\':
				next = "&#092;";
				break;
			case ']':
				next = "&#093;";
				break;
			case '^':
				next = "&#094;";
				break;
			case '_':
				next = "&#095;";
				break;
			case '`':
				next = "&#096;";
				break;
			case '{':
				next = "&#123;";
				break;
			case '|':
				next = "&#124;";
				break;
			case '}':
				next = "&#125;";
				break;
			case '~':
				next = "&#126;";
				break;
			case '�':
				next = "&#161;";
				break;
			case '�':
				next = "&#162;";
				break;
			case '�':
				next = "&#163;";
				break;
			case '�':
				next = "&#164;";
				break;
			case '�':
				next = "&#165;";
				break;
			case '�':
				next = "&#166;";
				break;
			case '�':
				next = "&#167;";
				break;
			case '�':
				next = "&#168;";
				break;
			case '�':
				next = "&#169;";
				break;
			case '�':
				next = "&#170;";
				break;
			case '�':
				next = "&#171;";
				break;
			case '�':
				next = "&#172;";
				break;
			case '�':
				next = "&#173;";
				break;
			case '�':
				next = "&#174;";
				break;
			case '�':
				next = "&#175;";
				break;
			case '�':
				next = "&#176;";
				break;
			case '�':
				next = "&#177;";
				break;
			case '�':
				next = "&#178;";
				break;
			case '�':
				next = "&#179;";
				break;
			case '�':
				next = "&#180;";
				break;
			case '�':
				next = "&#181;";
				break;
			case '�':
				next = "&#182;";
				break;
			case '�':
				next = "&#183;";
				break;
			case '�':
				next = "&#184;";
				break;
			case '�':
				next = "&#185;";
				break;
			case '�':
				next = "&#186;";
				break;
			case '�':
				next = "&#187;";
				break;
			case '�':
				next = "&#188;";
				break;
			case '�':
				next = "&#189;";
				break;
			case '�':
				next = "&#190;";
				break;
			case '�':
				next = "&#191;";
				break;
			case '�':
				next = "&#192;";
				break;
			case '�':
				next = "&#193;";
				break;
			case '�':
				next = "&#194;";
				break;
			case '�':
				next = "&#195;";
				break;
			case '�':
				next = "&#196;";
				break;
			case '�':
				next = "&#197;";
				break;
			case '�':
				next = "&#198;";
				break;
			case '�':
				next = "&#199;";
				break;
			case '�':
				next = "&#200;";
				break;
			case '�':
				next = "&#201;";
				break;
			case '�':
				next = "&#202;";
				break;
			case '�':
				next = "&#203;";
				break;
			case '�':
				next = "&#204;";
				break;
			case '�':
				next = "&#205;";
				break;
			case '�':
				next = "&#206;";
				break;
			case '�':
				next = "&#207;";
				break;
			case '�':
				next = "&#208;";
				break;
			case '�':
				next = "&#209;";
				break;
			case '�':
				next = "&#210;";
				break;
			case '�':
				next = "&#211;";
				break;
			case '�':
				next = "&#212;";
				break;
			case '�':
				next = "&#213;";
				break;
			case '�':
				next = "&#214;";
				break;
			case '�':
				next = "&#215;";
				break;
			case '�':
				next = "&#216;";
				break;
			case '�':
				next = "&#217;";
				break;
			case '�':
				next = "&#218;";
				break;
			case '�':
				next = "&#219;";
				break;
			case '�':
				next = "&#220;";
				break;
			case '�':
				next = "&#221;";
				break;
			case '�':
				next = "&#222;";
				break;
			case '�':
				next = "&#223;";
				break;
			case '�':
				next = "&#224;";
				break;
			case '�':
				next = "&#225;";
				break;
			case '�':
				next = "&#226;";
				break;
			case '�':
				next = "&#227;";
				break;
			case '�':
				next = "&#228;";
				break;
			case '�':
				next = "&#229;";
				break;
			case '�':
				next = "&#230;";
				break;
			case '�':
				next = "&#231;";
				break;
			case '�':
				next = "&#232;";
				break;
			case '�':
				next = "&#233;";
				break;
			case '�':
				next = "&#234;";
				break;
			case '�':
				next = "&#235;";
				break;
			case '�':
				next = "&#236;";
				break;
			case '�':
				next = "&#237;";
				break;
			case '�':
				next = "&#238;";
				break;
			case '�':
				next = "&#239;";
				break;
			case '�':
				next = "&#240;";
				break;
			case '�':
				next = "&#241;";
				break;
			case '�':
				next = "&#242;";
				break;
			case '�':
				next = "&#243;";
				break;
			case '�':
				next = "&#244;";
				break;
			case '�':
				next = "&#245;";
				break;
			case '�':
				next = "&#246;";
				break;
			case '�':
				next = "&#247;";
				break;
			case '�':
				next = "&#248;";
				break;
			case '�':
				next = "&#249;";
				break;
			case '�':
				next = "&#250;";
				break;
			case '�':
				next = "&#251;";
				break;
			case '�':
				next = "&#252;";
				break;
			case '�':
				next = "&#253;";
				break;
			case '�':
				next = "&#254;";
				break;
			case '�':
				next = "&#255;";
				break;
			default:
				next = "" + aString.charAt(i);
		}
		aStringBuilder.append(next);
	}
	return aStringBuilder.toString();
	
}
/**
 * This function looks through a String and changes any
 * characters that may cause problems in HTML scripting languages
 * and makes them compliant.
 * NOTE: currently only looks for characters that may cause processing of the script to
 *		 end prematurely (', ", and /)
 * @return java.lang.String HTML formatted string
 * @param aString java.lang.String The String to be formatted
 */
public static String formatForScript(String aString) 
{
	StringBuilder aStringBuilder = new StringBuilder("");
	String next = new String();
	int stringLength = aString.length();
	
	for(int i = 0; i <  stringLength; i++)
	{
		switch(aString.charAt(i))
		{
			case '\'':
				next = "\\\'";
				break;
			case '\"':
				next = "\\\"";
				break;
			case '/':
				next = "\\/";
				break;
			default:
				next = "" + aString.charAt(i);
		}
		aStringBuilder.append(next);
	}
	return aStringBuilder.toString();
	
}
/**
 * This function looks through a String and changes any
 * characters that can cause problems in HTML and makes them
 * HTML format compliant.
 * @return java.lang.String HTML formatted string
 * @param aString java.lang.String The String to be formatted
 */
public static String formatLeaveWhitespace(String aString) 
{
	StringBuilder aStringBuilder = new StringBuilder("");
	String next = new String();
	int stringLength = aString.length();
	
	for(int i = 0; i <  stringLength; i++)
	{
		switch(aString.charAt(i))
		{
			case '!':
				next = "&#033;";
				break;
			case '"':
				next = "&#034;";
				break;
			case '#':
				next = "&#035;";
				break;
			case '$':
				next = "&#036;";
				break;
			case '%':
				next = "&#037;";
				break;
			case '&':
				next = "&#038;";
				break;
			case '\'':
				next = "&#039;";
				break;
			case '(':
				next = "&#040;";
				break;
			case ')':
				next = "&#041;";
				break;
			case '*':
				next = "&#042;";
				break;
			case '+':
				next = "&#043;";
				break;
			case ',':
				next = "&#044;";
				break;
			case '-':
				next = "&#045;";
				break;
			case '/':
				next = "&#047;";
				break;
			case ':':
				next = "&#058;";
				break;
			case ';':
				next = "&#059;";
				break;
			case '<':
				next = "&#060;";
				break;
			case '=':
				next = "&#061;";
				break;
			case '>':
				next = "&#062;";
				break;
			case '@':
				next = "&#064;";
				break;
			case '[':
				next = "&#091;";
				break;
			case '\\':
				next = "&#092;";
				break;
			case ']':
				next = "&#093;";
				break;
			case '^':
				next = "&#094;";
				break;
			case '_':
				next = "&#095;";
				break;
			case '`':
				next = "&#096;";
				break;
			case '{':
				next = "&#123;";
				break;
			case '|':
				next = "&#124;";
				break;
			case '}':
				next = "&#125;";
				break;
			case '~':
				next = "&#126;";
				break;
			case '�':
				next = "&#161;";
				break;
			case '�':
				next = "&#162;";
				break;
			case '�':
				next = "&#163;";
				break;
			case '�':
				next = "&#164;";
				break;
			case '�':
				next = "&#165;";
				break;
			case '�':
				next = "&#166;";
				break;
			case '�':
				next = "&#167;";
				break;
			case '�':
				next = "&#168;";
				break;
			case '�':
				next = "&#169;";
				break;
			case '�':
				next = "&#170;";
				break;
			case '�':
				next = "&#171;";
				break;
			case '�':
				next = "&#172;";
				break;
			case '�':
				next = "&#173;";
				break;
			case '�':
				next = "&#174;";
				break;
			case '�':
				next = "&#175;";
				break;
			case '�':
				next = "&#176;";
				break;
			case '�':
				next = "&#177;";
				break;
			case '�':
				next = "&#178;";
				break;
			case '�':
				next = "&#179;";
				break;
			case '�':
				next = "&#180;";
				break;
			case '�':
				next = "&#181;";
				break;
			case '�':
				next = "&#182;";
				break;
			case '�':
				next = "&#183;";
				break;
			case '�':
				next = "&#184;";
				break;
			case '�':
				next = "&#185;";
				break;
			case '�':
				next = "&#186;";
				break;
			case '�':
				next = "&#187;";
				break;
			case '�':
				next = "&#188;";
				break;
			case '�':
				next = "&#189;";
				break;
			case '�':
				next = "&#190;";
				break;
			case '�':
				next = "&#191;";
				break;
			case '�':
				next = "&#192;";
				break;
			case '�':
				next = "&#193;";
				break;
			case '�':
				next = "&#194;";
				break;
			case '�':
				next = "&#195;";
				break;
			case '�':
				next = "&#196;";
				break;
			case '�':
				next = "&#197;";
				break;
			case '�':
				next = "&#198;";
				break;
			case '�':
				next = "&#199;";
				break;
			case '�':
				next = "&#200;";
				break;
			case '�':
				next = "&#201;";
				break;
			case '�':
				next = "&#202;";
				break;
			case '�':
				next = "&#203;";
				break;
			case '�':
				next = "&#204;";
				break;
			case '�':
				next = "&#205;";
				break;
			case '�':
				next = "&#206;";
				break;
			case '�':
				next = "&#207;";
				break;
			case '�':
				next = "&#208;";
				break;
			case '�':
				next = "&#209;";
				break;
			case '�':
				next = "&#210;";
				break;
			case '�':
				next = "&#211;";
				break;
			case '�':
				next = "&#212;";
				break;
			case '�':
				next = "&#213;";
				break;
			case '�':
				next = "&#214;";
				break;
			case '�':
				next = "&#215;";
				break;
			case '�':
				next = "&#216;";
				break;
			case '�':
				next = "&#217;";
				break;
			case '�':
				next = "&#218;";
				break;
			case '�':
				next = "&#219;";
				break;
			case '�':
				next = "&#220;";
				break;
			case '�':
				next = "&#221;";
				break;
			case '�':
				next = "&#222;";
				break;
			case '�':
				next = "&#223;";
				break;
			case '�':
				next = "&#224;";
				break;
			case '�':
				next = "&#225;";
				break;
			case '�':
				next = "&#226;";
				break;
			case '�':
				next = "&#227;";
				break;
			case '�':
				next = "&#228;";
				break;
			case '�':
				next = "&#229;";
				break;
			case '�':
				next = "&#230;";
				break;
			case '�':
				next = "&#231;";
				break;
			case '�':
				next = "&#232;";
				break;
			case '�':
				next = "&#233;";
				break;
			case '�':
				next = "&#234;";
				break;
			case '�':
				next = "&#235;";
				break;
			case '�':
				next = "&#236;";
				break;
			case '�':
				next = "&#237;";
				break;
			case '�':
				next = "&#238;";
				break;
			case '�':
				next = "&#239;";
				break;
			case '�':
				next = "&#240;";
				break;
			case '�':
				next = "&#241;";
				break;
			case '�':
				next = "&#242;";
				break;
			case '�':
				next = "&#243;";
				break;
			case '�':
				next = "&#244;";
				break;
			case '�':
				next = "&#245;";
				break;
			case '�':
				next = "&#246;";
				break;
			case '�':
				next = "&#247;";
				break;
			case '�':
				next = "&#248;";
				break;
			case '�':
				next = "&#249;";
				break;
			case '�':
				next = "&#250;";
				break;
			case '�':
				next = "&#251;";
				break;
			case '�':
				next = "&#252;";
				break;
			case '�':
				next = "&#253;";
				break;
			case '�':
				next = "&#254;";
				break;
			case '�':
				next = "&#255;";
				break;
			default:
				next = "" + aString.charAt(i);
		}
		aStringBuilder.append(next);
	}
	return aStringBuilder.toString();
	
}
/**
 * This function looks through a String and changes any
 * characters that can cause problems in HTML and makes them
 * HTML format compliant.
 * @return java.lang.String HTML formatted string
 * @param aString java.lang.String The String to be formatted
 */
public static String formatWithEncoding(String aString) 
{
	StringBuilder aStringBuilder = new StringBuilder("");
	String next = new String();
	int stringLength = aString.length();
	
	for(int i = 0; i <  stringLength; i++)
	{
		switch(aString.charAt(i))
		{
			case '!':
				next = "&#033;";
				break;
			case '"':
				next = "&#034;";
				break;
			case '#':
				next = "&#035;";
				break;
			case '$':
				next = "&#036;";
				break;
			case '%':
			/* Check if encoded space and change to &nbsp if is */
				if ((i + 1) < stringLength)
				{
					if (aString.charAt(i+1) == '2' && aString.charAt(i+2) == '0')
					{
						next = "&nbsp;";
						i++;
						i++;
					}
					else
					{
						next = "&#037;";
					}
				}
				else
				{
					next = "&#037;";
				}
				break;
			case '&':
				next = "&#038;";
				break;
			case '\'':
				next = "&#039;";
				break;
			case '(':
				next = "&#040;";
				break;
			case ')':
				next = "&#041;";
				break;
			case '*':
				next = "&#042;";
				break;
			case '+':
				next = "&#043;";
				break;
			case ',':
				next = "&#044;";
				break;
			case '-':
				next = "&#045;";
				break;
			case '/':
				next = "&#047;";
				break;
			case ':':
				next = "&#058;";
				break;
			case ';':
				next = "&#059;";
				break;
			case '<':
				next = "&#060;";
				break;
			case '=':
				next = "&#061;";
				break;
			case '>':
				next = "&#062;";
				break;
			case '@':
				next = "&#064;";
				break;
			case '[':
				next = "&#091;";
				break;
			case '\\':
			/* Check if newline characters and leave alone if is */
				if (i < stringLength)
				{
					if (aString.charAt(i+1) == 'n')
					{
						next = "\n";
						i++;
					}
				}
				else
				{
					next = "&#092;";
				}	
				break;
			case ']':
				next = "&#093;";
				break;
			case '^':
				next = "&#094;";
				break;
			case '_':
				next = "&#095;";
				break;
			case '`':
				next = "&#096;";
				break;
			case '{':
				next = "&#123;";
				break;
			case '|':
				next = "&#124;";
				break;
			case '}':
				next = "&#125;";
				break;
			case '~':
				next = "&#126;";
				break;
			case '�':
				next = "&#161;";
				break;
			case '�':
				next = "&#162;";
				break;
			case '�':
				next = "&#163;";
				break;
			case '�':
				next = "&#164;";
				break;
			case '�':
				next = "&#165;";
				break;
			case '�':
				next = "&#166;";
				break;
			case '�':
				next = "&#167;";
				break;
			case '�':
				next = "&#168;";
				break;
			case '�':
				next = "&#169;";
				break;
			case '�':
				next = "&#170;";
				break;
			case '�':
				next = "&#171;";
				break;
			case '�':
				next = "&#172;";
				break;
			case '�':
				next = "&#173;";
				break;
			case '�':
				next = "&#174;";
				break;
			case '�':
				next = "&#175;";
				break;
			case '�':
				next = "&#176;";
				break;
			case '�':
				next = "&#177;";
				break;
			case '�':
				next = "&#178;";
				break;
			case '�':
				next = "&#179;";
				break;
			case '�':
				next = "&#180;";
				break;
			case '�':
				next = "&#181;";
				break;
			case '�':
				next = "&#182;";
				break;
			case '�':
				next = "&#183;";
				break;
			case '�':
				next = "&#184;";
				break;
			case '�':
				next = "&#185;";
				break;
			case '�':
				next = "&#186;";
				break;
			case '�':
				next = "&#187;";
				break;
			case '�':
				next = "&#188;";
				break;
			case '�':
				next = "&#189;";
				break;
			case '�':
				next = "&#190;";
				break;
			case '�':
				next = "&#191;";
				break;
			case '�':
				next = "&#192;";
				break;
			case '�':
				next = "&#193;";
				break;
			case '�':
				next = "&#194;";
				break;
			case '�':
				next = "&#195;";
				break;
			case '�':
				next = "&#196;";
				break;
			case '�':
				next = "&#197;";
				break;
			case '�':
				next = "&#198;";
				break;
			case '�':
				next = "&#199;";
				break;
			case '�':
				next = "&#200;";
				break;
			case '�':
				next = "&#201;";
				break;
			case '�':
				next = "&#202;";
				break;
			case '�':
				next = "&#203;";
				break;
			case '�':
				next = "&#204;";
				break;
			case '�':
				next = "&#205;";
				break;
			case '�':
				next = "&#206;";
				break;
			case '�':
				next = "&#207;";
				break;
			case '�':
				next = "&#208;";
				break;
			case '�':
				next = "&#209;";
				break;
			case '�':
				next = "&#210;";
				break;
			case '�':
				next = "&#211;";
				break;
			case '�':
				next = "&#212;";
				break;
			case '�':
				next = "&#213;";
				break;
			case '�':
				next = "&#214;";
				break;
			case '�':
				next = "&#215;";
				break;
			case '�':
				next = "&#216;";
				break;
			case '�':
				next = "&#217;";
				break;
			case '�':
				next = "&#218;";
				break;
			case '�':
				next = "&#219;";
				break;
			case '�':
				next = "&#220;";
				break;
			case '�':
				next = "&#221;";
				break;
			case '�':
				next = "&#222;";
				break;
			case '�':
				next = "&#223;";
				break;
			case '�':
				next = "&#224;";
				break;
			case '�':
				next = "&#225;";
				break;
			case '�':
				next = "&#226;";
				break;
			case '�':
				next = "&#227;";
				break;
			case '�':
				next = "&#228;";
				break;
			case '�':
				next = "&#229;";
				break;
			case '�':
				next = "&#230;";
				break;
			case '�':
				next = "&#231;";
				break;
			case '�':
				next = "&#232;";
				break;
			case '�':
				next = "&#233;";
				break;
			case '�':
				next = "&#234;";
				break;
			case '�':
				next = "&#235;";
				break;
			case '�':
				next = "&#236;";
				break;
			case '�':
				next = "&#237;";
				break;
			case '�':
				next = "&#238;";
				break;
			case '�':
				next = "&#239;";
				break;
			case '�':
				next = "&#240;";
				break;
			case '�':
				next = "&#241;";
				break;
			case '�':
				next = "&#242;";
				break;
			case '�':
				next = "&#243;";
				break;
			case '�':
				next = "&#244;";
				break;
			case '�':
				next = "&#245;";
				break;
			case '�':
				next = "&#246;";
				break;
			case '�':
				next = "&#247;";
				break;
			case '�':
				next = "&#248;";
				break;
			case '�':
				next = "&#249;";
				break;
			case '�':
				next = "&#250;";
				break;
			case '�':
				next = "&#251;";
				break;
			case '�':
				next = "&#252;";
				break;
			case '�':
				next = "&#253;";
				break;
			case '�':
				next = "&#254;";
				break;
			case '�':
				next = "&#255;";
				break;
			default:
				next = "" + aString.charAt(i);
		}
		aStringBuilder.append(next);
	}
	return aStringBuilder.toString();
	
}
}
