package dal.Impl;

import bo.Realisateur;
import dal.DALException;
import dal.dao.DAO;
import dal.settings.Settings;

import javax.persistence.EntityManager;
import java.util.List;

public class RealisateurImpl implements DAO<Realisateur> {
    EntityManager em = Settings.getProperty();

    @Override
    public void insert(Realisateur data) throws DALException {
        em.getTransaction().begin();
        em.persist(data);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Realisateur data) throws DALException {

    }

    @Override
    public void update(Realisateur data) throws DALException {

    }

    // getSingleResult() returns Acteur OU Exception (et pas null)
    @Override
    public Realisateur selectById(long id) throws DALException {
        List<Realisateur> realisateurList = em.createQuery("SELECT r FROM Realisateur r WHERE r.id=:id", Realisateur.class).setParameter("id", id).getResultList();
        if(!realisateurList.isEmpty()){
            return realisateurList.get(0);
        }
        return null;
    }

    @Override
    public List<Realisateur> selectAll() throws DALException {
        List<Realisateur> realisateurList = em.createQuery("SELECT r FROM Realisateur r", Realisateur.class).getResultList();
        if(!realisateurList.isEmpty()){
            return realisateurList;
        }
        return null;
    }
}
