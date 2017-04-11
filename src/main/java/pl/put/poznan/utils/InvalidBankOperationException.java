package pl.put.poznan.utils;

public class InvalidBankOperationException extends Exception {
	public InvalidBankOperationException(String msg) {
		super(msg);
	}
}
