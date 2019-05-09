/**
 * MovieManager act as a central control of Movie
 * 
 *  Created by Nawakanok Muengkam (Guitar) 5907050101044
 *      Build project's possible framework and some implementation
 *  Modified by jarudet Wichit (Jardet) 59070501008
 *      7/5/2019 Continuing implement project
 *  Modified by Nawakanok Muengkam (Guitar) 5907050101044
 *      9/5/2019 Implement initialize method
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
                allMovies.addMovie(movie);
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
    public ArrayList<Movie> printSearch(String key,int searchOption)
    {
        switch(searchOption)
        {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
        /** if the name exactly matched print only */
        if(allMovies.checkMovie(key))
        {

        }
    }

    /* incomplete */
    public void printSort()
    {

    }

}