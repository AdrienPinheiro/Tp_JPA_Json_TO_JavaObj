package bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "filming_locations", cascade = CascadeType.ALL)
    private Set<Film> films = new HashSet<>();

    public LieuTournage() {
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

    @JsonProperty("film")
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
