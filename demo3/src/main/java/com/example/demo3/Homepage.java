package com.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Homepage {

    @FXML
    protected void addNewstudent(ActionEvent actionevent) throws Exception{

        Stage stg = new Stage();
        stg.setTitle("Add New Student");
        Parent root = FXMLLoader.load(getClass().getResource("newstudent.fxml"));
        Scene newscene = new Scene(root,400,400);
        stg.setScene(newscene);
        stg.show();

        Stage previousStage = (Stage) ((Node)actionevent.getSource()).getScene().getWindow();
        previousStage.hide();

        try{

        }catch (Exception e){

        }
    }

    @FXML
    protected void viewStudents(ActionEvent actionevent) throws Exception{

    }
}
