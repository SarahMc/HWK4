/*
 * Name: Sarah McIntosh, Melissa Lam, Kevin Mano
 * MacID: mcintosc, lamma, manok2
 * Student Number: 1414333, 1404421, 1421518
 * Description: Displays all user interface pages, and takes user input to navigate
 * pages, add items to a shopping cart, and purchase those items.
 */

import java.util.Scanner;	// Required for use of Scanner class

public class UserInterface {
	private String[][] readables;
	private String[][] audioProducts;
	private int currentPage;
	private int conID;
	private String username;
	ShoppingCart cart;
	
	public void getReadables() {
		Readable readable = new Readable();
		readables = readable.assignReadables();
	}
	public void getAudioProducts(){
		Audio audio = new Audio();
		audioProducts = audio.assignAudio();
	}
	
	// Prints a chart containing readables information
	public void showReadables(){ ////////// Reading from readables array. Elements added when called upon when currentPage == 8 
		////////// DONT MESS WITH MY FORMATTING (PLEASE)
		System.out.println("Readables:\t\t\t\t\t\t\t\tP8\n");
		System.out.printf("%-5s %10s %16s %10s %10s %7s %n","S.No", "Name of the Book", "Author", "Price($)", "Quantity in Store", "Type");
		
		for (int i=0; i<readables.length;i++){
			if (readables[i][0] != null){
				for (int j=0; j<6;j++){
					System.out.print(" " + readables[i][j]+ "   ");
					if (j == 1 && readables[i][j].length() > 14){
						System.out.print("\t");
					}
					else if (j == 1){
						System.out.print("\t\t");
					}
					if (j == 3){
						System.out.print("\t");
					}
					if (j == 4){
						System.out.print("\t\t");
					}	
				}
			}
			else
			{
				break;
			}
			System.out.println();
		}
	}
	
	public void showAudioProducts(){ ////////// Same as showReadables
		System.out.println("Audio:\t\t\t\t\t\t\t\t\tP9\n");
		System.out.printf("%-5s %5s %28s %10s %10s %7s %n","S.No", "Name", "Artist", "Price($)", "Quantity in Store", "Type");
		
		for (int i=0; i<audioProducts.length;i++){
			if (audioProducts[i][0] != null){
				
				for (int j=0; j<6;j++){
					
					if (j == 0 && (Integer.parseInt(audioProducts[i][j]) < 10)){
						System.out.print(" ");
					}
					System.out.print(audioProducts[i][j]+ "   ");
					if (j == 1 && audioProducts[i][j].length()> 20){
						System.out.print(" ");
					}
					else if (j == 1 && audioProducts[i][j].length() > 14){
						System.out.print("\t  ");
					}
					else if (j == 1){
						System.out.print("\t\t\t  ");
					}
					if (j == 2){
						System.out.print("    ");
					}
					if (j == 3){
						System.out.print("\t");
					}
					if (j == 4){
						System.out.print("\t\t  ");
					}	
				}
				System.out.println();
			}
			else
			{
				break;
			}
		}
	}
	
