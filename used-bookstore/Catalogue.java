/*
 * Catalogue.java
 * @author
 * CIS 36B, Lab 7
 */

import java.io.IOException;
import java.util.Scanner;

public interface Catalogue {
    /**
     * Reads from a file and populates the catalogue
     * @param input the Scanner used to read in the data
     */
    void populateCatalogue(Scanner input) throws IOException;


    /**
     * Searches for media in the catalogue
     * @param media the Media to locate
     * @return the location of media in
     * the catalogue
     */
    int binarySearch(Media media);

    /**
     * Sorts the catalogue into
     * ascending order
     */
    void bubbleSort();

    /**
     * Prints a menu of options to interact
     * with the catalogue
     */
    void printMenu();

    /**
     * Prints out the current catalogue
     */
    void printStock();
}