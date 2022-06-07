package com.example.demo3;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.xdevapi.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class Viewstudents implements Initializable {

    @FXML
    private TableView <Student> table;

    @FXML
    private TableColumn <Student,Integer> id;

    @FXML
    private TableColumn <Student,String> name;

    @FXML
    private TableColumn <Student, LocalDate> admissionDate;

    @FXML
    private TableColumn <Student,LocalDate> birthday;

    @FXML
    private TableColumn<Student,String> nicNo;

    @FXML
    private TableColumn <Student,String> juvpvt;

    @FXML
    private TableColumn <Student,String> training;

    @FXML
    private TableColumn <Student,String> recidence;

    @FXML
    private TableColumn <Student,String> parents;

    @FXML
    private TableColumn <Student,Integer> fileNo;

    @FXML
    private TableColumn <Student,String> status;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            id.setCellValueFactory(new PropertyValueFactory<Student,Integer>("id"));
            name.setCellValueFactory(new PropertyValueFactory<Student,String>("name"));
            admissionDate.setCellValueFactory(new PropertyValueFactory<Student,LocalDate>("admissionDate"));
            birthday.setCellValueFactory(new PropertyValueFactory<Student,LocalDate>("birthday"));
            nicNo.setCellValueFactory(new PropertyValueFactory<Student,String>("NICno"));
            juvpvt.setCellValueFactory(new PropertyValueFactory<Student,String>("JUVPVT"));
            training.setCellValueFactory(new PropertyValueFactory<Student,String>("Training"));
            recidence.setCellValueFactory(new PropertyValueFactory<Student,String>("Recidence"));
            parents.setCellValueFactory(new PropertyValueFactory<Student,String>("Parents"));
            fileNo.setCellValueFactory(new PropertyValueFactory<Student,Integer>("FileNo"));
            status.setCellValueFactory(new PropertyValueFactory<Student,String>("Status"));

            ObservableList<Student> stdlist = StudentDAO.getAllRecords();
            populateTable(stdlist);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    protected void deleteClick(){
        ObservableList<Student> selectedStd;
        selectedStd = table.getSelectionModel().getSelectedItems();

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION,"Delete "+selectedStd.get(0).getName()+" ?", ButtonType.YES,ButtonType.NO);
        confirmationAlert.setHeaderText("Are you sure you want to delete this record?");
        confirmationAlert.showAndWait();

        if(confirmationAlert.getResult() == ButtonType.YES){
            try{
                StudentDAO.removeStudent(selectedStd.get(0).getId());
                Alert deletedAlert  = new Alert(Alert.AlertType.INFORMATION);
                deletedAlert.setHeaderText("Record Deleted Successfully !");
                deletedAlert.show();

                ObservableList<Student> stdlist = StudentDAO.getAllRecords();
                populateTable(stdlist);

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }

    @FXML
    protected void editClick() throws IOException, SQLException, ClassNotFoundException {

        ObservableList<Student> selectedStd;
        selectedStd = table.getSelectionModel().getSelectedItems();

        String sql = "SELECT * FROM STUDENTS WHERE id='"+selectedStd.get(0).getId()+"'";

        ResultSet rs = DBUtil.dbExecuteQuery(sql);

        if(rs.next()){
            Student editStd = new Student(
                    rs.getInt("id"),
                    rs.getString("stdName"),
                    rs.getTimestamp("admissionDate").toLocalDateTime(),
                    rs.getTimestamp("birthDay").toLocalDateTime(),
                    rs.getString("NICno"),
                    rs.getString("JUVPVT"),
                    rs.getString("Training"),
                    rs.getString("Recidence"),
                    rs.getString("Parents"),
                    rs.getInt("FileNo"),
                    rs.getString("Status")
            );

            Edit.getStudent(editStd);
        }

        Stage stg = new Stage();
        stg.setTitle("Edit");
        Parent root = FXMLLoader.load(getClass().getResource("edit.fxml"));
        Scene scn = new Scene(root,505,619);
        stg.setScene(scn);
        stg.show();


    }

    private void populateTable(ObservableList<Student> stdList){
        table.setItems(stdList);
    }

}
