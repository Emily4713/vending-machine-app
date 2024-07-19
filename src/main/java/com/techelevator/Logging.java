package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

public class Logging {
    File log = new File("transactions.log");

    LocalDateTime present = LocalDateTime.now();
    /* I am using the dateTimeFormatter object here so instead of localdatetime printing like 2024-06-07T21:53:10.954523
    it should instead print like 2024-06-07 09:50
     */
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
    String dateTimeFormat = present.format(formatter);

    public void moneyInputted(DecimalFormat format, double money, int input){

        try (PrintWriter writer = new PrintWriter(new FileOutputStream(log,true))){

            writer.println(dateTimeFormat + " FEED MONEY: $" + format.format(money) + " $" + format.format(money + input));


        }catch (FileNotFoundException e){
            System.out.println("file not found");
        }
    }

    /*
    iterates through the list of products, comparing user input to the slot locations of a product
    if they match then it will log the matching products name slot location and price
     */
    public void purchase(double money, String slot) {
        Application application = new Application();
        for (Product s : application.getProductsList()) {
            if (slot.equalsIgnoreCase(s.getSlotLocation())) {
                try (PrintWriter writer = new PrintWriter((new FileOutputStream(log, true)))) {
                    writer.println(dateTimeFormat + " " + "PURCHASE: " + s.getProductName() + " " + s.getSlotLocation() + " $" + s.getPrice() +" $"+ money);
                } catch (FileNotFoundException e) {
                    System.out.println("file not found");
                }
            }


        }
    }

    // logs change given and shows money is reset to 0 after change is given in the log
    public void change(DecimalFormat format, double money) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(log, true))) {
            writer.println(dateTimeFormat + " " + "GIVE CHANGE: $" + format.format(money) + " $" + "0.00");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
