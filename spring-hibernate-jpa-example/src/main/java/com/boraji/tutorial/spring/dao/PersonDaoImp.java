package com.boraji.tutorial.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.boraji.tutorial.spring.entity.Person;

/**
 * @author imssbora
 *
 */
@Repository
public class PersonDaoImp implements PersonDao {

   @PersistenceContext
   private EntityManager em;

   @Override
   public void add(Person person) {
      em.persist(person);
   }

   @Override
   public List<Person> listPersons() {
      CriteriaQuery<Person> criteriaQuery = em.getCriteriaBuilder().createQuery(Person.class);
      @SuppressWarnings("unused")
      Root<Person> root = criteriaQuery.from(Person.class);
      return em.createQuery(criteriaQuery).getResultList();
   }

}
