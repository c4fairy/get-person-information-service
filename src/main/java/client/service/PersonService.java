package client.service;

import client.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import util.PersonUtil;

import java.util.Objects;

@Slf4j
@Service
public class PersonService {

    private final String url = "http://localhost:8081/persons";

    public String[] getSurnames(RestTemplate restTemplate) {
        log.info("Получение всех фамилий:");
        return PersonUtil.getPersonSurname(Objects.requireNonNull(restTemplate
                .getForObject(url, Person[].class)));
    }

    public void createNewPerson(RestTemplate restTemplate, Person person) {
        log.info("Создание новой записи Person:");
        restTemplate.postForEntity(url, person, Person.class);
    }

    public void changePerson(RestTemplate restTemplate, Long id, Person newPerson) {
        log.info("Изменение существующей записи Person:");
        restTemplate.put(url + "/" + id, newPerson);
    }

    public void getAllPersons(RestTemplate restTemplate) {
        log.info("Получение всех Person");
        restTemplate.getForObject(url, Person[].class);
    }
}
