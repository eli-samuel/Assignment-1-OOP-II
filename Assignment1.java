//Eli Samuel (ID) and David Roper(40131739)
//COMP 249
//Assignment 1
//January 31st 2020

import java.util.Scanner;

public class Assignment1 {

    public static void main(String[] args) {
        //initializing variables
        Scanner input = new Scanner(System.in);
        int maxAppliances = 0;
        int option = 0;
        Appliance[] inventory;
        //setting Password and variable for users password input
        final String PASSWORD = "c249";
        String userPass = "";

        System.out.println("\nWelcome to the appliance tracker!\n");

        System.out.print("Enter the max appliances: ");
        maxAppliances = input.nextInt();
        System.out.println();

        inventory = new Appliance[maxAppliances];

        System.out.println(Appliance.findNumberOfCreatedAppliances());

        do {
            option = displayMenu(input, option);
        } while (option < 1 || option > 5);

        int counter = 0;
        while (true) {
            if (option == 1) {
                if (!passwordChecker(input, userPass, PASSWORD) && counter<4) {
                    counter++;
                    if (counter == 4) {
                        System.out.println("\nProgram detected suspicious activities and will terminate immediately!");
                        System.exit(0);
                    }
                    option = displayMenu(input, option);
                    continue;
                }

                counter=0;
                System.out.print("Enter number of appliances to add: ");
                int numAppliances = input.nextInt();

                if (numAppliances > inventory.length) {
                    System.out.println("\nThere is not enough room in the inventory (" + inventory.length + " place(s) remain)."
                    + " Adding " + inventory.length + " appliances.");
                    numAppliances = inventory.length;
                }

                inventory = applianceAdder(inventory, numAppliances, input);

                option = displayMenu(input, option);
            }
            else if (option == 2) {
                if (!passwordChecker(input, userPass, PASSWORD)) {
                    option = displayMenu(input, option);
                    continue;
                }

                System.out.print("Input the serial number of the item you would like to change: ");
                long serialNum = input.nextLong();

                int item = 0;
                for (item=0; item<inventory.length; item++) {
                    if (inventory[item].getSerialNum() == serialNum) break;
                }

                System.out.println(inventory[item]);

                int choice = 0;

                do {
                    do {
                        System.out.print("\nWhat information would you like to change?"
                                        + "\n\t1. Type\n\t2. Brand\n\t3. Price\n\t4. Quit\nEnter your choice: ");
                        choice = input.nextInt();
                    } while (choice > 4 && choice < 1);

                    if (choice == 1) {
                        System.out.print("Enter new type: ");
                        inventory[item].setType(input.next());
                        System.out.println(inventory[item]);
                    }
                    else if (choice == 2) {
                        System.out.print("Enter new brand: ");
                        inventory[item].setBrand(input.next());
                        System.out.println(inventory[item]);
                    }
                    else if (choice == 3) {
                        System.out.print("Enter new price: ");
                        inventory[item].setPrice(input.nextDouble());
                        System.out.println(inventory[item]);
                    }
                } while (choice != 4);

                break;
            }
            else if (option == 3) {
                System.out.print("Enter a brand name: ");
                String brand = input.next();
                findAppliancesBy(brand, inventory);
            }
            else if (option == 4) {
                System.out.print("Enter a price: ");
                double price = input.nextDouble();

                findCheaperThan(price, inventory);
            }
            else { // option == 5
                System.out.println("Thanks for using the program!");
                break;
            }
        }

    }
    /**
    * Checks user input for password and compares it to actual password
    *
    * @param input a Scanner variable that takes user input
    * @param userPass a String vaiable for user password input
    * @param PASSWORD a Final string variable for the real password user input is compared to 
    * @return boolean value if user input is equal to final password
    */
    public static boolean passwordChecker(Scanner input, String userPass, final String PASSWORD) {
        int triesRemaining = 3;

        do {
            System.out.print("Enter password (tries remaining: " + triesRemaining + "): ");
            userPass = input.next();
            triesRemaining--;
        } while (!(userPass.equals(PASSWORD)) && triesRemaining > 0);

        return userPass.equals(PASSWORD);
    }
    /**
    * Displays menu to the user, asks user for input
    *
    * @param input a Scanner variable for user input
    * @param option an integer variable
    * @return option an integer variable which stores the user input 
    */
    public static int displayMenu(Scanner input, int option) {
        System.out.print("\nWhat do you want to do?\n\t1.\tEnter new appliances (password required)"
        + "\n\t2.\tChange information of an appliance (password required)"
        + "\n\t3.\tDisplay all appliances by a specific brand"
        + "\n\t4.\tDisplay all appliances under a certain price"
        + "\n\t5.\tQuit\nPlease enter your choice: ");
        option = input.nextInt();
        System.out.println();

        return option;
    }
    /**
    * Searches through all appliances and finds the appliance cheaper than the one given 
    *
    * @param price an double value, the price of the appliance
    * @param Appliance[] inventory an array of type appliance 
    */
    public static void findCheaperThan(double price, Appliance[] inventory) {
        for (int i=0; i<inventory.length; i++) {
            if (inventory[i].getPrice() < price && inventory[i].getType() != null) System.out.println(inventory[i]);
        }
    }
    /**
    * Searches through appliances and find the one of same brand name
    *
    * @param brand a String value for brand name
    * @param Appliance [] inventory a Array of type appliance
    */
    public static void findAppliancesBy(String brand, Appliance[] inventory) {
        for (int i=0; i<inventory.length; i++) {
            if (inventory[i].getBrand().toLowerCase().equals(brand.toLowerCase())) System.out.println(inventory[i]);
        }
    }
    /** 
    * Adds new appliance(s) to an inventory
    *
    *@param Appliance [] inventory a Array of type appliance
    *@param numAppliances an integer value for number of appliances
    *@param input a Scanner value for user to input appliances
    *@return inventory an Array of type Appliance made by user
    */
    public static Appliance[] applianceAdder(Appliance[] inventory, int numAppliances, Scanner input) {
        for (int i=0; i<numAppliances; i++) {
            System.out.println("Enter type, brand, and price (separated by a newline).");
            String extra = input.nextLine();
            inventory[i] = new Appliance(input.nextLine(), input.nextLine(), input.nextDouble());
            System.out.println("\nAdded: " + inventory[i]);
        }

        return inventory;
    }
    /**
    *Prints out appliance info
    *
    *@param a a value of type Appliance
    */
    public static void printAppliance(Appliance a) { // JUST USE THE TOSTRING METHOD
        System.out.println("\nAppliance Serial #: " + a.getSerialNum()
                        + "\nBrand: " + a.getBrand()
                        + "\nType: " + a.getType()
                        + "\nPrice: " + a.getPrice());
    }
}
