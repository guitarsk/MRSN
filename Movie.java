import java.util.ArrayList;

public class Movie
{
    private String name = null;
    private ArrayList<String> genre ;
    private int releaseYear;
    private double rating = 0;
    private ReviewCollection reviewList = new ReviewCollection();

    public Movie(String name, ArrayList<String> type, int year)
    {
        this.name = name;
        this.genre = type;
        this.releaseYear = year;
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