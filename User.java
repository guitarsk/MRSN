import java.util.ArrayList;

public class User
{
    private String name = null;
    private String email = null;
    private String password = null;
    private ArrayList<String> favoriteMovieType = new ArrayList<String>();
    private ArrayList<User> followedList = new ArrayList<User>();

    public User(String name,String email,String password,ArrayList<String> movieType)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.favoriteMovieType.addAll(movieType);
    }

    public String getUserName()
    {
        return this.name;
    }

    public String getEmail()
    {
        return this.email;
    }

    public boolean setName(String newName)
    {
        this.name= newName;
        return true;
    }

    public boolean setPassword(String newPassword)
    {
        password = newPassword;
        return true;
    }

    public boolean login(String password)
    {
        if(this.password == password)
            return true;
        return false;
    }

    public void printFollowed()
    {
        for(int i=0;i<followedList.size();i++)
        {
            System.out.println(followedList.get(i).getUserName());
        }
    }

    public void addFollowed(User followedUser)
    {
        followedList.add(followedUser);
    }

    public User getFollowed(int index)
    {
        return followedList.get(index);
    }

}