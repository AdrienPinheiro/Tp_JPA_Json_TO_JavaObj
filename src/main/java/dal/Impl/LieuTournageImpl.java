package dal.Impl;

import bo.LieuTournage;
import dal.DALException;
import dal.dao.DAO;
import dal.settings.Settings;

import javax.persistence.EntityManager;
import java.util.List;

public class LieuTournageImpl implements DAO<LieuTournage> {
    EntityManager em = Settings.getProperty();

    @Override
    public void insert(LieuTournage data) throws DALException {
        em.getTransaction().begin();
        em.persist(data);
        em.getTransaction().commit();
    }

    @Override
    public void delete(LieuTournage data) throws DALException {

    }

    @Override
    public void update(LieuTournage data) throws DALException {

    }

    // getSingleResult() returns Acteur OU Exception (et pas null)
    @Override
    public LieuTournage selectById(long id) throws DALException {
        List<LieuTournage> lieuTournageList = em.createQuery("SELECT lt FROM LieuTournage lt WHERE lt.id=:id", LieuTournage.class).setParameter("id", id).getResultList();
        if(!lieuTournageList.isEmpty()){
            return lieuTournageList.get(0);
        }
        return null;
    }

    @Override
    public List<LieuTournage> selectAll() throws DALException {
        List<LieuTournage> lieuTournageList = em.createQuery("SELECT lt FROM LieuTournage lt", LieuTournage.class).getResultList();
        if(!lieuTournageList.isEmpty()){
            return lieuTournageList;
        }
        return null;
    }
}
