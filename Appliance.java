public class Appliance {

    private String type;
    private String brand;
    private long serialNum;
    private double price;

    /**
    * Default Appliance constructor
    */
    public Appliance(String type, String brand, long serialNum, double price) {
        setType(type);
        setBrand(brand);
        setSerialNum(serialNum);
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
    * @param
    */
    public void setType(String type) {
        this.type = type;
    }

    /**
    * Sets new value of brand
    * @param
    */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
    * Sets new value of serialNum
    * @param
    */
    public void setSerialNum(long serialNum) {
        this.serialNum = serialNum;
    }

    /**
    * Sets new value of price
    * @param
    */
    public void setPrice(double price) {
        this.price = price;
    }

    public boolean equals(Appliance a) {
        return (type == a.type && brand == a.brand && price == a.price);
    }

    public int findNumberOfCreatedAppliances() {
        return 0;
    }

	/**
	* Create string representation of Appliance for printing
	* @return
	*/
	@Override
	public String toString() {
		return "Appliance [type=" + type + ", brand=" + brand + ", serialNum=" + serialNum + ", price=" + price + "]";
	}

}
