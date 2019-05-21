import java.util.ArrayList;

/**
 * Movie class represent a movie
 * 
 * Created by Nawakanok Muangkham (Guitar) 59070501044
 *      Build project's possible framework and some implementation
 *  Modified by jarudet Wichit (Jardet) 59070501008
 *      7 May 2019 Continuing implement project
 *  Modified by Nawakanok Muangkham (Guitar) 59070501044
 *      9 May 2019 implement getDataToWrite method
 */

public class Movie
{
    private int movieID;
    private String name = null;
    private ArrayList<String> genre ;
    private int releaseYear;
    private double rating = 0;
    private static int count = 0;

    /**
     * Constructor for create instance of Movie
     * @param name name for this Movie
     * @param genre of this movie 
     * @param year this movie created
     */
    public Movie(String name, ArrayList<String> genre, int year)
    {
        count++;
        this.movieID = count;
        this.name = name;
        this.genre = genre;
        this.releaseYear = year;
    }

    /**
     * Constructor for create instance of Movie when read it from file.
     * @param movieID   movie id of this movie.
     * @param name  name for this Movie.
     * @param genre of this movie .
     * @param year this movie created.
     */
    public Movie(int movieID,String name, ArrayList<String> genre, int year)
    {
        count++;
        this.movieID = movieID;
        this.name = name;
        this.genre = genre;
        this.releaseYear = year;
    }

    /**
     * Getter method for movie id.
     * @return  movie id of this movie.
     */
    public int getMovieID()
    {
        return this.movieID;
    }

    /**
     * getter for Movie's name
     * @return name of this movie
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * getter for Movie's genre
     * @return genre of this Movie
     */
    public ArrayList<String> getGenre()
    {
        return this.genre;
    }

    /**
     * getter for this Movie releaseYear
     * @return releaseYear of this movie
     */
    public int getReleaseYear()
    {
        return this.releaseYear;
    }

    /**
     * calculate this Movie rating from average rating of all review of this movie.
     */
    private void calMovieRating()
    {
        this.rating = ReviewManager.getInstance().calMovieRating(movieID);
    }

    /**
     * show Movie's info
     */
    public void showMovie()
    {
        calMovieRating();
        System.out.println(name+" ("+releaseYear+")");
        System.out.println("Rating :"+this.rating);
        System.out.print("Genre :");
        for(int i = 0 ; i < genre.size() ; i++)
        {
            System.out.print(genre.get(i)+" ");
        }

    }

    /**
     * Return the information of movie to write in file.
     * @return String of movie info
     */
    public String getDataToWrite()
    {
        String data = null;
        data = "[\nMOVIEID|" + this.movieID + "\nMOVIENAME|" + this.name + "\nGENRE";
        for(int i = 0 ; i < this.genre.size() ; i++)
        {
            data += "|" + this.genre.get(i);
        }
        data += "\nYEAR|" + this.releaseYear + "\n]";
        return data;
    }
}