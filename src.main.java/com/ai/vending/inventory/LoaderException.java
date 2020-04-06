package com.ai.vending.inventory;

/**
 * Custom Exception that is throw when the Loader fails
 * 
 * @author geletac
 *
 */
@SuppressWarnings("serial")
public class LoaderException extends Exception {

	public LoaderException(String errorMessage) {
		super(errorMessage);
	}
}
