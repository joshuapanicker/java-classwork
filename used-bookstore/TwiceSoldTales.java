/**
 * CIS 36B
 * @author Joshua Panicker
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public final class TwiceSoldTales implements Catalogue {
    private ArrayList<Book> books = new ArrayList<Book>();
    private static final String FILENAME = "books.txt";
    public static final int NEXT = 1;
    public static final double PERCENT = 0.25;

    public TwiceSoldTales()
    {
    }

    /**
     * populates the catalogue
     * @param input the scanner
     */
    public void populateCatalogue(Scanner input) throws IOException
    {
        while (input.hasNext())
        {
            String title, author, isbn, priceStr, numCopiesStr;
            double price;
            int numCopies;
            Book book;

            title = input.nextLine();
            author = input.nextLine();
            priceStr = input.nextLine();
            price = Double.parseDouble(priceStr);
            isbn = input.nextLine();
            numCopiesStr = input.nextLine();
            numCopies = Integer.parseInt(numCopiesStr);
            book = new Book(title, author, price, isbn, numCopies);
            books.add(book);
        }
    }


    /**
     * Searches for media in the catalogue
     * @param media the media to locate
     * @return the index of media in
     * the catalogue
     */
    public int binarySearch(Media media)
    {
        int low = 0;
        int high = books.size() - 1;
        int mid, compare;

        while (low <= high)
        {
            mid = (low + high)/2;
            compare = media.getTitle().compareTo(books.get(mid).getTitle());
            if (compare == 0)
            {
                return mid;
            }
            else if (compare < 0)
            {
                high = mid - 1;
            }
            else
            {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Sorts the catalogue into
     * ascending order
     */
    public void bubbleSort()
    {
        for (int i = 0; i < books.size() - NEXT; i++)
        {
            for (int j = 0; j < books.size() - i - NEXT; j++)
            {
                if (books.get(j).getTitle().compareTo(books.get(j + NEXT).getTitle()) > 0)
                {
                    Book temp = books.get(j);
                    books.set(j, books.get(j + NEXT));
                    books.set(j + NEXT, temp);
                }
            }
        }
    }

    /**
     * Prints a menu of options to interact
     * with the catalogue
     */
    public void printMenu()
    {
        Scanner input = new Scanner(System.in);
        String choice;
        boolean repeats = true;

        System.out.printf("We currently have %d books in stock!\n", Book.getNumBooks());
        System.out.println();

        while (repeats)
        {
            System.out.println("Please select from one of the options: ");
            System.out.println();
            System.out.println("A. Search for a book to purchase");
            System.out.println("B. Sell a book");
            System.out.println("C. Print catalogue");
            System.out.println("X. Exit");
            System.out.println();
            System.out.print("Enter your choice: ");
            choice = input.next();
            if (choice.equals("X") || choice.equals("x")) {
                System.out.println();
                System.out.println("Please come again!");
                repeats = false;
            }
            else if (choice.equals("A") || choice.equals("a"))
            {
                String title, author;
                int index;

                System.out.println();
                System.out.println("Enter the book information below: ");
                System.out.println();
                input.useDelimiter("\n");
                System.out.print("Title: ");
                title = input.next();
                System.out.print("Author: ");
                author = input.next();
                System.out.println();

                Book book1 = new Book(title, author);

                index = binarySearch(book1);

                if (index != -1)
                {
                    Book bookInCatalogue = books.get(index);

                    if (bookInCatalogue.availableToPurchase())
                    {
                        String yesOrNo;

                        System.out.printf("We have %s in stock!\n", title);
                        System.out.println();
                        System.out.printf("%s\n", bookInCatalogue.toString());
                        System.out.print("Would you like to purchase it (y/n): ");
                        yesOrNo = input.next();
                        if (yesOrNo.equals("y") || yesOrNo.equals("Y"))
                        {
                            bookInCatalogue.updateNumCopies(-1);
                            Book.updateNumBooks(-1);
                            bookInCatalogue.updatePrice();
                            System.out.println("Thank you for your purchase!");
                            System.out.println();
                        }
                        else if (yesOrNo.equals("n") || yesOrNo.equals("N"))
                        {
                            System.out.println();
                        }
                        else
                        {
                            System.out.println();
                            System.out.println("Invalid option!");
                        }
                    }
                    else
                        System.out.println("Not available.");
                }
                else {
                    System.out.println("Sorry! We don't carry that title right now.");
                    System.out.println();
                }
            }
            else if (choice.equals("B") || choice.equals("b"))
            {
                String title, author, isbn;
                double price;
                int index;

                System.out.println("Enter the book information below: ");
                System.out.println();
                System.out.print("Title: ");
                input.useDelimiter("\n");
                title = input.next();
                System.out.print("Author: ");
                author = input.next();

                Book book = new Book(title, author);
                index = binarySearch(book);

                if (index != -1)
                {
                    Book bookInCatalogue = books.get(index);

                    price = bookInCatalogue.getPrice() * PERCENT;
                    bookInCatalogue.updateNumCopies(1);
                    Book.updateNumBooks(1);
                    System.out.printf("Thank you! We will purchase the book for $%.2f\n", price);
                    System.out.println();
                }
                else
                {
                    System.out.print("ISBN #: ");
                    isbn = input.next();
                    System.out.print("Please enter the price you paid: $");
                    price = input.nextDouble();
                    book.updateNumCopies(1);
                    Book.updateNumBooks(1);
                    book.setPrice(price);
                    book.setIsbn(isbn);
                    books.add(book);
                    bubbleSort();
                    System.out.printf("Thank you! We will purchase the book for $%.2f\n", price * PERCENT);
                    System.out.println();
                }
            }
            else if (choice.equals("C") || choice.equals("c"))
            {
                printStock();
                System.out.println();
            }
            else {
                System.out.println();
                System.out.println("Invalid option!");
                System.out.println();
                System.out.println();
            }
        }
    }


    /**
     * Prints out the current catalogue
     */
    public void printStock()
    {
        System.out.println("Current Selection of Books:");
        for (int i = 0; i < books.size(); i++)
            System.out.printf("%s\n", books.get(i).toString());
    }

    public static void main(String[] args) throws IOException {
        TwiceSoldTales tales = new TwiceSoldTales();
        File infile = new File("books.txt");
        Scanner input = new Scanner(infile);
        boolean repeats = true;

        System.out.println("Welcome to Twice Sold Tales!");

        tales.populateCatalogue(input);
        tales.bubbleSort();
        System.out.println();
        tales.printMenu();
    }
}
