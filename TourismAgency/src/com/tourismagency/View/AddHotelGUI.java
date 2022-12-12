package com.tourismagency.View;

import com.tourismagency.Helper.Config;
import com.tourismagency.Helper.Helper;
import com.tourismagency.Model.Hotel;
import com.tourismagency.Model.HotelSeason;
import com.tourismagency.Model.HotelType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddHotelGUI extends JFrame {
    private JPanel wrapper;
    private JTextField txtfld_HotelName;
    private JTextField txtfld_HotelAddress;
    private JTextField txtfld_Email;
    private JTextField txtfld_Phone;
    private JComboBox combo_selectStar;
    private JTextField txtfld_Properties;
    private JRadioButton ultraAllInRadioButton;
    private JRadioButton allInRadioButton;
    private JRadioButton roomBreakfastRadioButton;
    private JRadioButton fullPensionRadioButton;
    private JRadioButton halfPensionRadioButton;
    private JRadioButton onlyBedRadioButton;
    private JRadioButton fullCreditExceptAlcoholRadioButton;
    private JLabel label_Title;
    private JLabel label_HotelName;
    private JLabel label_HotelAddress;
    private JLabel label_Email;
    private JLabel label_Phone;
    private JLabel comboBox_Star;
    private JLabel label_Properties;
    private JLabel label_Type;
    private JLabel label_TitleStayPeriods;
    private JTextField txtfld_Start1;
    private JTextField txtfld_End1;
    private JTextField txtfld_Start2;
    private JTextField txtfld_End2;
    private JLabel label_Start;
    private JLabel label_Finish;
    private JLabel label_FirstPeriod;
    private JLabel label_SecondPeriod;
    private JButton button_AddHotel;
    private String select_Star;
    private int added_hotel_id;  //it holds the new added hotels id

    public AddHotelGUI() {
        add(wrapper);
        setSize(800, 600);
        setLocation(Helper.findCenter("x", getSize()), Helper.findCenter("y", getSize()));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        ultraAllInRadioButton.setText(Helper.HotelType("1"));
        allInRadioButton.setText(Helper.HotelType("2"));
        roomBreakfastRadioButton.setText(Helper.HotelType("3"));
        fullPensionRadioButton.setText(Helper.HotelType("4"));
        halfPensionRadioButton.setText(Helper.HotelType("5"));
        onlyBedRadioButton.setText(Helper.HotelType("6"));
        fullCreditExceptAlcoholRadioButton.setText(Helper.HotelType("7"));

        select_Star = combo_selectStar.getSelectedItem().toString();

        button_AddHotel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFieldEmpty(txtfld_HotelName) || Helper.isFieldEmpty(txtfld_HotelAddress) || Helper.isFieldEmpty(txtfld_Email) ||
                        Helper.isFieldEmpty(txtfld_Phone) || Helper.isFieldEmpty(txtfld_Properties) || Helper.isFieldEmpty(txtfld_Start1) ||
                        Helper.isFieldEmpty(txtfld_End1) || (!ultraAllInRadioButton.isSelected() && !allInRadioButton.isSelected()
                        && !roomBreakfastRadioButton.isSelected() && !fullPensionRadioButton.isSelected() && !halfPensionRadioButton.isSelected()
                        && !onlyBedRadioButton.isSelected() && !fullCreditExceptAlcoholRadioButton.isSelected())) {
                    Helper.getMessage("fill");
                } else {
                    String name = txtfld_HotelName.getText();
                    String address = txtfld_HotelAddress.getText();
                    String email = txtfld_Email.getText();
                    String phoneNumber = txtfld_Phone.getText();
                    String stars = (String) combo_selectStar.getSelectedItem();
                    String properties = txtfld_Properties.getText();
                    String start1 = txtfld_Start1.getText();
                    String end1 = txtfld_End1.getText();
                    String start2 = txtfld_Start2.getText();
                    String end2 = txtfld_End2.getText();

                    if (Hotel.add(name,address,email,phoneNumber,stars,properties)){
                        Hotel addedHotels =Hotel.FetchByPhoneNumber(phoneNumber);
                        added_hotel_id =addedHotels.getId();

                        //add hotel properties
                        for (int i = 1; i<=7;i++){
                            switch (i){
                                case 1:
                                    if (ultraAllInRadioButton.isSelected()){
                                        HotelType.add(ultraAllInRadioButton.getText(), added_hotel_id);
                                    }
                                    break;

                                case 2:
                                    if (allInRadioButton.isSelected()){
                                        HotelType.add(allInRadioButton.getText(),added_hotel_id);
                                    }
                                    break;

                                case 3:
                                    if (roomBreakfastRadioButton.isSelected()){
                                        HotelType.add(roomBreakfastRadioButton.getText(),added_hotel_id);
                                    }
                                    break;

                                case 4:
                                    if (fullPensionRadioButton.isSelected()){
                                        HotelType.add(fullPensionRadioButton.getText(),added_hotel_id);
                                    }
                                    break;

                                case 5:
                                    if (halfPensionRadioButton.isSelected()){
                                        HotelType.add(halfPensionRadioButton.getText(),added_hotel_id);
                                    }
                                    break;

                                case 6:
                                    if (onlyBedRadioButton.isSelected()){
                                        HotelType.add(onlyBedRadioButton.getText(),added_hotel_id);
                                    }
                                    break;

                                case 7:
                                    if (fullCreditExceptAlcoholRadioButton.isSelected()){
                                        HotelType.add(fullCreditExceptAlcoholRadioButton.getText(),added_hotel_id);
                                    }
                                    break;
                            }
                        }

                        HotelSeason.add(start1,end1,added_hotel_id);
                        if (!Helper.isFieldEmpty(txtfld_Start2) && !Helper.isFieldEmpty(txtfld_End2)){
                            HotelSeason.add(start2,end2,added_hotel_id);
                        }
                        Helper.getMessage("done");

                        txtfld_HotelName.setText(null);
                        txtfld_HotelAddress.setText(null);
                        txtfld_Email.setText(null);
                        txtfld_Phone.setText(null);
                        combo_selectStar.setSelectedIndex(0);
                        txtfld_Properties.setText(null);
                        txtfld_Start1.setText(null);
                        txtfld_End1.setText(null);
                        txtfld_Start2.setText(null);
                        txtfld_End2.setText(null);
                        ultraAllInRadioButton.setSelected(false);
                        allInRadioButton.setSelected(false);
                        roomBreakfastRadioButton.setSelected(false);
                        fullPensionRadioButton.setSelected(false);
                        halfPensionRadioButton.setSelected(false);
                        onlyBedRadioButton.setSelected(false);
                        fullCreditExceptAlcoholRadioButton.setSelected(false);
                    }
                }
                dispose();
            }
        });
    }
}
