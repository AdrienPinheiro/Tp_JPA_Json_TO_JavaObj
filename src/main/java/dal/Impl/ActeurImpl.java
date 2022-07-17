package dal.Impl;

import bo.Acteur;
import bo.Film;

import dal.dao.ActeurDAO;
import dal.settings.Settings;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class ActeurImpl implements ActeurDAO {

    EntityManager em = Settings.getProperty();

    /**
     * @param acteur
     * Persist actor on BDD
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
     * @param id
     * @return Acteur
     * Take actor with id on BDD
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
     * @return List<Acteur>
     * Take all actor on BDD
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
     * @param idJson
     * @return Acteur
     * Select actor with id_imbd on BDD
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
     * @param title
     * @return List<Acteur>
     * Take all actors on casting of film on BDD
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
}
