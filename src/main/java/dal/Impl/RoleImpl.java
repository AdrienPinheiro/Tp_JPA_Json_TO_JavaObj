package dal.Impl;

import bo.Role;
import dal.DALException;
import dal.dao.DAO;
import dal.settings.Settings;

import javax.persistence.EntityManager;
import java.util.List;

public class RoleImpl implements DAO<Role> {
    EntityManager em = Settings.getProperty();

    @Override
    public void insert(Role data) throws DALException {
        em.getTransaction().begin();
        em.persist(data);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Role data) throws DALException {

    }

    @Override
    public void update(Role data) throws DALException {

    }

    // getSingleResult() returns Acteur OU Exception (et pas null)
    @Override
    public Role selectById(long id) throws DALException {
        List<Role> roleList = em.createQuery("SELECT r FROM Role r WHERE r.id=:id", Role.class).setParameter("id", id).getResultList();
        if(!roleList.isEmpty()){
            return roleList.get(0);
        }
        return null;
    }

    @Override
    public List<Role> selectAll() throws DALException {
        List<Role> roleList = em.createQuery("SELECT r FROM Role r", Role.class).getResultList();
        if(!roleList.isEmpty()){
            return roleList;
        }
        return null;
    }
}
