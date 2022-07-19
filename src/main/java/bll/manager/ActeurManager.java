package bll.manager;

import bll.BLLException;
import bo.Acteur;

import dal.dao.ActeurDAO;
import dal.DALException;
import dal.dao.DAOFactory;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Call with control parameters the Implementation dal
 */
public class ActeurManager {
    private static volatile ActeurManager instance;
    private static ActeurDAO impl;
    private ActeurManager(){
        impl = DAOFactory.getAuteurDAO();
    }
    public static ActeurManager getInstance(){
        if(instance == null){
            synchronized (ActeurManager.class){
                if(instance == null){
                    instance = new ActeurManager();
                }
            }
        }
        return instance;
    }

    /**
     * Verification if actor identity is not null and call selectByImdb is not null too
     * @param acteur the actor object
     * @throws BLLException
     * BllException error
     */
    public void addActeur(Acteur acteur) throws BLLException{
        controlActeur(acteur);
        try{
            //List<Acteur> listActeur = FilmManager.getInstance().getFilmsActeurs();
            if(acteur.getIdentity() != null && impl.selectByImdb(acteur.getIdentity()) == null) {
                impl.insert(acteur);
            }
        } catch (DALException e){
            throw new BLLException("Erreur lors de l'ajout d'un acteur", e.getCause());
        }
    }

    /**
     * Try to call select one acteur by id method on ActeurImpl
     * @param id the actor id
     * @return Acteur
     * @throws BLLException
     * BllException error
     */
    public Acteur getOneActeur(long id) throws BLLException {
        Acteur acteur;
        try{
            acteur = impl.selectById(id);
        } catch (DALException e){
            throw new BLLException("Erreur lors de la récupération d'un acteur - Id = "+id, e.getCause());
        }
        return acteur;
    }

    /**
     * Try to call select all acteur method on ActeurImpl
     * @return List object actor
     * @throws BLLException
     * BllException error
     */
    public List<Acteur> getActeurs() throws BLLException{
        List<Acteur> acteurList;
        try{
            acteurList = impl.selectAll();
        } catch (DALException e){
            throw new BLLException("Erreur lors de la récupération des acteurs", e.getCause());
        }
        return acteurList;
    }

    /**
     * Try to call method select acteur or create
     * @param acteur actor object
     * @return Acteur
     * @throws DALException
     * BllException error
     */
    public Acteur getOrCreateActeur(Acteur acteur) throws BLLException, DALException {
        controlActeur(acteur);
        Acteur acteurVerif = impl.selectByImdb(acteur.getIdImdb());
        if(acteurVerif != null){
            return acteurVerif;
        }
        impl.insert(acteur);
        return acteur;
    }

    /**
     * Verification if acteur params is not null
     * @param acteur actor object
     * @throws BLLException
     * BLLException error
     */
    public void controlActeur(Acteur acteur) throws BLLException{
        boolean valid = true;
        StringBuilder sb = new StringBuilder();
        if(acteur==null){
            throw new BLLException("Erreur : l'acteur est null");
        }
    }

    /**
     * Try to get casting of film after verification if film is not null
     * @param film
     * @return List actor object
     * @throws DALException
     * BLLException error
     */
    public List<Acteur> getCasting(String film) throws DALException {
        if(film != null){
            return impl.castingFilm(film);
        }
        return null;
    }

    /**
     * Try to get all same actors present in two different film
     * @param filmOne the name of first film
     * @param filmTwo the name of second film
     * @return List actor object
     * @throws DALException
     * DALException error
     */
    public List<Acteur> selectActeurFilm(String filmOne, String filmTwo) throws DALException {
        if(filmOne != null && filmTwo != null){
            return impl.selectActeurFilm(filmOne, filmTwo);
        }
        return null;
    }
}
