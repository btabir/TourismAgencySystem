package com.tourismagency.View;

import com.tourismagency.Helper.Config;
import com.tourismagency.Helper.Helper;

import javax.swing.*;

public class ReservationGUI extends JFrame{
    private JPanel wrapper;
    private JLabel label_Title;
    private JLabel label_Title2;
    private JTextField txtfld_HotelName;
    private JTextField txtfld_Address;
    private JTextField txtfld_Phone;
    private JTextField txtfld_Services;
    private JTextField txtfld_RoomType;
    private JTextField txtfld_ChildrenNumber;
    private JTextField textField7;
    private JTextField txtfld_Checkin;
    private JTextField txtfld_Checkout;
    private JTextField txtfld_Amount2;
    private JTextField txtfld_TotalAmount;
    private JLabel label_Title3;
    private JTextField txtfld_NameSurname;
    private JTextField txtfld_PhoneNumber;
    private JTextField txtfld_Email;
    private JTextField txtfld_Note;
    private JLabel label_HotelName;
    private JLabel label_Address;
    private JLabel label_Phone;
    private JLabel label_Services;
    private JLabel label_RoomType;
    private JLabel label_AdultNumber;
    private JLabel label_ChildrenNumber;
    private JLabel label_Checkin;
    private JLabel label_Checkout;
    private JLabel label_TotalAmountTwoNigths;
    private JLabel label_TotalAmount;
    private JLabel label_NameSurname;
    private JLabel label_PhoneNumber;
    private JLabel label_Email;
    private JLabel label_Note;
    private JButton button_SaveReservation;

    public ReservationGUI(){
        add(wrapper);
        setSize(800,600);
        setLocation(Helper.findCenter("x",getSize()),Helper.findCenter("y",getSize()));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
    }

    public static void main(String[] args) {
        Helper.setLayout();
        ReservationGUI rs = new ReservationGUI();
    }
}
