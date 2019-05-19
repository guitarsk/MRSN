import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is read the information of users from file and create user.
 * File format is
 * [
 * NAME|GUITAR
 * EMAIL|guitar_sk134@hotmail.com
 * PASSWORD|12345678
 * FAVTYPE|ROMANCE|COMEMEDY|ACTION
 * ]
 *  | (or bitwise) use for separate fields 
 * Created by Nawakanok Muangkham (Guitar) 59070501044
 *      9 May 2019 
 */
public class UserFileManager extends TextFileManager
{

    /**
     * Return user instance by read from file and create it.
     * @return user instance.
     */
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

    /**
     * Return HashMap of followedList key field for user email , value for 
     * Array of followed user by read from file and create it.
     * @return list of followed by user
     */
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

    /**
     * Write data into file.
     * @param data data to write into file.
     */
    public void writeData(String data)
    {
        writeNextLine(data);
    }

}