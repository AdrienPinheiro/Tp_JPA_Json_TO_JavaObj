package bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "realisateurs")
public class Realisateur {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonProperty("identite")
    @Column(name = "identity", nullable = false)
    private String identity;
    @JsonProperty("url")
    @Column(name = "url", nullable = false)
    private String url;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "film_realisateur",
            joinColumns = @JoinColumn(name = "id_realisateur"),
            inverseJoinColumns = @JoinColumn(name = "id_film"))
    private Set<Film> films = new HashSet<>();

    public Realisateur() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @JsonProperty("film")
    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    @Override
    public String toString() {
        return "Realisateur{" +
                "id=" + id +
                ", identity='" + identity + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
