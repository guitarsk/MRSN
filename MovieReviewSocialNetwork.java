import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *  MovieReviewSocialnetwork is facade class for MRSN project
 * 
 * Created by Nawakanok Muangkham (Guitar) 59070501044
 *      Build project's possible framework and some implementation
 *  Modified by Jarudet Wichit (Jardet) 59070501008
 *      7/5/2019 Continuing implement project  
 *      21/5/2019 Finalize the design
 * 
 *  Borrow method getOneInteger() and getOneString() from Prof.Sally Goldin
 */

public class MovieReviewSocialNetwork
{
    /** keep user that logged in at the moment */
    private User currentUser = null; 

    /** use to keep MRSN current state */
    private String state = "begin"; 

    /** use in searchState and searchResultState */
    private String searchState = null;

    /** current page of search result use in SearhResultState */
    private Integer searchResultPage = 1;

    /** id of movies or reviews that has been searched */
    private ArrayList<Integer> idTemp = null;

    /** id of movies or reviews that will show their info in movieState or reviewState */
    private Integer singleIdTemp = null;
    
    /** flag to detect the need to rewrite the entire allReviews.txt */
    private boolean editUserReview = false; 

    /** flag to detect the need to rewrite the entire allUsers.txt */
    private boolean editUserProfile = false;

    /** flag to detect the need to rewrite the entire allFollows.txt */
    private boolean userAddNewFollow = false;

    /** central temp variable for input integer value */
    private Integer intInput = null;

