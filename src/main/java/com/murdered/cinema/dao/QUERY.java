package com.murdered.cinema.dao;

public enum QUERY {
    SQL_FIND_ALL_FILMS("SELECT * FROM film"),
    SQL_FIND_FILM_BY_ID("SELECT * FROM film WHERE id = ?"),
    SQL_ADD_NEW_FILM("INSERT INTO film (title, description, genre, duration, imdb_rating) VALUES (?,?,?,?,?)"),
    SQL_DELETE_FILM_BY_ID("DELETE FROM film WHERE id = ?"),

    SQL_FIND_ALL_SESSIONS_ORDER_BY_DATE("SELECT * FROM session ORDER BY time desc"),
    SQL_INSERT_SESSION("INSERT INTO session (film_id, time, price, available_places) VALUES(?, ?, ?, ?)"),
    SQL_FIND_SESSION_BY_ID("SELECT * FROM session WHERE id = ?"),
    SQL_FIND_SESSION_AFTER("SELECT * FROM session WHERE time > ? ORDER BY time"),
    SQL_DELETE_SESSION_BY_ID("DELETE FROM session WHERE id = ?"),


    SQL_INSERT_USER("INSERT INTO user(email, password, login, role_id) VALUES(?, ?, ?, ?)"),
    SQL_FIND_USER_BY_LOGIN_PASS("SELECT * FROM user WHERE login = ? AND password = ?"),
    SQL_FIND_USER_BY_LOGIN("SELECT * FROM user WHERE login = ?"),
    SQL_FIND_USER_BY_EMAIL("SELECT * FROM user WHERE email = ?"),
    SQL_FIND_USER_BY_ID("SELECT * FROM user WHERE id = ?"),
    SQL_FIND_ALL_USERS("SELECT * FROM user"),
    SQL_DELETE_USER_BY_ID("DELETE from user WHERE id = ?"),

    SQL_FIND_NUM_OF_TICKETS_ON_SESSION("SELECT COUNT(1) FROM ticket WHERE session_id = ?"),
    SQL_INSERT_TICKET("INSERT INTO ticket (user_id, session_id, session_film_id) VALUES(?, ?, ?)"),
    SQL_FIND_TICKET_BY_USER("SELECT * FROM ticket WHERE user_id = ?"),
    SQL_DELETE_TICKET_BY_ID("DELETE FROM ticket WHERE id = ?"),
    SQL_FIND_TICKET_BY_ID("SELECT * FROM ticket WHERE id = ?"),
    SQL_FIND_ALL_TICKETS("SELECT * FROM ticket");


    private final String query;

    QUERY(String query) {
        this.query = query;
    }

    public String query(){
        return query;
    }
}
