package com.example.appdataserver.Bd;

import com.example.appdataserver.Client.AuthRequest;
import com.example.appdataserver.Client.BasicRequest;

import java.sql.*;

public class BdUsers  {

    private static Connection connection;
    static String Url =  "jdbc:sqlite:C:/Users/jylve/Desktop/AppDataServer/src/main/resources/users.db";
    public BdUsers(){
        try {
            connection = DriverManager.getConnection(Url);
        }catch (SQLException e){
            throw new RuntimeException("не удалось подключится к БД" +e.getMessage(),e);
        }
    }
    public static boolean isReg(String login,String pass){
        try {
            Connection connection = DriverManager.getConnection(Url);
            PreparedStatement statement = connection.prepareStatement("select  nick from auth where login = ? and pass = ? ");
            statement.setString(1,login);
            statement.setString(2,pass);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet!=null){
                return true;
            }else return false;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String getNickByLoginAndPassword(String login, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement("select  nick from auth where login = ? and pass = ? ");
            statement.setString(1,login);
            statement.setString(2,password);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.getString(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
