//Eli Samuel (ID) and David Roper(40131739)
//COMP 249
//Assignment 1
//January 31st 2020

public class Appliance {

    private String type;
    private String brand;
    private long serialNum;
    private double price;
    private static long sNum = 1000000;
    private static long lastNum = 1000000;


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
    }

    /**
    * Default empty Appliance constructor
    */
    public Appliance() {

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
        if (type.equals("fridge") || type.equals("air conditioner") || type.equals("washer")
            || type.equals("dryer") || type.equals("freezer") || type.equals("stove")
            || type.equals("dishwasher") || type.equals("water heater") || type.equals("microwave"))
            this.type = type;
        else this.type = "unknown";
    }

    /**
    * Sets new value of brand
    * @param brand a String variable
    */
    public void setBrand(String brand) {
        if (brand.length() > 0) this.brand = brand;
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
    }

    /**
    * Equals method for appliance
    *
    *@param a a object of Appliance class
    *@return boolean value
    */
    public boolean equals(Appliance a) {
        return (type == a.type && brand == a.brand && price == a.price);
    }

<<<<<<< HEAD
    public static long findNumberOfCreatedAppliances() {
        long diff = sNum - lastNum;
        lastNum = sNum;
        return diff;
=======
    /**
    * Finds number of appliances created
    *
    * @return 0
    */
    public int findNumberOfCreatedAppliances() {
        return 0;
>>>>>>> 8cfc94f738c7cc1dcec8a03dd1b574ca84aa0ceb
    }

	/**
	* Create string representation of Appliance for printing
	* @return
	*/
	@Override
	public String toString() {
		return "\nType: " + type + "\nBrand: " + brand + "\nAppliance Serial #: " + serialNum + "\nPrice: " + price;
	}

    private static long serialNumGenerator() {
        return sNum++;
    }

}
