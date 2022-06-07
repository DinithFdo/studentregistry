package com.example.demo3;

import javafx.scene.control.Alert;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Student {

    private int id;
    private String name;
    private LocalDate admissionDate;
    private LocalDate birthday;
    private String NICno;
    private String JUVPVT;
    private String Training;
    private String Recidence;
    private String Parents;
    private int fileNo;
    private String status;

    public Student(){}

    public Student(int id,String name, LocalDateTime admissionDate, LocalDateTime birthday, String niCno, String juvpvt, String training, String recidence, String parents, int fileNo, String status) {
        this.id = id;
        this.name = name;
        this.admissionDate = LocalDate.from(admissionDate);
        this.birthday = LocalDate.from(birthday);
        this.NICno = niCno;
        this.JUVPVT = juvpvt;
        this.Training = training;
        this.Recidence = recidence;
        this.Parents = parents;
        this.fileNo = fileNo;
        this.status = status;
    }

    public void setName(String name) {
        if(name.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Name field cannot be kept empty! ");
            alert.showAndWait();
        }else{
            this.name = name;
        }
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public void setBirthday(LocalDate birthday,LocalDate now) {
        if(birthday != null && birthday.isBefore(now)){
            this.birthday = birthday;
        }else{
            System.out.println("Birthday cant be same as today!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Birthday cant be same as today!");
            alert.showAndWait();
        }

    }

    public void setFileNo(int fileNo) {
        this.fileNo = fileNo;
    }

    public void setJUVPVT(String JUVPVT) {
        this.JUVPVT = JUVPVT;
    }

    public void setNICno(String NICno) {
        this.NICno = NICno;
    }

    public void setParents(String parents) {
        this.Parents = parents;
    }

    public void setRecidence(String recidence) {
        Recidence = recidence;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTraining(String training) {
        Training = training;
    }

    public int getId(){
        return id;
    }
    public String getName() {
        return name;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getNICno() {
        return NICno;
    }

    public String getJUVPVT() {
        return JUVPVT;
    }

    public String getTraining() {
        return Training;
    }

    public String getRecidence() {
        return Recidence;
    }

    public String getParents() {
        return Parents;
    }

    public int getFileNo() {
        return fileNo;
    }

    public String getStatus() {
        return status;
    }
}
