package free.tests.dao;

import free.tests.entity.Country;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

@Repository
public class CommonDaoImp implements CommonDao {

    @PersistenceContext
    private EntityManager em;

    private CriteriaBuilder criteriaBuilder = null;

    @PostConstruct
    public void init() {
        if (null == this.em)
            System.out.println("Entity Manager instance em is null");
        else
            criteriaBuilder = em.getCriteriaBuilder();
    }

    @Override
    public Country selectUsingCriteriaApi(String countryCode) {
        CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(Country.class);
        Root<Country> employee = criteriaQuery.from(Country.class);
        ParameterExpression<String> parameterExpression = criteriaBuilder.parameter(String.class);
        criteriaQuery.select(employee).where(criteriaBuilder.equal(employee.get("countryCode"), parameterExpression));
        TypedQuery<Country> typedQuery = em.createQuery(criteriaQuery);
        typedQuery.setParameter(parameterExpression, "US");
        return typedQuery.getSingleResult();
    }

}
