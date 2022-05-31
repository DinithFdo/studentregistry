package com.example.demo3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;

public class Newstudent {

    @FXML
    private TextField studentName;

    @FXML
    private DatePicker admissionDate;

    @FXML
    private DatePicker birthday;

    @FXML
    private TextField nicNo;

    @FXML
    private ComboBox<String> juvpvt;

    @FXML
    private ComboBox<String> training;

    @FXML
    private ComboBox<String> recidence;

    @FXML
    private TextArea parents;

    @FXML
    private TextField fileNo;

    @FXML
    private TextField status;

    @FXML
    private Label displayLabel;


    @FXML
    protected void juvpvtSelect(){
        ObservableList<String> list = FXCollections.observableArrayList("JUV","PVT");
        juvpvt.setItems(list);
    }


    @FXML
    protected void trainingSelect(){
        ObservableList<String> list = FXCollections.observableArrayList("Welding","Electrical","Motor Mechanic","Press","Carpentry");
        training.setItems(list);
    }


    @FXML
    protected void recidenceSelect(){
        ObservableList<String> list = FXCollections.observableArrayList("Day","Resident");
        recidence.setItems(list);
    }
    

    @FXML
    protected void addStudent(ActionEvent actionevent) throws Exception {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentRegistry?useSSL=false","root","Dinith20010620");

            String stdName = studentName.getText();
            LocalDate admidate =  admissionDate.getValue();
            LocalDate bday = birthday.getValue();
            String NIC = nicNo.getText();
            String juvpvtResult = juvpvt.getValue();
            String trainingResult = training.getValue();
            String recidenceResult = recidence.getValue();
            String parentsInfo = parents.getText();
            int fileNum = Integer.parseInt(fileNo.getText());
            String stdStatus = status.getText();

            Statement st = con.createStatement();

            String sql = "INSERT INTO Students (stdName,admissionDate,birthDay,NICno,JUVPVT,Training,Recidence,Parents,FileNo,Status)" +
                    "VALUES ('"+stdName+"','"+admidate+"','"+bday+"','"+NIC+"','"+juvpvtResult+"','"+trainingResult+"','"+recidenceResult+"','"+parentsInfo+"','"+fileNum+"','"+stdStatus+"')";

            st.executeUpdate(sql);
            displayLabel.setText("Student Successfully Added !");

        }catch (Exception e){
            displayLabel.setText("Error!");
            System.out.println(e.getMessage());
        }

    }



}
