import java.util.ArrayList;

public class User
{
    private String name = null;
    private String email = null;
    private String password = null;
    private ArrayList<String> favoriteMovieType = new ArrayList<String>();
    private ReviewCollection userReviews = new ReviewCollection();
    private ArrayList<String> followedList = new ArrayList<String>();

    public User(String name,String email,String password,ArrayList<String> movieType)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.favoriteMovieType.addAll(movieType);
    }

    public String getUserName()
    {
        return this.name;
    }

    public String getEmail()
    {
        return this.email;
    }

    public boolean setName(String newName)
    {
        this.name= newName;
        return true;
    }

    public boolean setPassword(String newPassword)
    {
        password = newPassword;
        return true;
    }

    public boolean login(String password)
    {
        if(this.password == password)
            return true;
        return false;
    }

    public void addNewReview(Review  e)
    {
        userReviews.add(e);
    }

    public void printFollowed()
    {
        for(int i=0;i<followedList.size();i++)
        {
            System.out.println(followedList.get(i).getUserName());
        }
    }

    public void addFollowed(String e)
    {
        followedList.add(e);
    }

    public String getFollowedEmail(int index)
    {
        return followedList.get(index);
    }

    public void manageReview()
    {
        userReviews.printAll();
        userReviews.edit(index);
        userReviews.delete(index);
        userReviews.getReviewID();
        
        Moive a =MovieManager.getInstance().getMovie(movieName);
        
    }

    public void showOwnReview()
    {

    }
}