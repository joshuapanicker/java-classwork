import java.awt.*;

/**
 * @author jenniferparrish
 * CIS 36B, Lab 8
 */
public class Sculpture extends Painting { //update to inherit from Painting
    private boolean humanForm;

    public Sculpture()
    {
        this("No name", "No Artist", -1, "No medium", false);
    }

    public Sculpture(Sculpture sculpture)
    {
        this(sculpture.getName(), sculpture.getArtist(), sculpture.getYear(), sculpture.getMedium(), sculpture.getHumanForm());
    }

    public Sculpture(String name, String artist, int year, String medium, boolean humanForm)
    {
        super(name, artist, year, medium);
        this.humanForm = humanForm;
    }

    public boolean getHumanForm()
    {
        return humanForm;
    }

    public void setHumanForm()
    {
        this.humanForm = true;
    }

    @Override public String toString()
    {
        String humanB;

        if (getHumanForm())
            humanB = "true";
        else
            humanB = "false";

        return "Artist: " + getArtist() + "\n" +
                "   Name: " + getName() + "\n" +
                "   Year: " + getYear() + "\n" +
                "    Medium: " + getMedium() + "\n" +
                "    Human Form: " + humanB + "\n";
    }
}
