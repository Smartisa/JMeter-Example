package com.DB;

public class UserConfig {
    private final String host;
    private final String userName;
    private final String password;
    private final String database;

    public String getHost() {return host;}
    public String getUserName() {return userName;}
    public String getPassword() {return password;}
    public String getDatabase() {return database;}

    public UserConfig()
    {
        host = "localhost";
        userName = "root";
        password = "zb753951";
        database = "nuget_dependences";
    }

    public UserConfig(String _host, String _userName, String _password, String _database)
    {
        host = _host;
        userName = _userName;
        password = _password;
        database = _database;
    }
}
