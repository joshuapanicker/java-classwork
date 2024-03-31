/**
 * @author Joshua Panicker
 * CIS 36B, Lab 10
 */
import java.sql.Array;
import java.util.ArrayList;

public class Player extends GameCharacter {
    private int numHealth;
    private ArrayList<Weapon> inventory;

    /**
     * Two argument constructor for Player
     * @param name the Player name
     * @param health the starting amount
     * of health points
     * Also initializes an empty ArrayList
     * of Weapons for the inventory
     */
    public Player(String name, int health)
    {
        super(name);
        this.numHealth = health;
        this.inventory = new ArrayList<Weapon>();
    }

    /**
     * Access the current health points of the Player
     * @return the Player's current health points
     */
    public int getNumHealth() {
        return numHealth;
    }

    /**
     * Updates (increases or decreases) health points
     * of the Player
     * @param numHealth the total health of the Player
     */
    public void updateNumHealth(int numHealth)
    {
        this.numHealth += numHealth;
        if (this.numHealth < 0)
            this.numHealth = 0;
    }

    /**
     * Adds a new weapon to inventory
     * @param weapon a new Weapon to add
     */
    public void addInventory(Weapon weapon)
    {
        inventory.add(weapon);
    }

    /**
     * returns the weapon to use against the knight
     * @param againstWeapon the weapon it goes against
     * @return weapon
     */
    public Weapon getWeapon(Weapon againstWeapon)
    {
        for (int i = 0; i < inventory.size(); i++)
        {
            if (againstWeapon.getDefeatedBy().equals(inventory.get(i).getName()))
            {
                return inventory.get(i);
            }
        }
        return null;
    }

    /**
     * Uses the linearSearch algorithm to locate
     * a Weapon inside the inventory
     * @param weapon the Weapon to locate
     * @return whether the Weapon is contained
     * in the Player's inventory
     */
    public boolean searchInventory(Weapon weapon) {
        for (int i = 0; i < inventory.size(); i++)
        {
            if (inventory.get(i).equals(weapon))
                return true;
        }
        return false;
    }

    /**
     * Displays the current inventory to the console
     */
    public void printInventory()
    {
        System.out.println("***Here is your current inventory:***");
        for (int i = 0; i < inventory.size(); i++)
        {
            System.out.printf("%d. %s\n", i, inventory.get(i).toString());
        }
    }

}
