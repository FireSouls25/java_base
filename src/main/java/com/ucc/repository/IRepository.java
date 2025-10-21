package com.ucc.repository;

import java.sql.SQLException;
import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxCrud.Update;
import com.ucc.model.Actor;

public interface IRepository {
    List<Actor> findAll() throws SQLException;
    Actor getById(Long id) throws SQLException;
    Actor save(Actor actor) throws SQLException;
    void update(Actor actor) throws SQLException;
    void delete(Long id) throws SQLException;
}
