/**
 * MovieManager act as a central control of Movie
 * 
 *  Created by Nawakanok Muengkam (Guitar) 5907050101044
 *      Build project's possible framework and some implementation
 *  Modified by jarudet Wichit (Jardet) 59070501008
 *      7/5/2019 Continuing implement project
 * 
 */

import java.util.ArrayList;

public class MovieManager
{
    private final String movieFileName = "allMovies.txt";

    private MovieFileManager movieFileManager = null;

    private MovieCollection allMovies;

    private static MovieManager singletonInstance = new MovieManager();

    private MovieManager()
    {

    }

    /**
     * get instance of MovieManager
     * @return MovieManager
     */
    public static MovieManager getInstance()
    {
	    return singletonInstance;
    }

    /**
     * initialize value inside MovieManager
     */
    public void initialize()
    {
        Movie movie = null;
        movieFileManager = new MovieFileManager();
        allMovies = new MovieCollection();
        if(movieFileManager.openRead(movieFileName)==true)
        {
            while((movie = movieFileManager.readMovie())!=null)
            {
                allMovies.addMovie(movie.getName(), movie);
            }
        }
    }

    /* incomplete */
    public void checkMovie(String movieName)
    {
        allMovies.checkMovie(movieName);
        //allMovies.add(movieName, movie);
    }

    /**
     * get instance of Movie from MovieCollection
     * @param movieName name of Movie to find
     * @return instance of Movie
     */
    public Movie getMovie(String movieName)
    {
        return allMovies.getMovie(movieName);
    }

    /**
     * get path for Movie file
     * @return path for Movie file
     */
    public String getMovieFileName()
    {
        return movieFileName;
    }

    /**
     * print all Movie
     */
    public void printAll()
    {
        allMovies.printAll();
    }

    /* incomplete */
    public void printSearch(String key)
    {

    }

    /* incomplete */
    public void printSort()
    {

    }

}