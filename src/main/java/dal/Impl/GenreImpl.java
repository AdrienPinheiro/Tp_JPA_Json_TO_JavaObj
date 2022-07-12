package dal.Impl;

import bo.Genre;
import dal.DALException;
import dal.DAO;
import dal.Settings;

import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GenreImpl implements DAO<Genre> {
    @Override
    public void insert(Genre data) throws DALException {
        try{
            Settings.getProperty().getTransaction().begin();
            Settings.getProperty().persist(data);
            Settings.getProperty().getTransaction().commit();
        } catch (DALException e) {
            throw new DALException("Erreur lors de l'insertion d'un acteur");
        }
    }

    @Override
    public void delete(Genre data) throws DALException {

    }

    @Override
    public void update(Genre data) throws DALException {

    }

    @Override
    public Genre selectById(long id) throws DALException {
        return null;
    }

    @Override
    public List<Genre> selectAll() throws DALException {
        ResultSet rs;
        List<Genre> genreList = new ArrayList<>();
        try{
            TypedQuery<Genre> selectAll = Settings.getProperty().createQuery("SELECT g FROM Genre g", Genre.class);
            genreList = selectAll.getResultList();
        } catch (DALException e) {
            throw new DALException("Problème lors de la récupération de la liste d'acteur");
        }
        return genreList;
    }
}
