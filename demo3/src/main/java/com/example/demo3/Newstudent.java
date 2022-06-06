package com.example.demo3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Newstudent implements Initializable {

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
    private TableView <Student> table;

    @FXML
    private TableColumn<Student,Integer> id;
    @FXML
    private TableColumn<Student,String> name;

    @FXML
    private TableColumn<Student,LocalDate> admissiondate;

    @FXML
    private TableColumn<Student,LocalDate> birthdate;

    @FXML
    private TableColumn<Student,String> nic;

    @FXML
    private TableColumn<Student,String> juvPvt;

    @FXML
    private TableColumn<Student,String> Training;

    @FXML
    private TableColumn<Student,String> Recidence;

    @FXML
    private TableColumn<Student,String> Parents;

    @FXML
    private TableColumn<Student,Integer> fileno;

    @FXML
    private TableColumn<Student,String> Status;




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
    protected void addStudent(ActionEvent actionevent) throws SQLException,ClassNotFoundException {

        Student newstudent = new Student();

        newstudent.setName(studentName.getText());
        newstudent.setAdmissionDate(admissionDate.getValue());
        newstudent.setBirthday(birthday.getValue(),LocalDate.now());
        newstudent.setNICno(nicNo.getText());
        newstudent.setJUVPVT(juvpvt.getValue());
        newstudent.setTraining(training.getValue());
        newstudent.setRecidence(recidence.getValue());
        newstudent.setParents(parents.getText());
        newstudent.setFileNo(Integer.parseInt(fileNo.getText()));
        newstudent.setStatus(status.getText());

        StudentDAO.insertStudent(newstudent.getName(),newstudent.getAdmissionDate(),newstudent.getBirthday(),newstudent.getNICno(),newstudent.getJUVPVT(),newstudent.getTraining(),newstudent.getRecidence(),newstudent.getParents(),newstudent.getFileNo(),newstudent.getStatus());

        ObservableList<Student> stdlist = StudentDAO.getAllRecords();
        populateTable(stdlist);

        displayLabel.setText("Student Successfully Added !");
    }

    @FXML
    protected void goBackBttn(ActionEvent actionevent) throws Exception{

        Stage stg = new Stage();
        stg.setTitle("HomePage");
        Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        Scene scn = new Scene(root,600,400);
        stg.setScene(scn);
        stg.show();

        Stage previousStage = (Stage) ((Node)actionevent.getSource()).getScene().getWindow();
        previousStage.hide();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            id.setCellValueFactory(new PropertyValueFactory<Student,Integer>("id"));
            name.setCellValueFactory(new PropertyValueFactory<Student,String>("name"));
            admissiondate.setCellValueFactory(new PropertyValueFactory<Student,LocalDate>("admissionDate"));
            birthdate.setCellValueFactory(new PropertyValueFactory<Student,LocalDate>("birthday"));
            nic.setCellValueFactory(new PropertyValueFactory<Student,String>("NICno"));
            juvPvt.setCellValueFactory(new PropertyValueFactory<Student,String>("JUVPVT"));
            Training.setCellValueFactory(new PropertyValueFactory<Student,String>("Training"));
            Recidence.setCellValueFactory(new PropertyValueFactory<Student,String>("Recidence"));
            Parents.setCellValueFactory(new PropertyValueFactory<Student,String>("Parents"));
            fileno.setCellValueFactory(new PropertyValueFactory<Student,Integer>("FileNo"));
            Status.setCellValueFactory(new PropertyValueFactory<Student,String>("Status"));

            ObservableList<Student> stdlist = StudentDAO.getAllRecords();
            populateTable(stdlist);

        }catch(Exception e ){
            System.out.println(e.getMessage());
        }

    }

    private void populateTable(ObservableList<Student> stdList){
        table.setItems(stdList);
    }

}
