/**
 * Plant.java
 * @author
 * CIS 36B, Final Exam
 */

import java.text.DecimalFormat;

public abstract class Plant implements Comparable<Plant> { //update to inherit from Comparable interface
    private String name;
    private double price;

    /**
     * Defalut constructor for the Plant class
     */
    public Plant()
    {
        this.name = "No name";
        this.price = -1;

    }

    /**
     * 2-argument constructor for the Plant class
     * @param name the String name of the Plant
     * @param price the double price for the Plant
     */
    public Plant(String name, double price)
    {
        this.name = name;
        this.price = price;
    }

    /**
     * Accessor for the name variable
     * @return the String name of the Plant
     */
    public String getName()
    {
        return name;
    }

    /**
     * Mutator for the name variable
     * @param name the String name of the Plant
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Accessor for the price variable
     * @return the double price of the Plant
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * Mutator for the price variable
     * @param price the double price of the Plant
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    /**
     * Creates a String from important Plant info
     * @return a String version of the Plant
     */
    @Override public String toString()
    {
        String pattern = "###,###.00";
        DecimalFormat df = new DecimalFormat(pattern);
        return "Name: " + name + "\nPrice: $" + df.format(price) + "\n";
    }

    /**
     * Compares this Plant to another Plant for equality
     * @param obj another Object, possibly a Plant
     * @return whether this is equal to Obj or another Plant
     */

    @Override public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        else if (!(obj instanceof Plant))
            return false;
        else
        {
            Plant plant = (Plant) obj;
            return this.name.equals(plant.name) && (Double.compare(this.price, plant.price) == 0);
        }
    }

    /**
     * Compares two Plants to determine correct order
     * Compares first by name and then by price
     * @param plant another Plant Object to compare to this
     * @return an integer number specifying the correct order
     */
    public int compareTo(Plant plant)
    {
        if (!plant.getName().equals(this.getName()))
            return this.getName().compareTo(plant.getName());

        if (plant.getPrice() != (this.getPrice()))
            return Double.compare(this.getPrice(), plant.getPrice());

        return 0;
    }

}
