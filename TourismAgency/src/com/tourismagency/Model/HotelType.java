package com.tourismagency.Model;

import com.tourismagency.Helper.DBConnector;
import com.tourismagency.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HotelType {
    private int id;
    private String type;
    private int hotel_id;
    private Hotel hotel;

    public HotelType(){}

    public HotelType(int id, String type, int hotel_id) {
        this.id = id;
        this.type = type;
        this.hotel_id = hotel_id;
        this.hotel =Hotel.getFetch(hotel_id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public static ArrayList<HotelType> getList(){
        ArrayList<HotelType> hotelTypeList = new ArrayList<>();
        String query = "SELECT * FROM hotel_type";
        HotelType object;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                object = new HotelType();
                object.setId(rs.getInt("id"));
                object.setType(rs.getString("type"));
                object.setHotel_id(rs.getInt("hotel_id"));

                hotelTypeList.add(object);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hotelTypeList;
    }

    public static boolean delete(int hotel_id){
        String query = "DELETE FROM hotel_type WHERE hotel_id = ?";
        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);

            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static ArrayList<HotelType> getListByHotelId(int id){
        ArrayList<HotelType> hotelTypeList = new ArrayList<>();
        HotelType object;
        String query = "SELECT * FROM hotel_type WHERE hotel_id =?";

        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                object = new HotelType();
                object.setId(rs.getInt("id"));
                object.setType(rs.getString("type"));
                object.setHotel_id(rs.getInt("hotel_id"));
                hotelTypeList.add(object);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return hotelTypeList;
    }

    public static boolean add(String type,int hotel_id){
        String query = "INSERT INTO hotel_type(type,hotel_id) VALUES (?,?)";
        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,type);
            pr.setInt(2,hotel_id);

            int response = pr.executeUpdate();
            if (response == -1){
                Helper.getMessage("error");
            }

            return response !=-1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static HotelType getFetch(int id) {
        HotelType obj = null;
        String query = "SELECT * FROM hotel_type WHERE id = ?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new HotelType(rs.getInt("id"), rs.getString("type"), rs.getInt("hotel_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
