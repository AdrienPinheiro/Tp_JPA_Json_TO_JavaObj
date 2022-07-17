package dal.Impl;

import bo.Realisateur;
import dal.DALException;

import dal.dao.RealisateurDAO;
import dal.settings.Settings;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class RealisateurImpl implements RealisateurDAO {
    EntityManager em = Settings.getProperty();

    /**
     * @param data
     * @throws DALException
     * Persist realisator on BDD
     */
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

    /**
     * @param id
     * @return Realisateur
     * @throws DALException
     * Take one realisator with id on BDD
     */
    // getSingleResult() returns Acteur OU Exception (et pas null)
    @Override
    public Realisateur selectById(long id) throws DALException {
        List<Realisateur> realisateurList = em.createQuery("SELECT r FROM Realisateur r WHERE r.id=:id", Realisateur.class).setParameter("id", id).getResultList();
        if(!realisateurList.isEmpty()){
            return realisateurList.get(0);
        }
        return null;
    }

    /**
     * @return List<Realisateur>
     * @throws DALException
     * Take all realisator on BDD
     */
    @Override
    public List<Realisateur> selectAll() throws DALException {
        List<Realisateur> realisateurList = em.createQuery("SELECT r FROM Realisateur r", Realisateur.class).getResultList();
        if(!realisateurList.isEmpty()){
            return realisateurList;
        }
        return null;
    }

    /**
     * @param identity
     * @return Realisateur
     * @throws DALException
     * Take one realisateur with identity on BDD
     */
    @Override
    public Realisateur selectByIdentity(String identity) throws DALException {
        try{
            return em.createQuery("SELECT r FROM Realisateur r WHERE r.identity=:identity", Realisateur.class).setParameter("identity", identity).getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }
}
