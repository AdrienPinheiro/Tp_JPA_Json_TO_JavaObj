package bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "film_role",
            joinColumns = @JoinColumn(name = "id_role"),
            inverseJoinColumns = @JoinColumn(name = "id_film"))
    private Set<Film> filmsRole = new HashSet<>();

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private Set<Acteur> acteurs = new HashSet<>();

    public Role() {
    }

    public Role(String name, Set<Film> filmsRole, Set<Acteur> acteurs) {
        this.name = name;
        this.filmsRole = filmsRole;
        this.acteurs = acteurs;
    }

    public Role(long id, String name, Set<Film> filmsRole, Set<Acteur> acteurs) {
        this.id = id;
        this.name = name;
        this.filmsRole = filmsRole;
        this.acteurs = acteurs;
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
    public Set<Film> getFilmsRole() {
        return filmsRole;
    }

    public void setFilmsRole(Set<Film> filmsRole) {
        this.filmsRole = filmsRole;
    }

    public Set<Acteur> getActeurs() {
        return acteurs;
    }

    public void setActeurs(Set<Acteur> acteurs) {
        this.acteurs = acteurs;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", films=" + filmsRole +
                ", acteurs=" + acteurs +
                '}';
    }
}
