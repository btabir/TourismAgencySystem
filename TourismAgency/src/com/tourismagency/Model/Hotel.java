package com.tourismagency.Model;

import com.tourismagency.Helper.DBConnector;
import com.tourismagency.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Queue;

public class Hotel {

    private int id;
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private String stars;
    private String properties;

    public Hotel(){

    }

    public Hotel(int id, String name, String address, String email, String phoneNumber, String stars, String properties) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.stars = stars;
        this.properties = properties;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public static ArrayList<Hotel> getList(){
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String query = "SELECT * FROM hotels";
        Hotel object;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                object = new Hotel();
                object.setId(rs.getInt("id"));
                object.setName(rs.getString("Name"));
                object.setAddress(rs.getString("Address"));
                object.setEmail(rs.getString("EMail"));
                object.setPhoneNumber(rs.getString("phoneNumber"));
                object.setStars(rs.getString("Stars"));
                object.setProperties(rs.getString("Properties"));
                hotelList.add(object);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hotelList;
    }

    public static boolean delete(int id){
        String query = "DELETE FROM hotels WHERE id = ?";
        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);

            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static boolean add(String name,String address,String email,String phoneNumber,String stars,String properties){
        String query = "INSERT INTO hotels (name,address,email,phoneNumber,stars,properties) VALUES (?,?,?,?,?,?)";
        Hotel findHotel = Hotel.FetchByPhoneNumber(phoneNumber);
        if (findHotel != null){
            Helper.getMessage("WARNING! This hotel added before!");
            return false;
        }
        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,address);
            pr.setString(3,email);
            pr.setString(4,phoneNumber);
            pr.setString(5,stars);
            pr.setString(6,properties);

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

    public static Hotel getFetch(int id){
        Hotel object = null;
        String query = "SELECT * FROM hotels WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs =pr.executeQuery();
            if (rs.next()){
                object = new Hotel();
                object.setId(rs.getInt("Id"));
                object.setName(rs.getString("name"));
                object.setName(rs.getString("address"));
                object.setName(rs.getString("email"));
                object.setName(rs.getString("phoneNumber"));
                object.setName(rs.getString("stars"));
                object.setName(rs.getString("properties"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return object;
    }

    public static Hotel FetchByPhoneNumber(String phoneNumber){
        Hotel object = null;
        String query = "SELECT * FROM hotels WHERE phoneNumber = ?";

        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,phoneNumber);
            ResultSet rs =pr.executeQuery();
            if (rs.next()){
                object = new Hotel();
                object.setId(rs.getInt("Id"));
                object.setName(rs.getString("name"));
                object.setName(rs.getString("address"));
                object.setName(rs.getString("email"));
                object.setName(rs.getString("phoneNumber"));
                object.setName(rs.getString("stars"));
                object.setName(rs.getString("properties"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return object;
    }
}
