/**
 * 
 */
package com.gwccnet.opencsv;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author kkrueg0
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CsvColumnHeader 
{
	String columnName() default "";
	boolean isExported() default true;
	String formatString() default "";
	int position() default 0;
}
