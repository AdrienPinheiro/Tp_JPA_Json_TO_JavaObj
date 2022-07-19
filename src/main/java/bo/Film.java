package bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.*;

/**
 * Film object
 */
@Entity
@Table(name = "films")
public class Film {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty("id")
    @Column(name = "id_imdb")
    private String idImdb;

    @JsonProperty("nom")
    @Column(name = "title")
    private String title;

    @JsonProperty("url")
    @Column(name = "url")
    private String url;

    @JsonProperty("plot")
    @Column(name = "description")
    private String description;

    @JsonProperty("langue")
    @Column(name = "langage")
    private String langage;

    @JsonProperty("anneeSortie")
    @Column(name = "release_year")
    private String releaseYear;

    @ManyToMany
    @JoinTable(name = "film_realisateur",
            joinColumns = @JoinColumn(name = "id_film"),
            inverseJoinColumns = @JoinColumn(name = "id_realisateur"))
    private Set<Realisateur> realisateurs = new HashSet<>();

    @OneToMany(mappedBy = "film")
    private Set<Role> roles = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "film_acteur",
            joinColumns = @JoinColumn(name = "id_film", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_acteur", referencedColumnName = "id"))
    private Set<Acteur> acteurFilms = new HashSet<>();

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "film_genre", joinColumns = @JoinColumn(name = "id_film"))
    private List<String> genres;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pays")
    @JsonProperty("pays")
    private Pays country;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tournage")
    @JsonProperty("lieuTournage")
    private LieuTournage filmingLocations;

    @ManyToMany
    @JoinTable(name = "casting_principals",
        joinColumns = @JoinColumn(name = "id_film", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "id_acteur", referencedColumnName = "id"))
    private Set<Acteur> castingPrincipals = new HashSet<>();

    /**
     * Film constructor simple
     */
    public Film() {
    }

    /**
     * Film constructor without id
     * @param idImdb the id JSON
     * @param title the title film
     * @param url the url film
     * @param description the description film
     * @param langage the language film
     * @param releaseYear the release year film
     * @param realisateurs all realisator film
     * @param roles all role film
     * @param acteurFilms all actor film
     * @param genres all genre film
     * @param country the country film
     * @param filmingLocations the filmingLocations film
     * @param castingPrincipals the castingPrincipals film
     */
    public Film(String idImdb, String title, String url, String description, String langage, String releaseYear, Set<Realisateur> realisateurs, Set<Role> roles, Set<Acteur> acteurFilms, List<String> genres, Pays country, LieuTournage filmingLocations, Set<Acteur> castingPrincipals) {
        this.idImdb = idImdb;
        this.title = title;
        this.url = url;
        this.description = description;
        this.langage = langage;
        this.releaseYear = releaseYear;
        this.realisateurs = realisateurs;
        this.roles = roles;
        this.acteurFilms = acteurFilms;
        this.genres = genres;
        this.country = country;
        this.filmingLocations = filmingLocations;
        this.castingPrincipals = castingPrincipals;
    }

    /**
     * Film constructor without id
     * @param id generate id with BDD
     * @param idImdb the id JSON
     * @param title the title film
     * @param url the url film
     * @param description the description film
     * @param langage the language film
     * @param releaseYear the release year film
     * @param realisateurs all realisator film
     * @param roles all role film
     * @param acteurFilms all actor film
     * @param genres all genre film
     * @param country the country film
     * @param filmingLocations the filmingLocations film
     * @param castingPrincipals the castingPrincipals film
     */
    public Film(long id, String idImdb, String title, String url, String description, String langage, String releaseYear, Set<Realisateur> realisateurs, Set<Role> roles, Set<Acteur> acteurFilms, List<String> genres, Pays country, LieuTournage filmingLocations, Set<Acteur> castingPrincipals) {
        this.id = id;
        this.idImdb = idImdb;
        this.title = title;
        this.url = url;
        this.description = description;
        this.langage = langage;
        this.releaseYear = releaseYear;
        this.realisateurs = realisateurs;
        this.roles = roles;
        this.acteurFilms = acteurFilms;
        this.genres = genres;
        this.country = country;
        this.filmingLocations = filmingLocations;
        this.castingPrincipals = castingPrincipals;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdImdb() {
        return idImdb;
    }

    public void setIdImdb(String idImdb) {
        this.idImdb = idImdb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLangage() {
        return langage;
    }

    public void setLangage(String langage) {
        this.langage = langage;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    @JsonProperty("realisateurs")
    public Set<Realisateur> getRealisateurs() {
        return realisateurs;
    }

    public void setRealisateurs(Set<Realisateur> realisateurs) {
        this.realisateurs = realisateurs;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @JsonProperty("pays")
    public Pays getCountry() {
        return country;
    }

    public void setCountry(Pays country) {
        this.country = country;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @JsonProperty("acteurs")
    public Set<Acteur> getActeurFilms() {
        return acteurFilms;
    }

    public void setActeurFilms(Set<Acteur> acteurFilms) {
        this.acteurFilms = acteurFilms;
    }

    public void addActeurFilm(Acteur acteurFilm){
        this.acteurFilms.add(acteurFilm);
    }

    @JsonProperty("lieuTournage")
    public LieuTournage getFilmingLocations() {
        return filmingLocations;
    }

    public void setFilmingLocations(LieuTournage filmingLocations) {
        this.filmingLocations = filmingLocations;
    }

    @JsonProperty("castingPrincipal")
    public Set<Acteur> getCastingPrincipals() {
        return castingPrincipals;
    }

    public void setCastingPrincipals(Set<Acteur> castingPrincipals) {
        this.castingPrincipals = castingPrincipals;
    }

    public void addCastingPrincipals(Acteur acteur) {
        this.castingPrincipals.add(acteur);
    }

    public void addRealisateur(Realisateur realisateur) {this.realisateurs.add(realisateur);}

    @Override
    public String toString() {
        return "Film{" +
                " id = " + id +
                ", id_imdb = '" + idImdb + '\'' +
                ", titre = '" + title + '\'' +
                ", année de sortie = '" + releaseYear + '\'' +
                ", pays d'origine = " + country +
                ", lieu de tournage = " + filmingLocations +
                ", genres = " + genres +
                ", description = '" + description + '\'' +
                ", langue = '" + langage + '\'' +
                ", réalisateurs = " + realisateurs +
                ", acteurs = " + acteurFilms +
                '}';
    }
}
