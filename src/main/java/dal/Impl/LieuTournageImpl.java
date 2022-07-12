package dal.Impl;

import bo.LieuTournage;
import dal.DALException;
import dal.DAO;
import dal.Settings;

import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LieuTournageImpl implements DAO<LieuTournage> {
    @Override
    public void insert(LieuTournage data) throws DALException {
        try{
            Settings.getProperty().getTransaction().begin();
            Settings.getProperty().persist(data);
            Settings.getProperty().getTransaction().commit();
        } catch (DALException e) {
            throw new DALException("Erreur lors de l'insertion d'un lieu de tournage");
        }
    }

    @Override
    public void delete(LieuTournage data) throws DALException {

    }

    @Override
    public void update(LieuTournage data) throws DALException {

    }

    @Override
    public LieuTournage selectById(long id) throws DALException {
        return null;
    }

    @Override
    public List<LieuTournage> selectAll() throws DALException {
        ResultSet rs;
        List<LieuTournage> lieuTournageList = new ArrayList<>();
        try{
            TypedQuery<LieuTournage> selectAll = Settings.getProperty().createQuery("SELECT lt FROM LieuTournage lt", LieuTournage.class);
            lieuTournageList = selectAll.getResultList();
        } catch (DALException e) {
            throw new DALException("Problème lors de la récupération de la liste des lieux de tournage");
        }
        return lieuTournageList;
    }
}
