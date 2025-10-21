package com.ucc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ucc.Connection.DatabaseConnection;
import com.ucc.model.Actor;
import com.ucc.repository.ActorRepository;
import com.ucc.repository.IRepository;

public class Main {
    public static void main(String[] args) throws SQLException {
       
        try(Connection myConn = DatabaseConnection.getInstanceConnection()){
            IRepository actorRepository = new ActorRepository();

            // Save a new actor
            Actor actor = new Actor();
            actor.setActor_id(1000);
            actor.setFirst_name("JuanCode2");
            actor.setLast_name("HernandezCode2");
            actorRepository.save(actor);
            System.out.println("Actor saved: " + actor);

            // Find all actors
            System.out.println("\n--- All Actors ---");
            actorRepository.findAll().forEach(System.out::println);

            // Get actor by ID
            System.out.println("\n--- Getting Actor by ID (1000) ---");
            Actor foundActor = actorRepository.getById(1000L);
            if (foundActor != null) {
                System.out.println("Found Actor: " + foundActor);
            } else {
                System.out.println("Actor with ID 1000 not found.");
            }

            // Update actor
            System.out.println("\n--- Updating Actor (1000) ---");
            if (foundActor != null) {
                foundActor.setFirst_name("JuanCodeUpdated");
                foundActor.setLast_name("HernandezUpdated");
                actorRepository.update(foundActor);
                System.out.println("Actor 1000 updated.");
            }
            System.out.println("Updated Actor: " + actorRepository.getById(1000L));


            // Delete actor
            System.out.println("\n--- Deleting Actor (1000) ---");
            actorRepository.delete(1000L);
            System.out.println("Actor 1000 deleted.");

            // Verify deletion
            System.out.println("\n--- Verifying Deletion ---");
            actorRepository.findAll().forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Conexion Fail");
            e.printStackTrace();
        } 
    }
}