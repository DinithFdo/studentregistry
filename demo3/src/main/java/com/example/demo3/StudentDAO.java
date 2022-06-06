package com.example.demo3;

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
}
