import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UserManager
{
    private final String userFileName = "allUsers.txt";

    private final String followFileName = "allFollows.txt";

    private UserFileManager userFileManager = null;

    private HashMap<String,User> allUsers = new HashMap<String,User>();

    private static UserManager singletonInstance = new UserManager();

    private UserManager()
    {

    }

    public static UserManager getInstance()
    {
	    return singletonInstance;
    }

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

    public User login(String email,String password)
    {
        if(allUsers.isEmpty()==true)
        {
            System.out.println("Please register first");
            return null;
        }
        else if(allUsers.containsKey(email)==true)
        {
            if(allUsers.get(email).login(password)==true)
            {
                System.out.println("Login complete");
                return allUsers.get(email);
            }
            else
            {
                System.out.println("Incorrect password");
                return null;
            }
        }
        else
        {
            System.out.println("Invalid email address");
            return null;
        }
    }

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

    public boolean register(String name,String email,String password,ArrayList<String> movieType)
    {
        boolean success = true;
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
        else
        {
            success = false;
        }
        return success;
    }

    public User getUser(String email)
    {
        return allUsers.get(email);
    }

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
                userFileManager.writeData(data);
            }
            userFileManager.closeWrite();
            success = true;
        }
        return success;
    }

    
}