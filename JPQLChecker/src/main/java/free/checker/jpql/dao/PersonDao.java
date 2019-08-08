package free.checker.jpql.dao;

import free.checker.jpql.model.Person;

import java.util.List;

public interface PersonDao {

    void add(Person person);

    List<Person> listPersons();

}
