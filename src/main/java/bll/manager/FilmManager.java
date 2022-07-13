package bll.manager;

import bll.BLLException;
import bo.Acteur;
import bo.Film;
import dal.DALException;
import dal.dao.DAO;
import dal.dao.DAOFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FilmManager {
    private static volatile FilmManager instance;
    private static DAO<Film> impl;
    private FilmManager(){
        impl = DAOFactory.getFilmDAO();
    }
    public static FilmManager getInstance(){
        if(instance == null){
            synchronized (FilmManager.class){
                if(instance == null){
                    instance = new FilmManager();
                }
            }
        }
        return instance;
    }
    public void addFilm(Film film) throws BLLException {
        try{
            if(film.getId_imdb() != null){
                impl.insert(film);
            }
        } catch (DALException e){
            throw new BLLException("Erreur lors de l'ajout d'un acteur", e.getCause());
        }
    }

    public List<Set<Acteur>> getFilmsActeurs() throws BLLException{
        List<Film> filmList;
        List<Set<Acteur>> acteurList = new ArrayList<Set<Acteur>>();
        try{
            filmList = impl.selectAll();
            if(filmList != null && !filmList.isEmpty()){
                for(Film film : filmList){
                    acteurList.add(film.getActeurFilms());
                }
                return acteurList;
            }
        } catch (DALException e){
            throw new BLLException("Erreur lors de la récupération des acteurs", e.getCause());
        }
        return null;
    }
}
