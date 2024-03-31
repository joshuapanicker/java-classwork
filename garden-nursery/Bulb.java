/**
 * Bulb.java
 * @author
 * CIS 36B
 */

import java.text.DecimalFormat;

public class  Bulb extends Plant{ //update to inherit from Plant
    private int numBulbs;
    private String season;

    /**
     * Default constructor for Bulb
     * Assigns "No name" to name, -1 to price, 0 to numBulbs and "No season" to season
     * Must call the 4-argument constructor
     */
    public Bulb()
    {
        this("No name", -1, 0, "No season");
    }

    /**
     * A two argument constructor for Bulb
     * Assigns values to name and price but sets
     * numBulbs to 0 and season to "No season"
     * @param name the plant's name
     * @param price the cost of the plant
     */
    public Bulb(String name, double price)
    {
        this(name, price, 0, "No season");
    }

    /**
     * A four argument constructor for Bulb
     * @param name the plant's name
     * @param price the cost of the plant
     * @param numBulbs the total number of bulbs per package
     * @param season the season in which the bulbs will bloom
     */
    public Bulb(String name, double price, int numBulbs, String season)
    {
        super(name, price); // Calls the Plant constructor
        this.numBulbs = numBulbs;
        this.season = season;
    }

    public int getNumBulbs() {
        return numBulbs;
    }

    public void setNumBulbs(int numBulbs) {
        this.numBulbs = numBulbs;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    /**
     * toString for the Bulb class in the following format
     * Name: name
     * Price: $ price written to 2 decimal places
     * Season: season
     * Number of bulbs per pack: number of bulbs
     */
    @Override
    public String toString()
    {
        String pattern = "###,###.00";
        DecimalFormat df = new DecimalFormat(pattern);

        return "Name: " + getName() + "\n" +
                "Price: $" + df.format(getPrice()) + "\n" +
                "Season: " + season + "\n" +
                "Number of bulbs per pack: " + numBulbs + "\n";
    }
}

