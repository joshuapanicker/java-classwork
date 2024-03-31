/**
 * @author Joshua Panicker
 * CIS 36B, Lab 10
 */
public class GameCharacter {
    private String name;

    /**
     * One-argument constructor for the Character class
     * @param name the name of the Character
     */
    public GameCharacter(String name)
    {
        this.name = name;
    }

    /**
     * Accesses the name of the Character
     * @return the name of the Character
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the name of the Character
     * @param name the name of the Character
     */
    public void setName(String name)
    {
        this.name = name;
    }
}
