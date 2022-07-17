package bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "acteurs")
public class Acteur {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty("id")
    @Column(name = "id_imdb")
    private String idImdb;

    @JsonProperty("identite")
    @Column(name = "identity")
    private String identity;

    @Embedded
    @JsonProperty("naissance")
    private Naissance naissance;

    @JsonProperty("url")
    @Column(name = "url")
    private String url;

    @ManyToMany(mappedBy = "acteurFilms")
    private Set<Film> films = new HashSet<>();

    @OneToMany(mappedBy = "acteur")
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(mappedBy = "castingPrincipals")
    private Set<Film> filmCastings = new HashSet<>();

    public Acteur() {
    }

    public Acteur(String idImdb, String identity, Naissance naissance, String url, Set<Film> films, Set<Role> roles, Set<Film> filmCastings) {
        this.idImdb = idImdb;
        this.identity = identity;
        this.naissance = naissance;
        this.url = url;
        this.films = films;
        this.roles = roles;
        this.filmCastings = filmCastings;
    }

    public Acteur(long id, String idImdb, String identity, Naissance naissance, String url, Set<Film> films, Set<Role> roles, Set<Film> filmCastings) {
        this.id = id;
        this.idImdb = idImdb;
        this.identity = identity;
        this.naissance = naissance;
        this.url = url;
        this.films = films;
        this.roles = roles;
        this.filmCastings = filmCastings;
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

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Naissance getNaissance() {
        return naissance;
    }

    public void setNaissance(Naissance naissance) {
        this.naissance = naissance;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    @JsonProperty("roles")
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addFilm(Film film) {
        this.films.add(film);
    }

    public Set<Film> getFilmCastings() {
        return filmCastings;
    }

    public void setFilmCastings(Set<Film> filmCastings) {
        this.filmCastings = filmCastings;
    }

    public void addFilmCasting(Film filmCasting){
        this.filmCastings.add(filmCasting);
    }

    @Override
    public String toString() {
        return "Acteur{" +
                "id=" + id +
                ", idImdb='" + idImdb + '\'' +
                ", identity='" + identity + '\'' +
                ", naissance=" + naissance +
                ", url='" + url + '\'' +
                '}';
    }
}

