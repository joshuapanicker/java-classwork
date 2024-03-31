public class Painting extends Art{ //update to inherit from Art
    private String medium;

    public Painting()
    {
        this("No name", "No artist", -1, "No medium");
    }

    public Painting(Painting painting)
    {
        this(painting.getName(), painting.getArtist(), painting.getYear(), painting.getMedium());
    }

    public Painting(String name, String artist, int year, String medium)
    {
        super(name, artist, year);
        this.medium = medium;
    }

    public String getMedium()
    {
        return medium;
    }

    public void setMedium()
    {
        this.medium = medium;
    }

    @Override public String toString()
    {
        return "Artist: " + getArtist() + "\n" +
            "   Name: " + getName() + "\n" +
            "   Year: " + getYear() + "\n" +
            "    Medium: " + getMedium() + "\n";

    }
}