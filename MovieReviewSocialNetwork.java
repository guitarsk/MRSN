/**
 *  MovieReviewSocialnetwork is facade class for MRSN project
 * 
 * Created by Nawakanok Muengkam (Guitar) 5907050101044
 *      Build project's possible framework and some implementation
 *  Modified by Jarudet Wichit (Jardet) 59070501008
 *      7/5/2019 Continuing implement project  
 */

import java.util.ArrayList;
import java.util.Scanner;

public class MovieReviewSocialNetwork
{
    private User currentUser=null;
    
    /**
     * ask user for input and return one line of string
     * @return String user input
     */
   public String getLineString()
    {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    /**
     * ask user for input and return one int
     * @return int user input
     */
    public int getOneInt()
    {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        return number;
    }
    
    public void register()
    {
        System.out.print("Please enter user name :");
        String name = getLineString();
        System.out.print("Please enter email :");
        String email = getLineString();
        System.out.print("Please enter password :");
        String password = getLineString();
        System.out.print("Please choose favorite movie genres");
        ArrayList<String> genre = new ArrayList<String>();
        genre.add("Action");
        UserManager.getInstance().register(name,email,password,genre);
    }

    public boolean login(String email, String password)
    {
        currentUser = UserManager.getInstance().login(email, password);
        if(currentUser==null)
            return false;
        else
            return true;
    }

    public void viewMovie(String movieName)
    {
        MovieManager.getInstance().printAll();
        MovieManager.getInstance().getMovie(movieName).printReviewList();
        //MovieManager.getInstance().getMovie(movieName).getReview(index);
    }

    public void writeReview(String movieName,String title, String body,Double rate, String name)
    {
        MovieManager.getInstance().checkMovie(movieName);
        Movie movieToWrite = MovieManager.getInstance().getMovie(movieName);
        Review newReview = new Review(movieName, title, body, rate, name);
        ReviewManager.getInstance().addNewReview(newReview);
    }

    public void menuFollowed()
    {
        currentUser.printFollowed();
        //String email = currentUser.getFollowedEmail(index);
        //User a = UserManager.getInstance().getUser(email);
        //a.getOwnReview();
    }

    public void manageProfile()
    {
        /*currentUser.setName(newName);
        currentUser.setPassword(newPassword);*/
        
    }

    public void manageReview()
    {
        //currentUser.manageReview();
    }
    public void logout()
    {

    }

    public static void main(String arg[]) 
    {
        /** Initialize phase */
        MovieReviewSocialNetwork MRSN = new MovieReviewSocialNetwork();
        int intInput;
        String stringInput;
        MovieFileManager movieFileManager = new MovieFileManager();
        movieFileManager.openRead(MovieManager.getInstance().getMovieFileName());
        Movie test = null;
        while((test = movieFileManager.readMovie()) != null)
        {
            System.out.println(test.getName() + " " + test.getReleaseYear());
        }
        movieFileManager.closeRead();
        /** login or register phase */
        System.out.println("Welcome to 'Movie Review Social Network!' ");
        System.out.println("Please enter your action");
        System.out.println("1) Login");
        System.out.println("2) Register");
        System.out.println("3) Exit");
        intInput = MRSN.getOneInt();
        if(intInput == 1)
        {

        }
        else if (intInput == 2)
        {

        }
        else if(intInput == 3)
        {
            System.exit(0);
        }
        else
        {
            
        }
        /** using website phase */


        /** save data phase */
    }
}