import java.util.ArrayList;

public class Movie
{
    private int movieID;
    private String name = null;
    private ArrayList<String> genre ;
    private int releaseYear;
    private double rating = 0;
    private ReviewCollection reviewList = new ReviewCollection();
    private static int count = 0;

    public Movie(String name, ArrayList<String> type, int year)
    {
        this.name = name;
        this.genre = type;
        this.releaseYear = year;
        count++;
        this.movieID=count;
    }

    public String getName()
    {
        return this.name;
    }

    public String getGenre()
    {
        return this.genre;
    }

    public int getYear()
    {
        return this.releaseYear;
    }

    public double calRating()
    {
        this.rating = reviewList.calRating();
        return this.rating;
    }

    public void addNewReview(Review e)
    {
        reviewList.add(e);
    }

    public void printReviewList()
    {
        reviewList.printAll();
    }

    public void showReview(int index)
    {
        reviewList.showReview(index);
    }
}