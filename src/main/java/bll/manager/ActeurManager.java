package bll.manager;

import bll.BLLException;
import bo.Acteur;
import bo.Film;
import dal.dao.ActeurDAO;
import dal.DALException;
import dal.dao.DAOFactory;

import java.util.List;
import java.util.Set;

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
    public void addActeur(Acteur acteur) throws BLLException{
        controlActeur(acteur);
        try{
            if(acteur.getIdImdb() != null && impl.selectByImdb(acteur.getIdImdb()) == null) {
                impl.insert(acteur);
            }
        } catch (DALException e){
            throw new BLLException("Erreur lors de l'ajout d'un acteur", e.getCause());
        }
    }

    public Acteur getOneActeur(long id) throws BLLException {
        Acteur acteur;
        try{
            acteur = impl.selectById(id);
        } catch (DALException e){
            throw new BLLException("Erreur lors de la récupération d'un acteur - Id = "+id, e.getCause());
        }
        return acteur;
    }

    public List<Acteur> getActeurs() throws BLLException{
        List<Acteur> acteurList;
        try{
            acteurList = impl.selectAll();
        } catch (DALException e){
            throw new BLLException("Erreur lors de la récupération des acteurs", e.getCause());
        }
        return acteurList;
    }

    public void controlActeur(Acteur acteur) throws BLLException{
        boolean valid = true;
        StringBuilder sb = new StringBuilder();
        if(acteur==null){
            throw new BLLException("Erreur : l'acteur est null");
        }
    }
}
