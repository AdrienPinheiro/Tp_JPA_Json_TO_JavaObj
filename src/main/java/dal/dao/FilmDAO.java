package dal.dao;

import bo.Acteur;
import bo.Film;
import dal.DALException;

import java.util.List;

public interface FilmDAO extends DAO<Film>{
    public List<Acteur> getActeursFilms() throws DALException;
}
