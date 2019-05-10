
/**
 *  MovieReviewSocialnetwork is facade class for MRSN project
 * 
 * Created by Nawakanok Muengkam (Guitar) 5907050101044
 *      Build project's possible framework and some implementation
 *  Modified by Jarudet Wichit (Jardet) 59070501008
 *      7/5/2019 Continuing implement project  
 * 
 *  Borrow method getOneInteger() and getOneString() from Prof.Sally Goldin
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieReviewSocialNetwork
{
    private User currentUser=null;
    private String state=null;
    private ArrayList<String> idTemp = null;
    
    /**
     * Asks for one integer value, and returns it
     * as the function value.
     * @param   prompt    String to print, telling which coordinate
     * @return  the value. Exits with error if user types in
     *          something that can't be read as an integer 
     */
    public int getOneInteger(String prompt)
    {
        int value = 0;	   
        String inputString;
        int readBytes = 0;
        byte buffer[] = new byte[200]; 
        System.out.println(prompt);
        try
        {
            readBytes = System.in.read(buffer,0,200);
	    }
        catch (IOException ioe)
        {
	        System.out.println("Input/output exception - Exiting");
	        System.exit(1);
        }
        inputString = new String(buffer);
        try 
        {
            /* modify to work for both Windows and Linux */
            int pos = inputString.indexOf("\r");
            if (pos <= 0)
                pos = inputString.indexOf("\n");
            if (pos > 0)
                inputString = inputString.substring(0,pos);
            value = Integer.parseInt(inputString);
        }
        catch (NumberFormatException nfe) 
        {
	        System.out.println("Bad number entered - Exiting");
	        System.exit(1);
        }
        return value;
    }

    /**
     * Asks for a string, and returns it
     * as the function value.
     * @param   prompt    String to print, telling which coordinate
     * @return  the string value entered, without a newline 
     */
    public String getOneString(String prompt)
    {	   
        String inputString;
        int readBytes = 0;
        byte buffer[] = new byte[200]; 
        System.out.println(prompt);
        try
        {
            readBytes = System.in.read(buffer,0,200);
        }
        catch (IOException ioe)
        {
            System.out.println("Input/output exception - Exiting");
            System.exit(1);
        }
        inputString = new String(buffer);
        /* modify to work for both Windows and Linux */
        int pos = inputString.indexOf("\r");
        if (pos <=0 )
            pos = inputString.indexOf("\n");
        if (pos > 0)
            inputString = inputString.substring(0,pos);
        return inputString;
    }
    
    /**
     * clear terminal screen
     */
    public void clearScreen() 
    {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  

    /**
     * wait for one input
     */
    public void pressAnyKeyToContinue()
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
            name = getOneString("Please enter user name :");
            email = getOneString("Please enter email :");
            System.out.print("Please enter password :");
            password = getOneString("Please enter password :");
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
    public void stateChange()
    {

    }

    public static void main(String arg[]) 
    {
        /** Initialize phase */
        MovieReviewSocialNetwork MRSN = new MovieReviewSocialNetwork();
        UserManager.getInstance().initialize();
        MovieManager.getInstance().initialize();
        ReviewManager.getInstance().initialize();

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
            intInput = MRSN.getOneInteger("Your input :");
            switch(intInput)
            {
                case 1:
                    while(true)
                    {
                        String email = MRSN.getOneString("Please enter email :");
                        String password = MRSN.getOneString("Please enter password :");
                        if(MRSN.login(email,password))
                        {
                            System.out.println("Login successful");
                            MRSN.pressAnyKeyToContinue();
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
            MRSN.clearScreen();
            System.out.println("Welcome to MRSN! Please choose your action");
            System.out.println("1) search for...");
            System.out.println("2) discover new things");
            System.out.println("3) followed");
            System.out.println("4) manage my reviews");
            System.out.println("5) edit my profile");
            System.out.println("6) write review");
            System.out.println("7) logout");
            intInput = MRSN.getOneInteger("Your input :");
            switch(intInput)
            {
                case 1:// search func still lack select movie state and select review state
                    System.out.println("\nChoose your searh option");
                    System.out.println("1) search movie name");
                    System.out.println("2) search by category");
                    System.out.println("3) search reviewer name");
                    intInput = MRSN.getOneInteger("Your input :");
                    stringInput = MRSN.getOneString("Search for :");
                    ArrayList<Integer> idTemp;
                    if(intInput == 1 || intInput == 2) // search using MovieManager
                    {
                        idTemp = MovieManager.getInstance().search(stringInput,intInput);
                        System.out.println(" "+idTemp.size()+" results found");
                        for(int i = 0 ; i <idTemp.size() ; i++ )
                        {
                            System.out.println(i+")");
                            MovieManager.getInstance().printSearch(idTemp.get(i));
                        }       
                    }
                    else if(intInput == 3) // search using ReviewManager
                    {
                        idTemp = ReviewManager.getInstance().search(stringInput,intInput);
                        System.out.println(" "+idTemp.size()+" results found");
                        for(int i = 0 ; i < idTemp.size() ; i++ )
                        {
                            System.out.println(i+")");
                            ReviewManager.getInstance().printSearch(idTemp.get(i));
                        }    
                    }
                    else
                    {
                        System.out.println("Error :Invalid number");
                    }
                    intInput = MRSN.getOneInteger("Select your Movie number ( enter 0 to exit ) :");
                    if(intInput != 0)
                    {
                        
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