package free.tests.dao;

import free.tests.entity.Company;
import free.tests.entity.Country;
import free.tests.entity.Department;
import free.tests.entity.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Subgraph;
import java.util.Collections;

@Repository
public class CommonDaoImp implements CommonDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Country testEntityGraph(String countryCode) {

        EntityGraph<Country> countryEntityGraph = em.createEntityGraph(Country.class);
        countryEntityGraph.addAttributeNodes("countryCode", "name", "rating");

        Subgraph<Company> companySubgraph = countryEntityGraph.addSubgraph("companies");
        companySubgraph.addAttributeNodes("companyCode", "companyName", "companyType");

        Subgraph<Department> departmentSubgraph = companySubgraph.addSubgraph("departments");
        departmentSubgraph.addAttributeNodes("deptCode", "deptName");

        Subgraph<Person> personSubgraph = departmentSubgraph.addSubgraph("people");
        personSubgraph.addAttributeNodes("ssn", "firstName", "lastName", "email");

        Country country = em.find(Country.class, countryCode, Collections.singletonMap("javax.persistence.loadgraph", countryEntityGraph));
        //Resulting Query:
        //select country0_.COUNTRY_CODE as COUNTRY_CODE1_2_0_, country0_.NAME as NAME2_2_0_, country0_.RATING as RATING3_2_0_, companies1_.COUNTRY_CODE as COUNTRY_CODE4_1_1_, companies1_.COMP_CODE as COMP_CODE1_1_1_, companies1_.COMP_CODE as COMP_CODE1_1_2_, companies1_.COMP_NAME as COMP_NAME2_1_2_, companies1_.COMP_TYPE as COMP_TYPE3_1_2_, companies1_.COUNTRY_CODE as COUNTRY_CODE4_1_2_, department2_.COMP_CODE as COMP_CODE3_3_3_, department2_.DEPT_CODE as DEPT_CODE1_3_3_, department2_.DEPT_CODE as DEPT_CODE1_3_4_, department2_.COMP_CODE as COMP_CODE3_3_4_, department2_.DEPT_NAME as DEPT_NAME2_3_4_, people3_.DEPT_CODE as DEPT_CODE5_4_5_, people3_.SSN as SSN1_4_5_, people3_.SSN as SSN1_4_6_, people3_.DEPT_CODE as DEPT_CODE5_4_6_, people3_.EMAIL as EMAIL2_4_6_, people3_.FIRST_NAME as FIRST_NAME3_4_6_, people3_.LAST_NAME as LAST_NAME4_4_6_ from COUNTRY country0_, COMPANY companies1_, DEPARTMENT department2_, PERSON people3_ where country0_.COUNTRY_CODE=companies1_.COUNTRY_CODE(+) and companies1_.COMP_CODE=department2_.COMP_CODE(+) and department2_.DEPT_CODE=people3_.DEPT_CODE(+) and country0_.COUNTRY_CODE='US';

        //Country country = em.find(Country.class, countryCode, Collections.singletonMap("javax.persistence.fetchgraph", countryEntityGraph));
        //Resulting Query:
        //TODO
        return country;
    }

}
