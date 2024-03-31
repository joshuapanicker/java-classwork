/**
 * CIS 36B
 * @author Joshua Panicker
 */
public abstract class Media {
    private String title;
    private double price;
    private int numCopies;

    /**
     * Default constructor for the Media class. Calls the three argument
     * constructor, passing in "title unknown", 0.0 and 0
     */
    public Media() {

    }

    /**
     * Constructor for the Media class
     * @param title     the media title
     * @param price     the sale price
     * @param numCopies the number of copies currently in stock
     */
    public Media(String title, double price, int numCopies)
    {
        this.title = title;
        this.price = price;
        this.numCopies = numCopies;
    }

    /**
     * Returns the title of the media
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the price of the media
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the current number of copies available
     * @return the number of copies
     */
    public int getNumCopies() {
        return numCopies;
    }

    /**
     * Returns whether there are more than 0 copies of the media
     * @return whether any copies are available to purchase
     */
    public boolean availableToPurchase() {
        return numCopies > 0;
    }

    /**
     * Sets the price to be a new price
     *
     * @param price the price of the media
     */
    public void setPrice(double price) {
        this.price = price;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Determines how to increase the price each time a copy gets sold (and media
     * gets rarer)
     */
    public abstract void updatePrice();

    /**
     * Decrements the number of copies when a media gets sold
     */
    public void decreaseCopies() {
        numCopies--;
    }

    /**
     * Updates the number of copies
     *
     * @param newCopies the number of copies to add to numCopies
     */
    public void updateNumCopies(int newCopies) {
        this.numCopies += newCopies;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        else if (!(obj instanceof Media))
            return false;
        else
        {
            Media media = (Media) obj;
            return this.getTitle().equals(media.getTitle()) && this.getPrice() == ((media.getPrice()));
        }
    }
}