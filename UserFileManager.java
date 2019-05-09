import java.util.ArrayList;
import java.util.HashMap;

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
        HashMap<String,ArrayList<String>> follow = new HashMap<String,ArrayList<String>>();
        
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
                    follow.put(name, email);
            }
        }
        while(follow.isEmpty() == true && lineRead != null);
        return follow;
    }

    public void writeUser(String userData)
    {
        writeNextLine(userData);
    }

    public void writeFollow(String followData)
    {
        writeNextLine(followData);
    }

    public static void main(String arg[])
    {
        UserFileManager userFileManager = new UserFileManager();
        userFileManager.openWrite("allUsers.txt",true);
        userFileManager.writeUser("[\nNAME|guitar\nEMAIL|tar_123@eiei.com\nPASSWORD|1234\nFAVTYPE|COMEMEDY|SCI-FI\n]");
        userFileManager.writeUser("[\nNAME|guitar\nEMAIL|tar_124@eiei.com\nPASSWORD|1234\nFAVTYPE|COMEMEDY|SCI-FI\n]");
        userFileManager.closeWrite();
        userFileManager.openRead("allUsers.txt");
        User test = null;
        while((test = userFileManager.readUser()) != null)
        {
            System.out.println(test.getUserName() + " " + test.getEmail());
        }
        userFileManager.closeRead();
        userFileManager.openWrite("allUsers.txt",true);
        userFileManager.writeUser("[\nNAME|guitar\nEMAIL|tar_123asd@eiei.com\nPASSWORD|1234\nFAVTYPE|COMEMEDY|SCI-FI\n]");
        userFileManager.closeWrite();
    }
}