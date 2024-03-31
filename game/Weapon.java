/**
 * @author Joshua Panicker
 * CIS 36B, Lab 10
 */
public class Weapon extends Tool {
    private String defeatedBy;

    /**
     * One-argument constructor for Weapon
     * @param name the name of the weapon
     * Also assigns defeatedBy to an empty String
     * and healthPoints to 0
     */
    public Weapon(String name)
    {
        super(name, 0);
    }

    /**
     * Three-argument constructor for Weapon
     * @param name the name of the Weapon
     * @param health the total damage it can inflict
     * @param defeatedBy the Weapon that can defeat it
     */
    public Weapon(String name, int health, String defeatedBy)
    {
        super(name, health);
        this.defeatedBy = defeatedBy;
    }

    /**
     * Access the weapon that can defeat this Weapon
     * @return the weapon that can defeat this
     */
    public String getDefeatedBy() {
        return defeatedBy;
    }

    /**
     * Updates the weapon that can defeat this Weapon
     * @param defeatedBy the name of a new Weapon
     */
    public void setDefeatedBy(String defeatedBy)
    {
        this.defeatedBy = defeatedBy;
    }

    /**
     * toString for Weapon
     * @return <name of weapon> defeats <defeatedBy>
     */
    @Override public String toString() {
        return getName() + " defeats " + defeatedBy;
    }

    /**
     * Compares two weapons to determine if
     * they are both weapons and, if so, whether
     * they are equal (determined by the name
     * and defeated by)
     * @param obj another Object
     * @return whether the two are both potions
     * and have the same name.
     */
    @Override public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Weapon)) {
            return false;
        } else {
            Weapon w = (Weapon) obj;
            return this.defeatedBy == w.defeatedBy
                    && this.getName() == w.getName();
        }
    }

}
