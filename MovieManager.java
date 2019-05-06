import java.util.ArrayList;

public class MovieManager
{
    private final String movieFileName = "allMovies.txt";

    private MovieCollection allMovies = new MovieCollection();

    private static MovieManager singletonInstance = new MovieManager();

    private MovieManager()
    {

    }

    public static MovieManager getInstance()
    {
	    return singletonInstance;
    }

    public void initialize()
    {
        
    }

    public void checkMovie(String movieName)
    {
        allMovies.checkMovie(movieName);
        allMovies.add(movieName, movie);
    }

    public Movie getMovie(String movieName)
    {
        return allMovies.getMovie(movieName);
    }

    public void printAll()
    {
        allMovies.printAll();
    }
}