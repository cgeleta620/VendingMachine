package com.ai.vending.inventory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Loader used to import data into the Inventory of the vending machine.
 * 
 * Example file: inventory.csv
 * 
 * Example data: A1,6,Snickers,1.50,1005 A2,7,Twix,2.00,1006
 * B1,2,Sunchips,.85,1007 B2,9,Water,1.00,1008 C1,10,Apple Juice,1.25,1009
 * C2,11,Orange Juice,1.25,1010 D1,12,Grape Juice,1.25,1011 D2,3,Fruit
 * Snacks,.95,1012 E1,10,Cheetos,1.10,1013 E2,0,Butterfinger,1.50,1014
 * 
 * 
 * @author geletac
 */
public class InventoryLoader {

	String filePath = "/Users/geletac/Documents/sandbox/VendingMachine/src/main/resources/inventory.csv";
	private Map<String, Integer> inventoryMap = new HashMap<>();
	private Map<String, Item> itemMap = new HashMap<>();
	static final int MAX_SLOT_COUNT = 15;

	/**
	 * Default Constructor
	 * 
	 * @param filePath
	 */
	public InventoryLoader() {

	}

	/**
	 * Constructor with filePath
	 * 
	 * @param filePath
	 */
	public InventoryLoader(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
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

	/**
	 * Loads in inventory data from a csv file.
	 * 
	 * @throws LoaderException
	 */
	@SuppressWarnings("resource")
	public void loadInventory() throws LoaderException {

		BufferedReader br = null;
		String line = "";
		String delimter = ",";

		try {
			br = new BufferedReader(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			throw new LoaderException("Error with loading inventory.");
		}
		try {
			while ((line = br.readLine()) != null) {

				// Splits each row by commas
				String[] row = line.split(delimter);

				// converts each data point to needed type
				String location = row[0];
				int count = Integer.parseInt(row[1]);

				if (count > MAX_SLOT_COUNT) { // max is 15 according to spec
					System.out.println("Cannot have more than 15 items in a slot.");
					throw new LoaderException("Error with loading inventory.");
				}
				
				String name = row[2];
				double price = Double.parseDouble(row[3]);
				int sku = Integer.parseInt(row[4]);

				// fills maps with data
				inventoryMap.put(location, count);
				Item item = new Item(name, price, sku);
				itemMap.put(location, item);

			}
		} catch (IOException e) {
			throw new LoaderException("Error with loading inventory.");
		} catch (NumberFormatException e1) {
			throw new LoaderException("Error with loading inventory.");
		} catch (NullPointerException e2) {
			throw new LoaderException("Error with loading inventory.");
		}

	}

}
