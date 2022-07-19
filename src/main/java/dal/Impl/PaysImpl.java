package dal.Impl;

import bo.Pays;
import dal.DALException;
import dal.dao.DAO;
import dal.settings.Settings;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * All call BDD for film object
 */
public class PaysImpl implements DAO<Pays> {
    EntityManager em = Settings.getProperty();

    /**
     * Persist country on BDD
     * @param data country object
     */
    @Override
    public void insert(Pays data) {
        em.getTransaction().begin();
        em.persist(data);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Pays data) {

    }

    @Override
    public void update(Pays data) {

    }

    /**
     * Take one country with id on BDD
     * @param id country id
     * @return Pays object
     */
    @Override
    public Pays selectById(long id) {
        List<Pays> paysList = em.createQuery("SELECT p FROM Pays p WHERE p.id=:id", Pays.class).setParameter("id", id).getResultList();
        if(!paysList.isEmpty()){
            return paysList.get(0);
        }
        return null;
    }

    /**
     * Take all country on BDD
     * @return List country object
     */
    @Override
    public List<Pays> selectAll(){
        List<Pays> paysList = em.createQuery("SELECT p FROM Pays p", Pays.class).getResultList();
        if(!paysList.isEmpty()){
            return paysList;
        }
        return null;
    }
}
