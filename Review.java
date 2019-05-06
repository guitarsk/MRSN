import java.util.Date;
import java.util.HashMap;

public class Review
{
    private String movieName = null;
    private String title = null;
    private String body = null;
    private Date reviewDate;
    private double rating = 0;
    private String writer=null;
    private HashMap<String,String> likeAndDislike = new HashMap<String,String>();

    public Review(String movie,String title,String body,double rate,String name)
    {
        this.movieName = movie;
        this.title = title;
        this.body = body;
        this.rating = rate;
        this.writer = name;
        reviewDate = new Date();
    }

    public String toString()
    {
        return "Movie:"+movieName+" Title:"+title+" by "+creatorName;
    }

    public void showReview()
    {
        System.out.println("Movie name: "+this.movieName);
        System.out.println("Writer: "+this.writer);
        System.out.println("Rating: "+this.rating);
        System.out.println("Review date: "+this.reviewDate);
        System.out.println("Title: "+this.title);
        System.out.println("Body: "+this.body);
        //System.out.println("\n"+);
    }

    public Date getReviewDate()
    {
        return this.reviewDate;
    }

    public String getWriter()
    {
        return this.writer;
    }

    public double getRating()
    {
        return this.rating;
    }

    public void setTitle(String newTitle)
    {
        this.title = newTitle;
    }

    public void setBody(String newBody)
    {
        this.body = newBody;
    }

    public void setRating(String newRate)
    {
        this.rating = newRate;
    }

    public void setLikeAndDislike(String email,String value)
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
}