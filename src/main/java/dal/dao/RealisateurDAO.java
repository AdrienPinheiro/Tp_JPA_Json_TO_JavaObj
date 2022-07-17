package dal.dao;

import bo.Realisateur;
import dal.DALException;

/**
 * Additionnal Realisator method for calling BDD
 */
public interface RealisateurDAO extends DAO<Realisateur> {
    public Realisateur selectByIdentity(String identity) throws DALException;
}
