package com.gwccnet.web.utilities;

/**
 * Insert the type's description here.
 * Creation date: (3/6/2002 6:23:59 PM)
 * @author: Rosenberg
 */
public class FormatRowTest {
	private static int[] columns = {10, 5, 5};
	private static int[] justifications = {FormatRow.CENTER, FormatRow.RIGHT, FormatRow.LEFT};
/**
 * FormatRowTest constructor comment.
 * @exception com.gwccnet.common.utilities.FormatRowMalformedDataException The exception description.
 */
public FormatRowTest() throws FormatRowMalformedDataException {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (3/6/2002 6:24:07 PM)
 * @param args java.lang.String[]
 */
public static void main(String[] args) 
{
	try
	{
		FormatRow aFormatRow = FormatRowFactory.singleton().getFormatRowInstance(columns, justifications);
		String[] items = {"A&", "B<>",  "C><\"\\"};
		String result = aFormatRow.format(items);
		System.out.println("X" + result + "X");
		result = HTMLFormat.format(aFormatRow.format(items));
		System.out.println("X" + result + "X");

	} catch (FormatRowMalformedDataException e)
	{
		e.printStackTrace();
	}
}
}
