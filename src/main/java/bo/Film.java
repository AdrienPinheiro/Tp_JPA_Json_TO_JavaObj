package bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "films")
public class Film {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty("id")
    @Column(name = "id_imdb")
    private String id_imdb;

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

    @JsonProperty("realisateurs")
    @ManyToMany(mappedBy = "films", cascade = CascadeType.ALL)
    private Set<Realisateur> realisateurs;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "film_role",
    joinColumns = @JoinColumn(name = "id_film"),
    inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(mappedBy = "films", cascade = CascadeType.ALL)
    private Set<Acteur> acteurs = new HashSet<>();

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "film_genre", joinColumns = @JoinColumn(name = "film_id"))
    private List<String> genres;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pays")
    private Pays country;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tournage")
    private LieuTournage filming_locations;

    public Film() {
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getId_imdb() {
        return id_imdb;
    }

    public void setId_imdb(String id_imdb) {
        this.id_imdb = id_imdb;
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

    @JsonProperty("genres")
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

    @JsonProperty("lieuTournage")
    public LieuTournage getFilming_locations() {
        return filming_locations;
    }

    public void setFilming_locations(LieuTournage filming_locations) {
        this.filming_locations = filming_locations;
    }

    @JsonProperty("roles")
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Acteur> getActeurs() {
        return acteurs;
    }

    public void setActeurs(Set<Acteur> acteurs) {
        this.acteurs = acteurs;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", id_imdb='" + id_imdb + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", langage='" + langage + '\'' +
                ", releaseYear='" + releaseYear + '\'' +
                ", realisateurs=" + realisateurs +
                ", acteurs=" + acteurs +
                ", genres=" + genres +
                ", country=" + country +
                ", filming_locations=" + filming_locations +
                '}';
    }
}
