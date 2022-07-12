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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "acteur_film",
            joinColumns = @JoinColumn(name = "id_acteur"),
            inverseJoinColumns = @JoinColumn(name = "id_film"))
    private Set<Film> films = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "acteur_role",
            joinColumns = @JoinColumn(name = "id_acteur"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles = new HashSet<>();

    public Acteur() {
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

    @JsonProperty("film")
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

    @Override
    public String toString() {
        return "Acteur{" +
                "id=" + id +
                ", idImdb='" + idImdb + '\'' +
                ", identity='" + identity + '\'' +
                ", naissance=" + naissance +
                ", url='" + url + '\'' +
                ", roles=" + roles +
                '}';
    }
}

