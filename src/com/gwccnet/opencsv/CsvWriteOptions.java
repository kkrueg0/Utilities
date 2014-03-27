package com.gwccnet.opencsv;

public class CsvWriteOptions {
	private boolean useQuotes = false;
	private boolean includeHeaderRow = true;
	private boolean disallowNullValues = true;
	
	public CsvWriteOptions() {
	}

	public boolean getUseQuotes() {
		return useQuotes;
	}

	public void setUseQuotes(boolean useQuotes) {
		this.useQuotes = useQuotes;
	}

	public boolean getIncludeHeaderRow() {
		return includeHeaderRow;
	}

	public void setIncludeHeaderRow(boolean includeHeaderRow) {
		this.includeHeaderRow = includeHeaderRow;
	}

	public boolean getDisallowNullValues() {
		return disallowNullValues;
	}

	public void setDisallowNullValues(boolean disallowNullValues) {
		this.disallowNullValues = disallowNullValues;
	}
}
