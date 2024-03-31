/**
 * @author Joshua Panicker
 * CIS 36B, Lab 10
 */
public class Potion extends Tool {
    private boolean isHarmful;

    /**
     * One-argument constructor for Potion
     * @param name the name of the potion
     * Also assigns isHarmful to false and
     * healthPoints to 0
     */
    public Potion(String name)
    {
        super(name,0);
        this.isHarmful = false;
    }

    /**
     * Two argument constructor for Potion
     * @param name the name of the potion
     * @param healthPoints the total healthPoints
     * of damage or healing it provides
     * @param isHarmful whether the potion is
     * poisonous when consumed
     */
    public Potion(String name, int healthPoints, boolean isHarmful)
    {
        super(name, healthPoints);
        this.isHarmful = isHarmful;
    }

    /**
     * Accesses whether the potion is poisonous
     * @return whether it is poisonous
     */
    public boolean isHarmful() {
        return isHarmful;
    }

    /**
     * Updates whether the potion is poisonous
     * @param isHarmful whether it is poisonous
     */
    public void setHarmful(boolean isHarmful)
    {
        this.isHarmful = isHarmful;
    }

    /**
     * Compares two potions to determine if
     * they are both potions and, if so, whether
     * they are equal (determined by the harm
     * of the potion and by  name)
     * @param obj another Object
     * @return whether the two are both potions
     * and have the same name.
     */
    @Override public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Potion)) {
            return false;
        } else {
            Potion p = (Potion) obj;
            return this.isHarmful == p.isHarmful
                    && this.getName() == p.getName();
        }
    }

    /**
     * toString for Potion
     * @return name of potion
     */
    @Override public String toString() {
        return getName();
    }
}
