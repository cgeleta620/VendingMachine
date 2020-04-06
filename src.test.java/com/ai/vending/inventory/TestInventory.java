package com.ai.vending.inventory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestInventory {

	InventoryLoader loader;

	@BeforeEach
	public void load() throws LoaderException {

		loader = new InventoryLoader();
		assertDoesNotThrow(() -> loader.loadInventory());

	}

	/**
	 * Tests removing an item from the inventory.
	 * 
	 * @throws OutOfProductException
	 */
	@Test
	public void testRemoveOneItemFromInventory() throws OutOfProductException {

		Inventory inv = new Inventory(loader.getInventoryMap(), loader.getItemMap());

		assertEquals(6, inv.getInventoryCount("A1"));
		inv.removeOneItemFromInventory("A1");
		assertEquals(5, inv.getInventoryCount("A1"));

		assertEquals(9, inv.getInventoryCount("B2"));
		inv.removeOneItemFromInventory("B2");
		assertEquals(8, inv.getInventoryCount("B2"));

	}

	/**
	 * Tests all the details helper methods.
	 * 
	 * @throws OutOfProductException
	 */
	@Test
	public void testCheckDetailsWhenInInventory() throws OutOfProductException {

		Inventory inv = new Inventory(loader.getInventoryMap(), loader.getItemMap());

		assertEquals(6, inv.getInventoryCount("A1"));
		assertEquals("Water", inv.getNameOfItem("B2"));
		assertTrue(inv.getPriceOfItem("E1") == 1.10);
		assertEquals(1009, inv.getSKUOfItem("C1"));

	}

	/**
	 * Test needed to check when things aren't in inventory.
	 * 
	 * @throws OutOfProductException
	 */
	@Test
	public void testCheckDetailsWhenNotInInventory() throws OutOfProductException {

		Inventory inv = new Inventory(loader.getInventoryMap(), loader.getItemMap());

		assertThrows(NullPointerException.class, () -> inv.getInventoryCount("B3"));
		assertThrows(NullPointerException.class, () -> inv.getNameOfItem("F9"));
		assertThrows(NullPointerException.class, () -> inv.getPriceOfItem("G8"));
		assertThrows(NullPointerException.class, () -> inv.getSKUOfItem("K11"));

	}

	/**
	 * Test needed to see what happens when the inventory is empty.
	 * 
	 * @throws OutOfProductException
	 */
	@Test
	public void testRemoveOneItemFromInventoryWithZeroCount() throws OutOfProductException {

		Inventory inv = new Inventory(loader.getInventoryMap(), loader.getItemMap());

		assertEquals(0, inv.getInventoryCount("E2"));
		assertThrows(OutOfProductException.class, () -> inv.removeOneItemFromInventory("E2"));
		assertEquals(0, inv.getInventoryCount("E2"));

	}

}
