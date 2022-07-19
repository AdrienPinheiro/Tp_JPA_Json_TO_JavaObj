package dal.Impl;

import bo.Role;
import dal.DALException;
import dal.dao.DAO;
import dal.settings.Settings;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * All call BDD for role object
 */
public class RoleImpl implements DAO<Role> {
    EntityManager em = Settings.getProperty();

    /**
     * Persist role on BDD
     * @param data role object
     */
    @Override
    public void insert(Role data) {
        em.getTransaction().begin();
        em.persist(data);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Role data) {

    }

    @Override
    public void update(Role data) {

    }

    /**
     * Take one role with id on BDD
     * @param id role id
     * @return Role object
     */
    @Override
    public Role selectById(long id) {
        List<Role> roleList = em.createQuery("SELECT r FROM Role r WHERE r.id=:id", Role.class).setParameter("id", id).getResultList();
        if(!roleList.isEmpty()){
            return roleList.get(0);
        }
        return null;
    }

    /**
     * Take all role on BDD
     * @return List role object
     */
    @Override
    public List<Role> selectAll() {
        List<Role> roleList = em.createQuery("SELECT r FROM Role r", Role.class).getResultList();
        if(!roleList.isEmpty()){
            return roleList;
        }
        return null;
    }
}
