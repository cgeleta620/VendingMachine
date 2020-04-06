package com.ai.vending.inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to store the inventory and prices of each item and slot.
 * 
 * @author geletac
 *
 */
public class Inventory {

	private Map<String, Integer> inventoryMap = new HashMap<>(); // Map of inventory Key: A1 Value:
	private Map<String, Item> itemMap = new HashMap<>(); // Map of Item

	/**
	 * Default Constructor
	 */
	public Inventory() {

	}

	/**
	 * Constructor
	 * 
	 * @param inventoryMap
	 * @param itemMap
	 */
	public Inventory(Map<String, Integer> inventoryMap, Map<String, Item> itemMap) {
		this.inventoryMap = inventoryMap;
		this.itemMap = itemMap;
	}

	/**
	 * 
	 * @param location
	 * @return
	 */
	public String getNameOfItem(String location) {
		String name = itemMap.get(location).getName();
		return name;
	}

	/**
	 * 
	 * @param location
	 * @return
	 */
	public int getSKUOfItem(String location) {
		int sku = itemMap.get(location).getSku();
		return sku;
	}

	/**
	 * 
	 * @param location
	 * @return
	 */
	public double getPriceOfItem(String location) {
		double price = itemMap.get(location).getPrice();
		return price;
	}

	/**
	 * 
	 * @param location
	 * @return
	 */
	public int getInventoryCount(String location) {
		int count = inventoryMap.get(location);
		return count;
	}

	/**
	 * Subtracts one from the count of a product in the inventory based off of
	 * location.
	 * 
	 * @param location
	 * @throws OutOfProductException
	 */
	public void removeOneItemFromInventory(String location) throws OutOfProductException {

		int count = inventoryMap.get(location);
		if (count > 0) {
			count--;
			inventoryMap.put(location, count);
			System.out.println("Dispensing " + getNameOfItem(location));
		} else {
			throw new OutOfProductException("Out of product");
		}
	}

	/**
	 * @return the inventoryMap
	 */
	public Map<String, Integer> getInventoryMap() {
		return inventoryMap;
	}

	/**
	 * @param inventoryMap the inventoryMap to set
	 */
	public void setInventoryMap(Map<String, Integer> inventoryMap) {
		this.inventoryMap = inventoryMap;
	}

	/**
	 * @return the itemMap
	 */
	public Map<String, Item> getItemMap() {
		return itemMap;
	}

	/**
	 * @param itemMap the itemMap to set
	 */
	public void setItemMap(Map<String, Item> itemMap) {
		this.itemMap = itemMap;
	}

}
