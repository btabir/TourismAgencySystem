package com.tourismagency.Helper;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Helper {

    public static int findCenter(String exxen, Dimension size) {
        int point;

        switch (exxen) {
            case "x":
                point = (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
                break;
            case "y":
                point = (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
                break;
            default:
                point = 0;
        }
        return point;
    }

    public static void setLayout() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }

    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    public static void getMessage(String str) {
        String message;
        String title;

        switch (str) {
            case "fill":
                message = "Please fill the all empty fields!";
                title = "Error";
                break;

            case "done":
                message = "Operation succesfull";
                title = "Done";
                break;

            case "error":
                message = "Operation failed";
                title = "Error";
                break;

            default:
                message = str;
                title = "Message";
        }
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void Confirm(String str) {
        String message;
        String title;
        switch (str) {
            case "sure":
                message = "Are you sure?";
                title = "Confirm";
                break;
            default:
                message = str;
                title = "Confirm";
                break;
        }
        JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
    }

    public static String HotelType(String number) {
        String type = "";
        switch (number) {
            case "1":
                type = "Ultra All-in";
                break;
            case "2":
                type = "All-in";
                break;
            case "3":
                type = "Room Breakfast";
                break;
            case "4":
                type = "Full Pension";
                break;
            case "5":
                type = "Half Pension";
                break;
            case "6":
                type = "Bed Only";
                break;
            case "7":
                type = "Full Credit Except Alcohol";
                break;
        }
        return type;
    }

    public static String RoomType(String number) {
        String type = "";
        switch (number) {
            case "1":
                type = "Single";
                break;
            case "2":
                type = "Double";
                break;
            case "3":
                type = "Suit";
                break;
            case "4":
                type = "Family";
                break;
            case "5":
                type = "Group";
                break;
        }
        return type;
    }

    public static String roomProperty(String number){
        String property="";
        switch (number){
            case "1":
                property = "Television ";
                break;
            case "2":
                property = "Minibar ";
                break;
            case "3":
                property = "Game Console";
                break;
            case "4":
                property = "Till";
                break;
            case "5":
                property = "Projection";
                break;
        }
        return property;
    }
}
