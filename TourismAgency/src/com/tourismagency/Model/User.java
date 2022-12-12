package com.tourismagency.Model;

import com.tourismagency.Helper.DBConnector;
import com.tourismagency.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User {

    private int id;
    private String name;
    private String uname;
    private String password;
    private String type;

    public User(int id, String name, String uname, String password, String type) {
        this.id = id;
        this.name = name;
        this.uname = uname;
        this.password = password;
        this.type = type;
    }



    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static ArrayList<User> getList (){
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM users";
        User object;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs =st.executeQuery(query);
            while (rs.next()){
                object = new User();
                object.setId(rs.getInt("id"));
                object.setName(rs.getString("name"));
                object.setUname(rs.getString("uname"));
                object.setPassword(rs.getString("password"));
                object.setType(rs.getString("type"));

                userList.add(object);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    public static boolean add(String name,String uname,String password,String type){
        String query = "INSERT INTO users (name,uname,password,type) VALUES (?,?,?,?)";
        User findUser =User.FetchByUname(uname);
        if (findUser !=null){
            Helper.getMessage("This username used before,try new one");
            return false;
        }
            try {
                PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
                pr.setString(1,name);
                pr.setString(2,uname);
                pr.setString(3,password);
                pr.setString(4,type);

                int response = pr.executeUpdate();

                if (response == -1){
                    Helper.getMessage("error");
                }
                return response != -1;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
        }
        return true;
    }

    public static boolean delete (int id){
        String query = "DELETE FROM users WHERE id = ?";

        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);

            return pr.executeUpdate() != -1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static ArrayList<User> searchUsers(String query){
        ArrayList<User> userList = new ArrayList<>();
        User obj ;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUname(rs.getString("uname"));
                obj.setPassword(rs.getString("password"));
                obj.setType(rs.getString("type"));
                userList.add(obj);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }


    public static String searchQuery(String name,String uname,String type){
        String query = "SELECT * FROM users WHERE name LIKE '%{{name}}%' AND uname LIKE '%{{uname}}%'";
        query = query.replace("{{name}}",name);
        query = query.replace("{{uname}}",uname);
        if (!type.isEmpty()){
            query += "  AND type LIKE '%{{type}}%'";
            query = query.replace("{{type}}",type);
        }

        return query;
    }

    public static User getFetch(String uname,String password){
        User obj = null;
        String query = "SELECT * FROM users WHERE uname = ? AND password = ?";
        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,uname);
            pr.setString(2,password);
            ResultSet rs =pr.executeQuery();

            if (rs.next()){
                switch (rs.getString("type")){
                    case "Operator":
                        obj = new Operator();
                        break;
                    case "Admin":
                        obj = new Admin();
                        break;
                    default:obj = new User();
                    break;
                }

                obj.setId(rs.getInt("Id"));
                obj.setName(rs.getString("Name"));
                obj.setUname(rs.getString("Uname"));
                obj.setPassword(rs.getString("Password"));
                obj.setType(rs.getString("Type"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return obj;
    }


    public static User FetchByUname(String uname){
        User object = null;
        String query = "SELECT * FROM users WHERE uname = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,uname);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                object = new User();
                object.setId(rs.getInt("id"));
                object.setName(rs.getString("name"));
                object.setName(rs.getString("uname"));
                object.setPassword(rs.getString("password"));
                object.setType(rs.getString("type"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return object;
    }
}
