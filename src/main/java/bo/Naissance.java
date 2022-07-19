package bo;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Naissance object
 */
@Embeddable
public class Naissance {
    @JsonProperty("dateNaissance")
    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @JsonProperty("lieuNaissance")
    @Column(name = "placeOfBirth")
    private String placeOfBirth;


    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    @Override
    public String toString() {
        return "{" +
                "date de naissance = '" + dateOfBirth + '\'' +
                ", lieu de naissance = '" + placeOfBirth + '\'' +
                '}';
    }
}
