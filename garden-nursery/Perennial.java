/**
 * Perennial.java
 * @author
 * CIS 36B, Final Exam
 */

import java.text.DecimalFormat;

public class Perennial extends Plant{  //Update to inherit from Plant
    private boolean lowWater;
    private boolean isNative;

    /**
     * A default constructor for Perennial
     * Assigns "No name" to name and -1 to price and
     * lowWater and isNative to false
     * Method must call 4-argument constructor
     */
    public Perennial()
    {
        this("No name", -1, false, false);
    }

    /**
     * A two argument constructor for Perennial
     * Assigns values to name and price but sets
     * lowWater and isNative to false
     * @param name the plant's name
     * @param price the cost of the plant
     */
    public Perennial(String name, double price)
    {
        this(name, price, false, false);
    }
    /**
     * A four argument constructor for Perennial
     * @param name the plant's name
     * @param price the cost of the plant
     * @param lowWater whether the plant has low water needs
     * @param isNative whether the plant is CA native
     */
    public Perennial(String name, double price, boolean lowWater, boolean isNative)
    {
        super(name, price);
        this.lowWater = lowWater;
        this.isNative = isNative;
    }
    public boolean isLowWater()
    {
        return lowWater;
    }

    public void setLowWater(boolean lowWater)
    {
        this.lowWater = lowWater;
    }

    public boolean isNative() {
        return isNative;
    }

    public void setNative(boolean isNative)
    {
        this.isNative = isNative;
    }

    /**
     * toString for the Perennial class in the following format
     * Name: name
     * Price: $ price written to 2 decimal places
     * Water-wise: Yes/No
     * Native Plant: Yes/No
     */
    @Override
    public String toString()
    {
        String pattern = "###,###.00";
        DecimalFormat df = new DecimalFormat(pattern);
        String nat, wat;

        if (isNative())
            nat = "Yes";
        else
            nat = "No";

        if (isLowWater())
            wat = "Yes";
        else
            wat = "No";


        return "Name: " + getName() + "\n" +
                "Price: $"+ df.format(getPrice()) + "\n" +
                "Water-wise: " + wat + "\n" +
                "Native Plant: " + nat + "\n";
    }
}
