package free.checker.jpql;

import free.checker.jpql.config.AppConfig;
import free.checker.jpql.model.Person;
import free.checker.jpql.service.PersonService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
	
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        PersonService personService = context.getBean(PersonService.class);

        List<Person> persons = personService.listPersons();

        persons.forEach(person -> {
            System.out.println(person);
        });

        context.close();
		
    }

}
