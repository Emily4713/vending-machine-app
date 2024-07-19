package com.techelevator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Productstest {
	Product chips = new Product("a1","Chocolate",10.0,"candy");

	@Test
	public void setSoundTest(){
		Assert.assertEquals("Munch Munch, Yum!",chips.productSound);
	}

	@Test
	public void subtractStockTest(){
		chips.setStock(5);
		//returns true when stock is subtracted
		Assert.assertEquals(true,chips.subtractStock());
		// returns false when stock is sold out
		chips.setStock(0);
		Assert.assertEquals(false,chips.subtractStock());

	}


}
