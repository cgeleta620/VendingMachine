package com.ai.vending.inventory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class TestItem {

	@Test
	public void testCreateItem() throws OutOfProductException {

		Item item = new Item("Twix", 1.50, 1008);

		assertEquals("Twix", item.getName());
		assertEquals(1008, item.getSku());
		assertTrue(item.getPrice() == 1.50);

	}

}
