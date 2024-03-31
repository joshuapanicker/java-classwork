/**
 * Nursery.java
 * @author
 * CIS 36B
 */

import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Nursery implements Catalogue<Plant> {
    private final int MAX_SIZE = 20;
    public final static int NEXT = 1;
    public Plant[] plants = new Plant[MAX_SIZE];
    public int numPlants = 0;

    public static void main(String[] args) {
        System.out.println("Nursery Stock Prototype\n");
        Nursery nursery = new Nursery();
        String choice = "";
        try {
            Scanner input = new Scanner(new File("nursery_stock.txt"));
            nursery.populateCatalogue(input);
            input.close();
            input = new Scanner(System.in);
            while (!choice.equalsIgnoreCase("X")) {
                nursery.printMenu();
                System.out.print("Enter your choice: ");
                choice = input.nextLine();
                if (choice.equalsIgnoreCase("V")) {
                    nursery.bubbleSort();
                    nursery.printStock();
                } else if (choice.equalsIgnoreCase("A")) {
                    Plant plant = nursery.addMenuOption(input);
                    nursery.printPlantNames();

                    try {
                        System.out.print("\nEnter the location to insert a new plant: ");
                        int loc = Integer.parseInt(input.nextLine());

                        nursery.addElement(plant, loc);
                        System.out.println("\n" + plant.getName() + " has been added!\n"); // Assume plant.getName()
                        nursery.printPlantNames();
                    } catch (Exception exception) { // Replace with specific exception(s)
                        System.out.println(exception.getMessage());
                        System.out.println("Returning to main menu...\n");
                    }
                } else if (choice.equalsIgnoreCase("R")) {
                    nursery.printPlantNames();

                    try {
                        // Example of intended action
                        System.out.print("Enter the number beside the plant to remove: ");
                        int number = Integer.parseInt(input.nextLine());
                        Plant plant = nursery.removeElement(number);

                        System.out.println("The following plant has been successfully removed: " + plant.getName() +
                                "\n");
                    } catch (Exception exception) { // Replace with specific exception(s)
                        System.out.println(exception.getMessage());
                        System.out.println("Returning to main menu...\n");
                    }
                } else if (choice.equalsIgnoreCase("F")) {
                    nursery.findMenuOption(input);
                } else if (choice.equalsIgnoreCase("X")) {
                    System.out.println("\n\nGoodbye!");
                } else {
                    System.out.println("\nInvalid menu option.\n");
                }
            }
            input.close();
        } catch (Exception exception) { // Replace with specific exception(s), like IOException
            System.out.println("Cannot open file nursery_stock.txt.\n\nGoodbye!");
        }
    }

    /**
     * Inserts an element into the catalogue
     * @param element the new element to insert
     * @param index the location to insert the element
     * @throws IndexOutOfBoundsException when the index < 0
     * or index > numElements
     * @throws IllegalStateException when the catalogue is full
     */
    @Override public void addElement(Plant element, int index) throws IndexOutOfBoundsException, IllegalStateException
    {
        if (index > numPlants || index < 0)
        {
            throw new IndexOutOfBoundsException("Cannot add element at index " + index);
        }
        else if (numPlants == MAX_SIZE)
        {
            throw new IllegalStateException("Catalogue is full and cannot add element. " +
                    "Please remove an element before inserting.");
        }

        for (int i = numPlants; i > index; i--)
        {
            plants[i] = plants[i - NEXT];
        }
        plants[index] = element;
        numPlants++;
    }

    /**
     * Removes an element from the catalogue
     * @param index the location of the element in the catalogue
     * @throws IndexOutOfBoundsException when the index < 0
     * or index >= numElements
     * @return the removed element
     */
    @Override public Plant removeElement(int index) throws IndexOutOfBoundsException
    {
        if (index > numPlants || numPlants == 0 || index < 0)
        {
            throw new IndexOutOfBoundsException("There is no plant " + index + ". Cannot remove.");
        }
        Plant plant = plants[index];
        for (int i = index; i < numPlants; i++)
        {
            plants[i] = plants[i + NEXT];
        }

        numPlants--;
        return plant;
    }

    /**
     * Prints the names of the plants
     */
    public void printPlantNames() {
        System.out.println("\nBelow is the current inventory:\n");
        for (int i = 0; i < numPlants; i++) {
            System.out.println(i + ". " + plants[i].getName());
        }
        System.out.println();
    }

    /**
     * Collects information about a plant to add for the 'A' menu option
     *
     * @param input a Scanner to read plant data from the console
     * @return a Plant object created based on user input
     */
    public Plant addMenuOption(Scanner input) {
        Plant plant;

        System.out.println("\nSelect one of the following:");
        System.out.println("\n1. Tree");
        System.out.println("2. Perennial");
        System.out.println("3. Bulb");
        System.out.print("\nEnter your choice: ");
        String choice = input.nextLine();
        if (choice.equals("1")) { // Tree
            System.out.println("\nEnter the new tree's information below: ");
            System.out.print("Name: ");
            String name = input.nextLine();
            System.out.print("Price: $");
            double price = Double.parseDouble(input.nextLine());
            System.out.print("Water-wise (yes/no): ");
            String waterWise = input.nextLine();
            boolean wat;
            if (waterWise.equals("yes")) {
                wat = true;
            } else {
                wat = false;
            }
            System.out.print("Native (yes/no): ");
            String nativ = input.nextLine();
            boolean ntv;
            if (nativ.equals("yes")) {
                ntv = true;
            } else {
                ntv = false;
            }
            System.out.print("Deciduous (yes/no): ");
            String deciduous = input.nextLine();
            boolean dcd;
            if (deciduous.equals("yes")) {
                dcd = true;
            } else {
                dcd = false;
            }
            System.out.print("Number of gallons: ");
            int numGallons = Integer.parseInt(input.nextLine());

            plant = new Tree(name, price, wat, ntv, dcd, numGallons);

        } else if (choice.equals("2")) { // Perennial
            System.out.println("\nEnter the new perennial's information below: ");
            System.out.print("Name: ");
            String name = input.nextLine();
            System.out.print("Price: $");
            double price = Double.parseDouble(input.nextLine());
            System.out.print("Water-wise (yes/no): ");
            String waterWise = input.nextLine();
            boolean wat;
            if (waterWise.equals("yes")) {
                wat = true;
            } else {
                wat = false;
            }
            System.out.print("Native (yes/no): ");
            String ntve = input.nextLine();
            boolean ntv;
            if (ntve.equals("yes")) {
                ntv = true;
            } else {
                ntv = false;
            }
            plant = new Perennial(name, price, wat, ntv);
        } else { // Bulb
            System.out.println("\nEnter the new bulb's information below: ");
            System.out.print("Name: ");
            String name = input.nextLine();
            System.out.print("Price: $");
            double price = Double.parseDouble(input.nextLine());
            System.out.print("Number of bulbs per packet: ");
            int numBulbs = Integer.parseInt(input.nextLine());
            System.out.print("Flowering season: ");
            String season = input.nextLine();
            plant = new Bulb(name, price, numBulbs, season);
        }
        return plant;
    }

    /**
     * Handles the 'F' menu option, which locates and displays a Plant
     * or prints that the plant is currently out of stock
     *
     * @param input a Scanner to read user input from the console
     */
    public void findMenuOption(Scanner input) {
        Plant plant;
        int index = -1;

        System.out.print("Enter the name of the plant: ");
        String name = input.nextLine();
        System.out.print("Enter the price of the plant: $");
        double price = Double.parseDouble(input.nextLine());
        System.out.print("Select one of the following:\n" +
                "\n" +
                "1. Tree\n" +
                "2. Perennial\n" +
                "3. Bulb\n" +
                "\n" +
                "Enter your choice: ");
        String choice = input.nextLine();
        if (choice.equals("1")) {
            plant = new Tree(name, price);
        } else if (choice.equals("2")) {
            plant = new Perennial(name, price);
        } else {
            plant = new Bulb(name, price);
        }
        bubbleSort();
        index = binarySearch(plant);
        if (index !=  -1) {
            System.out.println("\nWe have that plant in stock!\n" + plants[index]);
        } else {
            System.out.println("\nSorry! We don't have that plant in stock right now\n");
        }
    }


    /**
     * Reads from a file and populates the catalogue
     *
     * @param input the Scanner used to read in the data
     */
    @Override public void populateCatalogue(Scanner input) throws IOException {
        Plant plant;

        while (input.hasNextLine()) {
            String type = input.nextLine();
            String name = input.nextLine();
            double price = Double.parseDouble(input.nextLine());
            if (type.equals("Perennial")) {
                String lowWater = input.nextLine();
                boolean waterWise;
                if (lowWater.equals("low water")) {
                    waterWise = true;
                } else {
                    waterWise = false;
                }
                String isNative = input.nextLine();
                boolean nat;
                if (isNative.equals("native")) {
                    nat = true;
                } else {
                    nat = false;
                }
                plant = new Perennial(name, price, waterWise, nat);
            } else if (type.equals("Tree")) {
                String lowWater = input.nextLine();
                boolean waterWise;
                if (lowWater.equals("low water")) {
                    waterWise = true;
                } else {
                    waterWise = false;
                }
                String isNative = input.nextLine();
                boolean nat;
                if (isNative.equals("native")) {
                    nat = true;
                } else {
                    nat = false;
                }
                String isDeciduous = input.nextLine();
                boolean dec;
                if (isDeciduous.equals("evergreen")) {
                    dec = false;
                } else {
                    dec = true;
                }
                int numGallons = Integer.parseInt(input.nextLine());
                plant = new Tree(name, price, waterWise, nat, dec, numGallons);
            } else {
                int numBulbs = Integer.parseInt(input.nextLine());
                String season = input.nextLine();
                plant = new Bulb(name, price, numBulbs, season);
            }
            plants[numPlants] = plant;
            numPlants++;
        }
    }

    /**
     * Prints a menu of options to interact with the catalogue
     */
    public void printMenu() {
        System.out.println("Please select from one of the options: \n");
        System.out.println("V. View All Plants");
        System.out.println("A. Add a Plant");
        System.out.println("R. Remove a Plant");
        System.out.println("F. Find a Plant");
        System.out.println("X. Exit");
    }

    /**
     * Prints out the current catalogue
     */
    public void printStock() {
        System.out.println("\nCurrent Selection of " + numPlants + " Plants:\n");
        for (int i = 0; i < numPlants; i++) {
            System.out.println(plants[i]);
        }
    }
    /**
     * Sorts the catalogue into
     * ascending order
     */
    @Override public void bubbleSort()
    {
        for (int i = 0; i < numPlants - NEXT; i++)
        {
            for (int j = 0; j < numPlants - i - NEXT; j++)
            {
                if (plants[j].getName().compareTo(plants[j + NEXT].getName()) > 0)
                {
                    Plant temp = plants[j];
                    plants[j] = plants[j + NEXT];
                    plants[j + NEXT] = temp;
                }
            }
        }
    }
    /**
     * Searches for plant
     * @param plant the plant to locate
     * @return the index of plant
     * the catalogue
     */
    @Override public int binarySearch(Plant plant)
    {
        int low = 0;
        int high = numPlants - 1;
        int mid, compare;

        while (low <= high)
        {
            mid = (low + high)/2;
            compare = plant.getName().compareTo(plants[mid].getName());
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
}
