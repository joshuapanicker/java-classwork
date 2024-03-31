/**
 * CIS 36B Lab - Working with classes + objects + ArrayLists + sorting algorithms
 * to create a program that updates your work on prior lab to help an English
 * user to build their vocab in Spanish
 * @author Josh
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class FlashCard {
    public static final int ENGLISH_TO_SPANISH = 1;
    public static final int SPANISH_TO_ENGLISH = 2;
    public static final int ADD_WORD = 3;
    public static final int REMOVE_A_WORD = 4;
    public static final int OPTION_QUIT = 5;
    public static final int NEXT = 1;

    ArrayList<Card> cards;
    Scanner scanner;

    public FlashCard()
    {
        this.cards = new ArrayList<Card>();
    }

    /**
     * Uses the bubble sort algorithm to sort the deck
     * in alphabetical order by the English words
     * @param cards the ArrayList of Cards
     */
    public static void bubbleSortEnglish(ArrayList<Card> cards)
    {
        for(int i = 0; i < cards.size() - NEXT; i++) {
            for(int j = 0; j < cards.size() - i - NEXT; j++) {
                if(cards.get(j).english.compareTo(cards.get(j + NEXT).english) > 0) {
                    Card temp = cards.get(j);
                    cards.set(j, cards.get(j + NEXT));
                    cards.set(j + NEXT, temp);
                }
            }
        }
    }

    /**
     * Uses the bubble sort algorithm to sort the deck
     * in alphabetical order by the Spanish words
     * @param cards the ArrayList of Cards
     */
    public static void bubbleSortSpanish(ArrayList<Card> cards) {
        for(int i = 0; i < cards.size() - NEXT; i++) {
            for(int j = 0; j < cards.size() - i - NEXT; j++) {
                if(cards.get(j).spanish.compareTo(cards.get(j + NEXT).spanish) > 0) {
                    Card temp = cards.get(j);
                    cards.set(j, cards.get(j + NEXT));
                    cards.set(j + NEXT, temp);
                }
            }
        }
    }

    /**
     * Prints the list in alphabetical order by English words
     */
    public void print()
    {
        System.out.println("Below is the alphabetical list of cards:");
        System.out.println();
        for (int i = 0; i < cards.size(); i++)
        {
            System.out.printf("%d. %s: %s\n", i, cards.get(i).english, cards.get(i).spanish);
        }
        System.out.println();
    }

    /**
     * Asks for user input for spanish translation of english given words
     */
    public void practiceEnglishToSpanish()
    {
        int correct = 0;
        String spanish;

        scanner.useDelimiter("\n");
        bubbleSortEnglish(cards);
        for (int i = 0; i < cards.size(); i++)
        {
            System.out.println();
            System.out.printf("%d. %s\n", i, cards.get(i).english);
            System.out.print("Enter the translation = ");
            spanish = scanner.next();
            if (spanish.equals(cards.get(i).spanish))
            {
                System.out.println("Correct!");
                correct++;
            }
            else
            {
                System.out.printf("The correct translation is %s\n", cards.get(i).spanish);
            }
        }
        System.out.printf("Total correct: %d/%d\n", correct, cards.size());
    }

    /**
     * Asks for user input for English translation of Spanish given words
     */
    public void practiceSpanishToEnglish()
    {
        int correct = 0;
        String english;

        scanner.useDelimiter("\n");
        bubbleSortSpanish(cards);
        for (int i = 0; i < cards.size(); i++)
        {
            System.out.println();
            System.out.printf("%d. %s\n", i, cards.get(i).spanish);
            System.out.print("Enter the translation = ");
            english = scanner.next();
            if (english.equals(cards.get(i).english))
            {
                System.out.println("Correct!");
                correct++;
            }
            else
            {
                System.out.printf("The correct translation is %s\n", cards.get(i).english);
            }
        }
        System.out.printf("Total correct: %d/%d\n", correct, cards.size());
        bubbleSortEnglish(cards);
    }

    /**
     * Adds a spanish and english word per user request
     * @param english the english word
     * @param spanish the spanish word
     */
    public void addWord(String english, String spanish)
    {
        Card card = new Card(english, spanish);
        cards.add(card);
        bubbleSortEnglish(cards);
    }

    /**
     * Removes a spanish and english word per user request
     * @param index the index of the word in the list
     */
    public void removeWord(int index)
    {
        if (cards.isEmpty())
        {
            System.out.println("There are no words to remove.");
            return;
        }
        if (index < 0 || index >= cards.size())
        {
            System.out.println("Invalid choice. Please try again!");
            return;
        }
        bubbleSortEnglish(cards);
        System.out.printf("Removing: %s: %s\n", cards.get(index).english, cards.get(index).spanish);
        cards.remove(index);
    }

    /**
     * Reads the file
     */
    public void readFile() throws IOException
    {
        String english;
        String spanish;

        File infile = new File("words.txt");
        Scanner input = new Scanner(infile);

        while (input.hasNext())
        {
            english = input.nextLine();
            spanish = input.nextLine();
            addWord(english, spanish);
        }
    }

    public static void main(String[] args) throws IOException {
        String english, spanish;
        int option = 0;
        int index;
        String optionStr;
        FlashCard flashCard = new FlashCard();

        flashCard.scanner = new Scanner(System.in);
        flashCard.scanner.useDelimiter("\n");

        flashCard.readFile();

        System.out.println("***Spanish Flash Cards!***");
        while (option != OPTION_QUIT)
        {
            System.out.println();
            System.out.println("Please select from the menu options below:");
            System.out.println();
            System.out.println("1. Practice English to Spanish");
            System.out.println("2. Practice Spanish to English");
            System.out.println("3. Add a Word");
            System.out.println("4. Remove a Word");
            System.out.println("5. Exit");
            System.out.println();
            System.out.print("Enter your choice: ");

            optionStr = flashCard.scanner.next();
            if (optionStr.equals("1") ||
                    optionStr.equals("2") ||
                    optionStr.equals("3") ||
                    optionStr.equals("4") ||
                    optionStr.equals("5"))
            {
                option = Integer.parseInt(optionStr);
            }
            else
            {
                option = -1;
            }
            if (option == ENGLISH_TO_SPANISH)
            {
                flashCard.practiceEnglishToSpanish();
            }
            else if (option == SPANISH_TO_ENGLISH)
            {
                flashCard.practiceSpanishToEnglish();
            }
            else if (option == ADD_WORD)
            {
                flashCard.print();
                System.out.print("Enter the word to add in English: ");
                english = flashCard.scanner.next();
                System.out.printf("Enter %s's Spanish translation: ", english);
                spanish = flashCard.scanner.next();
                flashCard.addWord(english, spanish);
            }
            else if (option == REMOVE_A_WORD)
            {
                flashCard.print();
                System.out.print("Enter the number of the word to remove: ");
                index = flashCard.scanner.nextInt();
                flashCard.removeWord(index);
            }
            else if (option == OPTION_QUIT)
            {
                System.out.println("Goodbye!");
                System.out.println();
            }
            else
            {
                System.out.println("Invalid choice. Please try again!");
                System.out.println();
            }
        }
        flashCard.scanner.close();
    }





}

