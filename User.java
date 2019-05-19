import java.util.ArrayList;

/**
 * This class represent user profile and followed user in MRSN system 
 * 
 *  Created by Nawakanok Muangkham (Guitar) 5907050101044
 *      Build project's possible framework and some implementation
 *  Modified by Nawakanok Muangkham (Guitar) 5907050101044
 *      9 May 2019 complete all of methods
 */
public class User
{
    /** name of user */
    private String name = null;

    /** email that user use for login */
    private String email = null;

    /** password for login */
    private String password = null;

    /** list of user favorite movie type */
    private ArrayList<String> favoriteMovieType = new ArrayList<String>();

    /** list of followed by user */
    private ArrayList<User> followedList = new ArrayList<User>();


    /**
     *  The constructor of class use for create instance of User.
     *  @param name name of user.
     *  @param email email that user register.
     *  @param password password for login.
     *  @param movieType list of user favorite movie type.
     */
    public User(String name,String email,String password,ArrayList<String> movieType)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.favoriteMovieType.addAll(movieType);
    }

    /**
     * Getter method for get name of user.
     * @return name of user in  String. 
     */
    public String getUserName()
    {
        return this.name;
    }

    /**
     * Getter method for get email of user.
     * @return email of user in  String. 
     */
    public String getEmail()
    {
        return this.email;
    }

    /**
     * Setter method for change user name.
     * @param newName name that user want to change.
     * @return true for new name is set. 
     */
    public boolean setName(String newName)
    {
        this.name= newName;
        return true;
    }

    /**
     * Setter method for change user password.
     * @param newPassword password that user want to change.
     * @return true for new password is set. 
     */
    public boolean setPassword(String newPassword)
    {
        password = newPassword;
        return true;
    }

    public boolean setFavoriteMovieType(ArrayList<String> genre)
    {
        favoriteMovieType = genre;
        return true;
    }

    /**
     * Method for login in to MRSN system.
     * @param password password that user enter.
     * @return true for user enter correct password,otherwise false"
     */
    public boolean login(String password)
    {
        if(this.password == password)  
            return true;    
        return false;
    }

    /**
     * Print list of email of all anothor users that this user followed.
     */
    public void printFollowed()
    {
        for(int i=0;i<followedList.size();i++)
        {
            System.out.println(followedList.get(i).getUserName());
        }
    }

    /**
     * Add new followed user in to list.
     * @param followedUser new user that this user followed.
     */
    public void addFollowed(User followedUser)
    {
        followedList.add(followedUser);
    }

    public void removeFollowed(User unfollowedUser)
    {
        followedList.remove(unfollowedUser);
    }

    public int getFollowedSize()
    {
        return followedList.size();
    }

    /**
     * Getter method use for get specific followed user in list.
     * @param index index of followed user in list.
     * @return selected followed user.
     */
    public User getFollowed(int index)
    {
        return followedList.get(index);
    }

    public void showUser()
    {
        System.out.println("User name :"+name);
        System.out.println("Email :"+email);
        System.out.println("Favorite movie type :");
        for(int i = 0 ; i < favoriteMovieType.size() ; i++)
        {
            System.out.print(" "+favoriteMovieType.get(i));
        }
        System.out.println("Followed Reviewer :");
        for(int i = 0 ; i < followedList.size() ; i++)
        {
            System.out.print(" "+ followedList.get(i));
        }
    }

    /**
     * Return the information of user to write into file.
     * @return user data. 
     */
    public String getUserDataToWrite()
    {
        String data = null;
        data = "[\nNAME|"+this.name+"\nEMAIL|"+this.email+"\nPASSWORD|"+this.password+"\nFAVTYPE";
        for(int i = 0 ; i<favoriteMovieType.size() ; i++)
        {
            data += "|"+favoriteMovieType.get(i);
        }
        data += "\n]";
        return data;
    }

    /**
     * Return the information of followed user email to write into file.
     * @return data to write in file.
     */
    public String getFollowDataToWrite()
    {
        String data = null;
        if(followedList.isEmpty()==false)
        {
            data = "[\nUSER|"+this.email;
            for(int i=0 ; i < followedList.size() ;i++)
            {
                data += "\nFOLLOW|"+followedList.get(i).getEmail();
            }
            data += "\n]";
        }
        return data;
    }
}