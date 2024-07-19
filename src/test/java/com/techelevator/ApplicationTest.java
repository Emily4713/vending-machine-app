package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ApplicationTest {

    @Test
    public void productMadeCorrectlyTest(){
        Application application = new Application();
        application.makeListOfProducts();


        // checking to see that the products are being made correctly
       Assert.assertEquals("A1", application.getProductsList().get(0).getSlotLocation());
       Assert.assertEquals("Potato Crisps", application.getProductsList().get(0).getProductName());
       Assert.assertEquals(3.05, application.getProductsList().get(0).getPrice() , 0);
       Assert.assertEquals("Chip", application.getProductsList().get(0).getType());
       Assert.assertEquals(5, application.getProductsList().get(0).getStock());




    }
}
