package free.tests.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "COMPANY")
public class Company {

    @Id
    @Column(name = "COMP_CODE")
    private String companyCode;

    @Column(name = "COMP_NAME")
    private String companyName;

    @Column(name = "COMP_TYPE")
    private String companyType;

    @ManyToOne
    @JoinColumn(name = "COUNTRY_CODE", referencedColumnName = "COUNTRY_CODE", insertable = true, updatable = true)
    private Country country;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Department> departments;

    public Company() {
    }

    public Company(String companyCode, String companyName, String companyType, Country country, List<Department> departments) {
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.companyType = companyType;
        this.country = country;
        this.departments = departments;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyType='" + companyType + '\'' +
                ", departments=" + departments.toString() +
                '}';
    }
}
