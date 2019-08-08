package free.skeletons.jpa;

import free.skeletons.jpa.config.AppConfig;
import free.skeletons.jpa.entity.Person;
import free.skeletons.jpa.service.PersonService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        PersonService personService = context.getBean(PersonService.class);

        /*
        personService.add(new Person("Dora", "Dora", "mig1@nomail.net"));
        personService.add(new Person("Maggie", "Frog", "mig2@nomail.net"));
        personService.add(new Person("Sam", "Silvy", "mig3@nomail.net"));
        personService.add(new Person("Pat", "Patterson", "mig4@nomail.net"));
        */

        List<Person> persons = personService.listPersons();

        persons.forEach(person -> {
            System.out.println(person);
        });

        context.close();
    }

}
