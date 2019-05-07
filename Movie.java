import java.util.ArrayList;

public class Movie
{
    private int movieID;
    private String name = null;
    private ArrayList<String> genre ;
    private int releaseYear;
    private double rating = 0;
    private static int count = 0;

    public Movie(String name, ArrayList<String> type, int year)
    {
        this.name = name;
        this.genre = type;
        this.releaseYear = year;
        count++;
        this.movieID=count;
    }

    public String getName()
    {
        return this.name;
    }

    public ArrayList<String> getGenre()
    {
        return this.genre;
    }

    public int getYear()
    {
        return this.releaseYear;
    }

    public double calRating()
    {
        //this.rating = reviewList.calRating();
        return this.rating;
    }
}