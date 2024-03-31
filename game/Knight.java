/**
 * @author Joshua Panicker
 * CIS 36B, Lab 10
 */
public class Knight extends NPC {
    private Weapon weapon;



    /**
     * Four-argument constructor for Knight
     * @param name the name of the Knight
     * @param isEvil whether the Knight is evil
     * @param description scripting for encountering this Knight
     * @param weapon the weapon the Knight wields
     */
    public Knight(String name, boolean isEvil, String description, Weapon weapon)
    {
        super(name, isEvil, description);
        this.weapon = weapon;
    }

    /**
     * Acceses the weapon the Knight wields
     * @return the weapon the Knight wields
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Updates the weapon the Knight wields
     * @param weapon the weapon the Knight wields
     */
    public void setWeapon(Weapon weapon)
    {
        this.weapon = weapon;
    }

}
