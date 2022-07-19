package bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Country object
 */
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
    @OneToMany(mappedBy = "country")
    private Set<Film> films = new HashSet<>();

    /**
     * Country constructor simple
     */
    public Pays() {
    }

    /**
     * Country constructor without id
     * @param name the country name
     * @param url the country url
     * @param films all film country
     */
    public Pays(String name, String url, Set<Film> films) {
        this.name = name;
        this.url = url;
        this.films = films;
    }

    /**
     * Country constructor with id
     * @param id generate id with BDD
     * @param name the country name
     * @param url the country url
     * @param films all film country
     */
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
