/**
 * @author Joshua Panicker
 * CIS 36B, Lab 10
 */

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class Game {
    public static Scanner input;
    private ArrayList<NPC> npc = new ArrayList<>();
    private ArrayList<Potion> potions = new ArrayList<>();
    private ArrayList<Weapon> weapons = new ArrayList<>();
    private ArrayList<String> descriptions = new ArrayList<>();
    private Player player;
    private static boolean gameOver = false;
    private static boolean win = false;
    private final String FILE_1 = "tools.txt";
    private final String FILE_2 = "npc.txt";
    private final String FILE_3 = "settings.txt";
    private int round = 0;
    public static final int NEXT1 = 1;
    public static final int NEXT2 = 2;
    public static final int NEXT3 = 3;
    public static final int NEXT4 = 4;
    public static final int NEXT5 = 5;
    public static final int NEXT6 = 6;
    public static final int NEXT7 = 7;
    public static final int NEXT15 = 15;



    public static void main(String[] args) throws IOException {
        Game game = new Game();
        String choice;

        input = new Scanner(System.in);

        game.readTools();
        game.readNpc();
        game.readSettings();

        System.out.println("Greetings, weary traveler!\n");
        System.out.print("Please entereth thy name: ");

        String name = input.next();
        game.player = new Player(name, NEXT15);

        System.out.println();
        System.out.printf("Fair %s, thou hast walked many miles on foot.\n", name);
        System.out.println("Ahead lies Wolf's Bane Castle");
        System.out.println("A word of caution: Choosest thy path well.");

        while (!gameOver) {
            System.out.println("\nDost thou wish to go: \n");
            System.out.println("L. Left");
            System.out.println("R. Right");
            System.out.println("Q. Quit");
            System.out.print("\nEnter thy choice: ");
            choice = input.next();
            System.out.println();
            if (choice.equals("Q") || choice.equals("q"))
                gameOver = true;
            else if (choice.equals("R") || choice.equals("r"))
            {
                game.moveRight();
            }
            else if (choice.equals("L") || choice.equals("l"))
            {
                game.moveLeft();
            }
            else
                System.out.println("Invalid choice! Thou must engage!");

            if (game.player.getNumHealth() <= 0)
            {
                gameOver = true;
                win = false;
            }
            else if (game.round == NEXT4)
            {
                gameOver = true;
                win = true;
            }
        }


        if(win) {
            System.out.println("\nThou enterth successfully and claim the chalice!");
            System.out.println("Thou winneth!");
        }
        System.out.println("\n****Game over!****");
    }

    /**
     * Function to move right
     */
    public void moveRight()
    {
        NPC npc1 = null;
        String setting = null;

        round++;
        if (round == NEXT1)
        {
            npc1 = npc.get(NEXT1);
            setting = descriptions.get(NEXT1);
        }
        else if (round == NEXT2)
        {
            npc1 = npc.get(NEXT3);
            setting = descriptions.get(NEXT3);
        }
        else if (round == NEXT3)
        {
            npc1 = npc.get(NEXT5);
            setting = descriptions.get(NEXT5);
        }
        else if (round == NEXT4)
        {
            npc1 = npc.get(NEXT7);
            setting = descriptions.get(NEXT7);
        }
        System.out.printf("%s\n", setting);
        System.out.println();

        if (npc1 instanceof Witch)
            witchEncounter((Witch)npc1, input);
        else if (npc1 instanceof Knight)
            knightEncounter((Knight)npc1, input);
    }

    /**
     * Function to move left
     */
    public void moveLeft()
    {
        NPC npc1 = null;
        String setting = null;

        round++;
        if (round == NEXT1)
        {
            npc1 = npc.get(0);
            setting = descriptions.get(0);
        }
        else if (round == NEXT2)
        {
            npc1 = npc.get(NEXT2);
            setting = descriptions.get(NEXT2);
        }
        else if (round == NEXT3)
        {
            npc1 = npc.get(NEXT4);
            setting = descriptions.get(NEXT4);
        }
        else if (round == NEXT4)
        {
            npc1 = npc.get(NEXT6);
            setting = descriptions.get(NEXT6);
        }
        System.out.printf("%s\n", setting);
        System.out.println();

        if (npc1 instanceof Witch)
            witchEncounter((Witch)npc1, input);
        else if (npc1 instanceof Knight)
            knightEncounter((Knight)npc1, input);
    }

    /**
     * Uses the linearSearch algorithm to search
     * for a Potion inside the potions ArrayList
     * @param potion the potion to search for
     * @return whether it is contained in the ArrayList
     * Must call the equals method from Tool for comparison
     */
    private int searchPotion(Potion potion) {
        return -1;
    }

    /**
     * Uses the linearSearch algorithm to search
     * for a Weapon inside the weapons ArrayList
     * @param weapon the weapon to search for
     * @return whether it is contained in the ArrayList
     * Must call the equals method from Tool for comparison
     */
    private int searchWeapon(Weapon weapon) {
        return -1;
    }

    /**
     * Determines interaction with the Witch
     * starting when the Witch approaches
     * @param witch the Witch the Player may choose to encounter
     * @param input the Scanner connected to console input
     */
    private void witchEncounter(Witch witch, Scanner input) {
        System.out.println("A witch approaches thee.");
        System.out.println("Dost thou wish to engage with her?\n");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.println();
        System.out.print("Enter thy choice: ");
        String choice = input.next();
        System.out.println();
        if (choice.equals("2"))
        {
            if (witch.isEvil())
            {
                System.out.println("Thou cannost escape. The witch casts a spell on thee.");
                System.out.println();
                engageWitch(witch);
            }
            else
            {
                System.out.println();
                System.out.println("Thou continuest on thy way without engaging.");
                player.updateNumHealth(NEXT5);
            }

        }
        else if (choice.equals("1"))
        {
            engageWitch(witch);
        }
        else
        {
            System.out.println("Invalid choice! Thou must engage!");
            System.out.println();
            engageWitch(witch);
        }
    }

    /**
     * Scripts the game play when the Player chooses
     * to engage with a Witch
     * @param witch the Witch to engage
     */
    private void engageWitch(Witch witch)
    {
        if (witch.isEvil())
        {
            System.out.printf("Oh foolish, %s!\n", player.getName());
            System.out.printf("Thou darest to engage with %s?\n", witch.getName());
            System.out.println();
            System.out.printf("The witch retrieves a vial filled with %s from her cloak.\n",
                    witch.getPotion().getName());
            System.out.printf("%s\n", witch.getDescription());
            System.out.println();
            player.updateNumHealth(-witch.getPotion().getHealthPoints());
            System.out.printf("Thou feelest faint. Current health: %d\n", player.getNumHealth());
        }
        else
        {
            System.out.printf("Greetings, fair %s!\n", player.getName());
            System.out.printf("Thou hath chosen well to engage with %s.\n", witch.getName());
            System.out.println();
            System.out.printf("The witch retrieves a vial filled with %s from her cloak.\n",
                    witch.getPotion().getName());
            System.out.printf("%s\n", witch.getDescription());
            System.out.println();
            player.updateNumHealth(witch.getPotion().getHealthPoints());
            System.out.printf("Thou feelest renewed vigor. Current health: %d\n", player.getNumHealth());
        }

    }

    /**
     * Determines interaction with the Knight
     * starting when the Knight approaches
     * @param knight the Knight the Player may choose to encounter
     * @param input the Scanner connected to console input
     */
    private void knightEncounter(Knight knight, Scanner input) {
        System.out.println("A knight approaches thee.");
        System.out.println("Dost thou wish to engage with him?\n");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.print("\nEnter thy choice: ");
        String choice = input.next();
        if (choice.equals("2"))
        {
            System.out.println();
            System.out.println("Thou continuest on thy way without engaging.");
        }
        else if (choice.equals("1"))
        {
            engageKnight(knight);
        }
        else
        {
            System.out.println();
            System.out.println("Invalid choice! Thou must engage!");
            engageKnight(knight);
        }
    }

    /**
     * Scripts the game play when the Player chooses
     * to engage with a Knight
     * @param knight the Knight to engage
     */
    private void engageKnight(Knight knight)
    {
        Weapon weapon;

        System.out.println();
        System.out.printf("The knight displays his weapon, a %s.\n", knight.getWeapon().getName());
        System.out.println();
        if (knight.isEvil())
        {
            System.out.printf("I challenge thee, %s!\n", player.getName());
            System.out.printf("Prepare to engage with Sir %s.\n", knight.getName());
            System.out.println();
            System.out.printf("%s\n", knight.getDescription());
            weapon = player.getWeapon(knight.getWeapon());
            if (weapon == null)
            {
                System.out.println();
                player.updateNumHealth(-knight.getWeapon().getHealthPoints());
                System.out.printf("Thou feelest faint. Current health: %d\n", player.getNumHealth());
            }
            else
            {
                System.out.printf("Congratulations! Thy %s defeated Sir %s's %s!\n",
                        weapon.getName(), knight.getName(), knight.getWeapon().getName());
            }
        }
        else
        {
            System.out.printf("Greetings, fair %s!\n", player.getName());
            System.out.printf("Thou hath chosen well to engage with %s.\n", knight.getName());
            System.out.println();
            System.out.printf("%s\n", knight.getDescription());
            System.out.println();
            player.addInventory(knight.getWeapon());
            player.printInventory();
        }
    }

    /**
     * Function to look up the potion by name
     * @param name the name of the potion
     * @return potion the matching potion
     */
    public Potion lookUpPotion(String name)
    {
        for (int i = 0; i < potions.size(); i++)
        {
            if (potions.get(i).getName().equals(name))
                return potions.get(i);
        }
        return null;
    }

    /**
     * Function to look up the weapon by name
     * @param name the name of the weapon
     * @return weapon the matching weapon
     */
    public Weapon lookUpWeapon(String name)
    {
        for (int i = 0; i < weapons.size(); i++)
        {
            if (weapons.get(i).getName().equals(name))
                return weapons.get(i);
        }
        return null;
    }

    /**
     *  Reads the NPC file
     */
    public void readNpc() throws IOException
    {
        File file = new File(FILE_2);
        Scanner inputFile = new Scanner(file);

        while (inputFile.hasNextLine())
        {
            String npcType, name, evilOrGood, canCast, potionName, weaponName, description;
            boolean isEvil, canCastSpell;

            npcType = inputFile.nextLine();
            name = inputFile.nextLine();
            evilOrGood = inputFile.nextLine();
            if (evilOrGood.equals("good"))
                isEvil = false;
            else
                isEvil = true;
            if (npcType.equals("Witch"))
            {
                canCast = inputFile.nextLine();
                if (canCast.equals("can cast"))
                    canCastSpell = true;
                else
                    canCastSpell = false;
                potionName = inputFile.nextLine();
                description = inputFile.nextLine();
                Potion potion = lookUpPotion(potionName);
                Witch witch = new Witch(name, isEvil, description, potion, canCastSpell);
                npc.add(witch);
            }
            else if (npcType.equals("Knight"))
            {
                weaponName = inputFile.nextLine();
                description = inputFile.nextLine();
                Weapon weapon = lookUpWeapon(weaponName);
                Knight knight = new Knight(name, isEvil, description, weapon);
                npc.add(knight);
            }
            if (inputFile.hasNextLine())
                inputFile.nextLine();

        }
    }

    /**
     *  Reads the Tools file
     */
    public void readTools() throws IOException
    {
        File file = new File(FILE_1);
        Scanner inputFile = new Scanner(file);
        String temp;

        while (inputFile.hasNextLine())
        {
            String toolType, name, defeatedBy, harmfulOrNot;
            boolean isHarmful;
            int health;

            toolType = inputFile.nextLine();
            name = inputFile.nextLine();
            if (toolType.equals("Weapon")) {
                defeatedBy = inputFile.nextLine();
                health = inputFile.nextInt();
                Weapon weapon = new Weapon(name, health, defeatedBy);
                weapons.add(weapon);
            }
            else
            {
                harmfulOrNot = inputFile.nextLine();
                if (harmfulOrNot.equals("true"))
                    isHarmful = true;
                else
                    isHarmful = false;
                health = inputFile.nextInt();
                Potion potion = new Potion(name, health, isHarmful);
                potions.add(potion);
            }
            if (inputFile.hasNextLine())
            {
                temp = inputFile.nextLine();
                temp = inputFile.nextLine();
            }
        }
    }

    /**
     *  Reads the Settings file
     */
    public void readSettings() throws IOException
    {
        File file= new File(FILE_3);
        Scanner inputFile = new Scanner(file);

        while (inputFile.hasNextLine())
        {
            String description;

            description = inputFile.nextLine();
            descriptions.add(description);
        }
    }
}
