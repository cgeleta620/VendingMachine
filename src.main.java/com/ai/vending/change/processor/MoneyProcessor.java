package com.ai.vending.change.processor;

/**
 * This class is used to process and validate the money entered into the vending
 * machine.
 * 
 * @author geletac
 *
 */
public class MoneyProcessor {

	// total money entered. starts at 0.0
	private double total = 0.0;

	/**
	 * @return the totalEntered
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * @param totalEntered the totalEntered to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * Change must be entered as ".25, .50, .10, .05, 1, 5" Validates the input.
	 * When not valid throw an Exception and refunds. When valid total is
	 * calculated.
	 * 
	 * @throws InvalidMoneyException - throws when not valid
	 */
	public void validateChangeInput(String userInput) throws InvalidMoneyException {

		String[] inputDelimited = userInput.split(" ");

		for (int q = 0; q < inputDelimited.length; q++) {
			if (inputDelimited[q].equals(".25") || inputDelimited[q].equals(".5") || inputDelimited[q].equals(".5")
					|| inputDelimited[q].equals(".1") || inputDelimited[q].equals(".10")
					|| inputDelimited[q].equals(".05") || inputDelimited[q].equals("5")
					|| inputDelimited[q].equals("1")) {

				double value = Double.parseDouble(inputDelimited[q]);
				total += value;

			} else {
				System.out.println("Invalid moeny entered. Must be: .05, .10, .5, 1, 5");
				throw new InvalidMoneyException("Invalid money. Refunding all entered.");
			}
		}
		System.out.println("Total Entered: $" + total + "\n");
	}

	/**
	 * Refunds the balance
	 */
	public void refundBalance() {
		System.out.println("Refunding...");
		total = 0.0;
		System.out.println("New Balance: $" + total + "\n");
	}

	/**
	 * Dispenses the change after purchase
	 */
	public void dispenseChange() {

		System.out.println("\nChange: $" + total);
		total = 0.0;
		System.out.println("New Balance: $" + total);

	}

	/**
	 * Used to purchase the item. Price must be cheaper than the total entered.
	 * 
	 * @param price
	 * @throws NotEnoughMoneyException
	 */
	public void purchase(double price) throws NotEnoughMoneyException {

		if (total < price) {
			throw new NotEnoughMoneyException("Not enough money to purchase item");
		} else {
			total = total - price;
		}
	}

}
