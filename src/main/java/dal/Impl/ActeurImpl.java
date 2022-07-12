package dal.Impl;

import bo.Acteur;
import dal.DALException;
import dal.DAO;
import dal.Settings;

import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ActeurImpl implements DAO<Acteur> {
    @Override
    public void insert(Acteur data) throws DALException {
        try{
            Settings.getProperty().getTransaction().begin();
            Settings.getProperty().persist(data);
            Settings.getProperty().getTransaction().commit();
        } catch (DALException e) {
            throw new DALException("Erreur lors de l'insertion d'un acteur");
        }
    }

    @Override
    public void delete(Acteur data) throws DALException {

    }

    @Override
    public void update(Acteur data) throws DALException {

    }

    @Override
    public Acteur selectById(long id) throws DALException {
        Acteur acteur;
        try{
            TypedQuery<Acteur> selectById = Settings.getProperty().createQuery("SELECT a FROM Acteur a WHERE a.id=?", Acteur.class).setParameter(0, id);
            acteur = selectById.getSingleResult();
        } catch (DALException e){
            throw new DALException("Problème lors de la récupération d'un acteur - id = "+id, e.getCause());
        }
        return acteur;
    }

    @Override
    public List<Acteur> selectAll() throws DALException {
        ResultSet rs;
        List<Acteur> acteurList = new ArrayList<>();
        try{
            TypedQuery<Acteur> selectAll = Settings.getProperty().createQuery("SELECT a FROM Acteur a", Acteur.class);
            acteurList = selectAll.getResultList();
        } catch (DALException e) {
            throw new DALException("Problème lors de la récupération de la liste d'acteur");
        }
        return acteurList;
    }
}
