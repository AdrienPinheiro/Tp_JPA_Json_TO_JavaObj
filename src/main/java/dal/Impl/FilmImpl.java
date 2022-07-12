package dal.Impl;

import bo.Film;
import dal.DALException;
import dal.DAO;
import dal.Settings;

import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FilmImpl implements DAO<Film> {
    @Override
    public void insert(Film data) throws DALException {
        try{
            Settings.getProperty().getTransaction().begin();
            Settings.getProperty().persist(data);
            Settings.getProperty().getTransaction().commit();
        } catch (DALException e) {
            throw new DALException("Erreur lors de l'insertion d'un film");
        }
    }

    @Override
    public void delete(Film data) throws DALException {

    }

    @Override
    public void update(Film data) throws DALException {

    }

    @Override
    public Film selectById(long id) throws DALException {
        return null;
    }

    @Override
    public List<Film> selectAll() throws DALException {
        ResultSet rs;
        List<Film> filmList = new ArrayList<>();
        try{
            TypedQuery<Film> selectAll = Settings.getProperty().createQuery("SELECT f FROM Film f", Film.class);
            filmList = selectAll.getResultList();
        } catch (DALException e){
            throw new DALException("Problème lors de la récupération de la liste de film");
        }
        return filmList;
    }
}
