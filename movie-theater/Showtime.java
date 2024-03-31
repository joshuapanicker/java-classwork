/**
 * @author Joshua Panicker
 * CIS 36B, Lab 8
 */
public class Showtime {
    private final int SIZE = 9;
    private int ticketsSold;
    private String time;
    private String[][] seats = new String[SIZE][SIZE];

    /**
     * Default constructor for the Showtime class
     * Assigns the value "No showtime" to time,
     * Assigns the value 0 to ticketsSold
     * Calls initializeSeats method
     */
    public Showtime() {

    }

    /**
     * Constructor for the Showtime class
     * @param newTime the time the movie starts
     * Assigns the value 0 to ticketsSold
     * Calls initializes seats
     */
    public Showtime(String newTime)
    {
        time = newTime;
        ticketsSold = 0;
        initializeSeats();
    }

    /**
     * Called by the constructors only
     * Assigns the value of "X" to each
     * seat in the seats array
     */
    private void initializeSeats()
    {
        for (int row = 1; row <= SIZE; row++)
            for (int col = 1; col <= SIZE; col++)
            {
                setSeat(row, col, "X");
            }
    }

    /**
     * Returns the showtime of the movie
     * @return the time the movie starts
     */
    public String getTime() {
        return time;
    }

    /**
     * Returns the number of tickets
     * that have been sold
     * @return the number of tickets sold
     */
    public int getTicketsSold() {
        return ticketsSold;
    }

    /**
     * Returns the value stored for
     * a particular seat
     * @param row the seat row number
     * @param col the seat column number
     * @return an "X" or "T"
     */
    public String getSeat(int row, int col)
    {
        return seats[row - 1][col - 1];
    }

    /**
     * Updates the showtime of the movie
     * @param showTime the movie showtime
     */
    public void setShowTime(String newTime)
    {
        time = newTime;
    }

    /**
     * Updates a seat value to a T (Taken)
     * and increases ticketsSold variable
     * @param row the row number
     * @param col the column number
     * @param symbol the letter to assign to that seat position
     */
    public void setSeat(int row, int col, String symbol)
    {
        seats[row - 1][col - 1] = symbol;
    }

    /**
     * Prints out the seats array to the console
     * with row and column numbers
     * and with the placement of the SCREEN in
     * row 10
     * Along with the message "Here are the
     * available seats for <name>:"
     * Note that there are no <> around the output
     * @param name the name of the movie
     */
    public void printSeatingChart(String name) {
        System.out.printf("%10s\n", "  1 2 3 4 5 6 7 8 9");
        for(int row = 0; row < SIZE; row++) {
            System.out.printf("%d ", (row + 1));
            for(int col = 0; col < SIZE; col++)
            {
                System.out.printf("%s ", seats[row][col]);
            }
            System.out.println();
        }

        System.out.println("*******SCREEN******");
    }

    /**
     * Returns a String in the format
     * Showtime: <time>
     */
    @Override public String toString() {
        return "Showtime: " + time;
    }

}
