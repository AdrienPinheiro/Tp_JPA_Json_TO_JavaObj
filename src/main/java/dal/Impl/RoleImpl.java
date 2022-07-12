package dal.Impl;

import bo.Role;
import dal.DALException;
import dal.DAO;
import dal.Settings;

import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleImpl implements DAO<Role> {
    @Override
    public void insert(Role data) throws DALException {
        try{
            Settings.getProperty().getTransaction().begin();
            Settings.getProperty().persist(data);
            Settings.getProperty().getTransaction().commit();
        } catch (DALException e) {
            throw new DALException("Erreur lors de l'insertion d'un role");
        }
    }

    @Override
    public void delete(Role data) throws DALException {

    }

    @Override
    public void update(Role data) throws DALException {

    }

    @Override
    public Role selectById(long id) throws DALException {
        return null;
    }

    @Override
    public List<Role> selectAll() throws DALException {
        ResultSet rs;
        List<Role> roleList = new ArrayList<>();
        try{
            TypedQuery<Role> selectAll = Settings.getProperty().createQuery("SELECT r FROM Role r", Role.class);
            roleList = selectAll.getResultList();
        } catch (DALException e) {
            throw new DALException("Problème lors de la récupération de la liste d'acteur");
        }
        return roleList;
    }
}
