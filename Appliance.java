// ------------------------------------------------
// COMP 249
// Assignment 1
// Question: Part I
// January 31st 2020
// Written by: Eli Samuel (40122277) and David Roper (40131739)
// ------------------------------------------------

/**
* Eli Samuel (40122277) and David Roper (40131739)
* COMP 249
* Assignment 1
* January 31st 2020
*/

/*This is the Appliance class which contains variables and methods for objects of the Appliance class.
 * An appliance object has 4 attributes: type (string), brand (string), serialNum (long) and price (double).
 * these varaible represent type of appliance, brand name, serial number and price. the class
 * has two static methods being sNum (long) and counter (integer). the first being what the first
 * created appliances serial number will be and the second being a counter of total appliances created. The class
 * has a parameter and default contructor using setters to make sure the object is initialized correctly.
 * the class has appropriate accessor an mutator methods for each non-static variable.
 * the only mutator that is private is the setSerialNum as the user should able to change it.
 * the class also has an equals method which checks if its being compared to another appliance then
 * checks if there variables are equal. the class has a toString method to print out the objects variables
 * using the system.out.print function. last of all the class has two static methods SerialNumGenerator, which
 * creates a the serial number when a new object is created. the second is the findNumberOfCreatedAppliances
 * methods which returns the total number appliance objects.   */

public class Appliance {
    //intializing class variables
    private String type;
    private String brand;
    private long serialNum;
    private double price;

    //static variables
    private static long sNum = 1000000;
    private static int counter = 0;

    /**
    * Default Appliance constructor
    *
    * @param type a String variable for appliance type
    * @param brand a String variable for brand name
    * @param serialNum a long variable for appliance id number
    * @param price a integer variable for appliance price
    */
    public Appliance(String type, String brand, double price) {
        setType(type);
        setBrand(brand);
        setSerialNum(serialNumGenerator());
        setPrice(price);
        counter++;
    }

    /**
    * Default empty Appliance constructor
    */
    public Appliance() {
        setType("unknown");
        setBrand("unknown");
        setSerialNum(serialNumGenerator());
        setPrice(1.);
        counter++;
    }

    /**
    * Returns value of type
    * @return
    */
    public String getType() {
        return type;
    }

    /**
    * Returns value of brand
    * @return
    */
    public String getBrand() {
        return brand;
    }

    /**
    * Returns value of serialNum
    * @return
    */
    public long getSerialNum() {
        return serialNum;
    }

    /**
    * Returns value of price
    * @return
    */
    public double getPrice() {
        return price;
    }

    /**
    * Sets new value of type
    * @param type a String variable
    */
    public void setType(String type) {
        type = type.toLowerCase();
        // checks to see if the type is in the allowed set
        if (type.equals("fridge") || type.equals("air conditioner") || type.equals("washer")
            || type.equals("dryer") || type.equals("freezer") || type.equals("stove")
            || type.equals("dishwasher") || type.equals("water heaters") || type.equals("microwave"))
            this.type = type;
        else this.type = "unknown";
    }

    /**
    * Sets new value of brand
    * @param brand a String variable
    */
    public void setBrand(String brand) {
        // makes sure the user input something for brand
        if (brand.length() > 0) this.brand = brand;
        else this.brand = "unknown";
    }

    /**
    * Sets new value of serialNum
    * @param serialNum a long variable
    */
    private void setSerialNum(long serialNum) { // set to private so that it can't be changed from outside this class
        this.serialNum = serialNum;
    }

    /**
    * Sets new value of price
    * @param price a value of type double
    */
    public void setPrice(double price) {
        if (price > 0) this.price = price;
        else this.price = 1.;
    }

    /**
    * Equals method for appliance
    *
    *@param a a object of Appliance class
    *@return boolean value
    */
    public boolean equals(Object a) {
        if (a == null) return false;
        else if (getClass() != a.getClass()) return false;
        else {
            Appliance newAppliance = (Appliance) a;
            return (type == newAppliance.type && brand == newAppliance.brand && price == newAppliance.price);
        }
    }

    /**
    * Finds number of appliances created
    *
    * @return diff a long variable
    */
    public static int findNumberOfCreatedAppliances() {
        return counter;
    }

	/**
	* Create string representation of Appliance for printing
	* @return
	*/
	public String toString() {
		return "\nType: " + type + "\nBrand: " + brand + "\nAppliance Serial #: " + serialNum + "\nPrice: " + price;
	}

    /**
    *@return long value of the serial number
    */
    private static long serialNumGenerator() {
        return sNum++;
    }
}
