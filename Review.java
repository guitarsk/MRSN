import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Review class represent a review of movie
 * 
 *  Created by Nawakanok Muangkham (Guitar) 59070501044
 *      Build project's possible framework and some implementation
 *  Modified by Jarudet Wichit (Jardet) 59070501008
 *      7 May 2019 Continuing implement project
 *  Modified by Nawakanok Muangkham (Guitar) 59070501044
 *      9 May 2019 Implement getDataToWrite() method
 * 
 */
public class Review
{
    /** ID of this review */
    private int reviewID;

    /** ID of movie that this review refer */
    private int movieID ;

    /** Title of review */
    private String title = null;

    /** Body of review */
    private String body = null;

    /** Date that this review was created */
    private String reviewDate;

    /** Rating of this review */
    private double rating = 0;

    /** Email of user that write this review */
    private String writerEmail=null;

    /** Collection of like / dislike of this review */
    private HashMap<String,String> likeAndDislike;

    /** use for grow of reviewID */
    private static int count = 0;


    /**
     * Constructor for create review instance.
     * @param movieID of movie that review refer
     * @param title for this Review title
     * @param body for this Review body
     * @param rating for this Review rating
     * @param writerEmail for this Review writer's email
     */
    public Review(int movieID,String title,String body,double rating,String writerEmail)
    {
        count++;
        this.reviewID = count;
        this.movieID = movieID;
        this.title = title;
        this.body = body;
        this.rating = rating;
        this.writerEmail = writerEmail;
        this.reviewDate = new Date().toString();
        this.likeAndDislike = new HashMap<String,String>();
    }

    /**
     * Constructor for create review instance that was read from file.
     * @param movieID of movie that review refer
     * @param title for this Review title
     * @param body for this Review body
     * @param date that this review was created
     * @param rating for this Review rating
     * @param writerEmail for this Review writer's email
     * @param likeAndDislike of this review
     */
    public Review(int movieID,String title,String body,String date, double rating,String writerEmail, HashMap<String,String> likeAndDislike)
    {
        count++;
        this.reviewID = count;
        this.movieID = movieID;
        this.title = title;
        this.body = body;
        this.rating = rating;
        this.writerEmail = writerEmail;
        this.reviewDate = date;
        this.likeAndDislike = likeAndDislike;
    }

    /**
     * getter for reviewDate
     * @return date this Review was created
     */
    public String getReviewDate()
    {
        return this.reviewDate;
    }

    /**
     * getter for writer
     * @return email of User who write this Review
     */
    public String getWriterEmail()
    {
        return this.writerEmail;
    }

    /**
     * getter for rating
     * @return rating of this Review
     */
    public double getRating()
    {
        return this.rating;
    }

    /**
     * getter for movieID
     * @return movieID that this review refer.
     */
    public int getMovieID()
    {
        return this.movieID;
    }

    /**
     * getter for this review's ID
     * @return review's ID
     */
    public int getReviewID()
    {
        return this.reviewID;
    }

    /**
     * show all info of this Review
     */
    public void showReview()
    {
        System.out.println("<<"+this.title+">>");
        System.out.println("Movie name: "+MovieManager.getInstance().getMovie(movieID).getName());
        System.out.println("Writer: "+UserManager.getInstance().getUser(this.writerEmail).getUserName());
        System.out.println("Rating: "+this.rating);
        System.out.println("Review date: "+this.reviewDate);
        System.out.println("Body: "+this.body);
        //System.out.println("\n"+);
    }

    /**
     * use to set title to review
     * @param newTitle for review
     */
    public void setTitle(String newTitle)
    {
        this.title = newTitle;
    }

    /**
     * set text body of review
     * @param newBody for review
     */
    public void setBody(String newBody)
    {
        this.body = newBody;
    }

    /**
     * set rating to review
     * @param newRate for review
     */
    public void setRating(Double newRate)
    {
        this.rating = newRate;
    }

    /**
     * each user can set like or dislike on review but not both 
     * each new setLikeAndDislike will overwrite old setting 
     * @param email of user who set like or dislike
     * @param value this can either be like or dislike
     * @return true if like/dislike successful, false if already do
     */
    public boolean setLikeOrDislike(String email,String value)
    {
        if(likeAndDislike.isEmpty() == true)
        {
            likeAndDislike.put(email, value);
        }
        else if(likeAndDislike.containsKey(email) == true)
        {
            if(likeAndDislike.get(email).equalsIgnoreCase(value)==false)
            {
                likeAndDislike.replace(email, value);
            }
            else
            {
                return false;
            }
        }
        else
        {
            likeAndDislike.put(email, value);
        }
        return true;
    }

    /**
     * Getter for get data to write into the file
     * @return Data to write to file
     */
    public String getDataToWrite()
    {
        String data = null;
        data = "[\nMOVIEID|"+this.movieID+"\nTITLE|"+this.title+"\nBODY|"+this.body+"\nDATE|"+this.reviewDate;
        data += "\nRATING|"+this.rating+"\nWRITER|"+this.writerEmail;
        if(this.likeAndDislike.isEmpty() == false)
        {
            Iterator<Map.Entry<String,String>> it = this.likeAndDislike.entrySet().iterator();
            while(it.hasNext())
            {
                Map.Entry<String,String> pair = it.next();
                data += "\nLIKE&DISLIKE|"+pair.getKey()+"|"+pair.getValue();
            }
        }
        data += "\n]";
        return data;
    }
}