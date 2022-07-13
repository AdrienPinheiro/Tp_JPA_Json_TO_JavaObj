package dal.dao;

import bo.*;
import dal.Impl.*;

public class DAOFactory {
    public static ActeurDAO getAuteurDAO(){
        return new ActeurImpl();
    }
    public static DAO<Film> getFilmDAO(){
        return new FilmImpl();
    }
    public static DAO<LieuTournage> getLieuTournageDAO(){
        return new LieuTournageImpl();
    }
    public static DAO<Pays> getPaysDAO(){
        return new PaysImpl();
    }
    public static DAO<Realisateur> getRealisateurDAO(){
        return new RealisateurImpl();
    }
    public static DAO<Role> getRoleDAO(){
        return new RoleImpl();
    }
}
