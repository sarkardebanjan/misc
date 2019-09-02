package free.tests;

import free.tests.config.AppConfig;
import free.tests.entity.Country;
import free.tests.service.CommonService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        CommonService commonService = context.getBean(CommonService.class);
        Country country = commonService.selectUsingCriteriaApi("US");
        System.out.println("Country entity returned from selectUsingCriteriaApi: ");
        System.out.println(country.toString());
        context.close();
    }

}
