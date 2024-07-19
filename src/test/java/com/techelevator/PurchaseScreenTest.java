package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;

public class PurchaseScreenTest {

    @Test
    public void getChangeTest(){
        PurchaseScreen screen = new PurchaseScreen();
        screen.setMoney(1);

        // testing to make sure that the balance is reset to 0 after change is given
        screen.threeIsChosen();
        Assert.assertEquals(0,screen.getMoney(),0);
    }


}
