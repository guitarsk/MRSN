import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class UserManager
{
    private final String userFileName = "allUsers.txt";

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

    public boolean register(String name,String email,String password,ArrayList<String> movieType)
    {
        User newUser = new User(name, email, password, movieType);
        allUsers.put(email, newUser);
        return true;
    }

    public User getUser(String email)
    {
        return allUsers.get(email);
    }
}