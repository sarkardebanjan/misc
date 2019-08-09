package free.checker.jpql;

import free.checker.jpql.config.AppConfig;
import free.checker.jpql.model.Person;
import free.checker.jpql.service.PersonService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;

//@ExtendWith(SpringExtension.class) //Works for JUnit 5+
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
//for multiple: @ContextConfiguration(classes = {AppConfig.class, AppConfig2.class})
//for xml configuration: @ContextConfiguration(locations = {"classpath:pathTo/appConfig.xml", "classpath:pathTo/appConfig2.xml"})
public class TestQueries {

    @Resource(name = "personServiceImp")
    private PersonService personService;

    @Test
    public void testListPeople() {
        System.out.println("Inside testListPeople");
        List<Person> people = personService.listPersons();
        assertNotNull(people);
        assertEquals(4, people.size());
    }


}
