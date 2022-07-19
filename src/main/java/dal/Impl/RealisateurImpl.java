package dal.Impl;

import bo.Realisateur;
import dal.DALException;

import dal.dao.RealisateurDAO;
import dal.settings.Settings;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * All call BDD for realisator object
 */
public class RealisateurImpl implements RealisateurDAO {
    EntityManager em = Settings.getProperty();

    /**
     * Persist realisator on BDD
     * @param data realisator object
     */
    @Override
    public void insert(Realisateur data) {
        em.getTransaction().begin();
        em.persist(data);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Realisateur data) {

    }

    @Override
    public void update(Realisateur data) {

    }

    /**
     * Take one realisator with id on BDD
     * @param id realisator id
     * @return Realisateur object
     */
    @Override
    public Realisateur selectById(long id) {
        List<Realisateur> realisateurList = em.createQuery("SELECT r FROM Realisateur r WHERE r.id=:id", Realisateur.class).setParameter("id", id).getResultList();
        if(!realisateurList.isEmpty()){
            return realisateurList.get(0);
        }
        return null;
    }

    /**
     * Take all realisator on BDD
     * @return List realisator object
     */
    @Override
    public List<Realisateur> selectAll() {
        List<Realisateur> realisateurList = em.createQuery("SELECT r FROM Realisateur r", Realisateur.class).getResultList();
        if(!realisateurList.isEmpty()){
            return realisateurList;
        }
        return null;
    }

    /**
     * Take one realisateur with identity on BDD
     * @param identity realisator identity
     * @return Realisateur object
     */
    @Override
    public Realisateur selectByIdentity(String identity) {
        try{
            return em.createQuery("SELECT r FROM Realisateur r WHERE r.identity=:identity", Realisateur.class).setParameter("identity", identity).getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }
}
