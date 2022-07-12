package bll.manager;

import bll.BLLException;
import bo.Acteur;
import bo.Realisateur;
import dal.DALException;
import dal.DAO;
import dal.DAOFactory;

import java.util.List;

public class RealisateurManager {
    private static volatile RealisateurManager instance;
    private static DAO<Realisateur> impl;
    private RealisateurManager(){
        impl = DAOFactory.getRealisateurDAO();
    }
    public final static RealisateurManager getInstance(){
        if(RealisateurManager.instance == null){
            synchronized (RealisateurManager.class){
                if(RealisateurManager.instance == null){
                    RealisateurManager.instance = new RealisateurManager();
                }
            }
        }
        return RealisateurManager.instance;
    }
    public void addActeur(Realisateur acteur) throws BLLException {
        controlActeur(acteur);
        try{
            impl.insert(acteur);
        } catch (DALException e){
            throw new BLLException("Erreur lors de l'ajout d'un acteur", e.getCause());
        }
    }

    public Realisateur getOneActeur(long id) throws BLLException {
        Realisateur acteur;
        try{
            acteur = impl.selectById(id);
        } catch (DALException e){
            throw new BLLException("Erreur lors de la récupération d'un acteur - Id = "+id, e.getCause());
        }
        return acteur;
    }

    public List<Realisateur> getActeurs() throws BLLException{
        List<Realisateur> acteurList;
        try{
            acteurList = impl.selectAll();
        } catch (DALException e){
            throw new BLLException("Erreur lors de la récupération des acteurs", e.getCause());
        }
        return acteurList;
    }

    public void controlActeur(Realisateur acteur) throws BLLException{
        boolean valid = true;
        StringBuilder sb = new StringBuilder();
        if(acteur==null){
            throw new BLLException("Erreur : le rea est null");
        }
    }
}
