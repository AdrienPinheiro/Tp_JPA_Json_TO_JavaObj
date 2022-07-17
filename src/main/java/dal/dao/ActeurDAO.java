package dal.dao;

import bo.Acteur;
import dal.DALException;

import java.util.List;

/**
 * Additionnal Actor method for calling BDD
 */
public interface ActeurDAO extends DAO<Acteur> {
    public Acteur selectByImdb(String idImdb) throws DALException;
    public List<Acteur> castingFilm(String title) throws DALException;
}
