/**
 *  ReviewManager act as a central control of Review
 *  
 *  Created by Nawakanok Muengkam (Guitar) 5907050101044
 *      Build project's possible framework and some implementation
 *  Modified by jarudet Wichit (Jardet) 59070501008
 *      7/5/2019 Continuing implement project
 *  Modified by Nawakanok Muengkam (Guitar) 5907050101044
 *      9/5/2019 Implement initialize and rewriteAllReview method
 * 
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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

    public ArrayList<Integer> search(String key, int searchOption)
    {
        ArrayList<Integer> idTemp = new ArrayList<Integer>();
        switch (searchOption) 
        {
            case 3:
                return allReviews.searchReview(key);        
            default:
                System.out.println("Error in ReviewManager: Wrong search option");
                return null;
        }
    }

    /**
     * save all Review in ReviewCollection to file using ReviewFileManager
     * @return true for success write ,otherwise fail to write
     */
    public boolean rewriteAllReview()
    {
        String reviewData = null;
        HashMap<Integer,Review> temp = allReviews.getAllReview(); /* get all review in collection */
        boolean success = false;

        if(reviewFileManager.openWrite(reviewFileName, false))  /* open file to write */
        {
            Iterator<Map.Entry<Integer,Review>> it = temp.entrySet().iterator();
            while(it.hasNext()) //loop for get data to write in file
            {
                Map.Entry<Integer,Review> pair = it.next();
                reviewData = pair.getValue().getDataToWrite();
                reviewFileManager.writeReview(reviewData); //write 
            }
            reviewFileManager.closeWrite();
            success = true;
        }
        return success;
    }

    public boolean writeNewReview(Review newReview)
    {
        boolean success = false;
        if(reviewFileManager.openWrite(reviewFileName, true)==true)
        {
            reviewFileManager.writeReview(newReview.getDataToWrite());
            reviewFileManager.closeWrite();
            success = true;
        }
        return success;
    }

    public void printSearch(int id)
    {
        allReviews.showReview(id);
    }

    /* incomplete */
    public void printSort()
    {

    }
}