	public void showCart() {
		System.out.println("Billing Information:\t\t\t\t\t\t\tP10\n");
		System.out.println("  Name\t\t\tQuantity\tPrice\n");
		String[][] cart_content = cart.getContent();
		// Print out the name, quantity, and price of cart items in formatted rows
		for (int i=0; i<cart_content.length;i++){
			if (cart_content[i][0] != null){
				System.out.print(cart_content[i][1]);
				if 	(cart_content[i][1].length()>20) System.out.print("\t");
				else if (cart_content[i][1].length()>14) System.out.print("\t\t");
				else System.out.print("\t\t\t");
				System.out.print(cart_content[i][3]+"\t");
				for (int k = 0; k<readables.length;k++){
					if (readables[k][1]==cart_content[i][1]){
						System.out.println(readables[k][3]);
					}
				}
				for (int l = 0; l<audioProducts.length;l++){
					if (audioProducts[l][1]==cart_content[i][1]){
						System.out.println(audioProducts[l][3]);
					}
				}
			}
			else
			{
				break;
			}
		}
		int envtax = 0;
		int hst;
		int shipping = 0;
		int pricet = 0;
		double hst1 = 0;
		for (int i=0;i<cart_content.length;i++) {
			int gprice = 0;
			int price = 0;
			int cart_sNo = Integer.parseInt(cart_content[i][0]);
			if (cart_sNo == 1) {
				gprice = Readable.getPrice(3);
				price = 150;			
			}
			else if (cart_sNo == 2) {
				gprice = Readable.getPrice(5);
				price = 200;			
			}
			else if (cart_sNo>2&&cart_sNo<7) {
				gprice = Readable.getPrice(cart_sNo);
				price = Integer.parseInt(readables[cart_sNo-3][3]);
			}
			else if (cart_sNo>6) {
				gprice = Audio.getPrice(cart_sNo);
				price = Integer.parseInt(audioProducts[cart_sNo-7][3]);
			}
			int quantity = Integer.parseInt(cart_content[i][3]);
			
			envtax += (gprice-price)*quantity;
			hst1 += price*0.13*quantity;
			if ((gprice-price)!=0) shipping += price*0.1*quantity; 
			pricet += price*quantity;
		}
		hst = (int)hst1;
		
		System.out.println("\nEnvironment Tax\t2%\t\t"+envtax);
		System.out.println("  HST\t\t\t13%\t\t"+hst);
		System.out.println("\n Shipping\t\t\t10%\t\t"+shipping);
		int total = envtax + shipping + hst + pricet;
		System.out.println("\t\t\t\t\t\t__________\n  Total:\t\t\t\t\t"+total+"$");
	}
	
	// Sets initial conditions of class UserInterface
	public UserInterface() {
		// The first page is P1
		currentPage = 1;
		// The first confirmation ID will be U1000. The integer part of this is 1000.
		conID = 1000;
		getReadables();
		getAudioProducts();
	}
	
	// isInt returns true if a String str may be converted to int
	public boolean isInt(String str) {
		// Returns true if str contains an int
		try {
			// Tries the parseInt function on str
			Integer.parseInt(str);
			return true;
		}
		// Returns false if an exception is thrown - str can't be an int.
		catch(Exception x) {
			return false;
		}
	}
	
