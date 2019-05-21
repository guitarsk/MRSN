import java.util.ArrayList;

/**
 * MovieManager act as a central control of Movie
 * 
 *  Created by Nawakanok Muangkham (Guitar) 59070501044
 *      Build project's possible framework and some implementation
 *  Modified by jarudet Wichit (Jardet) 59070501008
 *      7 May 2019 Continuing implement project
 *  Modified by Nawakanok Muangkham (Guitar) 59070501044
 *      9 May 2019 Implement initialize method
 * 
 */
public class MovieManager
{
    /** String of file name */
    private final String movieFileName = "allMovies.txt";

    /** Instance for manage movie file */
    private MovieFileManager movieFileManager = null;

    /** Collection of all movies in system */
    private MovieCollection allMovies;

    /** Single instance of MovieManager */
    private static MovieManager singletonInstance = new MovieManager();

    /**
     * Private constructor to prevent to create another instance of class.
     */
    private MovieManager()
    {

    }

    /**
     * Get instance of MovieManager
     * @return MovieManager
     */
    public static MovieManager getInstance()
    {
	    return singletonInstance;
    }

    /**
     * Initialize MovieCollection inside MovieManager
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
            movieFileManager.closeRead();
        }
    }

    /**
     * Add new movie to MovieCollection.
     * @param newMovie to add in MovieCollection.
     */
    public void addMovie(Movie newMovie)
    {
        allMovies.addMovie(newMovie);
    }

    /**
     * Return Total movie in collection.
     * @return size of MovieCollection.
     */
    public int size()
    {
        return allMovies.size();
    }

    /**
     * get instance of Movie from MovieCollection.
     * @param movieId id of Movie to find.
     * @return instance of Movie.
     */
    public Movie getMovie(Integer movieId)
    {
        return allMovies.getMovie(movieId);
    }

    /* incomplete */
    public ArrayList<Integer> search(String key,int searchOption)
    {
        switch(searchOption)
        {
            case 1://search using movie name
                return allMovies.searchMovie(key);
            case 2://search using genre
                return allMovies.searchGenre(key);
            default:
                System.out.println("Error in MovieManager: wrong search option");
                return null;
        }
    }

    public void printSearch(int id)
    {
        allMovies.showMovie(id);
    }

    /**
     * Write new movie in to file.
     * @param newMovie new movie instance.
     * @return true for success write in to file, false for error occur.
     */
    public boolean writeNewMovie(Movie newMovie)
    {
        boolean success = false;
        if(movieFileManager.openWrite(movieFileName, true)==true)
        {
            movieFileManager.writeMovie(newMovie.getDataToWrite());
            movieFileManager.closeWrite();
            success = true;
        }
        return success;
    }
    
}