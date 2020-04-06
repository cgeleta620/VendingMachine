package com.ai.vending.inventory;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Tests the inventory loader.
 * 
 * @author geletac
 *
 */
public class TestInventoryLoader {

	/**
	 * Tests loading bad inventory data. i.e. more than 15 items in a slot.
	 * 
	 * @throws LoaderException
	 */
	@Test
	public void testLoadDataWithMoreThan15ItemsInASlot() throws LoaderException {

		InventoryLoader loader;
		loader = new InventoryLoader("/Users/geletac/Documents/sandbox/VendingMachine/src/main/resources/badInventory.csv");
		assertThrows(LoaderException.class, () -> loader.loadInventory());

	}

	/**
	 * Tests happy path of laoding good data.
	 * 
	 * @throws LoaderException
	 */
	@Test
	public void testLoadHappy() throws LoaderException {

		InventoryLoader loader;
		loader = new InventoryLoader("/Users/geletac/Documents/sandbox/VendingMachine/src/main/resources/inventory.csv");
		assertDoesNotThrow(() -> loader.loadInventory());

	}

	/**
	 * Test needed to ensure Maps are being filled with the data.
	 * 
	 * @throws LoaderException
	 */
	@Test
	public void testLoadMaps() throws LoaderException {

		InventoryLoader loader;
		loader = new InventoryLoader("/Users/geletac/Documents/sandbox/VendingMachine/src/main/resources/inventory.csv");
		assertDoesNotThrow(() -> loader.loadInventory());

		Map<String, Integer> invMap = loader.getInventoryMap();
		Map<String, Item> itemMap = loader.getItemMap();

		assertEquals(6, (int) invMap.get("A1"));
		assertEquals("Snickers", itemMap.get("A1").getName());

	}

}