	// Changes the current page depending on user input, and calls the content of that page
	public void changeCurrentPage(){
		Scanner option = new Scanner(System.in);
		String choice;// = option.next();
		if (currentPage == 1){
			choice = option.next(); ////////// Getting input after, or else needs some kind of integer input after every page, which was messing up the switch between P3 and P5
			if (choice.equals("1")) {
				currentPage = 3;
				getCurrentPage();
			}
			else if (choice.equals("2")) {
				currentPage = 2;
				getCurrentPage();
			}
			else {
				System.out.print("Invalid input.\nChoose your option:");
				changeCurrentPage();
			}
		}
		else if (currentPage == 2) {
			choice = option.next();
			currentPage = 1;
			getCurrentPage();
		}
		else if (currentPage == 3){ 
									////////// Instead of getting an input, just has the following delay of 3 seconds before switching to P5
			try {
				Thread.sleep(3000);
				currentPage = 5;
				getCurrentPage();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else if (currentPage == 4) {

			try {
				Thread.sleep(3000);	////////// Same as P3
				currentPage = 1;
				getCurrentPage();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else if (currentPage == 5) {
			choice = option.next();
			if (choice.equals("1")) {
				currentPage = 6;
				getCurrentPage();
			}
			else if (choice.equals("2")) {
				currentPage = 7;
				getCurrentPage();
			}
			else if (choice.equals("3")) {
				currentPage = 1;
				getCurrentPage();
			}
			else if (choice.equals("4")) {
				currentPage = 11;
				getCurrentPage();
			}
			else {
				System.out.print("\nInvalid input.\nChoose your option:");
				changeCurrentPage();
			}
		}
		else if (currentPage == 6) {
			choice = option.next();
			if (choice.equals("1")) {
				currentPage = 8;
				getCurrentPage();
			}
			else if (choice.equals("2")) {
				currentPage = 9;
				getCurrentPage();
			}
			else if (choice.equals("-1")) {
				currentPage = 5;
				getCurrentPage();
			}
			else {
				System.out.print("\nPress -1 to return to previous menu");
				changeCurrentPage();
			}
		}
		else if (currentPage == 7) {
			choice = option.next();
			// Nothing happens. Ends program? Goes back to P1? P5? @@@@@@@@@@@@@@
		}
		else if (currentPage == 8) {
			choice = option.next();
			if (choice.equals("0")) {
				currentPage = 10;
				getCurrentPage();
			}
			else if (choice.equals("-2")) {
				currentPage = 6;
				getCurrentPage();
			}
			else {
				System.out.print("Invalid input.\nChoose your option:");
				changeCurrentPage();
			}
		}
		else if (currentPage == 9) {
			choice = option.next();
			if (choice.equals("0")) {
				currentPage = 10;
				getCurrentPage();
			}
			else if (choice.equals("-2")) {
				currentPage = 6;
				getCurrentPage();
			}
			else {
				System.out.print("Invalid input.\nChoose your option:");
				changeCurrentPage();
			}
		}
		else if (currentPage == 10) {
			try {
				Thread.sleep(3000);	////////// Same as P3
				currentPage = 5;
				getCurrentPage();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else if (currentPage == 11) {
			choice = option.next();
			// Nothing happens. Ends program? Goes back to P1? P5?
		}
		else {
			System.out.println("Error: current page not found, change() function");
		}
		option.close();
	}


	// getCurrentPage calls different pages depending on the value of currentPage
	public int getCurrentPage() {
		if (currentPage == 1) {
			System.out.print("\n\n\n1.Sign in\t\t\tP1\n2.Sign up\nChoose your option:");
			changeCurrentPage();
			return 0;
		}
		
		else if (currentPage == 2) {
			System.out.print("\n\n\nChoose your username:");
			Scanner findNewUsername = new Scanner(System.in);
			String new_username = findNewUsername.next();
			User user2 = new User(new_username);
			user2.addUsername();
			
			ShoppingCart new_cart = new ShoppingCart(new_username);
			new_cart.newCart();
			
			System.out.println("\nUsername successfully added\t\t\tP2");
			changeCurrentPage();
			findNewUsername.close();
			return 0;
		}
		
		else if (currentPage == 3) {
			System.out.print("\n\n\nEnter your username:");
			Scanner findUsername = new Scanner(System.in);
			username = findUsername.next();
			User user3 = new User(username);
			
			if (user3.checksUsername()) {
				System.out.println("\nHello "+username+"\t\t\tP3");
				ShoppingCart cart = new ShoppingCart(username);
				changeCurrentPage();
			}
			else {
				System.out.println("\nNo Access\t\t\tP4");
				currentPage = 4;
				changeCurrentPage();
			}
			findUsername.close();
			return 0;
		}
		
		else if (currentPage == 5) {
			System.out.println("\n\n\n1.View Items By Category\t\tP5\n2.View Shopping Cart\n3.Sign out\n4.View Previous Orders\n\nChoose your option:");
			changeCurrentPage();
			return 0;
		}
		
		else if (currentPage == 6) {
			System.out.println("\n\n\n1.Readables\t\t\tP6\n2.Audio\n\nChoose your option:");
			changeCurrentPage();
			return 0;
		}
		
		// Shows contents of cart. Not able to go anywhere from this page.
		else if (currentPage == 7) {
			String[][] content = cart.getContent();
			System.out.println("\n\n\n\t\t\t\t\tP7");
			for (int i = 0; i<content.length; i++) {
				for (int j = 0; j<content[0].length; j++) {
					if (j<3) System.out.print(content[i][j]+", ");
					else System.out.print(content[i][j]+"\n");
				}
			}
			return 0;
		}
		
		else if (currentPage == 8) {
			showReadables();		////////// Calls the method from the very top
			System.out.print("Choose your option:");
			
			Scanner findSNo1 = new Scanner(System.in);
			String sNo = findSNo1.next();
			
			while (!sNo.equals("3")||!sNo.equals("4")||!sNo.equals("5")||!sNo.equals("6")||!sNo.equals("-1")){
				System.out.println("Press -1 to return to previous menu.\nChoose your option:");
				Scanner findSNo2 = new Scanner(System.in);
				sNo = findSNo2.next();
				findSNo2.close();
			}
			if (sNo == "-1") {
				currentPage = 6;
				getCurrentPage();
				findSNo1.close();
			}
			else {
				// Otherwise takes positive input quantity
				System.out.println("Enter quantity:");
				Scanner findQuantity = new Scanner(System.in);
				String quantity1 = findQuantity.next();
				findQuantity.close();
				// Checks if entered quantity is an integer
				if (isInt(quantity1)==false) System.out.println("Requested quantity not available.");
				else {
					int quantity = Integer.parseInt(quantity1);
					int index_num = Integer.parseInt(sNo)-3;
					// Finds quantity of requested item
					if (quantity<1||quantity>Integer.parseInt(readables[index_num][3])) System.out.println("Requested quantity not available.");
					else {
						System.out.println(quantity1+" "+readables[index_num][1]+" "+readables[index_num][5]+" successfully added to your cart.");
						cart.addItem(Integer.parseInt(sNo),quantity);
						// Subtracts input quantity from file @@@@@@@@@@@@@@@@@@@@@
					}
				}
				
				System.out.println("\nPress -2 to Continue Shopping or Press 0 to CheckOut:");
				changeCurrentPage();
				findSNo1.close();
			}
			return 0;
		}
		
		else if (currentPage == 9) {
			showAudioProducts(); ////////// Same as above
			
			System.out.println("Choose your option:");
			Scanner findSNo1 = new Scanner(System.in);
			String sNo = findSNo1.next();
			
			while (!sNo.equals("7")||!sNo.equals("8")||!sNo.equals("9")||!sNo.equals("10")||!sNo.equals("-1")){
				System.out.println("Press -1 to return to previous menu.\nChoose your option:");
				Scanner findSNo2 = new Scanner(System.in);
				sNo = findSNo2.next();
				findSNo2.close();
			}
			if (sNo == "-1") {
				currentPage = 6;
				getCurrentPage();
				findSNo1.close();
			}
			else {
				// Otherwise takes positive input quantity
				System.out.println("Enter quantity:");
				Scanner findQuantity = new Scanner(System.in);
				String quantity1 = findQuantity.next();
				// Checks if entered quantity is an integer
				if (isInt(quantity1)==false) System.out.println("Requested quantity not available.");
				else {
					int quantity = Integer.parseInt(quantity1);
					int index_num = Integer.parseInt(sNo)-7;
					// Finds quantity of requested item in corresponding file
					if (quantity<1||quantity>Integer.parseInt(audioProducts[index_num][3])) System.out.println("Requested quantity not available.");
					else {
						System.out.println(quantity1+" "+audioProducts[index_num][1]+" "+audioProducts[index_num][5]+" successfully added to your cart.");
						cart.addItem(Integer.parseInt(sNo),quantity);
						// Subtracts item quantity from file@@@@@@@@@@@@@@@@@@@@@@@@
					}
				findQuantity.close();
				}
				System.out.println("\nPress -2 to Continue Shopping or Press 0 to CheckOut:");
				changeCurrentPage();

				findSNo1.close();
			}
			return 0;
		}
		
		else if (currentPage == 10) {
			System.out.println("\n\n");
			showCart();
			System.out.print("\nAre you sure you want to pay? yes or no.");
			
			// The following scans input until some form of yes or no is entered
			Scanner pay = new Scanner(System.in);
			String ans = pay.next();
			pay.close();
			if (ans.equalsIgnoreCase("yes")){
				ans = "yes";
			}
			else if (ans.equalsIgnoreCase("no")) {
				ans = "no";
			}
			while (!(ans.equals("yes"))||!(ans.equals("no"))) {
				System.out.print("\nyes or no.");
				Scanner p10 = new Scanner(System.in);
				ans = p10.next();
				if (ans.equalsIgnoreCase("yes")) ans = "yes";
				else if (ans.equalsIgnoreCase("no")) ans = "no";
				p10.close();
			}
			
			if (ans.equals("no")) {
				currentPage = 6; // This maybe should be 1?
				getCurrentPage();
			}
			else {
				System.out.println("Confirmation ID: U"+conID);
				conID++;
				System.out.println("\nItems shipped to: "+username);
				// Put order details in ItemsBought.txt
				cart.newCart();
				currentPage = 6;
				getCurrentPage();
			}
			return 0;
		}
		
		else if (currentPage == 11) {
			System.out.println("\n\n\n");
			// Print contents of ItemsBought.txt
			return 0;
		}
		
		else {
			System.out.println("Error: current page not found, get() function");
			return 0;
		}
	}

}