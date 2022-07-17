package bll.manager;

import bll.BLLException;
import bo.Acteur;

import dal.dao.ActeurDAO;
import dal.DALException;
import dal.dao.DAOFactory;

import java.util.List;

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
     * @param acteur
     * @throws BLLException
     * Verification if acteur identity is not null and call selectByImdb is not null too
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
     * @param id
     * @return Acteur
     * @throws BLLException
     * Try to call select one acteur by id method on ActeurImpl
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
     * @return List<Acteur>
     * @throws BLLException
     * Try to call select all acteur method on ActeurImpl
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
     * @param acteur
     * @return Acteur
     * @throws DALException
     * Try to call method select acteur or create
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
     * @param acteur
     * @throws BLLException
     * Verification if acteur params is not null
     */
    public void controlActeur(Acteur acteur) throws BLLException{
        boolean valid = true;
        StringBuilder sb = new StringBuilder();
        if(acteur==null){
            throw new BLLException("Erreur : l'acteur est null");
        }
    }

    /**
     * @param film
     * @return List<Acteur>
     * @throws DALException
     * Try to get casting of film after verification if film is not null
     */
    public List<Acteur> getCasting(String film) throws DALException {
        if(film != null){
            return impl.castingFilm(film);
        }
        return null;
    }
}
