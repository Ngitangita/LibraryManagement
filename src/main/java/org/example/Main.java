package org.example;

import org.example.Config.ConnectionDB;
import org.example.Repository.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
       try (Connection connectionDB = (Connection) DriverManager.getConnection ( "jdbc:mysql://localhost:3306/lobrary_management","prog_admin", "123456" )) {
           AuthorCrudOperations authorCrudOperations = new AuthorCrudOperations ( connectionDB );
           BookCrudOperations bookCrudOperations = new BookCrudOperations ( connectionDB );
           TopicCrudOperations topicCrudOperations = new TopicCrudOperations ( connectionDB );
           SubscriberCrudOperations subscriberCrudOperations = new SubscriberCrudOperations ( connectionDB );
           AdministratorCrudOperations administratorCrudOperations = new AdministratorCrudOperations ( connectionDB );

           authorCrudOperations.findAll ();
           bookCrudOperations.findAll ();
           topicCrudOperations.findAll ();
           subscriberCrudOperations.findAll ();
           administratorCrudOperations.findAll ();

       } catch ( SQLException e ) {
           throw new RuntimeException ( e );
       }

    }
}
