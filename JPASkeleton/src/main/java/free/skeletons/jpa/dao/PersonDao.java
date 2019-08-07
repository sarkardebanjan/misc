package free.skeletons.jpa.dao;

import free.skeletons.jpa.entity.Person;

import java.util.List;

public interface PersonDao {

    void add(Person person);

    List<Person> listPersons();

}
