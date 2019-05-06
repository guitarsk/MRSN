import java.util.ArrayList;
import java.util.HashMap;

public class ReviewCollection
{
    private HashMap<Integer,Review> reviews;

    public ReviewCollection()
    {
        reviews = new HashMap<Integer,Review>();
    }

    public void printAll()
    {
    }
    
    /* incomplete */
    public ReviewCollection searchReview(String key)
    {
        
        return newList;
    }

    public double calRating()
    {
        double temp = 0;
        for(int i = 0 ; i < reviews.size() ; i++ )
        {
            temp += reviews.get(i).getRating();
        }
        return temp;
    }

    public void showReview(int index)
    {
        reviews.get(index).showReview();
        reviews.get(index).setLikeAndDislike(email, value);
    }

    public void delete(int index)
    {
        reviews.remove(index);
    }

    public void edit(int index)
    {
        reviews.get(index).setBody(newBody);
        reviews.get(index).setRating(newRate);
        reviews.get(index).setTitle(newTitle);
    }
    public void add(Review review)
    {
        reviews.add(review);
    }

}