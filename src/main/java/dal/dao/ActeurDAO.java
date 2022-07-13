package dal.dao;

import bo.Acteur;
import dal.DALException;

public interface ActeurDAO extends DAO<Acteur> {
    public Acteur selectByImdb(String idImdb) throws DALException;
}
