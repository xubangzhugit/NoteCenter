package javaNote.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Jdbc {
    public static void main(String[] args){
        String JDBC_URL = "jdbc:mysql://localhost:3306/test";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "password";
        try(Connection connection = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD);){
            try(PreparedStatement preparedStatement = connection.prepareStatement("");){
                try(ResultSet resultSet = preparedStatement.executeQuery();){
//                    connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                    while (resultSet.next()){
                        resultSet.getInt(1);
                        resultSet.getString(2);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }finally{

                }
            }catch(Exception e){
                e.printStackTrace();
            }finally{

            }
           new ArrayList<>().stream().forEach(System.out::println);
        }catch(Exception e){
            e.printStackTrace();
        }finally{

        }
    }
}
