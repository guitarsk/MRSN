import java.util.ArrayList;

public class ReviewManager
{
    private final String reviewFileName = "allReviews.txt";

    private ReviewCollection allReviews = new ReviewCollection();

    private static ReviewManager singletonInstance = new ReviewManager();

    private ReviewManager()
    {

    }

    public static ReviewManager getInstance()
    {
	    return singletonInstance;
    }

    public void initialize()
    {
        
    }

    public void addNewReview(Review e)
    {
        allReviews.add(e);
    }
}