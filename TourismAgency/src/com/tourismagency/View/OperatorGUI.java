package com.tourismagency.View;

import com.tourismagency.Helper.Config;
import com.tourismagency.Helper.Helper;
import com.tourismagency.Model.Operator;
import com.tourismagency.Model.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OperatorGUI extends JFrame {
    //private final Operator operator;
    private JPanel wrapper;
    private JTabbedPane tab_Menu1;
    private JPanel wTop;
    private JLabel label_Welcome;
    private JButton button_Exit;
    private JTextField txtfld_SearchNameSurname;
    private JLabel label_SearchNameSurname;
    private JTextField txtfld_SearchUsername;
    private JComboBox comboBox_SearchType;
    private JButton button_Search;
    private JLabel label_SearchUsername;
    private JLabel label_SearchType;
    private JTable table_userList;
    private JTextField txtfld_AddNameSurname;
    private JTextField txtfld_AddUsername;
    private JTextField txtfld_AddPassword;
    private JComboBox comboBox_AddType;
    private JLabel label_AddNameSurname;
    private JLabel label_AddUsername;
    private JLabel label_AddPassword;
    private JLabel label_AddType;
    private JButton button_AddOperator;
    private JTextField txtfld_DeleteById;
    private JButton button_Delete;
    private JLabel label_DeleteById;

    private DefaultTableModel model_userList;
    private Object[] row_userList;

    public OperatorGUI(){
        //this.operator = operator;

        add(wrapper);
        setSize(800,600);
        setLocation(Helper.findCenter("x",getSize()),Helper.findCenter("y",getSize()));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);

        setVisible(true);

        //Model userList

        model_userList = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 1 || column == 2 || column == 3 || column == 4 || column == 5){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_userList = {"Id","Name Surname","Username","Password","Type"};
        model_userList.setColumnIdentifiers(col_userList);

        row_userList = new Object[col_userList.length];
        loadUserModel();


        table_userList.setModel(model_userList);
        table_userList.getTableHeader().setReorderingAllowed(false);
        table_userList.getColumnModel().getColumn(0).setMaxWidth(50);

        //add user
        button_AddOperator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFieldEmpty(txtfld_AddNameSurname) || Helper.isFieldEmpty(txtfld_AddUsername) || Helper.isFieldEmpty(txtfld_AddPassword)){
                    Helper.getMessage("fill");

                }else {
                    String name = txtfld_AddNameSurname.getText();
                    String uname = txtfld_AddUsername.getText();
                    String password = txtfld_AddPassword.getText();
                    String type = comboBox_AddType.getSelectedItem().toString();
                    if (User.add(name,uname,password,type)){
                        Helper.getMessage("done");

                        loadUserModel();

                        txtfld_AddNameSurname.setText(null);
                        txtfld_AddUsername.setText(null);
                        txtfld_AddPassword.setText(null);

                    }else {
                        Helper.getMessage("error");
                    }
                }
            }
        });
        //##add user

        //delete user
        button_Delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFieldEmpty(txtfld_DeleteById)){
                    Helper.getMessage("fill");
                }else {
                    int id = Integer.parseInt(txtfld_DeleteById.getText());
                    if (User.delete(id)){
                        Helper.getMessage("done");

                        loadUserModel();

                        txtfld_DeleteById.setText(null);
                    }else {
                        Helper.getMessage("error");
                    }
                }
            }
        });
        //##delete user

        //listen table
        //we can delete user with just click on it, and it automatically shows in the Delete by id text then we can delete it with click easily
        table_userList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    String selectedRow = table_userList.getValueAt(table_userList.getSelectedRow(),0).toString();
                    txtfld_DeleteById.setText(selectedRow);
                }catch (Exception exception){
                    System.out.println(exception.getMessage());
                }
            }
        });

        //##listen table

        //exit button
        button_Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });
        //##exit button

        //search button
        button_Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    String name = txtfld_SearchNameSurname.getText();
                    String uname = txtfld_SearchUsername.getText();
                    String type = comboBox_SearchType.getSelectedItem().toString();

                    String query = User.searchQuery(name,uname,type);
                    ArrayList<User> searchingUser = User.searchUsers(query);
                    loadUserModel(searchingUser);
                }
        });
        //##search button
    }


    public void loadUserModel(){
        DefaultTableModel clearModel = (DefaultTableModel) table_userList.getModel();
        clearModel.setRowCount(0);

        for (User object :User.getList()){
            int i = 0;

            row_userList[i++] = object.getId();
            row_userList[i++] = object.getName();
            row_userList[i++] = object.getUname();
            row_userList[i++] = object.getPassword();
            row_userList[i++] = object.getType();


            model_userList.addRow(row_userList);
        }
    }

    public void loadUserModel(ArrayList<User> list){
        DefaultTableModel clearModel = (DefaultTableModel) table_userList.getModel();
        clearModel.setRowCount(0);

        for (User object :list){
            int i = 0;

            row_userList[i++] = object.getId();
            row_userList[i++] = object.getName();
            row_userList[i++] = object.getUname();
            row_userList[i++] = object.getPassword();
            row_userList[i++] = object.getType();


            model_userList.addRow(row_userList);
        }
    }

    public static void main(String[] args) {
        Operator opr = new Operator();
        Helper.setLayout();

        OperatorGUI op = new OperatorGUI();
    }
}
