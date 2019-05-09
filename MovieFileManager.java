import java.util.ArrayList;

public class MovieFileManager extends TextFileManager
{
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
                if(movieID != -1 && name != null && genre.isEmpty()==false && year != -1)
                    movie = new Movie(movieID,name, genre, year);
            }
        }
        while(movie == null && lineRead != null);
        return movie;
    }

    public void writeMovie(String movieData)
    {
        writeNextLine(movieData);
    }

    public static void main(String arg[])
    {
        MovieFileManager movieManager = new MovieFileManager();
        // movieManager.openWrite("allMovies.txt",true);
        // movieManager.writeMovie("[\nMOVIENAME|lionking\nGENRE|romance|action\nyear|2018\n]");
        // movieManager.writeMovie("[\nMOVIENAME|eiei\nGENRE|romance|action\nyear|2018\n]");
        // movieManager.closeWrite();
        movieManager.openRead("allMovies.txt");
        Movie test = null;
        while((test = movieManager.readMovie()) != null)
        {
            System.out.println(test.getMovieID()+"\n"+test.getDataToWrite());
        }
        movieManager.closeRead();
        // movieManager.openWrite("allMovies.txt",true);
        // movieManager.writeMovie("[\nMOVIENAME|ghj\nGENRE|romance|action\nyear|2018\n]");
        // movieManager.writeMovie("[\nMOVIENAME|dfghjkl\nGENRE|romance|action\nyear|2018\n]");
        // movieManager.closeWrite();
    }
}