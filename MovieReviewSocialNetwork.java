public class MovieReviewSocialNetwork
{
    private User currentUser=null;
    
    public void register()
    {
        UserManager.getInstance().register();
    }

    public void login()
    {
        currentUser = UserManager.getInstance().login(email, password);
    }

    public void viewMovie()
    {
        MovieManager.getInstance().printAll();
        MovieManager.getInstance().getMovie(movieName).printReviewList();
        MovieManager.getInstance().getMovie(movieName).getReview(index);
    }

    public void writeReview()
    {
        MovieManager.getInstance().checkMovie(movieName);
        Review sadsa = new Review(movie, title, body, rate, name);
        currentUser.addNewReview(e);
        MovieManager.getInstance().getMovie(movieName).addNewReview(e);
        ReviewManager.getInstance().addNewReview(e);
    }

    public void menuFollowed()
    {
        currentUser.printFollowed();
        String email = currentUser.getFollowedEmail(index);
        User a = UserManager.getInstance().getUser(email);
        a.getOwnReview();
    }

    public void manageProfile()
    {
        currentUser.setName(newName);
        currentUser.setPassword(newPassword);
        
    }

    public void manageReview()
    {
        currentUser.manageReview();
    }
    public void logout()
    {

    }

    public static void main(String arg[]) 
    {
        
    }
}