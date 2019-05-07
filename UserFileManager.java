import java.util.ArrayList;

public class UserFileManager extends TextFileManager
{
    public User readUser()
    {
        User user = null;
        String lineRead = getNextLine();
        
        if(lineRead.equalsIgnoreCase("[")==true)
        {
            String name = null;
            String email = null;
            String password = null;
            ArrayList<String> type = new ArrayList<String>();
            
            lineRead = getNextLine();
            while(lineRead.equalsIgnoreCase("]")==false)
            {
                String fields[] = lineRead.split("\\s+");
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
                    System.out.println(fields[0]);
                    System.out.println("bad data");
                    return user;
                }
                lineRead = getNextLine();
            }
            if(name != null && email != null && password != null && type.isEmpty()==false)
                user = new User(name, email, password, type);
        }
        return user;
    }

    public void writeUser(String userData)
    {
        writeNextLine(userData);
    }

    public static void main(String arg[])
    {
        UserFileManager userFileManager = new UserFileManager();
        userFileManager.openWrite("allUser.txt",true);
        userFileManager.writeUser("[\nNAME guitar\nEMAIL  tar_123@eiei.com\nPASSWORD 1234\nFAVTYPE COMEMEDY SCI-FI\n]");
        userFileManager.writeUser("[\nNAME guitar\nEMAIL  tar_124@eiei.com\nPASSWORD 1234\nFAVTYPE COMEMEDY SCI-FI\n]");
        userFileManager.closeWrite();
        userFileManager.openRead("allUser.txt");
        User test = null;
        if((test = userFileManager.readUser()) != null)
        {
            System.out.println(test.getUserName() + " " + test.getEmail());
        }
        userFileManager.closeRead();
        userFileManager.openWrite("allUser.txt",true);
        userFileManager.writeUser("[\nNAME guitar\nEMAIL  tar_123asd@eiei.com\nPASSWORD 1234\nFAVTYPE COMEMEDY SCI-FI\n]");
        userFileManager.closeWrite();
    }
}