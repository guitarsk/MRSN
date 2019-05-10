
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
    private User currentUser = null;
    private String state = null;
    private String searchState = null;
    private Integer searchResultPage = 1;
    private ArrayList<Integer> idTemp = null;
    
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
                break;
            case "review":
                break;
            case "user":
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

    public void searchState()
    {
        // search func still lack select movie state and select review state
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
            idTemp = ReviewManager.getInstance().search(stringInput,intInput);
            state = "search result";
            searchState = "review"; 
            searchResultPage = 1; 
        }
        else
        {
            System.out.println("Error :Invalid number");
            tryAgain("main");
        }
        intInput = MRSN.getOneInteger("Select your Movie number ( enter 0 to exit ) :");
        if(intInput != 0)
        {
            
        }                    
    }

    public void searchResultState()
    {
        int menuNum=1;
        int previous=0,next=0,search=0,main=0;
        System.out.println(" "+idTemp.size()+" results found");
        if(searchState.equals("movie"))
        {
            for(int i = 5*(searchResultPage-1) ; i <idTemp.size() ; i++,menuNum++ )
            {
                System.out.println(menuNum+")");
                MovieManager.getInstance().printSearch(idTemp.get(i));
            }  
        }
        else if(searchState.equals("review"))
        {
            System.out.println(" "+idTemp.size()+" results found");
            for(int i = 5*(searchResultPage-1) ; i < idTemp.size() ; i++,menuNum++)
            {
                System.out.println(menuNum+")");
                ReviewManager.getInstance().printSearch(idTemp.get(i));
            }   
        }
        else
        {
            System.out.println("Error: Invalid searchState");
        }
        System.out.println("Enter your action");
        if(menuNum>1)
        {
            int temp = menuNum -1;
            System.out.println("(1-"+temp+") view movie");
        }
        if(searchResultPage>1)
        {
            System.out.println("("+menuNum+") previous page");
            previous = menuNum;
            menuNum++;
        }
        double checkSize = idTemp.size();
        if(searchResultPage<(checkSize/5))
        {
            System.out.println("("+menuNum+") next page");
            next = menuNum;
            menuNum++;
        }
        System.out.println("("+menuNum+") back to search");
        search = menuNum;
        menuNum++;
        System.out.println("("+menuNum+") back to search");
        main = menuNum;
        menuNum++;
    }

    /** unfinish need save */
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

        /** login or register phase */
        /**
         * attempt to login might need some method to break out of the loop to register without successfully login
         */
        while(true)
        {
            MRSN.stateChange();
        }
        /** using website phase */
        while(true)
        {
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