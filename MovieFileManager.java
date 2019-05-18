import java.util.ArrayList;

/**
 * This class is read the information of movies from file and create movies.
 * File format is
 * [
 * MOVIEID|1
 * MOVIENAME|LEGO
 * GENRE|ACTION|COMEDY
 * YEAR|2002
 * ]
 *  | (or bitwise) use for separate fields 
 * Created by Nawakanok Muangkham (Guitar) 59070501044
 *      7 May 2019
 * Modified by Nawakanok Muangkham (Guitar) 59070501044
 *      9 May 2019 bug issue.
 */
public class MovieFileManager extends TextFileManager
{
    /**
     * Return movie instance by read from file and create it.
     * @return movie instance.
     */
    public Movie readMovie()
    {
        Movie movie = null;
        String lineRead = null;

        do
        {
            lineRead = getNextLine();
            if(lineRead != null && lineRead.equalsIgnoreCase("[")==true)
            {
                int movieID = -1;
                String name = null;
                ArrayList<String> genre = new ArrayList<String>();
                int year = -1;
                lineRead = getNextLine();
                while(lineRead.equalsIgnoreCase("]")==false)
                {
                    String fields[] = lineRead.split("\\|");
                    if(fields[0].equalsIgnoreCase("MOVIEID") && fields.length == 2)
                    {
                        try
                        {
                            movieID = Integer.parseInt(fields[1]);
                        }
                        catch (NumberFormatException nfe)
                        {
                            System.out.println("bad line data --> skip line");
                        }
                    }
                    else if(fields[0].equalsIgnoreCase("MOVIENAME") && fields.length == 2)
                    {
                        name = fields[1];
                    }
                    else if(fields[0].equalsIgnoreCase("GENRE") && fields.length > 1)
                    {
                        for(int i = 1 ; i < fields.length ; i++)
                        {
                            genre.add(fields[i]);
                        }
                    }
                    else if(fields[0].equalsIgnoreCase("YEAR") && fields.length == 2)
                    {
                        try
                        {
                            year = Integer.parseInt(fields[1]);
                        }
                        catch (NumberFormatException nfe)
                        {
                            System.out.println("bad line data --> skip line");
                        }
                    }
                    else
                    {
                        System.out.println("bad line data --> skip line");
                    }
                    lineRead = getNextLine();
                }
                if(movieID != -1 && name != null && genre.isEmpty()==false && year != -1)   //check that can read all information to create movie
                    movie = new Movie(movieID,name, genre, year);
            }
        }
        while(movie == null && lineRead != null);
        return movie;
    }

    /**
     * Write movie data into file.
     * @param movieData data to write into file.
     */
    public void writeMovie(String movieData)
    {
        writeNextLine(movieData);
    }

}