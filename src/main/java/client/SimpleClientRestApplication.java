package client;

import client.config.ConfigProperties;
import client.model.Person;
import client.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SimpleClientRestApplication {
    public static void main(String[] args) {

        ClientService clientService = new ClientService();

        SpringApplication.run(SimpleClientRestApplication.class, args);
        RestTemplate restTemplate = new RestTemplate();
        Person person3 = new Person(3l, "Name3 Surname3", "Some address3");
        Person person4 = new Person("Name4 Surname4", "Some address4");
        Person person5 = new Person("Name5 Surname5", "Some address5");

        clientService.createNewPerson(restTemplate, person3);
        clientService.createNewPerson(restTemplate, person4);
        clientService.changePerson(restTemplate, person3.getId(), person5);
        clientService.getAllPersons(restTemplate);
        String[] surnames = clientService.getSurnames(restTemplate);
        for (String surname : surnames) System.out.println(surname);
    }
}
