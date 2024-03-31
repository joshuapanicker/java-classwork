public abstract class Art implements Comparable<Art>
{
    private String name;
    private String artist;
    private int year;

    public Art()
    {
        this("No Name", "No artist", -1);
    }

    public Art(String name, String artist, int year)
    {
        this.name = name;
        this.artist = artist;
        this.year = year;
    }

    public String getName()
    {
        return name;
    }

    public void setName()
    {
        this.name = name;
    }

    public String getArtist()
    {
        return artist;
    }

    public void setArtist()
    {
        this.artist = artist;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear()
    {
        this.year = year;
    }

    @Override public String toString()
    {
        return "Artist: " + getArtist() + "\n" +
                "   Name: " + getName() + "\n" +
                "   Year: " + getYear() + "\n";
    }

    public int compareTo(Art obj)
    {
        if (!obj.getArtist().equals(this.getArtist()))
        {
            return this.getArtist().compareTo(obj.getArtist());
        }

        if (!obj.getName().equals(this.getName()))
        {
            return this.getName().compareTo(obj.getName());
        }

        if (obj.getYear() != this.getYear())
        {
            return Integer.compare(this.getYear(), obj.getYear());
        }
        return 0;
    }

    @Override public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        else if (!(obj instanceof Art))
            return false;
        else
        {
            Art art = (Art) obj;
            return this.artist.equals(art.artist) && this.name.equals(art.name) && (Integer.compare(this.year, art.year) == 0);
        }
    }


}
//write your abstract Art class here