package com.murdered.cinema.service.film;

import com.murdered.cinema.dao.film.FilmDao;
import com.murdered.cinema.model.Film;
import org.apache.log4j.Logger;

import java.util.List;

public class FilmService {
    private static Logger logger = Logger.getLogger(FilmService.class);
    private final FilmDao filmDao;

    public FilmService(FilmDao filmDao){
        this.filmDao = filmDao;
    }

    public List<Film> getAllFilms(){
        logger.info("Getting all films");
        return filmDao.getAll();
    }

    public void add(Film newFilm) {
        logger.info("Adding new film");
        filmDao.save(newFilm);
    }

    public void delete(int id){
        logger.info("Deleting film");
        filmDao.delete(id);
    }

    public Film getFilmById(long id){
        logger.info("Getting film by id");
        return filmDao.get(id);
    }

}
