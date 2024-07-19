package com.techelevator;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PurchaseScreen extends Application{
    private double money = 0.00;
    Logging logging = new Logging();
    DecimalFormat df = new DecimalFormat("0.00");





    public void printPurchaseMenu(){
        boolean loop = false;
        int number = 0;
        String input;

        Scanner userInput = new Scanner(System.in);







        while (true) {

            printPurchaseScreen();


            do {

                try {
                    input = userInput.nextLine();
                    number = Integer.parseInt(input);
                    if (number > 3 || number < 1) {
                        System.out.println("Invalid try again");
                        loop = true;
                    } else {
                        loop = false;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("That is not a number try again");
                    loop = true;
                }

            } while	(loop);

            if (number == 1) {
                addMoney();

            }

            if (number == 2){
                oneIsChosen();
               System.out.println("Please input a slot");
               /*WE WILL USE A LOOP TO ITERATE THROUGH THE LIST, USING SOMETHING LIKE if(userinput.equals(list.get(i).getslotlocation)) TO
               CHECK IF THE USER INPUT A VALID SLOT LOCATION AND IF THEY DID, IT WILL REMOVE ONE STOCK
                */
                input = userInput.nextLine();
                for (Product s : getProductsList()){
                    if (input.equalsIgnoreCase(s.getSlotLocation())){
                       if (money >= s.getPrice()) {
                           if (s.subtractStock()){
                               money = money - s.getPrice();
                               logging.purchase(money, input);
                               System.out.println(s.getProductName() +" $"+  s.getPrice() + " Balance remaining : $" + df.format(money));
                               System.out.println(s.productSound);
                           }
                       } else {
                           System.out.println("Insufficient funds");
                       }

                    }
                }


            }

            if (number == 3){

                threeIsChosen();
                break;
            }
        }

    }

    @Override
    public void oneIsChosen() {
        super.oneIsChosen();
    }

    public void printPurchaseScreen(){
        System.out.println("Current Money Provided : $" + df.format(money));
        System.out.println("(1) Feed Money");
        System.out.println("(2) Select Product");
        System.out.println("(3) Finish Transaction");
    }



    public double getMoney() {
        return money;
    }

    public void addMoney() {
        Scanner moneyInputted = new Scanner(System.in);
        try {
            System.out.println("Please input a whole dollar amount");
            int input = moneyInputted.nextInt();
            logging.moneyInputted(df,money, input);
            money = money + input;
        } catch (InputMismatchException e){
            System.out.println("That is not a whole dollar amount please try again");
        }
    }

    public void threeIsChosen(){
        final double QUARTER_PRICE = .25;
        final double DIME_PRICE = .10;
        final double NICKEL_PRICE = .05;
        int quarters;
        int dime;
        int nickels;


        /*
        divides money by quarter/dime/nickel , which is then cast to an int (which rounds down)
        to get the WHOLE amount of a certain coin that goes into the money variable,
        then the whole amount is multiplied by either quarter/dime/nickel to get the actual amount e.g. (2 quarters * 0.25 = .50)
        which is then subtracted from the money variable.
        This gives us how many quarters nickels and dimes go into the money variable, which resets the money amount to 0
        AND gives us our change
         */
        logging.change(df,money);
        quarters = (int)(money / QUARTER_PRICE);
        money = money - (quarters * QUARTER_PRICE);
        money = Double.parseDouble(df.format(money));
        dime = (int)(money / DIME_PRICE);
        money = money - (dime * DIME_PRICE);
        money = Double.parseDouble(df.format(money));
        nickels = (int)(money / NICKEL_PRICE);
        money = money - (nickels * NICKEL_PRICE);
        money = Double.parseDouble(df.format(money));
        System.out.println("Your change is : " + quarters + " Quarters, " + dime + " Dimes, " + nickels + " Nickels");
    }

    public void setMoney(double money){
        this.money = money;
    }
}
