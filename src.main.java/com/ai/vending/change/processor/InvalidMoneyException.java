package com.ai.vending.change.processor;

/**
 * Custom Exception that is thrown when a used enter money that is not ".1, .25,
 * .5, 1, 5"
 * 
 * @author geletac
 *
 */
@SuppressWarnings("serial")
public class InvalidMoneyException extends Exception {

	public InvalidMoneyException(String errorMessage) {
		super(errorMessage);
	}

}
