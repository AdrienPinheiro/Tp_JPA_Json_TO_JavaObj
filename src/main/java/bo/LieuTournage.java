package bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Filming location object
 */
@Entity
@Table(name = "filming_locations")
public class LieuTournage {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty("ville")
    @Column(name = "city", nullable = false)
    private String city;

    @JsonProperty("etatDept")
    @Column(name = "department", nullable = false)
    private String department;

    @JsonProperty("pays")
    @Column(name = "country", nullable = false)
    private String country;

    @OneToMany(mappedBy = "filmingLocations")
    private Set<Film> films = new HashSet<>();

    /**
     * Filming location constructor simple
     */
    public LieuTournage() {
    }

    /**
     * Filming location constructor without id
     * @param city the city
     * @param department the department
     * @param country the country
     * @param films all film of filming location
     */
    public LieuTournage(String city, String department, String country, Set<Film> films) {
        this.city = city;
        this.department = department;
        this.country = country;
        this.films = films;
    }

    /**
     * Filming location constructor with id
     * @param id generate id with BDD
     * @param city the city
     * @param department the department
     * @param country the country
     * @param films all film of filming location
     */
    public LieuTournage(long id, String city, String department, String country, Set<Film> films) {
        this.id = id;
        this.city = city;
        this.department = department;
        this.country = country;
        this.films = films;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    @Override
    public String toString() {
        return "LieuTournage{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", department='" + department + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
