package dal.Impl;

import bo.Acteur;
import bo.Film;
import dal.DALException;
import dal.dao.DAO;
import dal.dao.FilmDAO;
import dal.settings.Settings;

import javax.persistence.EntityManager;
import java.util.List;

public class FilmImpl implements FilmDAO {
    EntityManager em = Settings.getProperty();

    @Override
    public void insert(Film data) throws DALException {
        em.getTransaction().begin();
        em.persist(data);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Film data) throws DALException {

    }

    @Override
    public void update(Film data) throws DALException {

    }

    // getSingleResult() returns Acteur OU Exception (et pas null)
    @Override
    public Film selectById(long id) throws DALException {
        List<Film> filmList = em.createQuery("SELECT f FROM Film f WHERE f.id=:id", Film.class).setParameter("id", id).getResultList();
        if(!filmList.isEmpty()){
            return filmList.get(0);
        }
        return null;
    }

    @Override
    public List<Film> selectAll() throws DALException {
        List<Film> filmList = em.createQuery("SELECT f FROM Film f", Film.class).getResultList();
        if(!filmList.isEmpty()){
            return filmList;
        }
        return null;
    }

    @Override
    public List<Acteur> getActeursFilms() throws DALException {
        return null;
    }
}
