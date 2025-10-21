package com.ucc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ucc.Connection.DatabaseConnection;
import com.ucc.model.Actor;

public class ActorRepository implements IRepository{

    private Connection getConnection() throws SQLException{
        return DatabaseConnection.getInstanceConnection();
    }


    @Override
    public List<Actor> findAll() throws SQLException{
        List<Actor> actors = new ArrayList<>();
        try (Statement myStat = getConnection().createStatement();
            ResultSet myRes= myStat.executeQuery("Select * from sakila.actor")) {
            while (myRes.next()) {
                Actor newActor = new Actor();
                newActor.setActor_id(myRes.getInt("actor_id"));
                newActor.setFirst_name(myRes.getString("first_name"));
                newActor.setLast_name(myRes.getString("last_name"));
                actors.add(newActor);
            }
        } 
        return actors;
    }

    @Override
    public Actor save(Actor actor) throws SQLException {
        String sql = "INSERT INTO sakila.actor(actor_id,first_name,last_name) VALUES (?,?,?)";
        try(PreparedStatement myPrepare = getConnection().prepareStatement(sql);  ){
            myPrepare.setInt(1, actor.getActor_id() );
            myPrepare.setString(2, actor.getFirst_name() );
            myPrepare.setString(3,actor.getLast_name() );    
            myPrepare.executeUpdate();
        }
        return actor;
    }

    @Override
    public Actor getById(Long id) throws SQLException {
        Actor actor = null;
        String sql = "SELECT * FROM sakila.actor WHERE actor_id = ?";
        try (PreparedStatement myPrepare = getConnection().prepareStatement(sql)) {
            myPrepare.setLong(1, id);
            try (ResultSet myRes = myPrepare.executeQuery()) {
                if (myRes.next()) {
                    actor = new Actor();
                    actor.setActor_id(myRes.getInt("actor_id"));
                    actor.setFirst_name(myRes.getString("first_name"));
                    actor.setLast_name(myRes.getString("last_name"));
                }
            }
        }
        return actor;
    }

    @Override
    public void update(Actor actor) throws SQLException {
        String sql = "UPDATE sakila.actor SET first_name = ?, last_name = ? WHERE actor_id = ?";
        try (PreparedStatement myPrepare = getConnection().prepareStatement(sql)) {
            myPrepare.setString(1, actor.getFirst_name());
            myPrepare.setString(2, actor.getLast_name());
            myPrepare.setInt(3, actor.getActor_id());
            myPrepare.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM sakila.actor WHERE actor_id = ?";
        try (PreparedStatement myPrepare = getConnection().prepareStatement(sql)) {
            myPrepare.setLong(1, id);
            myPrepare.executeUpdate();
        }
    }
    
}
