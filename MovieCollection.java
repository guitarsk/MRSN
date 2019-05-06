import java.util.ArrayList;
import java.util.HashMap;

public class MovieCollection
{
    private HashMap<String,Movie> movies;

    public MovieCollection()
    {
        movies = new HashMap<Movie>();
    }

    public void printAll()
    {
        
    }

    public MovieCollection findMovie(String key)
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

    public boolean checkMovie(String movieName)
    {
        movies.containsKey(movieName);
        return true;
    }
    public void add(String movieName,Movie movie)
    {
        movies.put(movieName,movie);
    }
    
    public Movie getMovie(String name)
    {
        return movies.get(name);
    }

}