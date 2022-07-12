package bll.manager;

import bll.BLLException;
import bo.Acteur;
import dal.DALException;
import dal.DAO;
import dal.DAOFactory;

import java.util.List;

public class ActeurManager {
    private static volatile ActeurManager instance;
    private static DAO<Acteur> impl;
    private ActeurManager(){
        impl = DAOFactory.getAuteurDAO();
    }
    public final static ActeurManager getInstance(){
        if(ActeurManager.instance == null){
            synchronized (ActeurManager.class){
                if(ActeurManager.instance == null){
                    ActeurManager.instance = new ActeurManager();
                }
            }
        }
        return ActeurManager.instance;
    }
    public void addActeur(Acteur acteur) throws BLLException{
        controlActeur(acteur);
        try{
            impl.insert(acteur);
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
