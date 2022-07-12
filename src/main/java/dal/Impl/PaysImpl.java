package dal.Impl;

import bo.Pays;
import dal.DALException;
import dal.DAO;
import dal.Settings;

import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PaysImpl implements DAO<Pays> {
    @Override
    public void insert(Pays data) throws DALException {

    }

    @Override
    public void delete(Pays data) throws DALException {

    }

    @Override
    public void update(Pays data) throws DALException {

    }

    @Override
    public Pays selectById(long id) throws DALException {
        return null;
    }

    @Override
    public List<Pays> selectAll() throws DALException {
        ResultSet rs;
        List<Pays> paysList = new ArrayList<>();
        try{
            TypedQuery<Pays> selectAll = Settings.getProperty().createQuery("SELECT p FROM Pays p", Pays.class);
            paysList = selectAll.getResultList();
        } catch (DALException e) {
            throw new DALException("Problème lors de la récupération de la liste des pays");
        }
        return paysList;
    }
}
