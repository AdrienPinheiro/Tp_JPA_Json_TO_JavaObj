package dal.Impl;

import bo.Acteur;
import bo.Film;

import dal.dao.ActeurDAO;
import dal.settings.Settings;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 * All call BDD for actor object
 */
public class ActeurImpl implements ActeurDAO {

    EntityManager em = Settings.getProperty();

    /**
     * Persist actor on BDD
     * @param acteur actor object
     */
    @Override
    public void insert(Acteur acteur) {
        em.getTransaction().begin();
        em.persist(acteur);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Acteur data) {

    }

    @Override
    public void update(Acteur data) {

    }

    /**
     * Take actor with id on BDD
     * @param id actor id
     * @return Acteur object
     */
    @Override
    public Acteur selectById(long id) {
        List<Acteur> acteurList = em.createQuery("SELECT a FROM Acteur a WHERE a.id=:id", Acteur.class).setParameter("id", id).getResultList();
        if(!acteurList.isEmpty()){
            return acteurList.get(0);
        }
        return null;
    }

    /**
     * Take all actor on BDD
     * @return List actor object
     */
    @Override
    public List<Acteur> selectAll() {
        List<Acteur> acteurList = em.createQuery("SELECT a FROM Acteur a", Acteur.class).getResultList();
        if(!acteurList.isEmpty()){
            return acteurList;
        }
        return null;
    }

    /**
     * Select actor with id_imbd on BDD
     * @param idJson actor JSON id
     * @return Acteur object
     */
    @Override
    public Acteur selectByImdb(String idJson){
        try{
            return em.createQuery("SELECT a FROM Acteur a WHERE a.idImdb=:idJson", Acteur.class).setParameter("idJson", idJson).getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    /**
     * Take all actors on casting of film on BDD
     * @param title film title for search actor on this
     * @return List actor object
     */
    @Override
    public List<Acteur> castingFilm(String title){
        try {
            Film films = em.createQuery("SELECT f FROM Film f WHERE f.title=:title", Film.class).setParameter("title", title).getSingleResult();
            return new ArrayList<>(films.getCastingPrincipals());
        } catch (NoResultException e){
            return null;
        }
    }

    /**
     * Tale all same actor on two different film on BDD
     * @param filmOne
     * @param filmTwo
     * @return List actor object
     */
    @Override
    public List<Acteur> selectActeurFilm(String filmOne, String filmTwo){
        try {
            return em.createQuery("SELECT a FROM Acteur a JOIN a.films f WHERE f.title=:filmOne AND a.id IN (SELECT a.id FROM Acteur a JOIN a.films f WHERE f.title=:filmTwo)").setParameter("filmOne", filmOne).setParameter("filmTwo", filmTwo).getResultList();
        } catch (NoResultException e){
            return null;
        }
    }
}
