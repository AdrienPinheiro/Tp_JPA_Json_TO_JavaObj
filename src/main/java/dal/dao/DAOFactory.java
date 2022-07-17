package dal.dao;

import bo.*;
import dal.Impl.*;

/**
 * Return a new instance of Impl
 */
public class DAOFactory {
    public static ActeurDAO getAuteurDAO(){
        return new ActeurImpl();
    }
    public static FilmDAO getFilmDAO(){
        return new FilmImpl();
    }
    public static DAO<LieuTournage> getLieuTournageDAO(){
        return new LieuTournageImpl();
    }
    public static DAO<Pays> getPaysDAO(){
        return new PaysImpl();
    }
    public static RealisateurDAO getRealisateurDAO(){
        return new RealisateurImpl();
    }
    public static DAO<Role> getRoleDAO(){
        return new RoleImpl();
    }
}
