package free.skeletons.jpa.service;

import free.skeletons.jpa.entity.Person;

import java.util.List;

public interface PersonService {

    void add(Person person);

    List<Person> listPersons();

}
