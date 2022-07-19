package dal.Impl;

import bo.LieuTournage;
import dal.DALException;
import dal.dao.DAO;
import dal.settings.Settings;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * All call BDD for filming location object
 */
public class LieuTournageImpl implements DAO<LieuTournage> {
    EntityManager em = Settings.getProperty();

    /**
     * Persist Filming location on BDD
     * @param data filming location object
     */
    @Override
    public void insert(LieuTournage data) {
        em.getTransaction().begin();
        em.persist(data);
        em.getTransaction().commit();
    }

    @Override
    public void delete(LieuTournage data) {

    }

    @Override
    public void update(LieuTournage data) {

    }

    /**
     * Take one filming location with id on BDD
     * @param id filming location id
     * @return LieuTournage object
     */
    @Override
    public LieuTournage selectById(long id) {
        List<LieuTournage> lieuTournageList = em.createQuery("SELECT lt FROM LieuTournage lt WHERE lt.id=:id", LieuTournage.class).setParameter("id", id).getResultList();
        if(!lieuTournageList.isEmpty()){
            return lieuTournageList.get(0);
        }
        return null;
    }

    /**
     * Take all filming location on BDD
     * @return List filming location object
     */
    @Override
    public List<LieuTournage> selectAll() {
        List<LieuTournage> lieuTournageList = em.createQuery("SELECT lt FROM LieuTournage lt", LieuTournage.class).getResultList();
        if(!lieuTournageList.isEmpty()){
            return lieuTournageList;
        }
        return null;
    }
}
