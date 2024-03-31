import java.text.DecimalFormat;

/**
 * Tree.java
 * @author
 * CIS 36B
 */

public class Tree extends Perennial{ //Update to inherit from Perennial
    private boolean isDeciduous;
    private int numGallons;

    /**
     * Default constructor for Tree
     * Calls the 6 argument constructor
     * Assigns "No name" to name, -1 to price, false to lowWater, false to isNative
     * false to isDeciduous, and 0 to numGallons
     */
    public Tree()
    {
        this("No name", -1, false, false, false, 0);
    }

    /**
     * A two argument constructor for Tree
     * Assigns values to name and price but sets
     * lowWater, isNative, and isDeciduous to false and 0 to numGallons
     * @param name the plant's name
     * @param price the cost of the plant
     */
    public Tree(String name, double price)
    {
        this(name, price, false, false, false, 0);
    }
    /**
     * A six argument constructor for Tree
     * @param name the plant's name
     * @param price the cost of the plant
     * @param lowWater whether the plant has low water needs
     * @param isNative whether the plant is CA native
     * @param isDeciduous whether the plant is deciduous
     * @param numGallons the size of the container in gallons
     */
    public Tree(String name, double price, boolean lowWater, boolean isNative, boolean isDeciduous, int numGallons)
    {
        super(name, price, lowWater, isNative);
        this.isDeciduous = isDeciduous;
        this.numGallons = numGallons;
    }
    public boolean isDeciduous() {
        return isDeciduous;
    }

    public void setDeciduous(boolean isDeciduous) {
        this.isDeciduous = isDeciduous;
    }

    public int getNumGallons() {
        return numGallons;
    }

    public void setNumGallons(int numGallons) {
        this.numGallons = numGallons;
    }

    /**
     * toString for the Tree class in the following format
     * Name: name
     * Price: $ price written to 2 decimal places
     * Water-wise: Yes/No
     * Native Plant: Yes/No
     * Deciduous tree: Yes/No
     * Gallon Size: number of gallons
     * New line
     */
    @Override
    public String toString()
    {
        String pattern = "###,###.00";
        DecimalFormat df = new DecimalFormat(pattern);
        String nat, dcd, wat;

        if (isNative())
            nat = "Yes";
        else
            nat = "No";

        if (isDeciduous)
            dcd = "Yes";
        else
            dcd = "No";

        if (isLowWater())
            wat = "Yes";
        else
            wat = "No";

        return "Name: " + getName() + "\n" +
                "Price: $" + df.format(getPrice()) + "\n" +
                "Water-wise: " + wat + "\n" +
                "Native Plant: " + nat + "\n" +
                "Deciduous tree: " + dcd + "\n" +
                "Gallon Size: " + getNumGallons() + "\n";
    }
}
