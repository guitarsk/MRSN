import java.util.Date;
import java.util.HashMap;

public class ReviewFileManager extends TextFileManager
{
    public Review readReview()
    {
        Review review = null;
        String lineRead = getNextLine();
        
        if(lineRead != '[')
        {
            String movieName = null;
            String title = null;
            String body = null;
            String date = null;
            double rate = -1;
            String write = null;
            HashMap<String,String> likeAndDislike = new HashMap<String,String>();
            
            lineRead = getNextLine();
            while(lineRead != ']')
            {
                String fields[] = lineRead.split("\\s+");
                if(fields[0].equalsIgnoreCase("MOVIENAME") && fields.length == 2)
                {
                    movieName = fields[1];
                }
                else if(fields[0].equalsIgnoreCase("TITLE") && fields.length == 2)
                {
                    title = fields[1];
                    
                }
                else if(fields[0].equalsIgnoreCase("BODY") && fields.length == 2)
                {
                    body = fields[1];
                }
                else if(fields[0].equalsIgnoreCase("DATE") && fields.length == 2)
                {
                    date = fields[1];
                }
                else if(fields[0].equalsIgnoreCase("RATING") && fields.length == 2)
                {
                    rate = fields[1];
                }
                else if(fields[0].equalsIgnoreCase("WRITER") && fields.length == 2)
                {
                    write = fields[1];
                }
                else if(fields[0].equalsIgnoreCase("LIKE&DISLIKE") && fields.length ==3)
                {
                    likeAndDislike.put(fields[1], fields[2]);
                }
                else
                {
                    System.out.println("bad data");
                    return review;
                }
                lineRead = getNextLine();
            }
            if(movieName != null && title != null && body != null && date != null && rate != -1 && write != null && likeAndDislike.isEmpty()==false)
                review = new Review(movie, title, body, rate, name);
        }
        return review;
    }

    public void writeReview(String reviewData)
    {
        writeNextLine(reviewData);
    }
}