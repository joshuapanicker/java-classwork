import java.io.IOException;
import java.util.Scanner;

public class ArtLibrary implements Catalogue<Art> { // update to inherit from Catalogue

    private Art[] artworks;
    private int numWorks;

    /**
     * Initializes artworks to a default size of 10
     * and numWorks to 0
     */
    public ArtLibrary()
    {
        this(10);
    }


    /**
     * Initializes artworks to the given size
     * and numWorks to 0
     * @param size the size of the array
     * @throws IllegalArgumentException if size
     * is negative
     */
    public ArtLibrary(int size) throws IllegalArgumentException
    {
        artworks = new Art[size];
        numWorks = 0;
    }
    /**
     * Increases the size of artworks by 10
     * more than its current length by creating
     * a new array, copying the data from the old
     * array into the new array and then assigning
     * the new array to become artworks.
     * numWorks remains unchanged
     * Used when array is full and a new element
     * needs to be added
     */
    private void resize()
    {
        int length = artworks.length + 10;
        Art temp[] = new Art[length];

        for (int i = 0; i < artworks.length; i++)
        {
            temp[i] = artworks[i];
        }
        artworks = temp;
    }

    @Override
    public void populateCatalogue(Scanner input) throws IOException
    {

    }

    @Override
    public void appendElement(Art element) {

    }

    @Override public Art removeElement(int index)
    {
        return null;
    }

    @Override public boolean removeElement(Art art)
    {
        return false;
    }

    @Override public Art get(int index) throws IndexOutOfBoundsException
    {
        return artworks[index];
    }

    @Override public void set(int index, Art art) throws IndexOutOfBoundsException
    {
    }

    @Override public int binarySearch(Art art)
    {
        return -1;
    }

    @Override public void bubbleSort()
    {
    }

    @Override
    public void printMenu() {

    }

    @Override
    public void printStock() {

    }

    @Override
    public void printNames() {

    }

}
