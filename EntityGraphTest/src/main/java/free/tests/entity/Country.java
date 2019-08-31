package free.tests.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "COUNTRY")
public class Country {

    @Id
    @Column(name = "COUNTRY_CODE")
    private String countryCode;

    @Column(name = "NAME")
    private String name;

    @Column(name = "RATING")
    private String rating;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Company> companies;

    public Country() {
    }

    public Country(String countryCode, String name, String rating, Set<Company> companies) {
        this.countryCode = countryCode;
        this.name = name;
        this.rating = rating;
        this.companies = companies;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryCode='" + countryCode + '\'' +
                ", name='" + name + '\'' +
                ", rating='" + rating + '\'' +
                ", companies=" + companies.toString() +
                '}';
    }
}
