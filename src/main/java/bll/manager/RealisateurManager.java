package bll.manager;

import bll.BLLException;

import bo.Realisateur;

import dal.DALException;
import dal.dao.DAOFactory;
import dal.dao.RealisateurDAO;

import java.util.List;

public class RealisateurManager {
    private static volatile RealisateurManager instance;
    private static RealisateurDAO impl;
    private RealisateurManager(){
        impl = DAOFactory.getRealisateurDAO();
    }
    public static RealisateurManager getInstance(){
        if(RealisateurManager.instance == null){
            synchronized (RealisateurManager.class){
                if(RealisateurManager.instance == null){
                    RealisateurManager.instance = new RealisateurManager();
                }
            }
        }
        return RealisateurManager.instance;
    }

    /**
     * @param acteur
     * @throws BLLException
     * Try to call realisator method on RealisateurImpl
     */
    public void addRealisateur(Realisateur realisateur) throws BLLException {
        controlRealisateur(realisateur);
        try{
            impl.insert(realisateur);
        } catch (DALException e){
            throw new BLLException("Erreur lors de l'ajout d'un realisateur", e.getCause());
        }
    }

    /**
     * @param id
     * @return Realisateur
     * @throws BLLException
     * Try to call one realisator method on RealisateurImpl
     */
    public Realisateur getOneRealisateur(long id) throws BLLException {
        Realisateur realisateur;
        try{
            realisateur = impl.selectById(id);
        } catch (DALException e){
            throw new BLLException("Erreur lors de la récupération d'un realisateur - Id = "+id, e.getCause());
        }
        return realisateur;
    }

    /**
     * @return List<Realisateur>
     * @throws BLLException
     * Try to call get all realisator method on RealisateurImpl
     */
    public List<Realisateur> getRealiateurs() throws BLLException{
        List<Realisateur> realisateurList;
        try{
            realisateurList = impl.selectAll();
        } catch (DALException e){
            throw new BLLException("Erreur lors de la récupération des realisateurs", e.getCause());
        }
        return realisateurList;
    }

    /**
     * @param realisateur
     * @return Realisateur
     * @throws DALException
     * Try to call get or create realisator method on RealisateurImpl
     */
    public Realisateur getOrCreateRealisateur(Realisateur realisateur) throws DALException {
        Realisateur realisateurVerif = impl.selectByIdentity(realisateur.getIdentity());
        if(realisateurVerif != null){
            return realisateurVerif;
        }
        impl.insert(realisateur);
        return realisateur;
    }

    /**
     * @param realisateur
     * @throws BLLException
     * Verification if Realisator is not null
     */
    public void controlRealisateur(Realisateur realisateur) throws BLLException{
        boolean valid = true;
        StringBuilder sb = new StringBuilder();
        if(realisateur==null){
            throw new BLLException("Erreur : le realisateur est null");
        }
    }
}
