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
    
    /**
     * clear terminal screen
     */
    public void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  

    /**
     * wait for one input
     */
    private void pressAnyKeyToContinue()
    { 
        System.out.println("Press any key to continue...");
        try
        {
            System.in.read();
        }  
        catch(Exception e)
        {}  
    }

    /**
     * ask for user info to register,
     * auto login after successful register
     */
    public void register()
    {
        String name,email,password;

        while(true)
        {
            System.out.print("Please enter user name :");
            name = getLineString();
            System.out.print("Please enter email :");
            email = getLineString();
            System.out.print("Please enter password :");
            password = getLineString();
            System.out.print("Please choose favorite movie genres");
            ArrayList<String> genre = new ArrayList<String>();
            genre.add("Action");
            if(UserManager.getInstance().register(name,email,password,genre))
            {
                System.out.println("Register successful");
                break;
            }
            else
            {
                System.out.println("This email is already used please try another email");
            }
        }
        this.login(email,password);
    }

    public boolean login(String email, String password)
    {
        currentUser = UserManager.getInstance().login(email, password);
        if(currentUser==null)
            return false;
        else
            return true;
    }

    /**
     * check for user login status
     * @return true if logged in, false if not logged in
     */
    public boolean checkLoginStatus()
    {
        if(this.currentUser == null)
        {
            return false;
        }
        else
        {
            return true;
        }
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
       
        /**
         * attempt to login might need some method to break out of the loop to register without successfully login
         */
        while(MRSN.checkLoginStatus()!=true)
        {
            System.out.println("Welcome to 'Movie Review Social Network!' ");
            System.out.println("Please enter your action");
            System.out.println("1) Login");
            System.out.println("2) Register");
            System.out.println("3) Exit");
            System.out.println("Your input :");
            intInput = MRSN.getOneInt();
            switch(intInput)
            {
                case 1:
                    while(true)
                    {
                        System.out.print("Please enter email :");
                        String email = MRSN.getLineString();
                        System.out.print("Please enter password :");
                        String password = MRSN.getLineString();
                        if(MRSN.login(email,password))
                        {
                            System.out.println("Login successful");
                            MRSN.pressAnyKeyToContinue();
                            MRSN.clearScreen();
                            break;
                        }
                        else
                        {
                            System.out.println("Invalid email/password please try again");
                        }
                    }
                    break;
                case 2:
                    MRSN.register();
                    break;
                case 3:
                    System.out.println("Close Program");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error :Invalid number");
                    break;
            }
        }
        /** using website phase */
        while(true)
        {
            System.out.println("Welcome to MRSN! Please choose your action");
            System.out.println("1) search for...");
            System.out.println("2) discover new things");
            System.out.println("3) manage my reviews");
            System.out.println("4) edit my profile");
            System.out.println("5) write review");
            System.out.println("6) logout");
            System.out.println("Your input :");
            intInput = MRSN.getOneInt();
            switch(intInput)
            {
                case 1:
                    System.out.println("\nChoose your searh option");
                    System.out.println("1) search movie name");
                    System.out.println("2) search by category");
                    System.out.println("3) search reviewer name");
                    System.out.println("Your input :");
                    intInput = MRSN.getOneInt();
                    System.out.println("Search for :");
                    stringInput = MRSN.getLineString();
                    ArrayList<Movie> movieTemp;
                    if((movieTemp = MovieManager.getInstance().printSearch(stringInput,intInput))!=null)
                    {
                        System.out.println(" "+movieTemp.size()+" results found");
                        for(int i = 0 ; i < movieTemp.size() ; i++ )
                        {
                            movieTemp.get(i).printMovieInfo();
                        }
                    }
                    else
                    {
                        System.out.println(" 0 results found");
                    }
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Error :Invalid number");
                    break;
            }

        }


        /** save data phase */
    }
}