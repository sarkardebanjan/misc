package free.skeletons.jpa.service;

import free.skeletons.jpa.dao.PersonDao;
import free.skeletons.jpa.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
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
