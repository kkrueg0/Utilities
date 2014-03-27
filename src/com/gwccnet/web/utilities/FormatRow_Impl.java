package com.gwccnet.web.utilities;

/**
 * The purpose of this class is to format a row of text passed in as Vector and
 * insert the appropriate spaces ' ' on the left or right based
 * on the column length and the justificaton which are both passed into the
 * constructor. <br>
 * <br>
 * Example: <br>
 * <code>
 * 	int[] columns = {10, 5, 5};<br>
 *	int[] justifications = {FormatRow.CENTER, FormatRow.RIGHT, FormatRow.LEFT};<br>
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
 * If you want to have a HTML safe version ie. &NBSP instead of spaces and
 * &AMP instead of & use the formatToHTML(Vector) method.
 * @author: Justin L. Rosenberg (<a href=mailto:rosenberg@us.ibm.com>)
 */
public class FormatRow_Impl implements FormatRow{
	private int columnLengths[];
	private int justifications[];
	private int rowLength;
	private int numberOfColumns;

/**
 * Default constructor is private to prevent default initialization by outside classes
 */
private FormatRow_Impl() {}
	/**
	 * Constructor for Format Row
	 * @param ColumnLenghts int[] The an array of maximun lengths for each column
	 * @param Justifications int[] Justificaitons for each column <code> FormatRow.CENTER, 
	 * FormatRow.LEFT, FormatRow.RIGHT </code> are the options.
	 * @throws FormatRowMalformedDataException if the length of the columnLenghts does not match the justifications.
	 */
	protected FormatRow_Impl(int[] columnLengths,
					 int[] justifications) throws FormatRowMalformedDataException
	{
		// Check to make sure there is data and that they are equal.
		if(columnLengths == null || justifications == null ||
			columnLengths.length != justifications.length)
		{
			throw new FormatRowMalformedDataException("The number of column lengths does not equal the number of justifications");
		}
		this.setNumberOfColumns(columnLengths.length);
		this.setColumnLengths(columnLengths);	
		this.setJustifications(justifications);

		int rowLength = 0;
		for(int i = 0; i < columnLengths.length; i++)
		{
			rowLength += columnLengths[i] + 1;
		}
		this.setRowLength(rowLength - 1);
	}
	/**
	 * Center Justifies a String.
	 * If the length of the String is odd the extra space will be put on the right side.
	 * If the string is longer than the maxLength the string will be cut off to fit in the maxLength.
	 * @return java.lang.String The formated String
	 * @param text java.lang.String The String to be formated
	 * @param maxLength int The maximum length of the String.
	 */
	public String centerJustify(String text, int maxLength) {
	
		if(text.length() > maxLength)
		{
			text = text.substring(0, maxLength);
		}

		int textLength = text.length();
		
		if((maxLength - textLength)%2 == 1 && textLength != maxLength) //it is an odd value and spaces still need to be added.
		{
			text += " ";
			maxLength--;
		}
	
		int spaces = (maxLength - textLength)/2;

		for(int i = 0; i < spaces; i++)
		{
		text = " "+ text + " ";
		}
	
		return text;
	}
	/**
	 * Formats a Vector of Strings to match the justifications and max lengths of each column passed into the constructor.
	 * @return java.lang.String - The formated String
	 * @param items java.util.Vector - A vector of Strings to be formated
	 * @throws FormatRowMalformedDataException if the vector of Strings does not match the 
	 * lengths of the format parameters passed to the constructor
	 */
	public String format(String[] items) throws FormatRowMalformedDataException 
	{
		String item;
		String returnString = new String();
		
		if(items == null || items.length != this.numberOfColumns)
		{
			throw new FormatRowMalformedDataException("The length of the items passed does not match the length of parameters passed for the row length");
		}
	
		for(int i = 0; i < numberOfColumns; i++)
		{
			/** For Debug purposes *
			System.out.println("columnLenghts[i]=" + columnLengths[i]);
			System.out.println("justifications[i]=" + justifications[i]);
			/*/

			//Add a space between each item in the row.
			if(i != 0) 
			{
				returnString += " ";
			}
			
			item = items[i];
			if(item == null)
			{
				item = "";
			}
	
			switch(this.justifications[i])
			{
				case LEFT:
					returnString += this.leftJustify(item, this.columnLengths[i]);
					break;
				case CENTER:
					returnString += this.centerJustify(item, this.columnLengths[i]);
					break;
				case RIGHT:
					returnString += this.rightJustify(item, this.columnLengths[i]);
					break;
				default:
					throw new FormatRowMalformedDataException("Unknown justification");
			}
		}		
		return returnString;
	}
/**
 * Insert the method's description here.
 * Creation date: (4/1/2002 12:24:04 PM)
 * @return java.lang.String
 * @param aString java.lang.String
 * @param initialLength int
 * @param index int
 */
public String format(String aString, int index) throws FormatRowMalformedDataException
{
	if(index < 0 || index >= justifications.length)
	{
		throw new FormatRowMalformedDataException("Index " + index + " outside the range of the number of columns for this row");
	}
	String returnString;
	switch(this.justifications[index])
	{
		case LEFT:
			returnString = this.leftJustify(aString, this.columnLengths[index]);
			break;
		case CENTER:
			returnString = this.centerJustify(aString, this.columnLengths[index]);
			break;
		case RIGHT:
			returnString = this.rightJustify(aString, this.columnLengths[index]);
			break;
		default:
			throw new FormatRowMalformedDataException("Unknown justification");
	}
	return returnString;

}
	/**
	 * Gets the number of characters wide the row is.
	 * @param rowLength int Row Length
	 */
	public int getRowLength() 
	{
		return this.rowLength;
	}
	/**
	 * Left Justifies a String.
	 * If the string is longer than the maxLength the string will be cut off to fit in the maxLength.
	 * @return java.lang.String The formated String
	 * @param text java.lang.String The String to be formated
	 * @param maxLength int The maximum length of the String.
	 */
	public String leftJustify(String text, int maxLength) {
	
		if(text.length() > maxLength)
		{
			text = text.substring(0, maxLength);
		}
		
		int textLength = text.length(); 
		for(int i = 0; i < (maxLength - textLength); i++)
		{
			text += " ";
		}
		return text;
	}
	/**
	 * Center Justifies a String.
	 * If the string is longer than the maxLength, the String will be cut off to fit in the maxLength.
	 * @return java.lang.String The formated String
	 * @param text java.lang.String The String to be formated
	 * @param maxLength int The maximum length of the String.
	 */
	public String rightJustify(String text, int maxLength) {
	
		if(text.length() > maxLength)
		{
			text = text.substring(0, maxLength);
		}
		
	    String returnString = new String();
		for(int i = 0; i < (maxLength - text.length()); i++)
		{
			returnString += " ";
		}
		returnString += text;
		
		return returnString;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (3/6/2002 5:42:11 PM)
	 * @param columnLenghts int[]
	 */
	private void setColumnLengths(int[] columnLengths) 
	{
		this.columnLengths = new int[this.numberOfColumns];
		System.arraycopy(columnLengths, 0, this.columnLengths, 0, this.numberOfColumns);
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (3/6/2002 5:43:14 PM)
	 * @param justifications int[]
	 */
	private void setJustifications(int[] justifications)
	{
		this.justifications = new int[this.numberOfColumns];
		System.arraycopy(justifications, 0, this.justifications, 0, this.numberOfColumns);	
	}
/**
 * Insert the method's description here.
 * Creation date: (4/3/2002 2:11:10 PM)
 */
private void setNumberOfColumns(int numberOfColumns) 
{	
	this.numberOfColumns = numberOfColumns;	
}
/**
 * 
 */
private void setRowLength(int aRowLength) 
{	
	this.rowLength = aRowLength;	
}
}
