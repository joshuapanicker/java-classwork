/**
 * @author Joshua Panicker
 * CIS 36B, Lab 8
 */
import java.text.DecimalFormat;

public class Movie {
    private String name;
    private double price;
    private Showtime showtime;
    private int seatNumbers;

    /**
     * Default constructor for the Movie
     * class. Initializes name to No name
     * price to 0.0, ticketsSold to 0,
     * calls the Showtime default constructor
     */
    public Movie() {

    }

    /**
     * Constructor for the Movie class
     * @param name the name of the movie
     * @param price the movie's price
     * @param showTime the showtime
     */
    public Movie(String newName, double newPrice, Showtime newShowtime)
    {
        this.name = newName;
        this.price = newPrice;
        this.showtime = newShowtime;
    }

    /**
     * Returns the name of the movie
     * @return the movie name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the movie
     * @return the movie price
     */
    public double getPrice()
    {
        return price;
    }

    /**
     * Returns the Showtime
     * @return the showtime
     */
    public Showtime getShowtime()
    {
        return showtime;
    }

    /**
     * Updates the name of the movie
     * @param name the new movie name
     */
    public void setName(String newName)
    {
        name = newName;
    }

    /**
     * Updates the price of the movie
     * @param price the new price for the movie
     */
    public void setPrice(double newPrice)
    {
        price = newPrice;
    }

    /**
     * Updates the value of the showtime variable
     * @param newShowtime the updated movie showtime
     */
    public void setShowtime(Showtime newShowtime)
    {
        showtime = newShowtime;
    }

    /**
     * Converts a movie into a String
     * in the format:
     * <name>
     * Ticket price: $<price>
     * <showtime>
     * Note that price should be to exactly 2 decimal places
     * Hint: call the Showtime toString()
     * Note that there are no <> around the output
     * The <> mean fill in here
     */
    @Override public String toString() {
        String pattern ="###,###.00";
        DecimalFormat df = new DecimalFormat(pattern);
        String priceStr = df.format(price);

        return name + "\n" +
                "Ticket price: $" + priceStr + "\n" +
                showtime.toString();
    }
}
