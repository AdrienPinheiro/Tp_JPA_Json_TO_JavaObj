package bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Role object
 */
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonProperty("characterName")
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_film")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "id_acteur")
    private Acteur acteur;

    /**
     * Role constructor simple
     */
    public Role() {
    }

    /**
     * Role constructor without id
     * @param name the role name
     * @param film all film of role
     * @param acteur all actor of role
     */
    public Role(String name, Film film, Acteur acteur) {
        this.name = name;
        this.film = film;
        this.acteur = acteur;
    }

    /**
     * Role constructor with id
     * @param id generate id with BDD
     * @param name the role name
     * @param film all film of role
     * @param acteur all actor of role
     */
    public Role(long id, String name, Film film, Acteur acteur) {
        this.id = id;
        this.name = name;
        this.film = film;
        this.acteur = acteur;
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

    @JsonProperty("film")
    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Acteur getActeur() {
        return acteur;
    }

    public void setActeur(Acteur acteur) {
        this.acteur = acteur;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", films=" + film +
                ", acteurs=" + acteur +
                '}';
    }
}
