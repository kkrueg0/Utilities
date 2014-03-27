package com.gwccnet.web.utilities;
/**
 * The purpose of this class is to format a row of text passed in as Vector and
 * insert the appropriate spaces <code>&NBSP;</code> on the left or right based
 * on the column length and the justificaton which are both passed into the
 * constructor. <br>
 * <br>
 * Example: <br>
 * <code>
 * 	private static int[] columns = {10, 5, 5};<br>
 *	private static int[] justifications = {FormatRow.CENTER, FormatRow.RIGHT, FormatRow.LEFT};<br>
 *	try<br>
 *	{<br>
 *		FormatRow aFormatRow = new FormatRow(columns, justifications);<br>
 *		Vector items = new Vector();<br>
 *		items.add("A");<br>
 *		items.add("B");<br>
 *		items.add("C");<br>
 *		String result = aFormatRow.format(items);<br>
 *		System.out.println(result);<br>
 *	} catch (FormatRowMalformedDataException e)<br>
 *	{<br>
 *		e.printStackTrace();<br>
 *	}<br>
 * </code>
 * @author: Justin L. Rosenberg (<a href=mailto:rosenberg@us.ibm.com>)
 */
public interface FormatRow {
	public final static int CENTER = 1;
	public final static int LEFT = 0;
	public final static int RIGHT = 2;
	/**
	 * Center Justifies a String.
	 * If the length of the String is odd the extra space will be put on the right side.
	 * If the string is longer than the maxLength the string will be cut off to fit in the maxLength.
	 * @return java.lang.String The formated String
	 * @param text java.lang.String The String to be formated
	 * @param maxLength int The maximum length of the String.
	 */
	public abstract String centerJustify(String text, int maxLength);
	/**
	 * Formats a Vector of Strings to match the justifications and max lengths of each column passed into the constructor.
	 * @return java.lang.String - The formated String
	 * @param items java.util.Vector - A vector of Strings to be formated
	 * @throws FormatRowMalformedDataException if the vector of Strings does not match the 
	 * lengths of the format parameters passed to the constructor
	 */
	public abstract String format(String[] items) throws FormatRowMalformedDataException;
/**
 * Insert the method's description here.
 * Creation date: (4/1/2002 12:24:04 PM)
 * @return java.lang.String
 * @param aString java.lang.String
 * @param initialLength int
 * @param index int
 */
public abstract String format(String aString, int index) throws FormatRowMalformedDataException;
	/**
	 * Left Justifies a String.
	 * If the string is longer than the maxLength the string will be cut off to fit in the maxLength.
	 * @return java.lang.String The formated String
	 * @param text java.lang.String The String to be formated
	 * @param maxLength int The maximum length of the String.
	 */
	public abstract String leftJustify(String text, int maxLength);
	/**
	 * Center Justifies a String.
	 * If the string is longer than the maxLength, the String will be cut off to fit in the maxLength.
	 * @return java.lang.String The formated String
	 * @param text java.lang.String The String to be formated
	 * @param maxLength int The maximum length of the String.
	 */
	public abstract String rightJustify(String text, int maxLength);

	public abstract int getRowLength();
}
