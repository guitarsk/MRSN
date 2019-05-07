/**
 *  ReviewManager act as a central control of Review
 *  
 *  Created by Nawakanok Muengkam (Guitar) 5907050101044
 *      Build project's possible framework and some implementation
 *  Modified by jarudet Wichit (Jardet) 59070501008
 *      7/5/2019 Continuing implement project
 * 
 */

import java.util.ArrayList;


public class ReviewManager
{
    private final String reviewFileName = "allReviews.txt";

    private ReviewCollection allReviews;

    // i don't know if this one is the right implementation
    private static ReviewManager singletonInstance = new ReviewManager();

    /** need to create review collection */
    private ReviewManager()
    {
        allReviews = new ReviewCollection();
    }

    /** 
     * get instance of ReviewManager
     * @return ReviewManger
     * 
     */
    public static ReviewManager getInstance()
    {
	    return singletonInstance;
    }

    // maybe guitar want to use this to initialize ReviewCollection object
    public void initialize()
    {
        
    }

    /**
     * add new instance of Review to allReviews 
     * @param e new instance of Reviews
     */
    public void addNewReview(Review e)
    {
        allReviews.add(e);
    }

    /**
     * delete Review that matched param reviewID
     * @param reviewID ID of Review to delete
     */
    public void deleteReview(int reviewID)
    {

    }

    /**
     * save all Review in ReviewCollection to file suing ReviewFileManager
     */
    public static void saveReview()
    {

    }
}