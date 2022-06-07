package com.example.demo3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Edit implements Initializable {

    private static int id;
    private static String name;
    private static LocalDate admissiondate;
    private static LocalDate birthDay;
    private static String NICno;
    private static String JUVPVT;
    private static String Training;
    private static String Recidence;
    private static String Parents;
    private static int fileno;
    private static String Status;

    @FXML
    private TextField search;
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


    public static void getStudent(Student editStd){
        System.out.println(editStd.getName());
        id = editStd.getId();
        name = editStd.getName();
        admissiondate = editStd.getAdmissionDate();
        birthDay = editStd.getBirthday();
        NICno = editStd.getNICno();
        JUVPVT = editStd.getJUVPVT();
        Training = editStd.getTraining();
        Recidence = editStd.getRecidence();
        Parents = editStd.getParents();
        fileno = editStd.getFileNo();
        Status = editStd.getStatus();
    }

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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        studentName.setText(name);
        admissionDate.setValue(admissiondate);
        birthday.setValue(birthDay);
        nicNo.setText(NICno);
        juvpvt.setValue(JUVPVT);
        training.setValue(Training);
        recidence.setValue(Recidence);
        parents.setText(Parents);
        fileNo.setText(String.valueOf(fileno));
        status.setText(Status);
    }

    @FXML
    protected void searchRecord(){
        String input = search.getText();


    }
}
