package client;

import client.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;
import util.PersonUtil;
@Slf4j
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableJpaRepositories
public class SimpleClientRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleClientRestApplication.class, args);
        RestTemplate restTemplate = new RestTemplate();
        Person person3 = new Person(3l, "Name3 Surname3", "Some address3");
        Person person4 = new Person("Name4 Surname4", "Some address4");
        Person person5 = new Person("Name5 Surname5", "Some address5");

        log.info("Создание новой записи Person:");
        restTemplate.postForEntity("http://localhost:8081//createNewPerson/", person3, Person.class);

        log.info("\nСоздание новой записи Person:");
        restTemplate.postForEntity("http://localhost:8081//createNewPerson/", person4, Person.class);


        log.info("\nИзменение существующей записи Person:");
        restTemplate.put("http://localhost:8081//changePersonById/" + person3.getId(), person5);

        log.info("\nПолучение всех Person");
        Person[] response = restTemplate
                .getForObject("http://localhost:8081//getAllPersons", Person[].class);

        assert response != null;
        String[] surnames = PersonUtil.getPersonSurname(response);
        for (String surname : surnames) System.out.println("Person Surname:" + surname);

    }
}
