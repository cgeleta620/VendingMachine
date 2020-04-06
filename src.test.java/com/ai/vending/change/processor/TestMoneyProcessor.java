package com.ai.vending.change.processor;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class used to test the money processor
 * 
 * @author geletac
 *
 */
public class TestMoneyProcessor {

	MoneyProcessor p;

	@BeforeEach
	public void load() {

		p = new MoneyProcessor();
	}

	/**
	 * Tests bad money input
	 * 
	 * @throws InvalidMoneyException
	 */
	@Test
	public void testMoneyProcessorBadInput() throws InvalidMoneyException {

		assertThrows(InvalidMoneyException.class, () -> p.validateChangeInput(".5 .5 .5 10"));
		assertThrows(InvalidMoneyException.class, () -> p.validateChangeInput(".5 50"));
		assertThrows(InvalidMoneyException.class, () -> p.validateChangeInput(".545"));

	}

	/**
	 * Tests good input for money - Happy path
	 * 
	 * @throws InvalidMoneyException
	 */
	@Test
	public void testMoneyProcessorGoodInput() throws InvalidMoneyException {

		assertDoesNotThrow(() -> p.validateChangeInput(".5 .5 .5"));
		assertDoesNotThrow(() -> p.validateChangeInput("1 .25 .1"));
		assertDoesNotThrow(() -> p.validateChangeInput("1 5 .10"));
		assertDoesNotThrow(() -> p.validateChangeInput(".5 .10 .1"));

	}

	/**
	 * Tests good inut and refund option.
	 * 
	 * @throws InvalidMoneyException
	 */
	@Test
	public void testMoneyProcessorGoodInputAndCheckAmount() throws InvalidMoneyException {

		assertDoesNotThrow(() -> p.validateChangeInput(".5 .5 .5"));
		assertTrue(1.50 == p.getTotal());

		p.refundBalance();
		assertTrue(0.0 == p.getTotal());

		assertDoesNotThrow(() -> p.validateChangeInput("1 .25 .1"));
		assertTrue(1.35 == p.getTotal());

	}

	/**
	 * Tests a happy purchase
	 * 
	 * @throws InvalidMoneyException
	 * @throws NotEnoughMoneyException
	 */
	@Test
	public void testPurchase() throws InvalidMoneyException, NotEnoughMoneyException {

		assertDoesNotThrow(() -> p.validateChangeInput(".5 .5 .5"));
		assertTrue(1.50 == p.getTotal());

		assertDoesNotThrow(() -> p.purchase(1.10));
	}

	/**
	 * Tests a bad purchase
	 * 
	 * @throws InvalidMoneyException
	 * @throws NotEnoughMoneyException
	 */
	@Test
	public void testPurchaseBad() throws InvalidMoneyException, NotEnoughMoneyException {

		assertDoesNotThrow(() -> p.validateChangeInput(".5 .5 .5"));
		assertTrue(1.50 == p.getTotal());
		assertThrows(NotEnoughMoneyException.class, () -> p.purchase(2.00));
	}

}
