/**
 * @author Joshua Panicker
 * CIS 36B, Lab 10
 */
public class Tool {
    private String name;
    private int healthPoints;

    /**
     * Two-argument constructor for a Tool
     * @param name the tool's name
     * @param healthPoints the total damage it can inflict in
     * health points
     */
    public Tool(String name, int healthPoints)
    {
        this.name = name;
        this.healthPoints = healthPoints;
    }

    /**
     * Accesses the tool's name
     * @return the name of the tool
     */
    public String getName() {
        return name;
    }

    /**
     * Accesses the amount of damage the
     * tool can inflict in health points
     * @return the damage in health points
     */
    public int getHealthPoints() {
        return healthPoints;
    }

    /**
     * Assigns a new name to the tool
     * @param name the tool's name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Updates the amount of damage
     * the tool can inflict
     * @param healthPoints the amount of
     * damage in health points
     */
    public void setHealthPoints(int healthPoints)
    {
        this.healthPoints += healthPoints;
    }

    /**
     * Compares two objects to determine if
     * they are both Tools and, if so, whether
     * they are equal (determined by the name
     * of the Tools
     * @param obj another Object
     * @return whether the two are both Tools
     * and have the same name.
     */
    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Tool)) {
            return false;
        } else {
            Tool a = (Tool) o;
            return this.name == a.name;
        }
    }
}
