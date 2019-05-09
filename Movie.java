/**
 * Movie class represent a movie
 * 
 * Created by Nawakanok Muengkam (Guitar) 5907050101044
 *      Build project's possible framework and some implementation
 *  Modified by jarudet Wichit (Jardet) 59070501008
 *      7/5/2019 Continuing implement project
 */

import java.util.ArrayList;

public class Movie
{
    private int movieID;
    private String name = null;
    private ArrayList<String> genre ;
    private int releaseYear;
    private double rating = 0;
    private static int count = 0;

    /**
     * create instance of Movie
     * @param name name for this Movie
     * @param genre of this movie 
     * @param year this movie created
     */
    public Movie(String name, ArrayList<String> genre, int year)
    {
        this.movieID = count;
        Movie.count++;
        this.name = name;
        this.genre = genre;
        this.releaseYear = year;
        count++;
        this.movieID=count;
    }

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
     * calculate this Movie rating and return average
     * @return this Movie's average rating
     */
    public double calRating()
    {
        // confused need to look for whereabout of reviewList
        return this.rating;
    }

    /**
     * print first 5 recent reviews of this Movie
     */
    public void printReviewList()
    {

    }

    /**
     * get one line of data to write to file need more info from Guitar
     * @return one line of String
     */
    public String getDataToWrite()
    {
        String data = null;
        data = "[\nMOVIENAME|" + this.name + "\nGERNE|";
        for(int i = 0 ; i < this.genre.size() ; i++)
        {
            data += "|" + this.genre.get(i);
        }
        data += "\nYEAR|" + this.releaseYear + "\n]";
        return data;
    }
}