package bll.manager;

import bll.BLLException;
import bo.Role;
import dal.DALException;
import dal.dao.DAO;
import dal.dao.DAOFactory;

import java.util.List;

/**
 * Call with control parameters the Implementation dal
 */
public class RoleManager {
    private static volatile RoleManager instance;
    private static DAO<Role> impl;
    private RoleManager(){
        impl = DAOFactory.getRoleDAO();
    }
    public static RoleManager getInstance(){
        if(instance == null){
            synchronized (RoleManager.class){
                if(instance == null){
                    instance = new RoleManager();
                }
            }
        }
        return instance;
    }

    /**
     * Try to call insert method on RoleImpl
     * @param role role object
     * @throws BLLException
     * BLLException error
     */
    public void addRole(Role role) throws BLLException {
        controlRole(role);
        try{
            impl.insert(role);
        } catch (DALException e){
            throw new BLLException("Erreur lors de l'ajout d'un role", e.getCause());
        }
    }

    /**
     * Try to call select by id method on RoleImpl
     * @param id role id
     * @return Role
     * @throws BLLException
     * BLLException error
     */
    public Role getOneRole(long id) throws BLLException {
        Role role;
        try{
            role = impl.selectById(id);
        } catch (DALException e){
            throw new BLLException("Erreur lors de la récupération d'un role - Id = "+id, e.getCause());
        }
        return role;
    }

    /**
     * Try to call select all role methods on RoleImpl
     * @return List object role
     * @throws BLLException
     * BLLException error
     */
    public List<Role> getRoles() throws BLLException{
        List<Role> roleList;
        try{
            roleList = impl.selectAll();
        } catch (DALException e){
            throw new BLLException("Erreur lors de la récupération des roles", e.getCause());
        }
        return roleList;
    }

    /**
     * Verification if Role is not null
     * @param role object role
     * @throws BLLException
     * BLLException error
     */
    public void controlRole(Role role) throws BLLException{
        boolean valid = true;
        StringBuilder sb = new StringBuilder();
        if(role==null){
            throw new BLLException("Erreur : le role est null");
        }
    }
}
