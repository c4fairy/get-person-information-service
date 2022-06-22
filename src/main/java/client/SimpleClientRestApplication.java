package client;

import client.config.ConnectionUrlConfig;
import client.model.Person;
import client.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SimpleClientRestApplication {
    public static void main(String[] args) {

        PersonService personService = new PersonService();

        SpringApplication.run(SimpleClientRestApplication.class, args);
        RestTemplate restTemplate = new RestTemplate();
        Person person3 = new Person(3l, "Name3 Surname3", "Some address3");
        Person person4 = new Person("Name4 Surname4", "Some address4");
        Person person5 = new Person("Name5 Surname5", "Some address5");

        personService.createNewPerson(restTemplate, person3);
        personService.createNewPerson(restTemplate, person4);
        personService.changePerson(restTemplate, person3.getId(), person5);
        personService.getAllPersons(restTemplate);
        String[] surnames = personService.getSurnames(restTemplate);
        for (String surname : surnames) System.out.println(surname);
    }

    @Bean
    public ConnectionUrlConfig getProps() {
        return new ConnectionUrlConfig();
    }
}
