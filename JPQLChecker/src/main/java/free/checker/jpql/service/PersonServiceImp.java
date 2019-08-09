package free.checker.jpql.service;

import free.checker.jpql.dao.PersonDao;
import free.checker.jpql.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("personServiceImp")
public class PersonServiceImp implements PersonService {

    @Autowired
    private PersonDao userDao;

    @Override
    @Transactional
    public void add(Person person) {
        userDao.add(person);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> listPersons() {
        return userDao.listPersons();
    }

}
