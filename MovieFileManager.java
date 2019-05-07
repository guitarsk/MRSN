import java.util.ArrayList;

public class MovieFileManager extends TextFileManager
{
    public Movie readMovie()
    {
        Movie movie = null;
        String lineRead = getNextLine();
        
        if(lineRead != '[')
        {
            String name = null;
            ArrayList<String> genre = new ArrayList<String>();
            int year = -1;
            lineRead = getNextLine();
            while(lineRead != ']')
            {
                String fields[] = lineRead.split("\\s+");
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
                        System.out.println("bad data");
                        return movie;
                    }
                }
                else
                {
                    System.out.println("bad data");
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
}