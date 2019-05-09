/**
 *  ReviewManager act as a central control of Review
 *  
 *  Created by Nawakanok Muengkam (Guitar) 5907050101044
 *      Build project's possible framework and some implementation
 *  Modified by jarudet Wichit (Jardet) 59070501008
 *      7/5/2019 Continuing implement project
 *  Modified by Nawakanok Muengkam (Guitar) 5907050101044
 *      9/5/2019 Implement initialize method
 * 
 */

import java.util.ArrayList;


public class ReviewManager
{
    private final String reviewFileName = "allReviews.txt";

    private ReviewFileManager reviewFileManager = null;

    private ReviewCollection allReviews;

    // i don't know if this one is the right implementation
    private static ReviewManager singletonInstance = new ReviewManager();

     /** need to create review collection */
    private ReviewManager()
    {
        
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
        
        Review review = null;
        reviewFileManager = new ReviewFileManager();
        allReviews = new ReviewCollection();
        if(reviewFileManager.openRead(reviewFileName)==true)
        {
            while((review = reviewFileManager.readReview())!=null)
            { 
                allReviews.addReview(review);
            }
            reviewFileManager.closeRead();
            allReviews.initMatchTable();
        }
    }

    /**
     * add new instance of Review to allReviews 
     * @param review new instance of Reviews
     */
    public void addNewReview(Review review)
    {
        allReviews.addReview(review);
    }

    /**
     * delete Review that matched param reviewID
     * @param reviewID ID of Review to delete
     */
    public void deleteReview(int reviewID)
    {

    }

    public ArrayList<Review> search(String key, int searchOption)
    {
        ArrayList<Review> reviewTemp = new ArrayList<Review>();
        switch (searchOption) 
        {
            case 3:
                for(Review review : allReviews.getAllReview().values())
                {   
                    if(review.getWriter().equals(key))
                    {
                        reviewTemp.add(review);
                    }
                }
                return reviewTemp;
        
            default:
                return reviewTemp;
        }
    }

    /**
     * save all Review in ReviewCollection to file suing ReviewFileManager
     */
    public void rewriteAllReview()
    {
        String reviewData = null;
        //seem like user iterator it
        reviewFileManager.writeReview(reviewData); 
    }
}