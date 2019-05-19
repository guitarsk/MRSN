/**
 * Review class represent a review of movie
 * 
 *  Created by Nawakanok Muangkham (Guitar) 5907050101044
 *      Build project's possible framework and some implementation
 *  Modified by Jarudet Wichit (Jardet) 59070501008
 *      7 May 2019 Continuing implement project
 *  Modified by Nawakanok Muangkham (Guitar) 5907050101044
 *      9 May 2019 Implement getDataToWrite() method
 * 
 */

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Review
{
    private int reviewID;
    private int movieID ;
    private String title = null;
    private String body = null;
    private String reviewDate;
    private double rating = 0;
    private String writer=null; // writer's email
    private HashMap<String,String> likeAndDislike;
    private static int count = 0; // use to create review ID


    /**
     * You know what this do
     * @param movieName for this Review movieName
     * @param title for this Review title
     * @param body for this Review body
     * @param rating for this Review rating
     * @param writer for this Review writer's name
     */
    public Review(int movieID,String title,String body,double rating,String writer)
    {
        count++;
        this.reviewID = count;
        this.movieID = movieID;
        this.title = title;
        this.body = body;
        this.rating = rating;
        this.writer = writer;
        this.reviewDate = new Date().toString();
        this.likeAndDislike = new HashMap<String,String>();
    }

    public Review(int movieID,String title,String body,String date, double rate,String writer, HashMap<String,String> likeAndDislike)
    {
        count++;
        this.reviewID = count;
        this.movieID = movieID;
        this.title = title;
        this.body = body;
        this.rating = rate;
        this.writer = writer;
        this.reviewDate = date;
        this.likeAndDislike = likeAndDislike;
    }

    /**
     * return basic info of this Review
     * @return review info
     */
    public String toString()
    {
        return "Title: "+this.title+" Rating: "+this.rating+" Written by: "+this.writer;
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
        return this.writer;
    }

    /**
     * getter for rating
     * @return rating for movie in this Review
     */
    public double getRating()
    {
        return this.rating;
    }

    /**
     * getter for movieName
     * @return this Review's movie
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
        System.out.println("Movie name: "+MovieManager.getInstance().getMovie(movieID).getName());
        System.out.println("Writer: "+UserManager.getInstance().getUser(this.writer).getUserName());
        System.out.println("Rating: "+this.rating);
        System.out.println("Review date: "+this.reviewDate);
        System.out.println("Title: "+this.title);
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
     */
    public void setLikeOrDislike(String email,String value)
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
                System.out.println("You already "+value+" this review");
                return;
            }
        }
        else
        {
            likeAndDislike.put(email, value);
        }
        System.out.println("You "+value+" this review");
    }

    /**
     * need more info about file format from Guitar b4 continue implement
     * @return Data to write to file
     */
    public String getDataToWrite()
    {
        String data = null;
        data = "[\nMOVIEID|"+this.movieID+"\nTITLE|"+this.title+"\nBODY|"+this.body+"\nDATE|"+this.reviewDate+"\nRATING|"+this.rating+"\nWRITER|"+this.writer;
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