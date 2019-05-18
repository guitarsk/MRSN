import java.util.ArrayList;
import java.util.HashMap;

/**
 * MovieCollection collect and organize Movie object
 * 
 *  Created by Nawakanok Muangkham (Guitar) 5907050101044
 *      Build project's possible framework and some implementation
 *  Modified by jarudet Wichit (Jardet) 59070501008
 *      7 May 2019 Continuing implement project
 * 
 */

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
    public boolean checkMovie(Integer movieId)
    {
        if(movies.containsKey(movieId))
        {
            return true;
        }
        return false;
    }
    
    public int size()
    {
        return movies.size();
    }

    /**
     * get instance of movie using name as key
     * @param name movie
     * @return instance of Movie
     */
    public Movie getMovie(Integer movieId)
    {
        return movies.get(movieId);
    }

    /**
     * get all movies from MovieCollection
     * @return all movies
     */
    public HashMap<Integer,Movie> getAllMovie()
    {
        return this.movies;
    }

    public void showMovie(Integer id)
    {
        movies.get(id).showMovie();
    }

}