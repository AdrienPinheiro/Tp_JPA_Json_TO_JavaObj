package bll.manager;

import bll.BLLException;
import bo.Role;
import dal.DALException;
import dal.dao.DAO;
import dal.dao.DAOFactory;

import java.util.List;

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
     * @param role
     * @throws BLLException
     * Try to call insert method on RoleImpl
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
     * @param id
     * @return Role
     * @throws BLLException
     * Try to call select by id method on RoleImpl
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
     * @return List<Role>
     * @throws BLLException
     * Try to call select all role methods on RoleImpl
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
     * @param role
     * @throws BLLException
     * Verification if Role is not null
     */
    public void controlRole(Role role) throws BLLException{
        boolean valid = true;
        StringBuilder sb = new StringBuilder();
        if(role==null){
            throw new BLLException("Erreur : le role est null");
        }
    }
}
