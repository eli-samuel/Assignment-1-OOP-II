import java.util.Scanner;

public class Assignment1 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int maxAppliances = 0;
        int option = 0;
        Appliance[] inventory;
        final String PASSWORD = "c249";
        String userPass = "";

        System.out.println("\nWelcome to the appliance tracker!\n");

        System.out.print("Enter the max appliances: ");
        maxAppliances = input.nextInt();
        System.out.println();

        inventory = new Appliance[maxAppliances];

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
                    System.out.println(numAppliances);
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

                printAppliance(inventory[item]);

                int choice = 0;

                do {
                    do {
                        System.out.print("\nWhat information would you like to change?"
                                        + "\n\t1. Brand\n\t2. Type\n\t3. Price\n\t4. Quit\nEnter your choice: ");
                        choice = input.nextInt();
                    } while (choice > 4 && choice < 1);

                    if (choice == 1) {
                        System.out.print("Enter new brand: ");
                        inventory[item].setBrand(input.next());
                        printAppliance(inventory[item]);
                    }
                    else if (choice == 2) {
                        System.out.print("Enter new type: ");
                        inventory[item].setType(input.next());
                        printAppliance(inventory[item]);
                    }
                    else if (choice == 3) {
                        System.out.print("Enter new price: ");
                        inventory[item].setPrice(input.nextDouble());
                        printAppliance(inventory[item]);
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
                System.out.println("\nThanks for using the program!\n");
                break;
            }
        }

    }

    public static boolean passwordChecker(Scanner input, String userPass, final String PASSWORD) {
        int triesRemaining = 3;

        do {
            System.out.print("Enter password (tries remaining: " + triesRemaining + "): ");
            userPass = input.next();
            triesRemaining--;
        } while (!(userPass.equals(PASSWORD)) && triesRemaining > 0);

        return userPass.equals(PASSWORD);
    }

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

    public static void findCheaperThan(double price, Appliance[] inventory) {
        for (int i=0; i<inventory.length; i++) {
            if (inventory[i].getPrice() < price && inventory[i].getType() != null) System.out.println(inventory[i]);
        }
    }

    public static void findAppliancesBy(String brand, Appliance[] inventory) {
        for (int i=0; i<inventory.length; i++) {
            if (inventory[i].getBrand().toLowerCase().equals(brand.toLowerCase())) System.out.println(inventory[i]);
        }
    }

    public static Appliance[] applianceAdder(Appliance[] inventory, int numAppliances, Scanner input) {
        for (int i=0; i<numAppliances; i++) {
            // System.out.print("Enter type: ");
            // String type = input.nextLine();
            // System.out.print("Enter brand: ");
            // String brand = input.nextLine();
            // System.out.print("Enter serial number: ");
            // long serialNum = input.nextLong();
            // System.out.print("Enter price: ");
            // double price = input.nextDouble();
            System.out.println("Enter type, brand, serial number, and price (separated by a newline).");
            String extra = input.nextLine();
            inventory[i] = new Appliance(input.nextLine(), input.nextLine(), input.nextLong(), input.nextDouble());
            System.out.println("Added: " + inventory[i]);
        }

        return inventory;
    }

    public static void printAppliance(Appliance a) {
        System.out.println("\nAppliance Serial #: " + a.getSerialNum()
                        + "\nBrand: " + a.getBrand()
                        + "\nType: " + a.getType()
                        + "\nPrice: " + a.getPrice());
    }
}
