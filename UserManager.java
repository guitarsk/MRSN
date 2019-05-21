import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class is the singleton clss. It contains the list of all user in MRSN system.
 * and manager all user in system. 
 * 
 *  Created by Nawakanok Muangkham (Guitar) 59070501044
 *      Build project's possible framework and some implementation.
 *  Modified by Nawakanok Muangkham (Guitar) 59070501044
 *      9 May 2019 complete all of methods.
 */
public class UserManager
{
    /** user file name that contain information of all user */
    private final String userFileName = "allUsers.txt";

    /** followed file name that contain information of all followed user of user */
    private final String followFileName = "allFollows.txt";

    /** UserFileManager instance for read/write file */
    private UserFileManager userFileManager = null;

    /** Collection of all users in MRSN system */
    private HashMap<String,User> allUsers = new HashMap<String,User>();

    /** Single, private instance of the class */
    private static UserManager singletonInstance = new UserManager();

    /**
     * Private constructor. Nobody can create new instances.
     */
    private UserManager()
    {

    }

    /**
     * Public method to return the singleton instance.
     */
    public static UserManager getInstance()
    {
	    return singletonInstance;
    }

    /**
     * Set up all user in system by reading data from file.
     */
    public void initialize()
    {
        User user = null;
        userFileManager = new UserFileManager();
        if(userFileManager.openRead(userFileName)==true)
        {
            while((user = userFileManager.readUser())!=null)
            {
                allUsers.put(user.getEmail(), user);
            }
            userFileManager.closeRead();
            if(userFileManager.openRead(followFileName)==true)
            {
                HashMap<String,ArrayList<String>> follow = null;
                while((follow = userFileManager.readFollow())!=null)
                {
                    Iterator<Map.Entry<String,ArrayList<String>>> it = follow.entrySet().iterator();
                    while(it.hasNext())
                    {
                        Map.Entry<String,ArrayList<String>> pair = it.next();
                        for(int i = 0 ; i < pair.getValue().size() ; i++)
                        {
                            user = allUsers.get(pair.getValue().get(i));
                            allUsers.get(pair.getKey()).addFollowed(user);
                        }
                    }
                }
                userFileManager.closeRead();
            }
        }
    }

    /**
     * 
     * @param email email of user that want to login.
     * @param password  password for login.
     * @return if email is valid and password is match return User that login
     *          ,otherwise return null for fail.
     */
    public User login(String email,String password)
    {
        email = email.toLowerCase();
        if(allUsers.isEmpty()==true)    //no user in system
        {
            return null;
        }
        else if(allUsers.containsKey(email)==true)  //found user
        {
            if(allUsers.get(email).login(password)==true)   //password match
            {
                return allUsers.get(email);
            }
            else    //password in correct
            {
                return null;
            }
        }
        else    //invalid email
        {
            return null;
        }
    }

    /**
     * Register new user to MRSN system.
     * @param name user name.
     * @param email user email address.
     * @param password password for login.
     * @param movieType Array of favourite movie type.
     * @return  true for success,otherwise false.
     */
    public boolean register(String name,String email,String password,ArrayList<String> movieType)
    {
        boolean success = true;
        email = email.toLowerCase();
        if(allUsers.isEmpty() == true) 
        {
            User newUser = new User(name, email, password, movieType);
            allUsers.put(email, newUser);
            writeNewUser(newUser);
        }
        else if(allUsers.containsKey(email)==false)
        {
            User newUser = new User(name, email, password, movieType);
            allUsers.put(email, newUser);
            writeNewUser(newUser);
        }
        else    //duplicate email.
        {
            success = false;
        }
        return success;
    }

    /**
     * Getter method for get user from email.
     * @param email email address of user.
     * @return  user instance.
     */
    public User getUser(String email)
    {
        return allUsers.get(email);
    }

    /**
     * check if allUsers has key
     * @param email is a key
     * @return true if has
     */
    public boolean checkEmail(String email)
    {
        return allUsers.containsKey(email);
    }

    /**
     * enter user name and get email back
     * @return User's email
     */
    public String searchForEmail(String name)
    {
        for(User user : allUsers.values())
        {
            if(user.getUserName().equals(name))
            {
                return user.getEmail();
            }
        }
        return "not_found@thisIsDefinitelyNotExist.com";
    }

    /**
     * Rewrite whole of user file.
     * @return  true for success, false for can't write to file.
     */
    public boolean rewriteAllUser()
    {
        String data = null;
        boolean success = false;
        if(userFileManager.openWrite(userFileName, false)==true)
        {
            Iterator<Map.Entry<String,User>> it = allUsers.entrySet().iterator();
            while(it.hasNext())
            {
                Map.Entry<String,User> pair = it.next();
                data = pair.getValue().getUserDataToWrite();
                userFileManager.writeData(data);
            }
            userFileManager.closeWrite();
            success = true;
        }
        return success;
    }

    /**
     * Rewrite whole of followed user file.
     * @return true for success, false for can't write to file.
     */
    public boolean rewriteAllFollow()
    {
        String data = null;
        boolean success = false;
        if(userFileManager.openWrite(followFileName, false))
        {
            Iterator<Map.Entry<String,User>> it = allUsers.entrySet().iterator();
            while(it.hasNext())
            {
                Map.Entry<String,User> pair = it.next();
                data = pair.getValue().getFollowDataToWrite();
                if(data != null)    //write only when list of followedUser in not empty 
                    userFileManager.writeData(data);
            }
            userFileManager.closeWrite();
            success = true;
        }
        return success;
    }

    /**
     * Method for keep new user that register in last line of file"
     * @param newUser user that register into system.
     * @return  true for success, false for can't write to file.
     */
    public boolean writeNewUser(User newUser)
    {
        boolean success = false;
        if(userFileManager.openWrite(userFileName, true)==true)
        {
            userFileManager.writeData(newUser.getUserDataToWrite());
            userFileManager.closeWrite();
            success = true;
        }
        return success;
    }

    
}