import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class UserFileManager extends TextFileManager
{
    public User readUser()
    {
        User user = null;
        String lineRead = null;
        
        do
        {
            lineRead = getNextLine();
            if(lineRead != null && lineRead.equalsIgnoreCase("[")==true)
            {
                String name = null;
                String email = null;
                String password = null;
                ArrayList<String> type = new ArrayList<String>();
                
                lineRead = getNextLine();
                while(lineRead.equalsIgnoreCase("]")==false)
                {
                    String fields[] = lineRead.split("\\|");
                    if(fields[0].equalsIgnoreCase("NAME") && fields.length == 2)
                    {
                        name = fields[1];
                    }
                    else if(fields[0].equalsIgnoreCase("EMAIL") && fields.length == 2)
                    {
                        email = fields[1];
                        
                    }
                    else if(fields[0].equalsIgnoreCase("PASSWORD") && fields.length == 2)
                    {
                        password = fields[1];
                    }
                    else if(fields[0].equalsIgnoreCase("FAVTYPE") && fields.length > 1)
                    {
                        for(int i = 1 ; i < fields.length ; i++)
                        {
                            type.add(fields[i]);
                        }
                    }
                    else
                    {
                        System.out.println("bad line data --> skip line");
                    }
                    lineRead = getNextLine();
                }
                if(name != null && email != null && password != null && type.isEmpty()==false)
                    user = new User(name, email, password, type);
            }
        }
        while(user == null && lineRead != null);
        return user;
    }

    public HashMap<String,ArrayList<String>> readFollow()
    {
        HashMap<String,ArrayList<String>> follow = null;
        
        String lineRead = null;
        do
        {
            lineRead = getNextLine();
            if(lineRead != null && lineRead.equalsIgnoreCase("[")==true)
            {
                ArrayList<String> email = new ArrayList<String>();
                String name = null;
                
                lineRead = getNextLine();
                while(lineRead.equalsIgnoreCase("]")==false)
                {
                    String fields[] = lineRead.split("\\|");
                    if(fields[0].equalsIgnoreCase("USER") && fields.length == 2)
                    {
                        name = fields[1];
                    }
                    else if(fields[0].equalsIgnoreCase("FOLLOW") && fields.length == 2)
                    {
                        email.add(fields[1]);
                    }
                    else
                    {
                        System.out.println("bad line data --> skip line");
                    }
                    lineRead = getNextLine();
                }
                if(name != null && email.isEmpty()==false)
                {
                    follow = new HashMap<String,ArrayList<String>>();
                    follow.put(name, email);
                }
                
            }
        }
        while(follow == null && lineRead != null);
        return follow;
    }

    public void writeData(String data)
    {
        writeNextLine(data);
    }

    public static void main(String arg[])
    {
        UserFileManager userFileManager = new UserFileManager();
        HashMap<String,User> allUser = new HashMap<String,User>();
        // userFileManager.openWrite("allUsers.txt",true);
        // userFileManager.writeData("[\nNAME|guitar\nEMAIL|tar_123@eiei.com\nPASSWORD|1234\nFAVTYPE|COMEMEDY|SCI-FI\n]");
        // userFileManager.writeData("[\nNAME|guitar\nEMAIL|tar_124@eiei.com\nPASSWORD|1234\nFAVTYPE|COMEMEDY|SCI-FI\n]");
        // userFileManager.closeWrite();

        // userFileManager.openWrite("allFollows.txt",true);
        // userFileManager.writeData("[\nUSER|tar_123@eiei.com\nFOLLOW|tar_124@eiei.com\nFOLLOW|tar_123asd@eiei.com\n]");
        // userFileManager.writeData("[\nUSER|tar_124@eiei.com\nFOLLOW|tar_123asd@eiei.com\n]");
        // userFileManager.closeWrite();

        userFileManager.openRead("allUsers.txt");
        User test = null;
        while((test = userFileManager.readUser()) != null)
        {
            allUser.put(test.getEmail(), test);
        }
        userFileManager.closeRead();

        HashMap<String,ArrayList<String>> follow = null;

        userFileManager.openRead("allFollows.txt");
        while((follow = userFileManager.readFollow()) != null)
        {
            Iterator<Map.Entry<String,ArrayList<String>>> it = follow.entrySet().iterator();
            while(it.hasNext())
            {
                Map.Entry<String,ArrayList<String>> pair = it.next();
                for(int i = 0 ; i < pair.getValue().size() ; i++)
                {
                    test = allUser.get(pair.getValue().get(i));
                    allUser.get(pair.getKey()).addFollowed(test);
                }
            }
        }
        userFileManager.closeRead();

        Iterator<Map.Entry<String,User>> it = allUser.entrySet().iterator();
            while(it.hasNext())
            {
                Map.Entry<String,User> pair = it.next();
                test = pair.getValue();
                System.out.println(test.getUserDataToWrite());
                System.out.println(test.getFollowDataToWrite());
            }
        // userFileManager.openWrite("allUsers.txt",true);
        // userFileManager.writeData("[\nNAME|guitar\nEMAIL|tar_123asd@eiei.com\nPASSWORD|1234\nFAVTYPE|COMEMEDY|SCI-FI\n]");
        // userFileManager.closeWrite();
    }
}