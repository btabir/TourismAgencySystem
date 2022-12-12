package com.tourismagency.Model;

import com.tourismagency.Helper.DBConnector;
import com.tourismagency.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HotelSeason {

    private int id;
    private String start_date;
    private String end_date;
    private int hotel_id;

    private Hotel hotel;

    public HotelSeason(int id, String start_date, String end_date, int hotel_id) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.hotel_id = hotel_id;
        this.hotel = Hotel.getFetch(hotel_id);
    }

    public HotelSeason(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart_Date() {
        return start_date;
    }

    public void setStart_Date(String start_Date) {
        this.start_date = start_Date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public static ArrayList<HotelSeason> getListByHotelId(int id){
        ArrayList<HotelSeason> hotelSeasonList = new ArrayList<>();
        HotelSeason object;
        String query = "SELECT * FROM hotel_season WHERE hotel_id=?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs =pr.executeQuery();
            while (rs.next()){
                object = new HotelSeason();
                object.setId(rs.getInt("id"));
                object.setStart_Date(rs.getString("start_date"));
                object.setEnd_date(rs.getString("end_date"));
                object.setHotel_id(rs.getInt("hotel_id"));
                hotelSeasonList.add(object);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hotelSeasonList;
    }

    public static ArrayList<HotelSeason> getList(){
        ArrayList<HotelSeason> hotelSeasonList = new ArrayList<>();
        String query = "SELECT * FROM hotel_season";
        HotelSeason object;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                object = new HotelSeason();
                object.setId(rs.getInt("id"));
                object.setStart_Date(rs.getString("start_date"));
                object.setEnd_date(rs.getString("end_date"));
                object.setHotel_id(rs.getInt("hotel_id"));

                hotelSeasonList.add(object);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hotelSeasonList;
    }

    public static boolean delete(int hotel_id){
        String query = "DELETE FROM hotel_season WHERE hotel_id = ?";
        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);

            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public static HotelSeason getFetch(int id){
        HotelSeason object = null;
        String query = "SELECT * FROM hotel_season WHERE id = ?";
        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                object = new HotelSeason(rs.getInt("id"),rs.getString("start_date"),rs.getString("end_date"),rs.getInt("hotel_id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return object;
    }

    public static boolean add(String start_date,String end_date,int hotel_id){
        String query = "INSERT INTO hotel_season (start_date,end_Date,hotel_id) VALUES (?,?,?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,start_date);
            pr.setString(2,end_date);
            pr.setInt(3,hotel_id);

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
}
