/**
 * @author Joshua Panicker
 * CIS 36B, Lab 10
 */
public class Witch extends NPC {
    private boolean canCastSpell;
    private Potion potion;

    /**
     * Five-argument constructor for Witch
     * @param name the name of the Witch
     * @param isEvil whether the Witch is evil
     * @param description scripting for encountering this Witch
     * @param potion the potion the Witch stores in her cloak
     * @param canCastSpell whether the Witch can immobilize the Player
     * with a spell
     */
    public Witch(String name, boolean isEvil, String description, Potion potion, boolean canCastSpell)
    {
        super(name, isEvil, description);
        this.canCastSpell = canCastSpell;
        this.potion = potion;
    }

    /**
     * Access whether the Witch can immobilize the Player
     * @return whether the Witch can immobilize the Player
     */
    public boolean getCanCastSpell() {
        return canCastSpell;
    }

    /**
     * Accesses the potion the Witch stores in her cloak
     * @return the potion the Witch stores in her cloak
     */
    public Potion getPotion() {
        return potion;
    }

    /**
     * Updates whether the Witch can immobilize the Player
     * @param canCastSpell whether the Witch can immobilize the Player
     */
    public void setCanCastSpell(boolean canCastSpell)
    {
        this.canCastSpell = canCastSpell;
    }

    /**
     * Updates the potion the Witch stores in her cloak
     * @param potion the new potion the Witch stores in her cloak
     */
    public void setPotion(Potion potion)
    {
        this.potion = potion;
    }
}
