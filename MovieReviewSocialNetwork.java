
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
import java.util.Random;

import com.sun.glass.ui.Size;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

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

    public void tryAgain(String newState,String text)
    {
        System.out.println(text);
        stringInput = getOneString("Do you want to try again [y/n]?");
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
                tryAgain("begin","Invalid email/password");
            }
        }
    }

    /** code redundancy exist here i will refacture this later
     *  and user can keep adding category forever even if they already added that category
     */
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
        System.out.println("Please choose favorite movie genres");
        ArrayList<String> genre = genreMaker();
        if(UserManager.getInstance().register(name,email,password,genre))
        {
            System.out.println("Register successful");
            login(email,password);
            state = "main";
        }
        else
        {
            tryAgain("begin","This email is already used please try another email");
        }
    }

    /** not finish still need loads of functioanlity */
    private void mainState()
    {
        System.out.println("Welcome to MRSN! Please choose your action");
            System.out.println("(1) search for...");
            System.out.println("(2) discover new movie");
            System.out.println("(3) followed");
            System.out.println("(4) manage my reviews");//
            System.out.println("(5) view my profile");//
            System.out.println("(6) write review");//
            System.out.println("(7) logout");//
            intInput = getOneInteger("Your input :");
            switch(intInput)
            {
                case 1:// search func still lack select movie state and select review state
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
                    state = "exit";
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
        if(intInput == 1 || intInput == 2) // search using MovieManager
        {
            idTemp = MovieManager.getInstance().search(stringInput,intInput);
            state = "search result";
            searchState = "movie";
            searchResultPage = 1;
        }
        else if(intInput == 3) // search using ReviewManager
        {
            stringInput = UserManager.getInstance().searchForEmail(stringInput);
            idTemp = ReviewManager.getInstance().search(stringInput);
            state = "search result";
            searchState = "review"; 
            searchResultPage = 1; 
        }
        else
        {
            tryAgain("main","Error: Invalid input");
        } 
    }

    // this method need to config idTemp, searchState and searchResultPage before use
    private void searchResultState()
    {
        //searchResultPage
        System.out.println(" "+idTemp.size()+" results");
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
                {;
                    tryAgain("main","Error: Invalid input");
                }
                break;
            default:
                tryAgain("main","Error: Invalid input");
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
                tryAgain("main","Error: Invalid input");
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
                tryAgain("main", "Error: Invalid input");
                break;
        }
    }

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

    private void changePasswordState()
    {
        System.out.println("You are about to change password");
        stringInput = getOneString("Please enter your old password: ");
        if(currentUser.login(stringInput))
        {
            stringInput = getOneString("Please enter your new password: ");
            currentUser.setPassword(stringInput);
            state = "main";
        }
        else
        {
            tryAgain("main", "Wrong password");
        }
    }
    private void writeReviewState()
    {
        boolean skip = false;
        stringInput = getOneString("Enter movie name");
        idTemp = MovieManager.getInstance().search(stringInput, 1);
        
        for(int i = 0 , j = 1 ; i < idTemp.size() ; i++,j++)
        {
            System.out.println(j+")");
            MovieManager.getInstance().printSearch(idTemp.get(i));
        }
        System.out.println("\nChoose your action");
        System.out.println("(1) select movie");
        System.out.println("(2) add new movie");
        System.out.println("(3) re-enter movie name");
        System.out.println("(4) back to main menu");
        intInput = getOneInteger("Enter your action :");
        switch(intInput)
        {
            case 1:
                intInput = getOneInteger("Enter movie number :");
                if(intInput >= idTemp.size())
                {
                    tryAgain("main", "Error: Invalid input");
                    break;
                }
                singleIdTemp = idTemp.get(intInput);
                break;
            case 2:
                String name = getOneString("Enter Movie name");
                ArrayList<String> genre = genreMaker();
                int year = getOneInteger("Enter the year this movie released");
                Movie newMovie = new Movie(name, genre, year);
                MovieManager.getInstance().addMovie(newMovie);
                singleIdTemp = newMovie.getMovieID();
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
            String title = getOneString("Enter title :");
            String body = getOneString("Enter body :");
            int rating = getOneInteger("Enter rating (integer number) :");
            Review newReview = new Review(MovieManager.getInstance().getMovie(singleIdTemp).getMovieID(),title,body,(double)rating,currentUser.getEmail());
            ReviewManager.getInstance().addNewReview(newReview);
            System.out.println("Added new review returning to main...");
            state ="main";
        }
        
    }
