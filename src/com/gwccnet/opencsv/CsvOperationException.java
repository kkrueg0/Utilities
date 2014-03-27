package com.gwccnet.opencsv;

public class CsvOperationException extends Exception {
	private static final long serialVersionUID = 6355545754122078808L;

	public CsvOperationException() {
		super();
	}

	public CsvOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public CsvOperationException(String message) {
		super(message);
	}

	public CsvOperationException(Throwable cause) {
		super(cause);
	}
}
