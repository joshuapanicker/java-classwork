/**
 * CIS 36B
 * @author Joshua Panicker
 */

import java.text.DecimalFormat;

public class Book extends Media { // update this signature!
    private String author;
    private String isbn;
    private static int numBooks = 0;

    /**
     * Default constructor for Book. Calls the 5 argument constructor Sets title
     * default to "title unknown" Sets author default to "author unknown" Sets price
     * to 0.0 Sets isbn to "0" Sets number of copies sold to 0
     */
    public Book()
    {
        this("title unknown", "author unknown", 0.0, "0", 0);
    }

    /**
     * Two argument constructor for the Book class. Calls the 5 argument constructor
     * Sets price to a default of 0.0, isbn to a default of "0" and numCopies to a
     * default of 0
     *
     * @param title  the book's title
     * @param author the book's author
     */
    public Book(String title, String author)
    {
        this(title, author, 0.0, "0", 0);
    }

    /**
     * Constructor for book. Calls media constructor
     * @param title     the book's title
     * @param author    the book's author
     * @param price     the book's price
     * @param isbn      the book's 7-digit isbn
     * @param numCopies the current num copies
     */
    public Book(String title, String author, double price, String isbn, int numCopies)
    {
        super(title, price, numCopies);
        this.author = author;
        this.isbn = isbn;
        numBooks += numCopies;
    }

    /**
     * Returns the first and last names of the author
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the 7 digit ISBN number
     *
     * @return the ISBN number
     */
    public String getISBN() {
        return isbn;
    }

    /**
     * Returns the total number of books
     *
     * @return the value of numBooks
     */
    public static int getNumBooks() {
        return numBooks;
    }

    public void updatePrice()
    {
        double price = getPrice();
        setPrice(price + 0.25);
    }

    /**
     * Updates numBooks variable by a specified amount
     * @param num the number of books to add
     */
    public static void updateNumBooks(int num)
    {
        numBooks += num;
    }

    /**
     * sets isbn
     * @param isbn the isbn of the book
     */
    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }
    /**
     * Overrides equals for Object using the formula given in class. For the
     * purposes of this assignment, we will consider two books to be equal on the
     * basis of title and author only
     *
     * @return whether two books have the same title and author
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        else if (!(obj instanceof Book))
            return false;
        else
        {
            Book book = (Book) obj;
            return this.getTitle().equals(book.getTitle()) && this.getAuthor().equals((book.getAuthor()));
        }
    }

    /**
     * Creates a book String in the following format Title: <title> Author: <author>
     * Price: $<price to two decimal places> ISBN #: <isbn> Copies: <numCopies> Note
     * that there are no <> around the values The <> are standard in programming to
     * indicate fill in here
     */
    @Override
    public String toString() {
        String pattern ="###,###.00";
        DecimalFormat df = new DecimalFormat(pattern);
        String priceStr = df.format(getPrice());

        return "Title: " + getTitle() + "\n" +
                "Author: " + getAuthor() + "\n" +
                "Price: $" + priceStr + "\n" +
                "ISBN#: " + isbn + "\n" +
                "Copies: " + getNumCopies() + "\n";
    }

    /**
     * Compares two Books. Returns 0 if the two Books are equal If the two books
     * have the same title returns compareTo of the authors Otherwise, returns
     * compareTo of the titles
     */

    public int compareTo(Book book) {
        if (!book.getTitle().equals(this.getTitle()))
            return this.getTitle().compareTo(book.getTitle());

        if (!book.getAuthor().equals(this.getAuthor()))
            return this.getAuthor().compareTo(book.getAuthor());

        return 0;
    }
}