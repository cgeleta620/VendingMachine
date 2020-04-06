package com.ai.vending.client;

import java.util.Scanner;

import com.ai.vending.change.processor.InvalidMoneyException;
import com.ai.vending.change.processor.MoneyProcessor;
import com.ai.vending.change.processor.NotEnoughMoneyException;
import com.ai.vending.inventory.Inventory;
import com.ai.vending.inventory.InventoryLoader;
import com.ai.vending.inventory.LoaderException;
import com.ai.vending.inventory.OutOfProductException;

/**
 * Client class used to run the Vending Machine
 * 
 * @author geletac
 */
public class Client {

	private Inventory inventory;
	private MoneyProcessor p;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Client client = new Client();
		client.runVendingMachine(args);

	}

	/**
	 * Runs the vending machine
	 */
	public void runVendingMachine(String[] args) {

		InventoryLoader loader = new InventoryLoader();

		try {
			loader.loadInventory();
		} catch (LoaderException e) {
			System.out.println("Inventory error. Please call 1-800-001-HELP to report issue.");
			return;
		}

		// loads in the inventory using the InvetoryLoader
		inventory = new Inventory(loader.getInventoryMap(), loader.getItemMap());
		p = new MoneyProcessor();

		// gets user input
		Scanner scan = new Scanner(System.in);

		while (true) {
			printMenu();
			String line = scan.nextLine();
			if (line.equalsIgnoreCase("q")) { // quits if q or Q
				refund();
				break;
			} else if (line.equals("1")) { // select item and buy
				selectAndPurchaseItem(scan);
			} else if (line.equals("2")) { // price check
				System.out.println("Columns A - E. Rows 1 - 2. Ex: A1");
				System.out.println("Enter location: ");
				String location = scan.nextLine();
				checkPrice(location);
			} else {
				System.out.println("Please enter a command from the menu.");
			}

		}
	}

	/**
	 * Selects the item from inventory and purchases. Input: Scanner for input.
	 * 
	 * @param scan
	 */
	public void selectAndPurchaseItem(Scanner scan) {

		System.out.println("Format: .1, .25, .5, 1, 5. Example: .5 .25 1");
		System.out.println("Enter Money (q to exit): ");
		String money = scan.nextLine();

		if (money.equalsIgnoreCase("q")) { // exits when "q or Q" is entered
			return;
		}

		try {
			p.validateChangeInput(money); // validated input through the MoneyProcessor
		} catch (InvalidMoneyException e) { // thrown when the user enters anything that isn't ".1, .25, .5, 1, 5"
			refund();
			return;
		}

		System.out.println("Columns A - E. Rows 1 - 2. Ex: A1");
		System.out.println("Enter location: ");

		double price = 0.0;
		String location = "";

		try {
			location = scan.nextLine(); // get the specific item requested from user
			price = inventory.getPriceOfItem(location); // checks price of item from inventory
		} catch (NullPointerException e1) { // if the user enters an invalid location
			System.out.println("Invalid Item. Columns A - E. Rows 1 - 2. Ex: A1");
			refund();
			return;
		}

		try {
			p.purchase(price); // purchases the item through the money processor
		} catch (NotEnoughMoneyException e) { // thrown when the user doesn't have enough
			System.out.println("Not enough money to purchase item.");
			refund();
			return;
		}

		try {
			inventory.removeOneItemFromInventory(location); // if the user has enough money and bought the item one item
															// is subtracted from the inventory.
		} catch (OutOfProductException e) {
			System.out.println("Out of stock. Please select something else.");
			refund();
			return;
		}

		dispenseChange(); // returns the change to the user.
		return;
	}

	/**
	 * Dispenses the change after purchase
	 */
	public void dispenseChange() {
		p.dispenseChange();
	}

	/**
	 * Refunds the item
	 */
	public void refund() {
		p.refundBalance();
	}

	/**
	 * Prints a price of an item in the inventory. Input example: "A1"
	 * 
	 * @param location
	 */
	public void checkPrice(String location) {
		double price = 0.0;
		String name = "";
		try {
			price = inventory.getPriceOfItem(location);
			name = inventory.getNameOfItem(location);
		} catch (NullPointerException e) { // thrown in invalid item example "D6"
			System.out.println("Invalid Item. Columns A - E. Rows 1 - 2. Ex: A1");
			return;
		}
		System.out.println("\nPrice: $" + price);
		System.out.println("Name: " + name + "\n");

	}

	/**
	 * Prints the main menu to console.
	 */
	public static void printMenu() {

		System.out.println("\nMenu:");
		System.out.println("Enter 1 to select an item (ex: A1).");
		System.out.println("Enter 2 to price check an item (ex: C2).");
		System.out.println("Enter q to end program at anytime (includes refund).\n");

	}

}
