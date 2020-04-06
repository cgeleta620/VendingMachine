package com.ai.vending.inventory;

/**
 * Custom Exception that is thrown when the Inventory is out of a certain
 * product.
 * 
 * @author geletac
 *
 */
@SuppressWarnings("serial")
public class OutOfProductException extends Exception {

	public OutOfProductException(String errorMessage) {
		super(errorMessage);
	}
}
