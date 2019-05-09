/** 
 *  ReviewCollection collect and organize Review object
 *  
 *  Created by Nawakanok Muengkam (Guitar) 5907050101044
 *      Build project's possible framework and some implementation
 *  Modified by jarudet Wichit (Jardet) 59070501008
 *      7/5/2019 Continuing implement project
 *  
 * 
 */

import java.util.ArrayList;
import java.util.HashMap;

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

    /*** print all review */
    public void printAll()
    {
    }
    
    /* incomplete  need specific implementation for each type of search*/
    public ReviewCollection searchReview(String key)
    {
        
        return this;
    }

    /** i don't know what this do need to ask guitar */
    public double calRating()
    {
        double temp = 0;
        for(int i = 0 ; i < reviews.size() ; i++ )
        {
            temp += reviews.get(i).getRating();
        }
        return temp;
    }

    /** show review information 
     * @param index of review
     **/
    public void showReview(String email, String value, int index)
    {
        reviews.get(index).showReview();
        reviews.get(index).setLikeOrDislike(email, value);
    }

    /**
     * delete review from index
     * @param index of review
     */
    public void deleteReview(int index)
    {
        reviews.remove(index);
    }

    /**
     * edit review in index
     * @param index of review
     */
    public void edit(int index)
    {
        reviews.get(index).setBody(newBody);
        reviews.get(index).setRating(newRate);
        reviews.get(index).setTitle(newTitle);
    }

    /** add new review
     * @param review new Review to add
     */
    public void add(Review review)
    {
        reviews.add((Integer)review.getReviewID(),review);
    }

    /** get HashMap of all reviews in this class
     * @return all reviews
     */
    public HashMap<Integer,Review> getAllReview()
    {
        return reviews; // unfinished need to make copy of this and return
    }

}