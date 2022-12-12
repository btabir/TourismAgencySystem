package com.tourismagency.View;

import com.tourismagency.Helper.Config;
import com.tourismagency.Helper.Helper;
import com.tourismagency.Model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdminGUI extends JFrame{
    private JPanel wrapper;
    private JTabbedPane tab_HMSystem;
    private JLabel label_AdminScreen;
    private JPanel wTop;
    private JButton button_SaveHotel;
    private JScrollPane scrool_HotelList1;
    private JScrollPane scroll_HotelList2;
    private JScrollPane scroll_HotelList3;
    private JPanel hotelManagement_Top;
    private JPanel hotelManagement_Bot;
    private JTable table_HotelList;
    private JTable table_hotelType;
    private JTable table_hotelSeason;
    private JButton button_AddRoom;
    private JTable table_RoomList;
    private JTextField txtfld_HotelCityName;
    private JTextField txtfld_Checkin;
    private JTextField txtfld_Checkout;
    private JTextField txtfld_AdultNumber;
    private JTextField txtfld_ChildrenNumber;
    private JButton button_SearchRoom;
    private JTextField txtfld_RoomId;
    private JButton button_ReserveRoom;
    private JLabel label_HotelCityName;
    private JLabel label_Checkin;
    private JPanel RightPanel;
    private JLabel label_Checkout;
    private JLabel label_AdultNumber;
    private JLabel label_ChildNumber;
    private JLabel label_ReserveRoom;
    private JTable table5;
    private JTable table6;
    private JButton button_Exit;
    private JButton button_delete;
    private JTextField txtfld_DeleteHotel;
    private DefaultTableModel model_hotelList;
    private DefaultTableModel model_hotelType;
    private Object[] row_hotelType;
    private Object[] row_hotelList;
    private DefaultTableModel model_hotelSeason;
    private Object[] row_hotelSeason;
    private int selected_hotel_id;


    public AdminGUI(){


        add(wrapper);
        setSize(800,600);
        setLocation(Helper.findCenter("x",getSize()),Helper.findCenter("y",getSize()));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        table_HotelList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) { //Kullanıcıya tıklayınca adını direkt delete kısmına yazdırıp silmeyi kolaylaştırmaya yarayan metod

                try {
                    String selectedRow = table_HotelList.getValueAt(table_HotelList.getSelectedRow(),0).toString();//tostring ile döndürdüğü objeyi yakalıyoruz
                    txtfld_DeleteHotel.setText(selectedRow);
                }catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });

        //hotel list
        model_hotelList = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 1)
                    return false;
                if (column == 2)
                    return false;
                if (column == 3)
                    return false;
                if (column == 4)
                    return false;
                if (column == 5)
                    return false;
                if (column == 6)
                    return false;
                if (column == 7)
                    return false;

                return super.isCellEditable(row, column);
            }
        };

        Object[] column_hotelList = {"Id","Hotel Name","Address","E-Mail","Phone Number","Stars","Properties"};
        model_hotelList.setColumnIdentifiers(column_hotelList);
        row_hotelList = new Object[column_hotelList.length];
        loadHotelModel();
        table_HotelList.setModel(model_hotelList);
        table_HotelList.getTableHeader().setReorderingAllowed(false);
        table_HotelList.getColumnModel().getColumn(0).setMaxWidth(50);

        //##hotel list

        table_hotelType.getSelectionModel().addListSelectionListener(e->{
            try {
                selected_hotel_id = Integer.parseInt(table_HotelList.getValueAt(table_HotelList.getSelectedRow(),0).toString());
            }catch (Exception exception){
                System.out.println(exception.getMessage());
            }
            loadHotelModel();
            loadHotelType(selected_hotel_id);
            loadHotelSeasonModel(selected_hotel_id);
            selected_hotel_id = 0;
        });

        //Pension Types
        model_hotelType = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row,int column) {
                if (column == 1)
                    return false;
                if (column == 2)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] column_hotelType = {"Id","Pension Types"};
        model_hotelType.setColumnIdentifiers(column_hotelType);
        row_hotelType = new Object[column_hotelType.length];
        loadHotelType(1);
        table_hotelType.setModel(model_hotelType);
        table_hotelType.getTableHeader().setReorderingAllowed(false);

        //##Pension Types

        //Hotel Session
        model_hotelSeason = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                if (column == 1)
                    return false;
                if (column == 2)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] column_hotelSession = {"Id","Period Start","Period End"};
        model_hotelSeason.setColumnIdentifiers(column_hotelSession);
        row_hotelSeason = new Object[column_hotelSession.length];
        loadHotelSeasonModel();
        table_hotelSeason.setModel(model_hotelSeason);
        table_hotelSeason.getTableHeader().setReorderingAllowed(false);
        //##Hotel Session

        //Save Hotel
        button_SaveHotel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddHotelGUI addHotel = new AddHotelGUI();
                addHotel.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        super.windowClosed(e);
                        loadHotelModel();
                        table_HotelList.getSelectionModel().clearSelection();
                    }
                });
            }
        });
        //##Save Hotel

        //Add Room
        button_AddRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddRoomGUI addRoom = new AddRoomGUI();
                addRoom.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadHotelModel();
                        table_RoomList.getSelectionModel().clearSelection();
                        super.windowClosed(e);
                    }
                });
            }
        });
        //##Add Room

        //Reserve Room
        button_ReserveRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReservationGUI rs = new ReservationGUI();
                rs.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        super.windowClosed(e);
                    }
                });
            }
        });
        //##Reserve Room

        //Exit Button
        button_Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                loadHotelModel();
            }
        });
        //##Exit Button
        button_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFieldEmpty(txtfld_DeleteHotel)){
                    Helper.getMessage("fill");
                }else {
                    int id =Integer.parseInt(txtfld_DeleteHotel.getText());
                    if (Hotel.delete(id) && HotelType.delete(id) && HotelSeason.delete(id)){
                        Helper.getMessage("done");
                        loadHotelModel();

                    }else {
                        Helper.getMessage("error");
                    }
                }
            }
        });
    }

    public void loadHotelModel(){
        DefaultTableModel clearModel = (DefaultTableModel) table_HotelList.getModel();
        clearModel.setRowCount(0);

        int i ;
        for (Hotel object:Hotel.getList()){
            i = 0;
            row_hotelList[i++] = object.getId();
            row_hotelList[i++] = object.getName();
            row_hotelList[i++] = object.getAddress();
            row_hotelList[i++] = object.getEmail();
            row_hotelList[i++] = object.getPhoneNumber();
            row_hotelList[i++] = object.getStars();
            row_hotelList[i++] = object.getProperties();

            model_hotelList.addRow(row_hotelList);
        }
    }


    public void loadHotelType(int id){
        DefaultTableModel clearModel = (DefaultTableModel) table_hotelType.getModel();
        clearModel.setRowCount(0);

        int i;
        for (HotelType object:HotelType.getListByHotelId(id)){
            i = 0;

            row_hotelType[i++] = object.getHotel_id();
            row_hotelType[i++] = object.getType();
            model_hotelType.addRow(row_hotelType);
        }
    }




    public void loadHotelSeasonModel(int id){
        DefaultTableModel clearModel = (DefaultTableModel) table_hotelSeason.getModel();
        clearModel.setRowCount(0);

        int i;
        for (HotelSeason object : HotelSeason.getListByHotelId(id)){
            i = 0;

            row_hotelSeason[i++] = object.getHotel_id();
            row_hotelSeason[i++] = object.getStart_Date();
            row_hotelSeason[i++] = object.getEnd_date();
            model_hotelSeason.addRow(row_hotelSeason);
        }
    }

    public void loadHotelSeasonModel(){
        DefaultTableModel clearModel = (DefaultTableModel) table_hotelSeason.getModel();
        clearModel.setRowCount(0);

        int i;
        for (HotelSeason object : HotelSeason.getList()){
            i = 0;

            row_hotelSeason[i++] = object.getHotel_id();
            row_hotelSeason[i++] = object.getStart_Date();
            row_hotelSeason[i++] = object.getEnd_date();
            model_hotelSeason.addRow(row_hotelSeason);
        }
    }

    public static void main(String[] args) {
        Helper.setLayout();
        AdminGUI ad = new AdminGUI();
    }


}
