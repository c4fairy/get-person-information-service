package client;

import client.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import util.PersonUtil;
@Slf4j
public class SimpleClientRestApplication {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        Person person3 = new Person(3l, "Name3 Surname3", "Some address3");
        Person person4 = new Person("Name4 Surname4", "Some address4");
        Person person5 = new Person("Name5 Surname5", "Some address5");
        restTemplate.postForEntity("http://localhost:8080//persons/", person3, Person.class);
        log.info("Создание новой записи Person");
        restTemplate.postForEntity("http://localhost:8080//persons/", person4, Person.class);
        log.info("Создание новой записи Person");
        restTemplate.put("http://localhost:8080//persons/" + person3.getId(), person5);
        log.info("Изменение существующей записи Person");
        Person[] response = restTemplate
                .getForObject("http://localhost:8080//getAllPersons", Person[].class);
        log.info("Получение всех Person");
        assert response != null;
        String[] surnames = PersonUtil.getPersonSurname(response);
        for (String surname : surnames) System.out.println("Person Surname:" + surname);
    }
}
