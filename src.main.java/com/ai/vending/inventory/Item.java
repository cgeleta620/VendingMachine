/**
 * 
 */
package com.ai.vending.inventory;

/**
 * Represents an Item in the Vending Machine
 * 
 * @author geletac
 *
 */
public class Item {

	private String name;
	private double price;
	private int sku;

	/**
	 * Default
	 */
	public Item() {

	}

	/**
	 * Constructor
	 */
	public Item(String name, double price, int sku) {
		this.name = name;
		this.price = price;
		this.sku = sku;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the sku
	 */
	public int getSku() {
		return sku;
	}

	/**
	 * @param sku the sku to set
	 */
	public void setSku(int sku) {
		this.sku = sku;
	}

}
