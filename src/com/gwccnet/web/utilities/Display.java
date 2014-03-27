package com.gwccnet.web.utilities;

import java.text.Format;
/**
 * Display is a utility class for displaying data in a Select Box friendly format
 * @author: Justin Rosenberg (Rosenberg@us.ibm.com)
 */
public class Display {
/**
 * Display constructor comment.
 */
public Display() {
	super();
}
/**
 * Takes the String and treats it as if it were the only item in the Format Row.
 * It returns the string with the appropriate spacing basedin the format Row in an
 * HTML safe format.
 * @return java.lang.String 
 * @param aString String - The string to be formated for the row.
 * @throws FormatRowMalformedDataException - If there is problems formatting the string with the FormatRow class
 */

public static String displayEmptyMesssage(String aString, FormatRow aFormatRow) throws FormatRowMalformedDataException
{
	return HTMLFormat.format(aFormatRow.leftJustify(aString, aFormatRow.getRowLength()));
}
/**
 * Formats an array of strings to be displayed with appropriate spacing in an HTML safe format
 * and adds the pre and post HTML code in front and behind the header strings respectivly.
 * @return java.lang.String - The formated HTML String
 * @param Names java.lang.String[] - The names of the headers.
 * @param PreHTML java.lang.String[] - The HTML code to be inserted before each of the respective headers.
 * @param PostHTML java.lang.String[] - The HTML code to be inserted after each of the respective headers.
 * @param aFormatRow com.gwccnet.WebEnhancement.web.utilities.FormatRow - FormatRow class used to format the row.
 */
public static String displayHeader(String[] Names, String[] PreHTML, String[] PostHTML, FormatRow aFormatRow) throws FormatRowMalformedDataException {
	String aString;
	String returnString = "";
	
	for(int i = 0; i < Names.length; i++)
	{
		aString = aFormatRow.format(Names[i], i); //Justifies adds apropriate spaces
		aString = HTMLFormat.format(getFrontSpaces(aString)) +
				  PreHTML[i] +
				  HTMLFormat.format(aString.trim()) +
				  PostHTML[i] +
				  HTMLFormat.format(getEndSpaces(aString));
		returnString += aString + "&nbsp;";
	}

	return returnString;	
}
/**
 * Formats an array of objects with the respective array of formats and formats it to the array of
 * FormatRows.
 * @return java.lang.String - The HTML safe formated row that can properly be displayed in a ListBox.
 * @param values java.lang.Object[] - The values to be displayed
 * @param formats com.gwccnet.WebEnhancement.web.utilities.FormatToString[] - The Format object to format the Object values into a String value.
 * @param aFormatRow com.gwccnet.WebEnhancement.web.utilities.FormatRow - FormatRow class used to format the row.
 */
public static String displayRow(Object[] values, Format[] formats, FormatRow aFormatRow) throws FormatRowMalformedDataException{
	return HTMLFormat.format(displayRowAsPlainText(values, formats, aFormatRow));
}
/**
 * Formats an array of objects with the respective array of formats and formats it to the array of
 * FormatRows.
 * @return java.lang.String - The HTML safe formated row that can properly be displayed in a ListBox.
 * @param values java.lang.Object[] - The values to be displayed
 * @param formats com.gwccnet.WebEnhancement.web.utilities.FormatToString[] - The Format object to format the Object values into a String value.
 * @param aFormatRow com.gwccnet.WebEnhancement.web.utilities.FormatRow - FormatRow class used to format the row.
 */
public static String displayRowAsPlainText(Object[] values, Format[] formats, FormatRow aFormatRow) throws FormatRowMalformedDataException{
	
	String[] formattedStrings = new String[values.length];

	try {
		for(int i = 0; i < values.length; i++)
		{
			if(values[i] == null)
			{
				formattedStrings[i] = "";
			}
			else if(formats[i] == null) //No formatting required
			{
				formattedStrings[i] = values[i].toString();
			}
			else //Needs to be formatted
			{
				formattedStrings[i] = formats[i].format(values[i]);
			}
		}
	} catch (ArrayIndexOutOfBoundsException e) {
		throw new FormatRowMalformedDataException(e.getMessage());
	}
	return aFormatRow.format(formattedStrings);
}
/**
 * 	Returns all the spaces at the end of a string.
 */
private static String getEndSpaces(String aString) {
	StringBuilder returnString = new StringBuilder("");
	if(aString != null && aString.trim().length() != 0)
	{
		int i = aString.length() - 1;
		while(i >= 0 && aString.charAt(i) == ' ')
		{
			returnString.append(" ");
			i--;
		}
	}
	return returnString.toString();
}

/**
 * 	Returns all the spaces in front of a string.
 */
private static String getFrontSpaces(String aString) {
	StringBuilder returnString = new StringBuilder("");
	if(aString != null)
	{
		int i = 0;
		while(i < aString.length() && aString.charAt(i) == ' ')
		{
			returnString.append(" ");
			i++;
		}
	}
	return returnString.toString();
}
}
