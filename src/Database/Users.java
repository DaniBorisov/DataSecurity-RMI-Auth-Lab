package Database;

public class Users {

    private String Username;
    private String Password;

    public Users (String username, String password)
    {
        this.Password = password;
        this.Username = username;
    }

    public String getUsername()
    {
        return this.Username;
    }

    public String getPassword()
    {
        return this.Password;
    }
}


