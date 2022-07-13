package dal.Impl;

import bo.Pays;
import dal.DALException;
import dal.dao.DAO;
import dal.settings.Settings;

import javax.persistence.EntityManager;
import java.util.List;

public class PaysImpl implements DAO<Pays> {
    EntityManager em = Settings.getProperty();

    @Override
    public void insert(Pays data) throws DALException {
        em.getTransaction().begin();
        em.persist(data);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Pays data) throws DALException {

    }

    @Override
    public void update(Pays data) throws DALException {

    }

    // getSingleResult() returns Acteur OU Exception (et pas null)
    @Override
    public Pays selectById(long id) throws DALException {
        List<Pays> paysList = em.createQuery("SELECT p FROM Pays p WHERE p.id=:id", Pays.class).setParameter("id", id).getResultList();
        if(!paysList.isEmpty()){
            return paysList.get(0);
        }
        return null;
    }

    @Override
    public List<Pays> selectAll() throws DALException {
        List<Pays> paysList = em.createQuery("SELECT p FROM Pays p", Pays.class).getResultList();
        if(!paysList.isEmpty()){
            return paysList;
        }
        return null;
    }
}
