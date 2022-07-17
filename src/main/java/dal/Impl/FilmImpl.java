package dal.Impl;

import bo.Film;
import dal.dao.FilmDAO;
import dal.settings.Settings;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import java.util.List;

public class FilmImpl implements FilmDAO {
    EntityManager em = Settings.getProperty();

    /**
     * @param film
     * Persist film on BDD
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
     * @param id
     * @return Film
     * Take one film with id on BDD
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
     * @return List<Film>
     * Take all film on BDD
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
     * @param idJson
     * @return Film
     * Take one film with id_imdb on BDD
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
     * @param identity
     * @return List<Film>
     * Take all film of one actor on BDD
     */
    @Override
    public List<Film> selectActeurFilm(String identity) {
        try{
            return em.createQuery("SELECT f FROM Film f JOIN Acteur a WHERE a.identity=:identity", Film.class).setParameter("identity", identity).getResultList();
        } catch (NoResultException e){
            return null;
        }
    }

    /**
     * @param startYear
     * @param endYear
     * @return List<Film>
     * Take all film between two years on BDD
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
     * @param firstActeur
     * @param secondActeur
     * @return List<Film>
     * Take all film who have 2 same actors on BDD
     */
    @Override
    public List<Film> selectFilmTwoActeur(String firstActeur, String secondActeur){
        try{
            return em.createQuery("SELECT f FROM Film f WHERE f.acteurFilms=:firstActeur AND f.acteurFilms=:secondActeur", Film.class).setParameter("firstActeur", firstActeur).setParameter("secondActeur", secondActeur).getResultList();
        } catch (NoResultException e){
            return null;
        }
    }

    /**
     * @param startYear
     * @param endYear
     * @param acteur
     * @return List<Film>
     * Tale all film who have 2 same actors and between 2 years on BDD
     */
    @Override
    public List<Film> selectFilmBetweenYearAndWithTwoActeur(int startYear, int endYear, String acteur){
        try{
            return em.createQuery("SELECT f FROM Film f JOIN Acteur a WHERE f.acteurFilms=:acteur AND f.releaseYear BETWEEN :startYear AND :endYear", Film.class).setParameter("acteur", acteur).setParameter("startYear", startYear).setParameter("endYear", endYear).getResultList();
        } catch (NoResultException e){
            return null;
        }
    }
}
