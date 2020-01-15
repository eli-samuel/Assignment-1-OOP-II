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

        int i = 0;
        while (true) {
            if (option == 1) {
                while (!passwordChecker(input, userPass, PASSWORD) && i<4) {
                    i++;
                    if (i == 4) {
                        System.out.println("\nProgram detected suspicious activities and will terminate immediately!");
                        System.exit(0);
                    }
                    option = displayMenu(input, option);
                    break;
                }
            }
            else if (option == 2) {
                while (!passwordChecker(input, userPass, PASSWORD)) {
                    option = displayMenu(input, option);
                    break;
                }
            }
            else if (option == 3) {
                System.out.print("Enter a brand name: ");
                String name = input.next();
            }
            else if (option == 4) {
                System.out.print("Enter a price: ");
                double price = input.nextDouble();

                findCheaperThan(price);
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

    public static void findCheaperThan(double price) {
        System.out.println(new Appliance());
        //return {new Appliance()};
    }

    public static void findAppliancesBy(String brand) {
        //return {new Appliance()};
    }

}