    /** central temp variable for input String value */
    private String stringInput = null;

    
    /**
     * Asks for one integer value, and returns it
     * as the function value.
     * @param   prompt    String to print, telling which coordinate
     * @return  the value. Exits with error if user types in
     *          something that can't be read as an integer 
     */
    private int getOneInteger(String prompt)
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
	        
        }
        return value;
    }

    /**
     * Asks for one double value, and returns it
     * as the function value.
     * @param   prompt    String to print, telling which coordinate
     * @return  the value. Exits with error if user types in
     *          something that can't be read as an integer 
     */
    private double getOneDouble(String prompt)
    {
        double value = 0;	   
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
            value = Double.parseDouble(inputString);
        }
        catch (NumberFormatException nfe) 
        {
	        System.out.println(nfe);
        }
        return value;
    }

    /**
     * Asks for a string, and returns it
     * as the function value.
     * @param   prompt    String to print, telling which coordinate
     * @return  the string value entered, without a newline 
     */
    private String getOneString(String prompt)
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
     * wait for enter
     */
    private void pressEnterToContinue()
    { 
        try
        {
            getOneString("\nPress enter to continue...");
        }  
        catch(Exception e)
        {

        }  
    }

    /**
     * ask user for confirmation if input not start with  y MRSN will change to new state 
     * @param newState the state that MRSN will change to (usually main)
     * @param text to show to user (usually error warning)
     */
    private void tryAgain(String newState,String text)
    {
        System.out.println(text);
        stringInput = getOneString("Do you want to try again [y/n]?");
        if(stringInput.toLowerCase().startsWith("y") == false)
        {
            state = newState;
        }
    }

    /**
     * this is tryAgain() but more primitive it will not change MRSN state
     * @param text to show to user (usually the thing user are about to do)
     * @return true if user enter something start with y
     */
    private boolean confirmation(String text)
    {
        String temp = getOneString("Confirm "+text+"[y/n]?");
        if(temp.toLowerCase().startsWith("y"))
            return true;
        else
            return false;
    }

    /**
     * most crucial method, check state of MRSN
     * show output, receive input based on MRSN state 
     */
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
                userState();
                break;
            case "change password":
                changePasswordState();
                break;
            case "write review":
                writeReviewState();
                break;
            case "edit":
                editState();
                break;
            case "followed":
                followedState();
                break;
            case "discover":
                discoverState();
                break;
            case "manage":
                manageState();
                break;
            case "edit review":
                editReviewState();
                break;
            case "logout":
                logoutState();
                break;
            case "exit":
                logoutState();
                System.out.println("Close Program");
                System.exit(0);
                break;
            default:
                System.out.println("Error: Invalid state change");
                break;
        }

        pressEnterToContinue();
    }

    /** 
     * first page of the site made user login or register
     * 
     * this state can change to "loginState", "registerState" and "exitState"
     */
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

    /**
     * receive input email and password
     * then try to login using login()
     * which using UserManager
     * after succeed change state to main
     * 
     * this state can change to "mainState" and "beginState"
     */
    private void loginState()
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
            tryAgain("begin","Invalid email/password");
        }
    }

    /**
     * ask for user info to register,
     * auto login after successful register
     * after succeed this change state to main
     * 
     * this state can change to "beginState" and "mainState"
     */
    private void registerState()
    {
        String name,email,password;
        email = getOneString("Please enter email :");
        if(UserManager.getInstance().checkEmail(email)==false) // check if email already in use
        {    
            name = getOneString("Please enter user name :");
            password = getOneString("Please enter password :");
            System.out.println("Please choose favorite movie genres");
            ArrayList<String> genre = genreMaker();
            if(confirmation("registration"))
            {
                UserManager.getInstance().register(name,email,password,genre);
                System.out.println("Register successful");
                login(email,password);
            }
            else
            {
                System.out.println("Registration cancel back to main menu...");
            }
            state = "main";
        }
        else
        {
            tryAgain("begin","This email is already used please try another email");
        }
       
    }

    /** 
     * main page to let user choose their action 
     * 
     * this state can change to any state
     */
    private void mainState()
    {
        System.out.println("Welcome to MRSN! Please choose your action");
        System.out.println("(1) search for...");
        System.out.println("(2) discover new movie");
        System.out.println("(3) followed");
        System.out.println("(4) manage my reviews");
        System.out.println("(5) view my profile");
        System.out.println("(6) write review");
        System.out.println("(7) logout");
        System.out.println("(8) exit");
        intInput = getOneInteger("Your input :");
        switch(intInput)
        {
            case 1:
                state = "search";
                break;
            case 2:
                state = "discover";
                break;
            case 3:
                state = "followed";
                break;
            case 4:
                state = "manage";
                break;
            case 5:
                state = "user";
                break;
            case 6:
                state = "write review";
                break;
            case 7:
                state = "logout";
                break;
            case 8:
                state = "exit";
                break;
            default:
                System.out.println("Error :Invalid number");
                break;
        }
    }

    /**
     * ask user for search type then search using MovieManager or ReviewManager
     * if search succeed change MRSN state to searchResultState
     * 
     * this state can change to "searchResultState" and "mainState"
     */
    private void searchState()
    {
        System.out.println("\nChoose your searh option");
        System.out.println("(1) search movie name");
        System.out.println("(2) search by category");
        System.out.println("(3) search reviewer name");
        System.out.println("(4) back to main menu");
        intInput = getOneInteger("Your input :");
        if(intInput == 1 || intInput == 2) // search using MovieManager
        {
            stringInput = getOneString("Search for :");
            idTemp = MovieManager.getInstance().search(stringInput,intInput);
            searchResultInitialize("movie");
        }
        else if(intInput == 3) // search using ReviewManager
        {
            stringInput = getOneString("Search for :");
            stringInput = UserManager.getInstance().searchForEmail(stringInput);
            idTemp = ReviewManager.getInstance().search(stringInput);
            searchResultInitialize("review");
        }
        else if(intInput == 4)
        {
            state =" main";
        }
        else
        {
            tryAgain("main","Error: Invalid input");
        } 
    }


    /**
     * this method used to set config state, searchState, and searchResultPage
     * before using searchResultState() 
     */
    private void searchResultInitialize(String searchState)
    {
        state = "search result";
        this.searchState = searchState; 
        searchResultPage = 1; 
    }

    /**
     * print out result of search from idTemp
     * this method should use searchResultInitialize() first
     */
    private void printResult()
    {
        System.out.println("     "+idTemp.size()+" results");
        if(searchState.equals("movie"))
        {
            for(int i = 5*(searchResultPage-1) , j = 1; (i <idTemp.size())&&(j<6) ; i++,j++)
            {
                System.out.print(j+")");
                MovieManager.getInstance().printSearch(idTemp.get(i));
                System.out.println();
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
    }

    /**
     *  this method show search result
     *  then ask user for their action
     * 
     *  this state can change to "movieState", "reviewState", and "mainState"
     * 
     *  if using this method after meking change in idTemp 
     *  should use searchresultInitialize() first
     */
     private void searchResultState()
    {
        printResult();
        System.out.println("\nYou are in "+searchResultPage+" page");
        System.out.println("Enter your action");
        System.out.println("(1) go to previous page");
        System.out.println("(2) go to next page");
        System.out.println("(3) select");
        System.out.println("(4) back to main menu");
        intInput = getOneInteger("Your input:");
        int pageMaximumResult = (searchResultPage-1)*5;
        switch(intInput)
        {
            case 1:
                if(searchResultPage>1)
                    searchResultPage--;
                else
                    System.out.println("You are already in first page");
                break;
            case 2:
                if(((searchResultPage)*5)<idTemp.size())
                {
                    System.out.println(pageMaximumResult+" "+idTemp.size());
                    searchResultPage++;
                }
                    
                else
                    System.out.println("You are already in last page");
                break;
            case 3:
                intInput = getOneInteger("Enter search result number:");
                if(((pageMaximumResult+(intInput-1))<idTemp.size()) && ((pageMaximumResult+(intInput-1)) >= 0)) // chack if index out of bound
                {
                    singleIdTemp = idTemp.get((pageMaximumResult+(intInput-1)));
                    if(searchState.equals("movie"))
                        state = "movie";
                    else if(searchState.equals("review"))
                        state = "review";
                }
                else
                {
                    tryAgain("main","Error: Invalid input");
                }
                break;
            case 4:
                System.out.println("Going to main menu...");
                state = "main";
                break;
            default:
                tryAgain("main","Error: Invalid input");
                break;
        }
        
    }

    /**
     * this method show Movie info from singleIdTemp
     * then ask user for their choice of action
     * 
     * this state can change to "searchResultState" and "mainState"
     */
    private void movieState()
    {
        //singleIdTemp
        MovieManager.getInstance().printSearch(singleIdTemp);
        idTemp = ReviewManager.getInstance().search(singleIdTemp);
        System.out.println("\n");
        if(idTemp.size() == 0)
        {
            System.out.println("This movie don't have any reviews");
        }
        else
        {
            System.out.println("**Review Lists**\n");
            for(int i = 0 ; (i < 5) && (i<idTemp.size()); i++)
            {
                ReviewManager.getInstance().printSearch(idTemp.get(i));
                System.out.println();
            }
        }
        
        System.out.println("\nChoose your action ");
        System.out.println("(1) read more review");
        System.out.println("(2) write review");
        System.out.println("(3) back to main menu");
        intInput = getOneInteger("Enter your action :");
        switch(intInput)
        {
            case 1:;
                searchResultInitialize("review");
                break;
            case 2:
                writeReview();
                state = "main";
                break;
            case 3:
                state = "main";
                break;
            default:
                tryAgain("main","Error: Invalid input");
        }
    }

    /**
     * this method show Review info from singleIdTemp.
     * then ask user for their choice of action
     * 
     * this state can change to "mainState"
     */
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
                if(confirmation("like review"))
                {
                    if(ReviewManager.getInstance().setLikeOrDislike(singleIdTemp,currentUser.getEmail(), "like"))
                    {
                        editUserReview = true;
                        System.out.println("You liked the review");
                    }
                    else
                        System.out.println("You already liked this review");
                    
                }
                else
                    System.out.println("Cancel like review");
                break;
            case 2:
                if(confirmation("dislike review"))
                {
                    if(ReviewManager.getInstance().setLikeOrDislike(singleIdTemp,currentUser.getEmail(), "dislike"))
                    {
                        editUserReview = true;
                        System.out.println("You disliked the review");
                    }
                    else
                        System.out.println("You already disliked this review");
                    
                }
                else 
                    System.out.println("Cancel dislike review");
                    break;
            case 3:
                if(confirmation("follow User"))
                {
                    currentUser.addFollowed(UserManager.getInstance().getUser((String)ReviewManager.getInstance().getSelect(singleIdTemp, "email")));
                    userAddNewFollow = true;
                    System.out.println("You followed this reviewer");
                }
                else 
                    System.out.println("Cancel follow reviewer");
                break;
            case 4:
                state = "main";
                System.out.println("Going back to main");
                break;
            default:
                tryAgain("main", "Error: Invalid input");
                break;
        }
    }

    /**
     * this state show currentUser info
     * then ask user for their action
     * 
     * this state can change to "editState", "changePasswordState", and "mainState"
     */
    private void userState()
    {
        currentUser.showUser();
        System.out.println("Choose your action");
        System.out.println("(1) edit profile");
        System.out.println("(2) change password");
        System.out.println("(3) back to main menu");
        intInput = getOneInteger("Enter your action :");
        switch(intInput)
        {
            case 1:
                state = "edit";
                break;
            case 2:
                state = "change password";
                break;
            case 3:
                state = "main";
                break;
            default:
                tryAgain("main","Error: Invalid input");
                break;
        }
    }

    /**
     * this state make user ener their password
     * if correct will allow user to change their password
     * 
     * this state can change to "mainState"
     */
    private void changePasswordState()
    {
        System.out.println("You are about to change password");
        stringInput = getOneString("Please enter your old password: ");
        if(UserManager.getInstance().login(currentUser.getEmail(),stringInput) != null)
        {
            stringInput = getOneString("Please enter your new password: ");
            if(confirmation("change password"))
            {
                currentUser.setPassword(stringInput);
                editUserProfile = true;
                System.out.println("Password changed");
            }
            else
                System.out.println("Cancel change password returning to main...");
            state = "main";
        }
        else
        {
            tryAgain("main", "Wrong password");
        }
    }

    /**
     * this method workflow:
     * 1.ask for movie name
     * 2.search for movie
     * 3.show movie
     * 4.ask for user choice of action
     * 
     * this state can change to "mainState"
     */
    private void writeReviewState()
    {
        boolean skip = false;
        System.out.println("You are in write review page");
        stringInput = getOneString("Enter movie name you want to review");
        idTemp = MovieManager.getInstance().search(stringInput, 1);
        System.out.println();
        if(idTemp.size()==0)
        {
            System.out.println("**Movie not found you can add new movie or exit**");
        }
        else
        {
            for(int i = 0 , j = 1 ; i < idTemp.size() ; i++,j++)
            {
                System.out.println(j+")");
                MovieManager.getInstance().printSearch(idTemp.get(i));
                System.out.println();
            }
        }

        System.out.println("\nChoose your action");
        System.out.println("(1) select movie");
        System.out.println("(2) add new movie to review");
        System.out.println("(3) re-enter movie name");
        System.out.println("(4) back to main menu");
        intInput = getOneInteger("Enter your action :");
        switch(intInput)
        {
            case 1:
                intInput = getOneInteger("Enter movie number[0 to back] :");
                intInput--;
                if(intInput == -1)
                {
                    skip = true;
                    break;
                }
                else if(intInput < -1 || intInput >= idTemp.size())
                {
                    tryAgain("main", "Error: Invalid input");
                    skip = true;
                    break;
                }
                singleIdTemp = idTemp.get(intInput);
                break;
            case 2:
                String name = getOneString("Enter Movie name");
                ArrayList<String> genre;

                while(true)
                {
                    genre = genreMaker();
                    if(genre.size()==0)
                        System.out.println("Movie should have atleast one category");
                    else
                        break;
                }
                
                int year = getOneInteger("Enter the year this movie released");
                if(confirmation("add new movie"))
                {
                    Movie newMovie = new Movie(name, genre, year);
                    MovieManager.getInstance().addMovie(newMovie);
                    MovieManager.getInstance().writeNewMovie(newMovie);
                    System.out.println("New movie added");
                    singleIdTemp = newMovie.getMovieID();
                }
                else
                {
                    System.out.println("Cancel adding new movie");
                    skip = true;
                }
                    
                break;
            case 3:
                skip = true;
                break;
            case 4:
                state = "main";
                skip = true;
                break;
            default:
                tryAgain("main", "Error: Invalid input");
                skip = true;
                break;
        }
        if(!skip)
        {
            writeReview();
            state ="main";
        }
        
    }

    /** 
     * show currentUser info
     * then ask for user choice of action
     * 
     * this state can change to "mainState", and "followedState"
     */
    private void editState()
    {
        currentUser.showUser();
        System.out.println("what do you want to change?");
        System.out.println("(1) User name");
        System.out.println("(2) Favorite movie categories");
        System.out.println("(3) The people you followed");
        System.out.println("(4) back to main menu");
        intInput = getOneInteger("Enter your action:");
        switch(intInput)
        {
            case 1:
                stringInput = getOneString("Please enter new User name:");
                if(confirmation("changing user name"))
                {
                    currentUser.setName(stringInput);
                    editUserProfile = true;
                    System.out.println("User name changed to "+stringInput);
                }
                else 
                    System.out.println("Cancel change user name");
                break;
            case 2:
                ArrayList<String> tempGenre = genreMaker();
                if(confirmation("change favorite genre"))
                {
                    currentUser.setFavoriteMovieType(tempGenre);
                    editUserProfile = true;
                    System.out.println("Favorite movie category changed");
                }
                else
                    System.out.println("Cancel change favorite genres");                
                break;
            case 3:
                state = "followed";
                break;
            case 4:
                state = "main";
                break;
            default:
                tryAgain("user", "Error: Invalid input");
                break;
        }

        

        
    }

    /**
     * show currentUser followed list
     * ask user to choose User they followed
     * then ask user for their choice of action
     * 
     * this state can change to "reviewState" and "mainState"
     */
    private void followedState()
    {
        if(currentUser.getFollowedSize()==0)
        {
            System.out.println("You did not follow anybody");
            state = "main";
        }
        else
        {
            System.out.println("Your follwed list");
            for(int i = 0, j = 0, k = 1 ; i < currentUser.getFollowedSize() ; i++,j++,k++)
            {
                
                System.out.print("("+k+") "+currentUser.getFollowed(i).getUserName()+" ");
                if(j>4)
                {
                    System.out.print("\n");
                    j=0;
                }
            }

            intInput = getOneInteger("\n Enter number of User you want to see (0 to exit)");
            intInput--;
            if(intInput < 0)
            {
                state ="main";
            }
            else if(intInput<currentUser.getFollowedSize())
            {
                User writer = currentUser.getFollowed(intInput);
                System.out.println("What do you want to do with "+currentUser.getFollowed(intInput));
                System.out.println("(1) see review");
                System.out.println("(2) unfollow");
                System.out.println("(3) back");
                System.out.println("(4) back to main menu");
                intInput = getOneInteger("Enter your action :");
                switch(intInput)
                {
                    case 1:
                        idTemp = ReviewManager.getInstance().search(writer.getEmail());
                        searchResultInitialize("review");
                        break;
                    case 2:
                        if(confirmation("unfollow "+writer.getUserName()))
                        {
                            currentUser.removeFollowed(writer);
                            userAddNewFollow = true;
                            System.out.println("Reviewer unfollowed");
                        }
                        else 
                            System.out.println("Cancel unfollow");
                        break;
                    case 3:
                        break;
                    case 4:
                        state = "main";
                        break;
                    default:
                        break;
                }
            }
            else
            {
                tryAgain("main","Error: Invalid input");
            }
        }
        
    }

    /**
     * random movie id and show movie
     * 
     * this state can change to "mainState" and will call movieState directly
     */
    private void discoverState()
    {
        int totalMovie = MovieManager.getInstance().size();
        if(totalMovie == 0)
        {
            System.out.println("no movie found");
            state = "main";
        }
        else
        {
            System.out.println("This is our random movie");
            Random rand = new Random(totalMovie);
            singleIdTemp = rand.nextInt(totalMovie);
            movieState();
        }
    }


    /**
     * show Review that currentUser write
     * then ask for user choice of action
     * then ask for NO. of review user want to make change
     * 
     * this state can change to "mainState", "editReviewState"
     */
    private void manageState()
    {
        idTemp = ReviewManager.getInstance().search(currentUser.getEmail());
        if(idTemp.size()==0)
        {
            System.out.println("You did not write any review returning to main...");
            state = "main";
        }
        else
        {
            System.out.println("You have "+idTemp.size()+" reviews");
            for(int i = 0, j = 1 ; i < idTemp.size(); i++,j++)
            {
                System.out.println(j+")");
                ReviewManager.getInstance().printSearch(idTemp.get(i));
            }  
            System.out.println("\nChoose your action");
            System.out.println("(1) edit review");
            System.out.println("(2) delete my review");
            System.out.println("(3) back to main menu");
            
            int action = getOneInteger("Enter your action");
            if(action==1||action==2)
            {
                intInput = getOneInteger("select review to edit/delete");
                intInput--;
                if(0<=intInput&&intInput<idTemp.size())
                {
                    singleIdTemp = idTemp.get(intInput);
                    if(action == 1)
                        state = "edit review";
                    else
                    {
                        if(confirmation("delete review"))
                        {
                            ReviewManager.getInstance().deleteReview(singleIdTemp);
                            editUserReview = true;
                            System.out.println("Review deleted");
                        }
                        else
                            System.out.println("Cancel delete review");
                    }
                }
                else
                {
                    tryAgain("main", "Error: invalid input");
                }

            }
            else if(action ==3)
                state = "main";
            else
                tryAgain("main", "Error: Invalid input");
        }
        
    }

    /**
     * show full review info from singleIdTemp
     * then ask user what they want to change
     * 
     * this state can change to "mainState"
     */
    private void editReviewState()
    {
        ReviewManager.getInstance().printSearch(singleIdTemp);
        System.out.println("\nwhat do you want to change?");
        System.out.println("(1) title");
        System.out.println("(2) body");
        System.out.println("(3) rating");
        System.out.println("(4) back to main menu");
        System.out.println("what do you want to change?");
        intInput = getOneInteger("Enter your action:");
        switch(intInput)
        {
            case 1:

                stringInput = getOneString("Enter new title:");
                if(confirmation("edit review title"))
                {
                    ReviewManager.getInstance().editReview(singleIdTemp, "title", stringInput);
                    editUserReview = true;
                    System.out.println("Title changed to "+stringInput);
                }
                else    
                    System.out.println("Cancel edit review title");
                break;
            case 2:
                stringInput = getOneString("Enter new body:");
                if(confirmation("edit review body"))
                {
                    ReviewManager.getInstance().editReview(singleIdTemp, "body", stringInput);
                    editUserReview = true;
                    System.out.println("Body changed to "+stringInput);
                }
                else    
                    System.out.println("Cancel edit review body");    
                break;
            case 3:
                double rating = getOneDouble("Enter new rating (number):");
                if(confirmation("edit review rating"))
                {
                    ReviewManager.getInstance().editReview(singleIdTemp, "rating", Double.toString(rating));
                    editUserReview = true;
                    System.out.println("Rating changed to "+ rating);
                }
                else 
                    System.out.println("Cancel esdit review rating");
                break;
            case 4:
                state = "main";
                break;
            default:
                tryAgain("main", "Error: Invalid input");
                break;
        }
    }


    /**
     * check changes flag
     * then save data to file
     * 
     * this state can change to "beginState"
     */
    private void logoutState()
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

        state = "begin"; 
        currentUser = null; 
        searchState = null; 
        searchResultPage = 1; 
        idTemp = null;
        singleIdTemp = null;
        editUserReview = false;
        editUserProfile = false;
        userAddNewFollow = false;
        intInput = null;
        stringInput = null;
        System.out.println("You are now logout");
    }

    /**
     * ask for user info to register,
     * auto login after successful register
     * @return true if login success
     */
    private boolean login(String email, String password)
    {
        currentUser = UserManager.getInstance().login(email, password);
        if(currentUser==null)
            return false;
        else
            return true;
    }

    /**
     * ask for review info input
     * then create new Review
     */
    private void writeReview()
    {
        System.out.println("\nYou are creating review");
        String title = getOneString("Enter review title :");
        String body = getOneString("Enter review body :");
        double rating;
        while(true)
        {
            rating = getOneDouble("Enter review rating (number 0 - 10) :");
            if(rating<0 || rating > 10)
            {
                System.out.println("Rating must be between 0 to 10");
            }
            else
                break;
        }
        
        if(confirmation("write review"))
        {
            Review newReview = new Review(MovieManager.getInstance().getMovie(singleIdTemp).getMovieID(),title,body,rating,currentUser.getEmail());
            ReviewManager.getInstance().addNewReview(newReview);
            ReviewManager.getInstance().writeNewReview(newReview);
            System.out.println("Added new review returning to main...");
        }
        else
            System.out.println("Cancel wrtte review returning to main...");
        
    }

    /**
     * this add genre into ArrayList based on User input
     * @return ArrayList of genres
     */
    private ArrayList<String> genreMaker()
    {
        ArrayList<String> genre = new ArrayList<String>();
        System.out.println("Enter category");
        System.out.println("(1) Action (2) Adventure (3) Comedy (4) Drama");
        System.out.println("(5) Horror (6) Music (7) Romance (8) Sci-fi");
        do
        {
            intInput = getOneInteger("Enter your category number(0 to finish): ");
            switch(intInput)
            {
                case 0:
                    System.out.println("Finish adding category");
                    break;
                case 1:
                    genreAdder("Action", genre);                        
                    break;
                case 2:
                    genreAdder("Adventure", genre); 
                    break;
                case 3:
                    genreAdder("Comedy", genre); 
                    break;
                case 4:
                    genreAdder("Drama", genre); 
                    break;
                case 5:
                    genreAdder("Horror", genre); 
                    break;
                case 6:
                    genreAdder("Music", genre); 
                    break;
                case 7:
                    genreAdder("Romance", genre); 
                    break;
                case 8:
                    genreAdder("Sci-fi", genre); 
                    break;
                default:
                    System.out.println("Wrong number");
                    break;
            }

        }while(intInput != 0);
        return genre;
    }

    /**
     *  add genre to ArrayList
     */
    private void genreAdder(String genre, ArrayList<String> genres)
    {
        if(genres.contains(genre))
        {
            System.out.println("You already added "+ genre);
        }
        else
        {
            genres.add(genre);
            System.out.println("Added "+genre);
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
    }
}