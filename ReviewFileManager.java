import java.util.Date;
import java.util.HashMap;

/**
 * This class is read the information of reviews from file and create reviews.
 * File format is
 * [
 * MOVIEID|1
 * TITLE|AAAA
 * BODY|BBBBBBBBBBBBBB
 * DATE|Tue May 07 18:20:39 ICT 2019
 * RATING|9.9
 * WRITER|guitar_sk134@hotmail.com
 * LIKE&DISLIKE|guitar_sk555@hotmail.com|LIKE
 * LIKE&DISLIKE|jardet@hotmail.com|DISLIKE
 * ]
 *  | (or bitwise) use for separate fields 
 * Created by Nawakanok Muangkham (Guitar) 59070501044
 *      6 May 2019
 * Modified by Nawakanok Muangkham (Guitar) 59070501044
 *      9 May 2019 bug issue.
 */
public class ReviewFileManager extends TextFileManager
{
    /**
     * Return review instance by read from file and create it.
     * @return  review instance.
     */
    public Review readReview()
    {
        Review review = null;
        String lineRead = null;
        
        do
        {
            lineRead = getNextLine();
            if(lineRead != null && lineRead.equalsIgnoreCase("[")==true)
            {
                int movieID = -1;
                String title = null;
                String body = null;
                String date = null;
                double rate = -1;
                String write = null;
                HashMap<String,String> likeAndDislike = new HashMap<String,String>();
                
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
                        try 
                        {
                            rate = Double.parseDouble(fields[1]);
                        } 
                        catch (NumberFormatException nfe) 
                        {
                            System.out.println("bad data");
                        }
                        
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
                        System.out.println("bad line data --> skip line");
                    }
                    lineRead = getNextLine();
                }
                if(movieID != -1 && title != null && body != null && date != null && rate != -1 && write != null && likeAndDislike.isEmpty()==false) //review with like and dislike
                    review = new Review(movieName, title, body, date, rate, write, likeAndDislike);
                else if(movieID != -1 && title != null && body != null && date != null && rate != -1 && write != null && likeAndDislike.isEmpty()==true)    //review with no like and dislike
                    review = new Review(movieName, title, body, date, rate, write, likeAndDislike);
            }
        }
        while(review == null && lineRead != null);
        
        return review;
    }

    /**
     * Write review data into file.
     * @param reviewData data to write in file.
     */
    public void writeReview(String reviewData)
    {
        writeNextLine(reviewData);
    }

}