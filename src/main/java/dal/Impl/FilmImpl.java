package dal.Impl;

import bo.Acteur;
import bo.Film;
import dal.dao.FilmDAO;
import dal.settings.Settings;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import java.util.List;
import java.util.Set;

/**
 * All call BDD for film object
 */
public class FilmImpl implements FilmDAO {
    EntityManager em = Settings.getProperty();

    /**
     * Persist film on BDD
     * @param film film object
     */
    @Override
    public void insert(Film film){
        em.getTransaction().begin();
        em.persist(film);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Film data) {

    }

    @Override
    public void update(Film data){

    }

    /**
     * Take one film with id on BDD
     * @param id film id
     * @return Film object
     */
    @Override
    public Film selectById(long id) {
        List<Film> filmList = em.createQuery("SELECT f FROM Film f WHERE f.id=:id", Film.class).setParameter("id", id).getResultList();
        if(!filmList.isEmpty()){
            return filmList.get(0);
        }
        return null;
    }

    /**
     * Take all film on BDD
     * @return List film object
     */
    @Override
    public List<Film> selectAll() {
        List<Film> filmList = em.createQuery("SELECT f FROM Film f", Film.class).getResultList();
        if(!filmList.isEmpty()){
            return filmList;
        }
        return null;
    }

    /**
     * Take one film with id_imdb on BDD
     * @param idJson film JSON id
     * @return Film object
     */
    @Override
    public Film selectByImdb(String idJson){
        try{
            return em.createQuery("SELECT f FROM Film f WHERE f.idImdb=:idJson", Film.class).setParameter("idJson", idJson).getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    /**
     * Take all film of one actor on BDD
     * @param identity film identity
     * @return List film object
     */
    @Override
    public List<Film> selectActeurFilm(String identity) {
        try{
            return em.createQuery("SELECT f FROM Film f JOIN f.acteurFilms a WHERE a.identity=:identity", Film.class).setParameter("identity", identity).getResultList();
        } catch (NoResultException e){
            return null;
        }
    }

    /**
     * Take all film between two years on BDD
     * @param startYear start year
     * @param endYear end year
     * @return List film object
     */
    @Override
    public List<Film> selectFilmBetweenYear(int startYear, int endYear){
        try{
            return em.createQuery("SELECT f FROM Film f WHERE f.releaseYear BETWEEN :startYear AND :endYear", Film.class).setParameter("startYear", String.valueOf(startYear)).setParameter("endYear", String.valueOf(endYear)).getResultList();
        } catch (NoResultException e){
            return null;
        }
    }

    /**
     * Take all film who have 2 same actors on BDD
     * @param firstActeur the name of first actor
     * @param secondActeur the name of second actor
     * @return List film object
     */
    @Override
    public List<Film> selectFilmTwoActeur(String firstActeur, String secondActeur){
        try{
            return em.createQuery("SELECT f FROM Film f JOIN f.acteurFilms a WHERE a.identity=:firstActeur AND f.id IN (SELECT f.id FROM Film f JOIN f.acteurFilms a WHERE a.identity=:secondActeur)", Film.class).setParameter("firstActeur", firstActeur).setParameter("secondActeur", secondActeur).getResultList();
        } catch (NoResultException e){
            return null;
        }
    }

    /**
     * Take all film who have 2 same actors and between 2 years on BDD
     * @param startYear start year
     * @param endYear end year
     * @param acteur actor name
     * @return List film object
     */
    @Override
    public List<Film> selectFilmBetweenYearWithActeur(String startYear, String endYear, String acteur) {
        try {
            return em.createQuery("SELECT f FROM Film f JOIN f.acteurFilms a WHERE a.identity=:acteur AND f.id IN (SELECT f.id FROM Film f WHERE f.releaseYear BETWEEN :startYear AND :endYear)", Film.class).setParameter("acteur", acteur).setParameter("startYear", startYear).setParameter("endYear", endYear).getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
