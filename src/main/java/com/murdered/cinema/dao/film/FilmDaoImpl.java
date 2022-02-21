package com.murdered.cinema.dao.film;

import com.murdered.cinema.connection.BasicConnectionPool;
import com.murdered.cinema.dao.QUERY;
import com.murdered.cinema.model.Film;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.murdered.cinema.dao.QUERY.SQL_FIND_ALL_FILMS;

public class FilmDaoImpl implements FilmDao{

    private static FilmDaoImpl instance;
    private Connection connection;
    private BasicConnectionPool basicConnectionPool;

    private FilmDaoImpl(){
        try {
            basicConnectionPool = BasicConnectionPool.create();
            connection = basicConnectionPool.getConnection();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static FilmDaoImpl getInstance(){
        if(instance == null){
            instance = new FilmDaoImpl();
        }
        return instance;
    }

    @Override
    public Film save(Film film) {
        try{
            PreparedStatement statement = connection.prepareStatement(QUERY.SQL_ADD_NEW_FILM.query());

            statement.setString(1, film.getTitle());
            statement.setString(2, film.getDescription());
            statement.setString(3, film.getGenre());
            statement.setInt(4, film.getDuration());
            statement.setDouble(5, film.getImdbRating());

            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
        //todo handling exceptions (affected rows)
        return film;
    }

    @Override
    public void update(Film film, String[] params) {

    }

    @Override
    public Film get(long id) {
        Film newFilm = new Film();

        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.SQL_FIND_FILM_BY_ID.query());
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                newFilm.setId(resultSet.getInt(1));
                newFilm.setTitle(resultSet.getString(2));
                newFilm.setDescription(resultSet.getString(3));
                newFilm.setGenre(resultSet.getString(4));
                newFilm.setDuration(resultSet.getInt(5));
                newFilm.setImdbRating(resultSet.getDouble(6));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return newFilm;
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.SQL_DELETE_FILM_BY_ID.query());

            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Film> getAll() {
        List<Film> filmList = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(QUERY.SQL_FIND_ALL_FILMS.query());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Film tempFilm = new Film();

                tempFilm.setId(resultSet.getInt(1));
                tempFilm.setTitle(resultSet.getString(2));
                tempFilm.setDescription(resultSet.getString(3));
                tempFilm.setGenre(resultSet.getString(4));
                tempFilm.setDuration(resultSet.getInt(5));
                tempFilm.setImdbRating(resultSet.getDouble(6));

                filmList.add(tempFilm);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return filmList;
    }
}
