package bll.manager;

import bll.BLLException;
import bo.Acteur;
import bo.Film;
import dal.DALException;
import dal.dao.DAOFactory;
import dal.dao.FilmDAO;

import java.util.ArrayList;
import java.util.List;

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
     * @param film
     * @throws BLLException
     * Try to call add film method on FilmImpl after check if film is not null
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
     * @return List<Film></Film>
     * @throws BLLException
     * Try to take all film method on FilmImpl
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
     * @param film
     * @return Film object
     * @throws DALException
     * Try to get film or create film method on FilmImpl
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
     * @return List
     * @throws BLLException
     * Try to get all film of one acteur
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
     * @param name
     * @return List<Film>
     * @throws DALException
     * Try to get all film of one acteur method on FilmImpl
     */
    public List<Film> getActeurFilm(String name) throws DALException {
        if(name != null){
            return impl.selectActeurFilm(name);
        }
        return null;
    }

    /**
     * @param startYear
     * @param endYear
     * @return List<Film></Film>
     * @throws DALException
     * Try to select all film between 2 years method on FilmImpl
     */
    public List<Film> selectFilmBetweenYear(int startYear, int endYear) throws DALException {
        if(startYear<=endYear){
            return impl.selectFilmBetweenYear(startYear, endYear);
        }
        return null;
    }

    /**
     * @param firstActeur
     * @param secondActeur
     * @return List<Film>
     * @throws DALException
     * Try to get all film with 2 same actors method on FilmImpl
     */
    public List<Film> selectFilmTwoActeur(String firstActeur, String secondActeur) throws DALException {
        if(firstActeur != null && secondActeur != null){
            return impl.selectFilmTwoActeur(firstActeur, secondActeur);
        }
        return null;
    }

    /**
     * @param startYear
     * @param endYear
     * @param acteur
     * @return List<Film>
     * @throws DALException
     * Try to get all film with 2 same actors and between 2 years method on FilmImpl
     */
    public List<Film> selectFilmBetweenYearAndWithTwoActeur(int startYear, int endYear, String acteur) throws DALException {
        if(acteur != null && startYear < endYear){
            return impl.selectFilmBetweenYearAndWithTwoActeur(startYear, endYear, acteur);
        }
        return null;
    }

}
