package com.example.demo3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login {
    @FXML
    private TextField usrName;

    @FXML
    private PasswordField usrPassword;

    @FXML
    private Label displayLabel;



    @FXML
    protected void clickLogin(ActionEvent actionevent) throws Exception {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentRegistry?useSSL=false","root","Dinith20010620");

            String username = usrName.getText();
            String password = usrPassword.getText();

            Statement stm = con.createStatement();

            String sql = "SELECT * FROM LoginAccnts WHERE userName = '"+username+"' AND passWord='"+password+"'";

            ResultSet rs = stm.executeQuery(sql);

            if(rs.next()){

                Stage stage = new Stage();
                stage.setTitle("Homepage");
                Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
                Scene newScene = new Scene(root,600,400);
                stage.setScene(newScene);
                stage.show();

                Stage previousStage = (Stage) ((Node)actionevent.getSource()).getScene().getWindow();
                previousStage.hide();

            }else{
                displayLabel.setText("username or password is incorrect !");
                usrName.setText("");
                usrPassword.setText("");
            }

        }catch(Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    @FXML
    protected void usernameSetFocus(){
        displayLabel.setText("");
    }
}