/**
 * @author Joshua Panicker
 * CIS 36B, Lab 10
 */
public class NPC extends GameCharacter {
    private boolean isEvil;
    private String description;

    /**
     * Four-argument constructor for an NPC
     * @param name the NPC's name
     * @param isEvil whether the NPC is evil or good
     * @param description scripting for encountering this NPC
     */
    public NPC(String name, boolean isEvil, String description)
    {
        super(name);
        this.isEvil = isEvil;
        this.description = description;
    }

    /**
     * Accesses whether the NPC is evil
     * @return whether the NPC is evil
     */
    public boolean isEvil() {
        return isEvil;
    }

    /**
     * Accesses the scripting for encountering this NPC
     * @return the scripting for encountering this NPC
     */
    public String getDescription() {
        return description;
    }

    /**
     * Updates whether the NPC is evil
     * @param isEvil whether the NPC is evil
     */
    public void setEvil(boolean isEvil)
    {
        this.isEvil = isEvil;
    }

    /**
     * Updates the scripting for encountering this NPC
     * @param description the scripting for encountering this NPC
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

}
