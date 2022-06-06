package com.example.demo3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class StudentDAO {

    public static void insertStudent(String name, LocalDate admissionDate,LocalDate birthDate,String nicNo,String juvpvt,String training,String recidence,String parents,int fileNO,String status) throws SQLException,ClassNotFoundException {
        String sql = "INSERT INTO Students (stdName,admissionDate,birthDay,NICno,JUVPVT,Training,Recidence,Parents,FileNo,Status)" +
                "VALUES ('"+name+"','"+admissionDate+"','"+birthDate+"','"+nicNo+"','"+juvpvt+"','"+training+"','"+recidence+"','"+parents+"','"+fileNO+"','"+status+"')";

        try{
            DBUtil.dbExecuteUpdate(sql);

        }catch (SQLException e){
            System.out.println("Exception occur while inserting the data" +e);
            e.printStackTrace();
            throw e;
        }
    }

    public static void removeStudent(int id) throws SQLException,ClassNotFoundException{
        String sql = "DELETE FROM Students WHERE id = '"+id+"'";

        try{
            DBUtil.dbExecuteUpdate(sql);

        }catch(SQLException e){
            System.out.println("Exception occur while deleting the data" +e);
            e.printStackTrace();
            throw e;
        }

    }

    public static ObservableList<Student> getAllRecords() throws SQLException,ClassNotFoundException{

        String sql = "SELECT * FROM Students";

        try{
            ResultSet rs = DBUtil.dbExecuteQuery(sql);
            ObservableList<Student> studentList = getStudentObjects(rs);
            return studentList;

        }catch(SQLException e){
            System.out.println("Error occured while fetching the records from the DB");
            e.printStackTrace();
            throw e;
        }

    }

    private static ObservableList<Student> getStudentObjects(ResultSet rs) throws SQLException,ClassNotFoundException {

        try{
            ObservableList<Student> stdList = FXCollections.observableArrayList();

            while(rs.next()){
                stdList.add(new Student(
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
                ));
            }

            return stdList;

        }catch(SQLException e){
            System.out.println("Error occured while fetching the data from the DB!" +e);
            e.printStackTrace();
            throw e;
        }

    }
}
