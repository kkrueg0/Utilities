package com.gwccnet.web.utilities;

/**
 * This is a Factory object that encapsulates the task of creating new instances of FormatRow.
 * Instead of using "new" to instantiate new instances of FormatRow, classes wishing to obtain
 * an instance of FormatRow should do so by using FormatRowFactory.
 * Creation date: (3/11/2002 5:26:51 PM)
 * @author: Blake
 */
public class FormatRowFactory {
	private static FormatRowFactory singleton = new FormatRowFactory();
/**
 * This constructor is private because it is not meant to be used by outside classes.
 * Instead, the class-evel method singleton() method should be used to obtain an
 * instance of FormatRowFactory.
 */
private FormatRowFactory() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (3/11/2002 5:57:27 PM)
 * @return com.gwccnet.WebEnhancement.web.utilities.FormatRow
 * @param columnLengths int[]
 * @param justifications int[]
 * @exception com.gwccnet.web.utilities.FormatRowMalformedDataException The exception description.
 */
public FormatRow getFormatRowInstance(int[] columnLengths,
						int[] justifications) throws FormatRowMalformedDataException {
	return new FormatRow_Impl(columnLengths, justifications);
}
/**
 * Insert the method's description here.
 * Creation date: (3/11/2002 5:35:19 PM)
 * @return com.gwccnet.WebEnhancement.web.utilities.FormatRowFactory
 */
public static FormatRowFactory singleton() {
	return singleton;
}
}
