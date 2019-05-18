
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

import com.sun.glass.ui.Size;

public class MovieReviewSocialNetwork
{
    private User currentUser = null; // keep user that logged in at the moment
    private String state = null; // use to keep MRSN current state
    private String searchState = null; // use in searchState and searchResultState
    private Integer searchResultPage = 1; // current page of search result use in SearhResultState
    private ArrayList<Integer> idTemp = null; // id of movies or reviews that has been searched
    private Integer singleIdTemp = null; // id of movies or reviews that will show their info in movieState or reviewState
    
    private boolean editUserReview = false;
    private boolean editUserProfile = false;
    private boolean userAddNewFollow = false;

    private Integer intInput = null;
    private String stringInput = null;
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
    private void clearScreen() 
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

    public void tryAgain(String newState)
    {
        stringInput = getOneString("Do you want to try again (y/n)?");
        if(!stringInput.toLowerCase().equals("y"))
        {
            state = newState;
        }
    }

    public void stateChange()
    {
        clearScreen();
        switch(state)
        {
            case "begin":
                beginState();
                break;
            case "login":
                loginState();
                break;
            case "register":
                registerState();
                break;
            case "main":
                mainState();
                break;
            case "search":
                searchState();
                break;
            case "search result":
                searchResultState();
                break;
            case "movie":
                movieState();
                break;
            case "review":
                reviewState();
                break;
            case "user":
                break;
            case "write review":
                break;
            case "edit":
                break;
            case "discover":
                break;
            case "manage":
                break;
            case "exit":
                exitState();
                break;
            default:
                System.out.println("Error: Invalid state change");
                break;
        }
        pressAnyKeyToContinue();
    }

    private void beginState()
    {
        System.out.println("Welcome to 'Movie Review Social Network!' ");
        System.out.println("Please enter your action");
        System.out.println("(1) Login");
        System.out.println("(2) Register");
        System.out.println("(3) Exit");
        intInput = getOneInteger("Your input :");
        switch(intInput)
        {
            case 1:
                state = "login";
                break;
            case 2:
                state = "register";
                break;
            case 3:
                state = "exit";
                break;
            default:
                System.out.println("Error :Invalid number");
                break;
        }
    }

    private void loginState()
    {
        while(true)
        {
            String email = getOneString("Please enter email :");
            String password = getOneString("Please enter password :");
            if(login(email,password))
            {
                System.out.println("Login successful");
                state = "main";
            }
            else
            {
                System.out.println("Invalid email/password");
                tryAgain("begin");
            }
        }
    }

    /**
     * ask for user info to register,
     * auto login after successful register
     */
    private void registerState()
    {
        String name,email,password;

        name = getOneString("Please enter user name :");
        email = getOneString("Please enter email :");
        password = getOneString("Please enter password :");
        /**need fix in genre */
        System.out.print("Please choose favorite movie genres");
        ArrayList<String> genre = new ArrayList<String>();
        genre.add("Action");
        if(UserManager.getInstance().register(name,email,password,genre))
        {
            System.out.println("Register successful");
            login(email,password);
            state = "main";
        }
        else
        {
            System.out.println("This email is already used please try another email");
            tryAgain("begin");
        }
    }

