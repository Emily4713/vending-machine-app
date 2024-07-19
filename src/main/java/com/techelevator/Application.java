package com.techelevator;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
	private static List<Product> productsList = new ArrayList<>();

	public static void main(String[] args) {
		Application application = new Application();
		PurchaseScreen purchaseScreen = new PurchaseScreen();

		int number = 0;
		Scanner mainMenuUserInput = new Scanner(System.in);
		String userInput;
		boolean loop = false;

		application.makeListOfProducts();



		while(true) {
			System.out.println();
			System.out.println("(1) Display Vending Machine Items");
			System.out.println("(2) Purchase");
			System.out.println("(3) Exit");

			/*
		The loop boolean is first set to false, meaning this loop will run only once if the user inputs
		a correct number, if they input a number that is not in range, or a word, then the loop boolean is set to true
		which will keep the loop going until both numberformatexception and the " if (number > 4 || number < 1) " are both false or " not caught "
		once a valid input is put in, loop will be set to false in the else statement, breaking out of the loop
		 */
			do {

				try {
					userInput = mainMenuUserInput.nextLine();
					number = Integer.parseInt(userInput);
					if (number > 4 || number < 1) {
						System.out.println("Invalid try again");
						loop = true;
					} else {
						loop = false;
					}
				} catch (NumberFormatException e) {
					System.out.println("That is not a number try again");
					loop = true;
				}

			} while (loop);


			if (number == 1) {
				application.oneIsChosen();
			}
			if (number == 2) {
				purchaseScreen.printPurchaseMenu();
			}
			if (number == 3){
				System.out.println("Thank you for shopping, Goodbye!");
				break;
			}
			if (number == 4){
				application.salesReport();

			}

		}



	}


	/*
	Here we are reading through a file and printing each line out, since the file is the same "size" as the list, we increment i by 1 each time
	the loop runs, which will update where we are in the list as well. Which lets us accurately get the stock for each product
	 */
	public void oneIsChosen(){

			File inventory = new File("vendingmachine.csv");
			int i = 0;
			try(Scanner inventoryFile = new Scanner(inventory)){
				while (inventoryFile.hasNextLine()){

					String inventoryString = inventoryFile.nextLine();
					if (productsList.get(i).getStock() == 0){
						System.out.println(inventoryString + "|" + "Sold Out");
					} else {
						System.out.println(inventoryString + "|" + productsList.get(i).getStock());
					}
					i++;
				}
			}catch (FileNotFoundException e){
				System.out.println("file not found");

			}



	}









	/*
	Here we are automating making a list of products, we go through the file line by line and split it into an array,
	we know what each index will hold and how many indexes there will be for each line so we put those indexes into the constructor to
	automate making a new product object. Then we add the new product to the list
	 */
	public void makeListOfProducts(){

			File inventory = new File("vendingmachine.csv");
			try(Scanner inventoryFile = new Scanner(inventory)){
				while (inventoryFile.hasNextLine()){

					String inventoryString = inventoryFile.nextLine();
					String[] array = inventoryString.split("\\|");

					Product product = new Product(array[0],array[1], Double.parseDouble(array[2]),array[3]);
					productsList.add(product);

				}
			}catch (FileNotFoundException e){

			}



	}

	public void salesReport(){

		/* we are converting localdatetime to a string so that everytime this method is run it makes a new file with the date and time
		of being created
		 */
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm-ss a-");
		DecimalFormat format = new DecimalFormat("0.00");

		String now = time.format(formatter);
		String finalName = now + "SalesReport.log";

		File salesReport = new File(finalName);

		/*
		we are iterating through the product list and checking how much of everything was sold by
		subtracting 5 by the current stock of a product, to get the amount sold
		then we multiply the amount sold by the price and add that on to total amount
		 */
		try	(PrintWriter writer = new PrintWriter(salesReport)) {
			double totalAmount = 0;
			for (Product s : productsList){
				int startingStock = 5;
				writer.println(s.getProductName() + " | " + (startingStock - s.getStock()));
				startingStock -= s.getStock();
				totalAmount = totalAmount + (startingStock * s.getPrice());



			}
			writer.println("***TOTAL SALES*** $" + format.format(totalAmount));

        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }

    }

	public List<Product> getProductsList() {
		return productsList;
	}
}
