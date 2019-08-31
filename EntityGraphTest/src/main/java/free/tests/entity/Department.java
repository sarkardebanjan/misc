package free.tests.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DEPARTMENT")
public class Department {

    @Id
    @Column(name = "DEPT_CODE")
    private String deptCode;

    @Column(name = "DEPT_NAME")
    private String deptName;

    @ManyToOne
    @JoinColumn(name = "COMP_CODE", referencedColumnName = "COMP_CODE")
    private Company company;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Person> people;

    public Department() {
    }

    public Department(String deptCode, String deptName, Company company, List<Person> people) {
        this.deptCode = deptCode;
        this.deptName = deptName;
        this.company = company;
        this.people = people;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptCode='" + deptCode + '\'' +
                ", deptName='" + deptName + '\'' +
                ", people=" + people.toString() +
                '}';
    }
}
