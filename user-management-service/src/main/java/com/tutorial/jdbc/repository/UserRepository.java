package com.tutorial.jdbc.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tutorial.jdbc.model.User;
import com.tutorial.jdbc.util.DbOperationLogger;

@Repository
public class UserRepository {

    private final DbOperationLogger dbOperationLogger;
    private final JdbcTemplate jdbcTemplate;

    UserRepository(DbOperationLogger dbOperationLogger, JdbcTemplate jdbcTemplate) {
        this.dbOperationLogger = dbOperationLogger;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> fetchUsers() {
        dbOperationLogger.log("GET_ALL");

        String query = "SELECT * FROM users;";

        List<User> result = jdbcTemplate.query(query, (row, rowNum) -> new User(
            row.getLong("id"),
            row.getString("name"),
            row.getString("email"),
            row.getBoolean("active")
        ));

        return new ArrayList<User>(result);
    }

    public User getUserById(Long id) {
        dbOperationLogger.log("GET_BY_ID");

        String query = "SELECT * FROM users WHERE id = ?;";

        User result = jdbcTemplate.queryForObject(query, (row, rowNum) -> new User(
            row.getLong("id"),
            row.getString("name"),
            row.getString("email"),
            row.getBoolean("active")
        ), id);

         return new User(result.getId(), result.getName(), result.getEmail(), result.getActive());
    }

    public int updateUserActiveStatus(boolean active, Long id) {
        dbOperationLogger.log("UPDATE_USER");

        String query = "UPDATE users SET active = ? WHERE id = ?;";

        int rows = jdbcTemplate.update(query, active, id);

        return rows;
    }
}