/*
    private void writeReviewState()
    {
        System.out.println("what do you want to change?");
        System.out.println("(1) title");
        System.out.println("(2) body");
        System.out.println("(3) rating");
        System.out.println("what do you want to change?");
        intInput = getOneInteger("Enter your action:");
        switch(intInput)
        {
            case 1:

        }
    }
*/
    /** need to figure out the design of edit page */
    private void editState()
    {
        currentUser.showUser();
        System.out.println("what do you want to change?");
        System.out.println("(1) User name");
        System.out.println("(2) Favorite movie categories");
        System.out.println("(3) The people you followed");
        intInput = getOneInteger("Enter your action:");
        switch(intInput)
        {
            case 1:
                stringInput = getOneString("Please enter new User name:");
                currentUser.setName(stringInput);
                System.out.println("User name changed to "+stringInput);
                break;
            case 2:
                currentUser.setFavoriteMovieType(genreMaker());
                System.out.println("Favorite movie category changed");
                break;
            case 3:
                state = "followed";
                break;
            default:
                tryAgain("user", "Error: Invalid input");
                break;
        }

        

        
    }

    private void followedState()
    {
        System.out.println("Your follwed list");
        for(int i = 0, j = 0 ; i < currentUser.getFollowedSize() ; i++,j++)
        {
            
            System.out.print("("+i+") "+currentUser.getFollowed(i).getUserName()+" ");
            if(j>4)
            {
                System.out.print("\n");
                j=0;
            }
        }

        int intInput = getOneInteger("\n Enter number of User you want to see (-1 to exit)");
        if(intInput < 0)
        {
            state ="main";
        }
        else if(intInput<currentUser.getFollowedSize())
        {
            User writer = currentUser.getFollowed(intInput)
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
                    state = "search result";
                    searchState = "review";
                    searchResultPage = 1;
                    break;
                case 2:
                    currentUser.removeFollowed(writer);
                    System.out.println("Reviewer unfollowed");
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

    private void discoverState()
    {
        System.out.println("This is our random movie");
        Random rand = new Random();
        singleIdTemp = rand.nextInt(MovieManager.getInstance().size());
        movieState();
    }

    /** unfinish I'll take care of this after I write writeReviewState */
    private void manageState()
    {
        System.out.println("Choose your action");
        System.out.println("(1) edit review");
        System.out.println("(2) delete my review");
        System.out.println("(3) back to main menu");
        intInput = getOneInteger("Enter your action");

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
                    if(!genre.contains("Action"))
                        genre.add("Action");
                    System.out.println("Added Action");
                    break;
                case 2:
                    if(!genre.contains("Adventure"))
                        genre.add("Adventure");
                    System.out.println("Added Adventure");
                    break;
                case 3:
                    if(!genre.contains("Comedy"))
                        genre.add("Comedy");
                    System.out.println("Added Comedy");
                    break;
                case 4:
                    if(!genre.contains("Drama"))
                        genre.add("Drama");
                    System.out.println("Added Drama");
                    break;
                case 5:
                    if(!genre.contains("Horror"))
                        genre.add("Horror");
                    System.out.println("Added Horror");
                    break;
                case 6:
                    if(!genre.contains("Music"))
                        genre.add("Music");
                    System.out.println("Added Music");
                    break;
                case 7:
                    if(!genre.contains("Romance"))
                        genre.add("Romance");
                    System.out.println("Added Romance");
                    break;
                case 8:
                    if(!genre.contains("Sci-fi"))
                        genre.add("Sci-fi");
                    System.out.println("Added Sci-fi");
                    break;
                default:
                    System.out.println("Wrong number");
                    break;
            }

        }while(intInput != 0);
        return genre;
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