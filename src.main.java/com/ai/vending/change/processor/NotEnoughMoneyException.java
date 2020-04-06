package com.ai.vending.change.processor;

/**
 * Custom Exception that is thrown when the user tries to purchase an item
 * without enough money entered in the machine.
 * 
 * @author geletac
 *
 */
@SuppressWarnings("serial")
public class NotEnoughMoneyException extends Exception {

	public NotEnoughMoneyException(String errorMessage) {
		super(errorMessage);
	}

}