    /** not finish still need loads of functioanlity */
    private void mainState()
    {
        System.out.println("Welcome to MRSN! Please choose your action");
            System.out.println("(1) search for...");
            System.out.println("(2) discover new things");
            System.out.println("(3) followed");
            System.out.println("(4) manage my reviews");
            System.out.println("(5) edit my profile");
            System.out.println("(6) write review");
            System.out.println("(7) logout");
            intInput = getOneInteger("Your input :");
            switch(intInput)
            {
                case 1:// search func still lack select movie state and select review state
                    state = "search";
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

    private void searchState()
    {
        System.out.println("\nChoose your searh option");
        System.out.println("(1) search movie name");
        System.out.println("(2) search by category");
        System.out.println("(3) search reviewer name");
        intInput = getOneInteger("Your input :");
        stringInput = getOneString("Search for :");
        ArrayList<Integer> idTemp;
        if(intInput == 1 || intInput == 2) // search using MovieManager
        {
            idTemp = MovieManager.getInstance().search(stringInput,intInput);
            state = "search result";
            searchState = "movie";
            searchResultPage = 1;
        }
        else if(intInput == 3) // search using ReviewManager
        {
            idTemp = ReviewManager.getInstance().search(stringInput);
            state = "search result";
            searchState = "review"; 
            searchResultPage = 1; 
        }
        else
        {
            System.out.println("Error :Invalid number");
            tryAgain("main");
        } 
    }

    private void searchResultState()
    {
        //searchResultPage
        System.out.println(" "+idTemp.size()+" results found");
        if(searchState.equals("movie"))
        {
            for(int i = 5*(searchResultPage-1) , j = 1; (i <idTemp.size())&&(j<6) ; i++,j++)
            {
                System.out.println(j+")");
                MovieManager.getInstance().printSearch(idTemp.get(i));
            }  
        }
        else if(searchState.equals("review"))
        {
            System.out.println(" "+idTemp.size()+" results found");
            for(int i = 5*(searchResultPage-1), j = 1 ; (i < idTemp.size())&&(j<6) ; i++,j++)
            {
                System.out.println(j+")");
                ReviewManager.getInstance().printSearch(idTemp.get(i));
            }   
        }
        System.out.println("You are in "+searchResultPage+" page");
        System.out.println("Enter your action");
        System.out.println("(1) go to previous page");
        System.out.println("(2) go to next page");
        System.out.println("(3) select");
        intInput = getOneInteger("Your input:");
        switch(intInput)
        {
            case 1:
                if(searchResultPage>1)
                    searchResultPage--;
                else
                    System.out.println("You are already in first page");
                break;
            case 2:
                if(((searchResultPage-1)*5)<idTemp.size())
                    searchResultPage++;
                else
                    System.out.println("You are already in last page");
                break;
            case 3:
                intInput = getOneInteger("Enter search result number:");
                if(((((searchResultPage-1)*5)+(intInput-1))<idTemp.size())) // chack if index out of bound
                {
                    singleIdTemp = idTemp.get((((searchResultPage-1)*5)+(intInput-1)));
                    if(searchState.equals("movie"))
                        state = "movie";
                    else if(searchState.equals("movie"))
                        state = "review";
                }
                else
                {
                    System.out.println("Error :invalid input");
                    tryAgain("main");
                }
                break;
            default:
                System.out.println("Error :invalid input");
                tryAgain("main");
                break;
        }
        
    }

    private void movieState()
    {
        //singleIdTemp
        MovieManager.getInstance().printSearch(singleIdTemp);
        idTemp = ReviewManager.getInstance().search(singleIdTemp);
        for(int i = 0 ; i < 5 ; i++)
        {
            ReviewManager.getInstance().printSearch(idTemp.get(i));
        }
        System.out.println("\nChoose your action ");
        System.out.println("(1) read more review");
        System.out.println("(2) write review");
        System.out.println("(3) back to main menu");
        intInput = getOneInteger("Enter your action :");
        switch(intInput)
        {
            case 1:;
                state = "search result";
                searchState = "review"; 
                searchResultPage = 1; 
                break;
            case 2:
                state= "write review";
                break;
            case 3:
                state = "main";
                break;
            default:
                System.out.println("Error : invalid input");
                tryAgain("main");
        }
    }

    private void reviewState()
    {
        //singleIdTemp
        ReviewManager.getInstance().printSearch(singleIdTemp);
        System.out.println("\nChoose your action");
        System.out.println("(1) like review");
        System.out.println("(2) dislike review");
        System.out.println("(3) follow reviewer");
        System.out.println("(4) back to main menu");
        intInput = getOneInteger("Enter your action :");
        switch(intInput)
        {
            case 1:
                ReviewManager.getInstance().setLikeOrDislike(singleIdTemp,currentUser.getEmail(), "like");
                System.out.println("You liked the review");
                break;
            case 2:
                ReviewManager.getInstance().setLikeOrDislike(singleIdTemp,currentUser.getEmail(), "dislike");
                System.out.println("You disliked the review");
                break;
            case 3:
                currentUser.addFollowed(UserManager.getInstance().getUser((String)ReviewManager.getInstance().getSelect(singleIdTemp, "email")));
                System.out.println("You followed this reviewer");
                break;
            case 4:
                state = "main";
                System.out.println("Going back to main");
                break;
            default:
                System.out.println("Error : invalid input");
                tryAgain("main");
                break;
        }
    }

    /** unfinish needed save */
    private void exitState()
    {
        System.out.println("Close Program");
        System.exit(0);
    }

    /**
     * ask for user info to register,
     * auto login after successful register
     */

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
        //if new review create call writeNewReview() in ReviewManager
        //if new movie create call writeNewMovie() in MovieManager
    }

    public void menuFollowed()
    {
        currentUser.printFollowed();
        /* if add new follow set userAddNewFollow to true */
    }

    public void manageProfile()
    {
        /* if edit profile set editUserProfile to true */
    }

    public void manageReview()
    {
        /* if edit review set editUserReview to true */
    }
    public void logout()
    {
        currentUser = null;
        if(userAddNewFollow == true)
        {
            UserManager.getInstance().rewriteAllFollow();
        }
        if(editUserProfile == true)
        {
            UserManager.getInstance().rewriteAllUser();
        }
        if(editUserReview == true)
        {
            ReviewManager.getInstance().rewriteAllReview();
        }
    }
    public static void main(String arg[]) 
    {
        /** Initialize phase */
        MovieReviewSocialNetwork MRSN = new MovieReviewSocialNetwork();
        UserManager.getInstance().initialize();
        MovieManager.getInstance().initialize();
        ReviewManager.getInstance().initialize();

        /** state change method show website UI based on MRSN current state */
        while(true)
        {
            MRSN.stateChange();
        }
        /** save data phase */
    }
}