package dal.Impl;

import bo.Acteur;
import dal.DALException;
import dal.dao.ActeurDAO;
import dal.settings.Settings;

import javax.persistence.EntityManager;
import java.util.List;

public class ActeurImpl implements ActeurDAO {

    EntityManager em = Settings.getProperty();

    @Override
    public void insert(Acteur data) throws DALException {
        em.getTransaction().begin();
        em.persist(data);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Acteur data) throws DALException {

    }

    @Override
    public void update(Acteur data) throws DALException {

    }

    // getSingleResult() returns Acteur OU Exception (et pas null)
    @Override
    public Acteur selectById(long id) throws DALException {
        List<Acteur> acteurList = em.createQuery("SELECT a FROM Acteur a WHERE a.id=:id", Acteur.class).setParameter("id", id).getResultList();
        if(!acteurList.isEmpty()){
            return acteurList.get(0);
        }
        return null;
    }

    @Override
    public List<Acteur> selectAll() throws DALException {
        List<Acteur> acteurList = em.createQuery("SELECT a FROM Acteur a", Acteur.class).getResultList();
        if(!acteurList.isEmpty()){
            return acteurList;
        }
        return null;
    }

    public Acteur selectByImdb(String idJson) throws DALException {
        List<Acteur> acteurList = em.createQuery("SELECT a FROM Acteur a WHERE a.identity=:idJson", Acteur.class).setParameter("idJson", idJson).getResultList();
        if(!acteurList.isEmpty()){
            return acteurList.get(0);
        }
        return null;
    }
}
