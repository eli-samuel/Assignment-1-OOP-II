// ------------------------------------------------
// COMP 249
// Assignment 1
// Question: Part II
// January 31st 2020
// Written by: Eli Samuel (40122277) and David Roper (40131739)
// ------------------------------------------------

/**
* Eli Samuel (40122277) and David Roper (40131739)
* COMP 249
* Assignment 1
* January 31st 2020
*/

/* This program allows a user to keep an create and edit an inventory of appliances. The user can choose
 * what appliances they create (under set types), brand, and price, and assigns the appliance a serial
 * number based on what number appliance that was created. The user can edit the appliance attributes
 * (except for serial number) as many times as they would like, as well as show a listing of all the appliances
 * that are under the same brand or under a certain price. The user can also end the program when they would
 * like by choosing to do so in the main menu */

import java.util.Scanner;

public class Assignment1 {

    public static void main(String[] args) {
        // initializing variables
        Scanner input = new Scanner(System.in);
        int maxAppliances = 0;
        int option = 0;
        Appliance[] inventory;
        boolean gotPassword = false;
        boolean menu = true;

        // setting password and variable for users password input
        final String PASSWORD = "c249";
        String userPass = "";

        //welcome message
        System.out.println("\nWelcome to the appliance tracker by Eli Samuel (40122277) and David Roper (40131739)!\n");

        //ask u
        System.out.print("Enter the max appliances: ");
        maxAppliances = input.nextInt();
        System.out.println();

        inventory = new Appliance[maxAppliances];

        int counter = 0;
        while (true) {
            // display the menu if needed
            if (menu) {
                do {
                    option = displayMenu(input, option);
                } while (option < 1 || option > 5);
                gotPassword = false; // resets
            }

            // if the user chooses 1, allow them to add new appliances to the inventory
            if (option == 1) {
                // check the password 12 times
                if (!passwordChecker(input, userPass, PASSWORD) && counter < 4) {
                    counter++;
                    // if the user failed 12 times, exit the program
                    if (counter == 4) {
                        System.out.println("\nProgram detected suspicious activities and will terminate immediately!");
                        System.exit(0);
                    }
                    option = displayMenu(input, option);
                    continue;
                }

                counter=0; // resetting number of attempts

                System.out.print("Enter number of appliances to add: ");
                int numAppliances = input.nextInt();

                // if the user input a number greater than the number of spaces left
                if (numAppliances > spaceLeft(inventory)) {
                    System.out.println("\nThere is not enough room in the inventory (" + spaceLeft(inventory) + " place(s) remain)."
                    + " Adding " + spaceLeft(inventory) + " appliance(s).");
                    numAppliances = spaceLeft(inventory);
                }

                // adds the appliances to the inventory
                inventory = applianceAdder(inventory, numAppliances, input);

                menu = true;
            }
            // if the user chooses 2, allow them to edit appliance settings to the inventory
            else if (option == 2) {
                // checking the password
                if (!gotPassword && !passwordChecker(input, userPass, PASSWORD)) {
                    option = displayMenu(input, option);
                    continue;
                }

                System.out.print("Input the serial number of the item you would like to change: ");
                long serialNum = input.nextLong();

                // makes sure the serial number exists
                int item = 0;
                boolean hasSerial = false;
                for (item=0; item<inventory.length-spaceLeft(inventory); item++) {
                    if (inventory[item].getSerialNum() == serialNum) {
                        hasSerial = true;
                        break;
                    }
                }

                // if the serial number does not exist
                int reply = 0;
                if (!hasSerial) {
                    do {
                        System.out.println("\nSerial number does not exist or bad input! Would you like to:\n\t1. Re-enter another serial number\n\t2. Return to the main menu");
                        reply = input.nextInt();

                        if (reply == 1) {
                            gotPassword = true;
                            menu = false;
                        }
                        else if (reply == 2) {
                            System.out.println("Returning to menu");
                            menu = true;
                        }

                    } while (reply != 1 && reply != 2);

                    continue;
                }

                // display appliance information
                System.out.println(inventory[item]);

                int choice = 0;

                do {
                    do {
                        System.out.print("\nWhat information would you like to change?"
                        + "\n\t1. Type\n\t2. Brand\n\t3. Price\n\t4. Quit\nEnter your choice: ");
                        choice = input.nextInt();
                    } while (choice > 4 && choice < 1);

                    if (choice == 1) {
                        input.nextLine();
                        System.out.print("Enter new type: ");
                        inventory[item].setType(input.nextLine());
                        System.out.println(inventory[item]);
                    }
                    else if (choice == 2) {
                        input.nextLine();
                        System.out.print("Enter new brand: ");
                        inventory[item].setBrand(input.nextLine());
                        System.out.println(inventory[item]);
                    }
                    else if (choice == 3) {
                        System.out.print("Enter new price: ");
                        inventory[item].setPrice(input.nextDouble());
                        System.out.println(inventory[item]);
                    }
                } while (choice != 4);

                gotPassword = false; // requires password on next attempt
                menu = true; // go back to the menu
            }
            // if the user chooses 3, display all the appliances with the same brand name
            else if (option == 3) {
                System.out.print("Enter a brand name: ");
                findAppliancesBy(input.next(), inventory);
            }
            // if the user chooses 4, display all the appliances under the specified type
            else if (option == 4) {
                System.out.print("Enter a price: ");
                findCheaperThan(input.nextDouble(), inventory);
            }
            // if the user chooses 5, end the program
            else {
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
            if (inventory[i] != null && inventory[i].getPrice() < price) System.out.println(inventory[i]);
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
            if (inventory[i] != null && inventory[i].getBrand().toLowerCase().equals(brand.toLowerCase())) System.out.println(inventory[i]);
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
        int a = Appliance.findNumberOfCreatedAppliances();
        for (int i=a; i<a+numAppliances; i++) {
            System.out.println("\nEnter type, brand, and price (separated by a newline).");
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
    /**
    *Checks space left in store for new appliances
    *
    *@param inventory Array of type Appliance
    *@return spaceLeft an integer value of spaces left
    */
    public static int spaceLeft(Appliance[] inventory) {
        int spaceLeft = 0;
        for (int i=0; i<inventory.length; i++) {
            if (inventory[i] == null) spaceLeft++;
        }
        return spaceLeft;
    }
}
