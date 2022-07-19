package bll.manager;

import bll.BLLException;
import bo.Acteur;
import bo.Film;
import dal.DALException;
import dal.dao.DAOFactory;
import dal.dao.FilmDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Call with control parameters the Implementation dal
 */
public class FilmManager {
    private static volatile FilmManager instance;
    private static FilmDAO impl;
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

    /**
     * Try to call add film method on FilmImpl after check if film is not null
     * @param film film object
     * @throws BLLException
     * BLLException error
     */
    public void addFilm(Film film) throws BLLException {
        try{
            if(film.getIdImdb() != null){
                impl.insert(film);
            }
        } catch (DALException e){
            throw new BLLException("Erreur lors de l'ajout d'un acteur", e.getCause());
        }
    }

    /**
     * Try to take all film method on FilmImpl
     * @return List film object
     * @throws BLLException
     * BLLException error
     */
    public List<Film> getFilms() throws BLLException{
        List<Film> filmList;
        try{
            filmList = impl.selectAll();
        } catch (DALException e){
            throw new BLLException("Erreur lors de la récupération des acteurs", e.getCause());
        }
        return filmList;
    }

    /**
     * Try to get film or create film method on FilmImpl
     * @param film film object
     * @return Film object
     * @throws DALException
     * BLLException error
     */
    public Film getOrCreateFilm(Film film) throws DALException {
        Film filmVerif = impl.selectByImdb(film.getIdImdb());
        if(filmVerif != null){
            return filmVerif;
        }
        impl.insert(film);
        return film;
    }

    /**
     * Try to get all film of one acteur
     * @return List acteur
     * @throws BLLException
     * BLLException error
     */
    public List<Acteur> getFilmsActeurs() throws BLLException{
        List<Film> filmList;
        List<Acteur> acteurList = new ArrayList<Acteur>();
        try{
            filmList = impl.selectAll();
            if(filmList != null && !filmList.isEmpty()){
                for(Film film : filmList){
                    acteurList.add((Acteur) film.getActeurFilms());
                }
                return acteurList;
            }
        } catch (DALException e){
            throw new BLLException("Erreur lors de la récupération des acteurs", e.getCause());
        }
        return null;
    }

    /**
     * Try to get all film of one acteur method on FilmImpl
     * @param name film name
     * @return List object film
     * @throws DALException
     * DALException error
     */
    public List<Film> getActeurFilm(String name) throws DALException {
        if(name != null){
            return impl.selectActeurFilm(name);
        }
        return null;
    }

    /**
     * Try to select all film between 2 years method on FilmImpl
     * @param startYear start year
     * @param endYear end year
     * @return List film object
     * @throws DALException
     * DALException error
     */
    public List<Film> selectFilmBetweenYear(int startYear, int endYear) throws DALException {
        if(startYear<=endYear){
            return impl.selectFilmBetweenYear(startYear, endYear);
        }
        return null;
    }

    /**
     * Try to get all film with 2 same actors method on FilmImpl
     * @param firstActeur the first actor name
     * @param secondActeur the second actor name
     * @return List film object
     * @throws DALException
     * DALException error
     */
    public List<Film> selectFilmTwoActeur(String firstActeur, String secondActeur) throws DALException {
        if(firstActeur != null && secondActeur != null){
            return impl.selectFilmTwoActeur(firstActeur, secondActeur);
        }
        return null;
    }

    /**
     * Try to get all film with 2 same actors and between 2 years method on FilmImpl
     * @param startYear start year
     * @param endYear end year
     * @param acteur acteur name
     * @return List<Film>
     * @throws DALException
     * DALException error
     */
    public List<Film> selectFilmBetweenYearWithActeur(String startYear, String endYear, String acteur) throws DALException {
        if(acteur != null && Integer.parseInt(startYear) <= Integer.parseInt(endYear)){
            return impl.selectFilmBetweenYearWithActeur(startYear, endYear, acteur);
        }
        return null;
    }

}
