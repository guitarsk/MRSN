import java.util.ArrayList;
import java.util.HashMap;

/**
 * MovieCollection collect and organize Movie object
 * 
 *  Created by Nawakanok Muangkham (Guitar) 59070501044
 *      Build project's possible framework and some implementation
 *  Modified by jarudet Wichit (Jardet) 59070501008
 *      7 May 2019 Continuing implement project
 * 
 */

public class MovieCollection
{
    /** Collection of movies */
    private HashMap<Integer,Movie> movies;

    /**
     * Constructor for create MovieCollection instance.
     */
    public MovieCollection()
    {
        movies = new HashMap<Integer,Movie>();
    }


    /**
     * search for Movie based on name
     * @param key movie name
     * @return ArrayList of movie ID
     */
    public ArrayList<Integer> searchMovie(String key)
    {
        ArrayList<Integer> idTemp = new ArrayList<Integer>();
        for(Movie movie : movies.values())
        {
            if(movie.getName().contains(key))
            {
                idTemp.add(movie.getMovieID());
            }
        }
        return idTemp;
    }


    /**
     * search for movie based on genre
     * @param key movie genre
     * @return ArrayList of movie ID
     */
    public ArrayList<Integer> searchGenre(String key)
    {
        ArrayList<Integer> idTemp = new ArrayList<Integer>();
        for (Movie movie : movies.values())
        {
            for(int i = 0 ; i < movie.getGenre().size() ; i++)
            {
                if(movie.getGenre().get(i).equals(key))
                {
                    idTemp.add(movie.getMovieID());
                }
            }
        }
        return idTemp;
    }

    /**
     * Add new movie to list.
     * @param movie new movie instance.
     */
    public void addMovie(Movie movie)
    {
        movies.put(movie.getMovieID(),movie);
    }
    
    /**
     * Return total number of movies in collection.
     * @return size of movie collection.
     */
    public int size()
    {
        return movies.size();
    }

    /**
     * get instance of movie using movie id as key.
     * @param movieId movie id.
     * @return instance of Movie.
     */
    public Movie getMovie(Integer movieId)
    {
        return movies.get(movieId);
    }

    /**
     * Show infomation of selected movie.
     * @param id of movie that want to show info.
     */
    public void showMovie(Integer id)
    {
        movies.get(id).showMovie();
    }

}