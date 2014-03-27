package com.gwccnet.opencsv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Collections;
import java.util.List;

import au.com.bytecode.opencsv.CSVWriter;

import com.gwccnet.utilities.CommonUtils;

public class CsvOperationsFacade {
	public <T> void writeCsv(T bean, Class<T> clazz, String fileName, CsvWriteOptions options) throws CsvOperationException {
		this.writeCsv(Collections.singletonList(bean), clazz, fileName, options);
	}
	
	public <T> void writeCsv(List<T> beans, Class<T> clazz, String fileName, CsvWriteOptions options) throws CsvOperationException {
		try {
			if(!CommonUtils.isNullOrEmpty(beans)) {
				BeanToCsvWriter<T> writer = new BeanToCsvWriter<T>();
				
				writer.setDisallowNullStrings(options.getDisallowNullValues());
				writer.setIncludeHeaderRow(options.getIncludeHeaderRow());
				
				char quoteChar;
				
				if(options.getUseQuotes())
				{
					quoteChar = '\"';
				}
				else
				{
					quoteChar = CSVWriter.NO_QUOTE_CHARACTER;
				}
				
				AnnotatedBeanHeaderColumnMappingStrategy<T> mappingStrat = new AnnotatedBeanHeaderColumnMappingStrategy<T>(clazz);
				
				CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fileName)), ',', quoteChar, System.getProperty("line.separator"));
				
				writer.write(csvWriter, mappingStrat, beans);
			}
		} catch(Exception e) {
			throw new CsvOperationException("Failed to write csv file", e);
		}
	}
}
