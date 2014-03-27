package com.gwccnet.opencsv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

public class CsvTemplateBuilder
{
	public static File buildCsvTemplate(String fileName,Class<?> cls) throws IOException
	{
		File file = new File(fileName);
		FileWriter fw = new FileWriter(file);
		String columnHeaders = "";
		for (Field field:cls.getDeclaredFields())
		{
			CsvColumnHeader ann = field.getAnnotation(CsvColumnHeader.class);
			if (ann != null)
			{
				if (columnHeaders.length() > 0)
				{
					columnHeaders += ",";
				}
				columnHeaders += ann.columnName();
			}
			
		}
		fw.write(columnHeaders);
		fw.flush();
		fw.close();
		return file;
	}
	public static void main(String []args)
	{
		try
		{
			if (args.length < 2)
			{
				throw new IllegalArgumentException("Invalid number of arguments.  Usage: CsvTemplateBuilder <className> <destinationFilePath>");
			}
			Class<?> cls = Class.forName(args[0]);
			CsvTemplateBuilder.buildCsvTemplate(args[1], cls);
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
