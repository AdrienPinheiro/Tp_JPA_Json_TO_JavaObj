package dal.dao;

import bo.Film;
import dal.DALException;

import java.util.List;

/**
 * Additionnal Film method for calling BDD
 */
public interface FilmDAO extends DAO<Film>{
    public Film selectByImdb(String idImdb) throws DALException;
    public List<Film> selectActeurFilm(String identity) throws DALException;
    public List<Film> selectFilmBetweenYear(int startYear, int endYear) throws DALException;
    public List<Film> selectFilmTwoActeur(String firstActeur, String secondActeur) throws DALException;
    public List<Film> selectFilmBetweenYearWithActeur(String startYear, String endYear, String acteur) throws DALException;
}
