package com.tourismagency.View;

import com.tourismagency.Helper.Config;
import com.tourismagency.Helper.Helper;
import com.tourismagency.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginGUI extends JFrame{
    private JPanel wrapper;
    private JPanel panelTop;
    private JPanel panelBot;
    private JTextField txtfld_userName;
    private JTextField txtfld_password;
    private JButton button_login;
    private JLabel label_userName;
    private JLabel label_password;
    private JLabel label_Icon;
    private JLabel label_Prefix;
    private User user;

    public LoginGUI(){
        add(wrapper);
        setSize(350,450);
        setLocation(Helper.findCenter("x",getSize()),Helper.findCenter("y",getSize()));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);

        setVisible(true);
        setResizable(false);


        button_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Helper.isFieldEmpty(txtfld_userName) || Helper.isFieldEmpty(txtfld_password)){
                    Helper.getMessage("fill");
                }else {
                    User user =User.getFetch(txtfld_userName.getText(),txtfld_password.getText());
                    if (user == null){
                        Helper.getMessage("User can not found");
                    }else {
                        switch (user.getType()){
                            case "operator":
                                OperatorGUI op = new OperatorGUI();
                                break;
                            case "admin":
                                AdminGUI ad = new AdminGUI();
                                break;
                        }
                        dispose();
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        LoginGUI lg =new LoginGUI();

    }
}
