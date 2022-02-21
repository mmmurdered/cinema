package com.murdered.cinema.service.film;

import com.murdered.cinema.dao.film.FilmDao;
import com.murdered.cinema.model.Film;

import java.util.List;

public class FilmService {
    private final FilmDao filmDao;

    public FilmService(FilmDao filmDao){
        this.filmDao = filmDao;
    }

    public List<Film> getAllFilms(){
        return filmDao.getAll();
    }

    public void add(Film newFilm) {
        filmDao.save(newFilm);
    }

    public void delete(int id){
        filmDao.delete(id);
    }

    public Film getFilmById(long id){
        return filmDao.get(id);
    }

}
