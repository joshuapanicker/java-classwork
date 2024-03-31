/**
 * @author Joshua Panicker
 * CIS 36B, Lab 8
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

public class Theater {
    private static Scanner input;

    public static void main(String[] args) throws IOException {
        int row, col, rowCol, seats, total = 0;
        String name, showTime;
        double price;
        String choice;
        ArrayList<Movie> movies = new ArrayList<>();
        Movie movie = null;
        final String FILE_NAME = "movies.txt";
        File file = new File(FILE_NAME);

        input = new Scanner(System.in);

        readFile(movies, file);
        System.out.println("Welcome to the CineBucks Movie Theater!\n");

        movie = selectMovie(movies);
        buySeats(movie);
    }

    /**
     * Selects a movie
     * @param movies the arraylist of movies
     * @return the movie
     */
    public static Movie selectMovie(ArrayList<Movie> movies )
    {
        int option = 0;
        Movie movie;
        boolean repeats = true;

        System.out.println("Below are the showtimes for 2022 Oscar Best Picture Vintage Film Festival:");
        System.out.println();
        for (int i = 0; i < movies.size(); i++)
        {
            System.out.printf("Option %d:\n", i + 1);
            System.out.printf("%s\n", movies.get(i).toString());
            System.out.println();
        }
        while (repeats)
        {
            System.out.print("Enter the option number of the movie: ");
            option = input.nextInt();
            System.out.println();
            if (option <= 0 || option > movies.size())
            {
                System.out.println("Invalid option. Please try again!");
                System.out.println();
            }
            else
                repeats = false;
        }

        movie = movies.get(option - 1);
        System.out.printf("You selected %s!\n", movie.getName());
        System.out.println();
        return movie;
    }
    /**
     * buys the seats
     * @param movie the specified movie
     */
    public static void buySeats(Movie movie) throws IOException
    {
        boolean repeats = true;
        Showtime showtime = movie.getShowtime();
        int row, col, ticketCount = 0;
        double totalPrice = 0;
        String seatId, choice, seat;

        while (repeats)
        {
            System.out.printf("Here are the available seats for %s:\n", movie.getName());
            System.out.println();
            showtime.printSeatingChart(movie.getName());
            System.out.println();
            System.out.print("Enter the rowcol of the seat to purchase: ");
            seatId = input.next();
            row = Character.getNumericValue(seatId.charAt(0));
            col = Character.getNumericValue(seatId.charAt(1));
            seat = showtime.getSeat(row, col);
            if (seat.equals("T"))
            {
                System.out.println();
                System.out.println("Sorry! That seat is already taken!");
                System.out.println("Please try again.");
            }
            else
            {
                System.out.println("Your ticket is confirmed!");
                System.out.println();
                showtime.setSeat(row, col, "O");
                ticketCount++;
                totalPrice = totalPrice + movie.getPrice();
                System.out.printf("Here are the available seats for %s:\n", movie.getName());
                System.out.println();
                showtime.printSeatingChart(movie.getName());
                System.out.println();
                System.out.print("Another seat (y/n)?: ");
                choice = input.next();
                System.out.println();
                if (!choice.equals("y") && !choice.equals("Y"))
                    repeats = false;
            }
        }

        System.out.printf("You ordered %d tickets for %s at %s.\n", ticketCount, movie.getName(), showtime.getTime());
        System.out.printf("Your total today is: $%.2f.\n", totalPrice);
        System.out.println();
        System.out.println("Please open receipt.txt to view your receipt.");
        System.out.println();
        System.out.println("Thank you! Please come again.");
        System.out.println();
        printReceipt(movie, ticketCount, totalPrice);
    }

    /**
     * prints the receipt onto to the new file
     * @param movie the specified movie
     * @param ticketCount the count of tickets
     * @param totalPrice the total price
     */
    public static void printReceipt(Movie movie, int ticketCount, double totalPrice) throws IOException
    {
        File outfile = new File("receipt.txt");
        PrintWriter out = new PrintWriter(outfile);
        Showtime showtime;

        showtime = movie.getShowtime();
        out.println("****Receipt****");
        out.println();
        out.printf("You ordered %d tickets for %s at %s.\n", ticketCount, movie.getName(), showtime.getTime());
        out.printf("Total: $%.2f.\n", totalPrice);
        out.println();
        out.print("Enjoy the show!");
        out.close();
    }

    /**
     * reads the file
     * @param movies the arraylist of movies
     * @param file file variable
     */
    public static void readFile(ArrayList<Movie> movies, File file) throws IOException
    {
        File infile = new File("movies.txt");
        Scanner input = new Scanner(infile);

        input.useDelimiter("\n");
        while (input.hasNextLine())
        {
            double price;
            String name, time, seatId;
            int row, col, rowCol, seats, total = 0;

            name = input.next();
            price = input.nextDouble();
            time = input.next();
            seats = input.nextInt();
            Showtime showTime = new Showtime(time);

            for (int i = 0; i < seats; i++)
            {
                if (input.hasNext())
                {
                    seatId = input.next();
                    row = Character.getNumericValue(seatId.charAt(0));
                    col = Character.getNumericValue(seatId.charAt(1));
                    showTime.setSeat(row, col, "T");
                }
            }

            Movie movie = new Movie(name, price, showTime);
            movies.add(movie);
        }
    }
}

		