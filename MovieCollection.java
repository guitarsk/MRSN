/**
 * MovieCollection collect and organize Movie object
 * 
 *  Created by Nawakanok Muengkam (Guitar) 5907050101044
 *      Build project's possible framework and some implementation
 *  Modified by jarudet Wichit (Jardet) 59070501008
 *      7/5/2019 Continuing implement project
 * 
 */

import java.util.ArrayList;
import java.util.HashMap;

public class MovieCollection
{
    private HashMap<Integer,Movie> movies;

    /**unfinished need to figure out how to create hashMap of Movie in one go */
    public MovieCollection()
    {
        movies = new HashMap<Integer,Movie>();
    }

    /**
     * print all movie
     */
    public void printAll()
    {
        
    }

    /* incomplete */
    public MovieCollection searchMovie(String key)
    {
        MovieCollection newList = new MovieCollection();
        return newList;
    }

    /* incomplete */
    public MovieCollection sortMovie(String key)
    {
        MovieCollection newList = new MovieCollection();
        return newList;
    }

    /**
     * add new Movie to List
     * @param movieName
     * @param movie
     */
    public void addMovie(Movie movie)
    {
        movies.put(movie.getMovieID(),movie);
    }
    
    /**
     * check for existing Movie
     * @param movieName name of movie
     * @return true if found, false if not
     */
    public boolean checkMovie(String movieName)
    {
        if(movies.containsKey(movieName))
        {
            return true;
        }
        return false;
    }
    
    /**
     * get instance of movie using name as key
     * @param name movie
     * @return instance of Movie
     */
    public Movie getMovie(String name)
    {
        return movies.get(name);
    }

    /**
     * get all movies from MovieCollection
     * @return all movies
     */
    public HashMap<String,Movie> getAllMovie()
    {
        return this.movies;
    }

}