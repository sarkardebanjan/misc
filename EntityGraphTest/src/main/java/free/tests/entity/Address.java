package free.tests.entity;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "AREA")
    private String area;

    @Column(name = "CITY")
    private String city;

    @ManyToOne
    private Country country;

    @ManyToOne
    private Person person;

    public Address() {
    }

    public Address(String area, String city, Country country) {
        this.area = area;
        this.city = city;
        this.country = country;
    }

    public Address(String area, String city, Country country, Person person) {
        this.area = area;
        this.city = city;
        this.country = country;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", area='" + area + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country.toString() + '\'' +
                '}';
    }
}
