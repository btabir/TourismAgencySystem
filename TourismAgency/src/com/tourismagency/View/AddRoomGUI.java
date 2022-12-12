package com.tourismagency.View;

import com.tourismagency.Helper.Config;
import com.tourismagency.Helper.Helper;
import com.tourismagency.Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddRoomGUI extends JFrame{
    private JPanel wrapper;
    private JLabel label_TitleRoomAdd;
    private JComboBox comboBox_HotelName;
    private JComboBox comboBox_RoomType;
    private JTextField txtfld_Stock;
    private JComboBox comboBox_PensionType;
    private JComboBox comboBox_Season;
    private JTextField txtfld_AdultPrice;
    private JTextField txtfld_ChildrenPrice;
    private JLabel label_HotelName;
    private JLabel label_RoomType;
    private JLabel label_Stock;
    private JLabel label_PensionType;
    private JLabel label_Season;
    private JLabel label_AdultPrice;
    private JLabel label_ChildrenPrice;
    private JTextField txtfld_BedType;
    private JTextField txtfld_RoomArea;
    private JRadioButton radioButton_Single;
    private JRadioButton radioButton_Double;
    private JRadioButton radioButton_Suit;
    private JRadioButton radioButton_Family;
    private JRadioButton radioButton_Group;
    private JLabel label_BedType;
    private JLabel label_RoomArea;
    private JButton button_AddRoom;
    private int added_room_id;

    public AddRoomGUI(){
        add(wrapper);
        setSize(800,600);
        setLocation(Helper.findCenter("x",getSize()),Helper.findCenter("y",getSize()));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        radioButton_Single.setText(Helper.roomProperty("1"));
        radioButton_Double.setText(Helper.roomProperty("2"));
        radioButton_Suit.setText(Helper.roomProperty("3"));
        radioButton_Family.setText(Helper.roomProperty("4"));
        radioButton_Group.setText(Helper.roomProperty("5"));

        loadHotelNameCombo();
        loadHotelTypeCombo();
        loadSeasonCombo();

        comboBox_HotelName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadHotelTypeCombo();
                loadSeasonCombo();
                comboBox_RoomType.setSelectedIndex(0);
                comboBox_Season.setSelectedIndex(0);
            }
        });

        button_AddRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFieldEmpty(txtfld_Stock) || Helper.isFieldEmpty(txtfld_AdultPrice) || Helper.isFieldEmpty(txtfld_ChildrenPrice) ||
                Helper.isFieldEmpty(txtfld_BedType) || Helper.isFieldEmpty(txtfld_RoomArea) ||
                        comboBox_RoomType.getSelectedItem().toString().equals("") || comboBox_HotelName.getSelectedItem() == null ||
                        comboBox_PensionType.getSelectedItem() == null || comboBox_Season.getSelectedItem() == null ||
                        (!radioButton_Single.isSelected() && !radioButton_Double.isSelected() && !radioButton_Suit.isSelected() &&
                                !radioButton_Family.isSelected() && !radioButton_Group.isSelected())){
                    Helper.getMessage("fill");
                }else {
                    String room_type =comboBox_RoomType.getSelectedItem().toString();
                    int stock =Integer.parseInt(txtfld_Stock.getText());
                    int season_id =0;
                    int adult_price =Integer.parseInt(txtfld_AdultPrice.getText());
                    int child_price = Integer.parseInt(txtfld_ChildrenPrice.getText());
                    Item hotelTypeItem = (Item) comboBox_PensionType.getSelectedItem();
                    int hotel_type_id =hotelTypeItem.getKey();
                    Item hotelItem = (Item) comboBox_HotelName.getSelectedItem();
                    int hotel_id = hotelItem.getKey();

                    for (HotelSeason object : HotelSeason.getListByHotelId(hotel_id)){
                        String season =(object.getStart_Date().toString() + " - " + object.getEnd_date().toString());
                        if (season.equals(comboBox_Season.getSelectedItem().toString())){
                            season_id =object.getId();
                            break;
                        }
                    }

                    if (Room.add(room_type,stock,season_id,adult_price,child_price,hotel_type_id,hotel_id)){
                        ArrayList<Room> roomList =Room.getList();
                        Room addedRooms =roomList.get(Room.getList().size()-1);
                        added_room_id = addedRooms.getId();

                        String roomProperties = "";
                        for (int i =1 ; i<=7 ; i++){
                            switch (i){
                                case 1:
                                    if (radioButton_Single.isSelected()){
                                        roomProperties += radioButton_Single.getText();
                                    }
                                    break;
                                case 2:
                                    if (radioButton_Double.isSelected()){
                                        roomProperties += "\n" +radioButton_Double.getText();
                                    }
                                    break;
                                case 3:
                                    if (radioButton_Suit.isSelected()){
                                        roomProperties += "\n" +radioButton_Suit.getText();
                                    }
                                    break;
                                case 4:
                                    if (radioButton_Family.isSelected()){
                                        roomProperties += "\n" +radioButton_Family.getText();
                                    }
                                    break;
                                case 5:
                                    if (radioButton_Group.isSelected()){
                                        roomProperties += "\n" +radioButton_Group.getText();
                                    }
                                    break;
                            }
                        }
                        RoomProperties.add(roomProperties,added_room_id,txtfld_BedType.getText(),Integer.parseInt(txtfld_RoomArea.getText().toString()));
                        Helper.getMessage("done");

                        comboBox_HotelName.setSelectedIndex(0);
                        comboBox_RoomType.setSelectedIndex(0);
                        txtfld_Stock.setText(null);
                        comboBox_PensionType.setSelectedIndex(0);
                        comboBox_Season.setSelectedIndex(0);
                        txtfld_AdultPrice.setText(null);
                        txtfld_ChildrenPrice.setText(null);
                        txtfld_BedType.setText(null);
                        txtfld_RoomArea.setText(null);
                        radioButton_Single.setSelected(false);
                        radioButton_Double.setSelected(false);
                        radioButton_Suit.setSelected(false);
                        radioButton_Family.setSelected(false);
                        radioButton_Group.setSelected(false);
                    }
                }
            }
        });

    }

    private void loadHotelNameCombo(){
        comboBox_HotelName.removeAllItems();
        comboBox_HotelName.addItem(new Item(0,null));
        for (Hotel object : Hotel.getList()){
            comboBox_HotelName.addItem(new Item(object.getId(),object.getName()));
        }
    }

    private void loadHotelTypeCombo(){
        Item hotelItem = (Item) comboBox_HotelName.getSelectedItem();
        comboBox_PensionType.removeAllItems();
        comboBox_PensionType.addItem(new Item(0,null));

        for (HotelType object:HotelType.getListByHotelId(hotelItem.getKey())){
            comboBox_PensionType.addItem(new Item(object.getId(),object.getType()));
        }
    }

    private void loadSeasonCombo() {
        Item hotelItem = (Item) comboBox_HotelName.getSelectedItem();
        comboBox_Season.removeAllItems();
        comboBox_Season.addItem(new Item(0,null));
        for (HotelSeason obj : HotelSeason.getListByHotelId(hotelItem.getKey())){
            comboBox_Season.addItem(new Item(obj.getId(), (obj.getStart_Date() + "  -  " + obj.getEnd_date())));
        }
    }

    public static void main(String[] args) {
        Helper.setLayout();
        AddRoomGUI addr = new AddRoomGUI();
    }
}
