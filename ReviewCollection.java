import java.util.ArrayList;
import java.util.HashMap;

/** 
 *  ReviewCollection collect and organize Review object
 *  
 *  Created by Nawakanok Muangkham (Guitar) 59070501044
 *      Build project's possible framework and some implementation
 *  Modified by jarudet Wichit (Jardet) 59070501008
 *      7/5/2019 Continuing implement project
 *  Modified by Nawakanok Muangkham (Guitar) 59070501044
 *      9/5/2019 Create initMatchTable() method 
 * 
 */
public class ReviewCollection
{
    /** match table of movie and review */
    private HashMap<Integer,ArrayList<Integer>> movieMatchReview;

    /** match table of user and review */
    private HashMap<String,ArrayList<Integer>> userMatchReview;

    /** Collection of reviews */
    private HashMap<Integer,Review> reviews;


    /**
     * Constructor for create ReviewCollection instance.
     */
    public ReviewCollection()
    {
        movieMatchReview = new HashMap<Integer,ArrayList<Integer>>();
        userMatchReview = new HashMap<String,ArrayList<Integer>>();
        reviews = new HashMap<Integer,Review>();
    }

    /**
     * Add reference of review and movie.
     * @param review review to add reference.
     */
    private void movieAddMatchTable(Review review)
    {
        Integer movieID = (Integer)review.getMovieID();
        if(movieMatchReview.isEmpty() == true)
        {
            ArrayList<Integer> reviewList = new ArrayList<Integer>();
            reviewList.add((Integer)review.getReviewID());
            movieMatchReview.put(movieID, reviewList);
        }
        else if(movieMatchReview.containsKey(movieID) == true)
        {
            movieMatchReview.get(movieID).add((Integer)review.getReviewID());
        }
        else
        {
            ArrayList<Integer> reviewList = new ArrayList<Integer>();
            reviewList.add((Integer)review.getReviewID());
            movieMatchReview.put(movieID, reviewList);
        }  
    }

    /**
     * Add reference of review and user.
     * @param review review to add reference.
     */
    private void userAddMatchTable(Review review)
    {
        String userEmail = review.getWriterEmail();
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
    }    
    
    /**
     * search for Review using reviewer name
     * @param key reviewer name
     * @return ArrayList of Review ID
     */
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

    /**
     * search for Review using Movie ID
     * @param key Movie ID
     * @return ArrayList of Movie ID
     */
    public ArrayList<Integer> searchReview(int key)
    { 
        ArrayList<Integer> idTemp = new ArrayList<Integer>();
        if(movieMatchReview.containsKey(key))
        {
            for(int i  = 0 ; i < movieMatchReview.get(key).size() ; i++)
            {
                idTemp.add(movieMatchReview.get(key).get(i));
            }
            
        }
        return idTemp;
    }

    /**
     * set Review like or dislike
     * @param reviewID that need to set like/dislike
     * @param email User email
     * @param value like or dislike value
     * @return true if succeed
     */
    public boolean setLikeOrDislike(int reviewID,String email, String value)
    {
       return  reviews.get(reviewID).setLikeOrDislike(email, value);
    }

    /**
     * get Review info object
     * such as String of email
     * or int of ID
     * this can change based on implementation
     * 
     *       ***** this method is risky handle with care**
     * 
     * @param reviewID need to get from
     * @param option for now can only get email
     * @return data object
     */
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

    /**
     * Calculate rating of select movie
     * @param movieID of select movie
     * @return  rating in double value
     */
    public double calMovieRating(int movieID)
    {
        double rating = 0;
        int reviewID = -1;
        int i = 0 ;
        
        if(movieMatchReview.containsKey(movieID) == true)
        {
            int totalReview = movieMatchReview.get(movieID).size();
            for( i = 0 ; i < totalReview ; i++)
            {
                reviewID = movieMatchReview.get(movieID).get(i);
                rating += reviews.get(reviewID).getRating();
            }
            rating = rating/totalReview;
        }
        return rating;
    }

    /** show review information 
     * @param id of review
     **/
    public void showReview(Integer id)
    {
        reviews.get(id).showReview();
    }

    /**
     * delete review from reviewID and match table
     * @param reviewID of review
     */
    public void deleteReview(int reviewID)
    {
        int movieID = reviews.get(reviewID).getMovieID();
        String email = reviews.get(reviewID).getWriterEmail();
        userMatchReview.get(email).remove((Integer)reviewID);
        movieMatchReview.get((Integer)movieID).remove((Integer)reviewID);
        reviews.remove(reviewID);
    }

   
    /**
     * edit title, body or rating of review
     * @param reviewID that need to edit
     * @param option edit option
     * @param text new info to replace
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

    /**
     * add new review and add match table
     * @param review new Review to add
     */
    public void addReview(Review review)
    {
        reviews.put((Integer)review.getReviewID(),review);
        userAddMatchTable(review);
        movieAddMatchTable(review);
    }

    /**
     * get HashMap of all reviews in this class
     * @return all reviews
     */
    public HashMap<Integer,Review> getAllReview()
    {
        return reviews; 
    }

}