/** 
 *  ReviewCollection collect and organize Review object
 *  
 *  Created by Nawakanok Muengkam (Guitar) 5907050101044
 *      Build project's possible framework and some implementation
 *  Modified by jarudet Wichit (Jardet) 59070501008
 *      7/5/2019 Continuing implement project
 *  Modified by Nawakanok Muengkam (Guitar) 5907050101044
 *      9/5/2019 Create initMatchTable() method 
 * 
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class ReviewCollection
{
    private HashMap<Integer,ArrayList<Integer>> movieMatchReview;
    private HashMap<String,ArrayList<Integer>> userMatchReview;
    private HashMap<Integer,Review> reviews;


    /** unfinished need to figure out how to put in data in one go */
    public ReviewCollection()
    {
        movieMatchReview = new HashMap<Integer,ArrayList<Integer>>();
        userMatchReview = new HashMap<String,ArrayList<Integer>>();
        reviews = new HashMap<Integer,Review>();
    }

    public void initMatchTable()
    {
        Iterator<Map.Entry<Integer,Review>> it = this.reviews.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry<Integer,Review> pair = it.next();
            Review review = pair.getValue();
            String userEmail = review.getWriterEmail();
            Integer movieID = (Integer)review.getMovieID();
            if(userMatchReview.isEmpty() == true)
            {
                ArrayList<Integer> reviewList = new ArrayList<Integer>();
                reviewList.add((Integer)review.getReviewID());
                userMatchReview.put(userEmail, reviewList);
            }
            else if(userMatchReview.containsKey(userEmail) == true)
            {
                userMatchReview.get(userEmail).add((Integer)review.getReviewID());
            }
            else
            {
                ArrayList<Integer> reviewList = new ArrayList<Integer>();
                reviewList.add((Integer)review.getReviewID());
                userMatchReview.put(userEmail, reviewList);
            }

            if(movieMatchReview.isEmpty() == true)
            {
                ArrayList<Integer> reviewList = new ArrayList<Integer>();
                reviewList.add((Integer)review.getReviewID());
                movieMatchReview.put(movieID, reviewList);
            }
            else if(movieMatchReview.containsKey(movieID) == true)
            {
                movieMatchReview.get(movieID).add(review.getReviewID());
            }
            else
            {
                ArrayList<Integer> reviewList = new ArrayList<Integer>();
                reviewList.add((Integer)review.getReviewID());
                movieMatchReview.put(movieID, reviewList);
            }  
        }
    }

    /*** print all review */
    /** seem like not use this */
    public void printAll()
    {
    }
    
    /** search using Reviewer name*/
    /** may need to implement partial name search */
    public ArrayList<Integer> searchReview(String key)
    {
        ArrayList<Integer> idTemp = new ArrayList<Integer>();
        for(Review review : reviews.values())
        {
            if(review.getWriterEmail().equals(key))
            {
                idTemp.add(review.getReviewID());
            }
        }
        return idTemp;
    }

    /** search using movieId*/
    public ArrayList<Integer> searchReview(int key)
    {
        ArrayList<Integer> idTemp = new ArrayList<Integer>();
        for(int i  = 0 ; i < movieMatchReview.get(key).size() ; i++)
        {
            idTemp.add(movieMatchReview.get(key).get(i));
        }
        return idTemp;
    }

    public void setLikeOrDislike(int reviewID,String email, String value)
    {
        reviews.get(reviewID).setLikeOrDislike(email, value);
    }

    public Object getSelect(int reviewID, String option)
    {
        switch(option)
        {
            case "email":
                return reviews.get(reviewID).getWriterEmail();
            default:
                return null;
        }
    }

    /** i don't know what this do need to ask guitar */
    /** use for rating of movie */
    public double calRating()
    {
        return 0;
    }

    /** show review information 
     * @param id of review
     **/
    public void showReview(Integer id)
    {
        reviews.get(id).showReview();
    }


    /**
     * delete review from reviewID
     * @param reviewID of review
     */
    public void deleteReview(int reviewID)
    {
        reviews.remove(reviewID);
    }

    /**
     * edit title body and rating of review
     * @param reviewID of review
     */
    public void editReview(int reviewID, String option,String text)
    {
        switch(option)
        {
            case "title":
                reviews.get(reviewID).setTitle(text);
                break;
            case "body":
                reviews.get(reviewID).setBody(text);
                break;
            case "rating":
                reviews.get(reviewID).setRating(Double.parseDouble(text));
            default:
                break;
        }
    }

    /** this is not complete still need to add review to other 2 new hashmap */
    /** add new review
     * @param review new Review to add
     */
    public void addReview(Review review)
    {
        reviews.put((Integer)review.getReviewID(),review);
    }

    /** get HashMap of all reviews in this class
     * @return all reviews
     */
    public HashMap<Integer,Review> getAllReview()
    {
        return reviews; // unfinished need to make copy of this and return
    }

}