package bll.json;

import bll.json.JsonReader;
import bll.manager.ActeurManager;

import bll.manager.FilmManager;
import bll.manager.RealisateurManager;
import bll.manager.RoleManager;
import bo.Acteur;

import bo.Film;
import bo.Realisateur;
import bo.Role;
import dal.settings.Settings;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JsonToBdd {
    /**
     * @param args
     * Build BDD with JsonReader
     */
    public static void main(String[] args) {
        ActeurManager acteurManager = ActeurManager.getInstance();
        FilmManager filmManager = FilmManager.getInstance();
        RoleManager roleManager = RoleManager.getInstance();
        RealisateurManager realisateurManager = RealisateurManager.getInstance();
        try{
            Settings.getProperty();
            List<Acteur> acteurObj = JsonReader.JsonReaderFilm();
            for(Acteur acteurJson : acteurObj) {
                Acteur acteur = acteurManager.getOrCreateActeur(acteurJson);
                for(Role roleJson : acteurJson.getRoles()){
                    Film filmJson = roleJson.getFilm();
                    Set<Acteur> castingPrincipals = filmJson.getCastingPrincipals();
                    Set<Acteur> acteurFilms = filmJson.getActeurFilms();
                    Set<Realisateur> realisateurs = filmJson.getRealisateurs();
                    filmJson.setCastingPrincipals(new HashSet<>());
                    filmJson.setActeurFilms(new HashSet<>());
                    filmJson.setRealisateurs(new HashSet<>());
                    Film film = filmManager.getOrCreateFilm(filmJson);
                    // traitement de la liaison entre acteur et film => castingPrincipals
                    for(Acteur acteurCastingJson : castingPrincipals){
                        Acteur acteurCasting = acteurManager.getOrCreateActeur(acteurCastingJson);
                        film.addCastingPrincipals(acteurCasting);
                        acteurCasting.addFilmCasting(film);
                    }
                    // traitement de la liaison entre acteur et film => acteurs
                    for(Acteur acteurFilmJson : acteurFilms){
                        Acteur acteurFilm = acteurManager.getOrCreateActeur(acteurFilmJson);
                        film.addActeurFilm(acteurFilm);
                        acteurFilm.addFilm(film);
                    }
                    // traitement de la liaison entre film et realisateur
                    for(Realisateur realisateurJson : realisateurs){
                        Realisateur realisateurFilm = realisateurManager.getOrCreateRealisateur(realisateurJson);
                        film.addRealisateur(realisateurFilm);
                        realisateurFilm.addFilm(film);
                    }
                    roleManager.addRole(new Role(roleJson.getName(), film, acteur));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
