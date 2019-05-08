import java.util.ArrayList;

public class MovieFileManager extends TextFileManager
{
    public Movie readMovie()
    {
        Movie movie = null;
        String lineRead = getNextLine();
        
        if(lineRead != null && lineRead.equalsIgnoreCase("[")==true)
        {
            String name = null;
            ArrayList<String> genre = new ArrayList<String>();
            int year = -1;
            lineRead = getNextLine();
            while(lineRead.equalsIgnoreCase("]")==false)
            {
                String fields[] = lineRead.split("\\|");
                if(fields[0].equalsIgnoreCase("MOVIENAME") && fields.length == 2)
                {
                    name = fields[1];
                }
                else if(fields[0].equalsIgnoreCase("GENRE") && fields.length >1)
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
                        System.out.println("bad dataa");
                        return movie;
                    }
                }
                else
                {
                    System.out.println(fields[0]);
                    System.out.println("bad datab");
                    return movie;
                }
                lineRead = getNextLine();
            }
            if(name != null && genre.isEmpty()==false && year != -1)
                movie = new Movie(name, genre, year);
        }
        return movie;
    }

    public void writeMovie(String movieData)
    {
        writeNextLine(movieData);
    }

    public static void main(String arg[])
    {
        MovieFileManager movieManager = new MovieFileManager();
        movieManager.openWrite("allMovie.txt",true);
        movieManager.writeMovie("[\nMOVIENAME|lionking\nGENRE|romance|action\nyear|2018\n]");
        movieManager.writeMovie("[\nMOVIENAME|eiei\nGENRE|romance|action\nyear|2018\n]");
        movieManager.closeWrite();
        movieManager.openRead("allMovie.txt");
        Movie test = null;
        if((test = movieManager.readMovie()) != null)
        {
            System.out.println(test.getName() + " " + test.getReleaseYear());
        }
        movieManager.closeRead();
        movieManager.openWrite("allMovie.txt",true);
        movieManager.writeMovie("[\nMOVIENAME|ghj\nGENRE|romance|action\nyear|2018\n]");
        movieManager.writeMovie("[\nMOVIENAME|dfghjkl\nGENRE|romance|action\nyear|2018\n]");
        movieManager.closeWrite();
    }
}