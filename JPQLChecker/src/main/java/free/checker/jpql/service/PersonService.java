package free.checker.jpql.service;

import free.checker.jpql.model.Person;

import java.util.List;

public interface PersonService {

    void add(Person person);

    List<Person> listPersons();

}
