package bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "country")
public class Pays {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonProperty("nom")
    @Column(name = "name", nullable = false)
    private String name;
    @JsonProperty("url")
    @Column(name = "url", nullable = false)
    private String url;
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private Set<Film> films = new HashSet<>();

    public Pays() {
    }

    public Pays(String name, String url, Set<Film> films) {
        this.name = name;
        this.url = url;
        this.films = films;
    }

    public Pays(long id, String name, String url, Set<Film> films) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.films = films;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    @Override
    public String toString() {
        return "Pays{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
