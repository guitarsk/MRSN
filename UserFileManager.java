import java.util.ArrayList;

public class UserFileManager extends TextFileManager
{
    public User readUser()
    {
        User user = null;
        String lineRead = getNextLine();
        
        if(lineRead != '[')
        {
            String name = null;
            String email = null;
            String password = null;
            ArrayList<String> type = new ArrayList<String>();
            
            lineRead = getNextLine();
            while(lineRead != ']')
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
}