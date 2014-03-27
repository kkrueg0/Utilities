/**
 * 
 */
package com.gwccnet.opencsv;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;

import com.gwccnet.utilities.CommonUtils;


/**
 * @author kkrueg0
 * @param <T>
 *
 */
public class AnnotatedBeanHeaderColumnMappingStrategy<T> extends
	BidirectionalHeaderColumnNameMappingStrategy<T> 
{
	public AnnotatedBeanHeaderColumnMappingStrategy(Class<T> cls) throws IntrospectionException
	{
		super(cls);
		
		for (Field field:getType().getDeclaredFields())
		{
			CsvColumnHeader ann = field.getAnnotation(CsvColumnHeader.class);
			if (ann != null)
			{
				String columnName = ((CsvColumnHeader)ann).columnName();
				if(CommonUtils.isNullOrEmpty(columnName))
				{
					columnName = field.getName();
				}
				
				ColumnMapping cm = addColumnMapping(columnName, field.getName());
				cm.setExported(ann.isExported());
				cm.setFormatString(ann.formatString());
				cm.setPosition(ann.position());
			}
			PropertyEditor propEd = field.getAnnotation(PropertyEditor.class);
			if (propEd != null)
			{
				PropertyDescriptor desc = findDescriptor(field.getName());
				desc.setPropertyEditorClass(propEd.editorClass());
			}
			
		}
		
		this.sortColumnMappings();
	}
    
	public static void main(String[] args)
	{
		AnnotatedBeanHeaderColumnMappingStrategy<TestBean> strategy;
		try
		{
			strategy = new 
				AnnotatedBeanHeaderColumnMappingStrategy<TestBean>(TestBean.class);
			File file = new File("N:\\gwcc utilities\\Utilities\\src\\com\\gwccnet\\opencsv\\Book1.csv");
			FileReader fr;

			fr = new FileReader(file);
			//CsvToBean<TestBean> ctb = new CsvToBean<TestBean>();
			//List<TestBean> beans = ctb.parse(strategy,fr);
			
			
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IntrospectionException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
