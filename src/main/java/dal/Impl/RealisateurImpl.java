package dal.Impl;

import bo.Realisateur;
import dal.DALException;
import dal.DAO;
import dal.Settings;

import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RealisateurImpl implements DAO<Realisateur> {
    @Override
    public void insert(Realisateur data) throws DALException {
        try{
            Settings.getProperty().getTransaction().begin();
            Settings.getProperty().persist(data);
            Settings.getProperty().getTransaction().commit();
        } catch (DALException e) {
            throw new DALException("Erreur lors de l'insertion d'un réalisateur");
        }
    }

    @Override
    public void delete(Realisateur data) throws DALException {

    }

    @Override
    public void update(Realisateur data) throws DALException {

    }

    @Override
    public Realisateur selectById(long id) throws DALException {
        return null;
    }

    @Override
    public List<Realisateur> selectAll() throws DALException {
        ResultSet rs;
        List<Realisateur> realisateurList = new ArrayList<>();
        try{
            TypedQuery<Realisateur> selectAll = Settings.getProperty().createQuery("SELECT r FROM Realisateur r", Realisateur.class);
            realisateurList = selectAll.getResultList();
        } catch (DALException e) {
            throw new DALException("Problème lors de la récupération de la liste de réalisateur");
        }
        return realisateurList;
    }
}
