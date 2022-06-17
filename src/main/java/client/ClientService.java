package client;

import client.config.ConfigProperties;
import client.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import util.PersonUtil;

import java.util.Objects;

@Slf4j
@Service
public class ClientService {
    ConfigProperties cfg;

    public String[] getSurnames(RestTemplate restTemplate) {
        log.info("Получение всех фамилий:");
        return PersonUtil.getPersonSurname(Objects.requireNonNull(restTemplate
                .getForObject(cfg.getUrl(), Person[].class)));
    }

    public void createNewPerson(RestTemplate restTemplate, Person person) {
        log.info("Создание новой записи Person:");
        restTemplate.postForEntity(cfg.getUrl(), person, Person.class);
    }

    public void changePerson(RestTemplate restTemplate, Long id, Person newPerson) {
        log.info("Изменение существующей записи Person:");
        restTemplate.put(cfg.getUrl() + "/" + id, newPerson);
    }

    public void getAllPersons(RestTemplate restTemplate) {
        log.info("Получение всех Person");
        restTemplate.getForObject(cfg.getUrl(), Person[].class);
    }
}
