package client.controller;

import client.config.ConfigProperties;
import client.model.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ClientController {
    RestTemplate restTemplate = new RestTemplate();
    @Inject
    ConfigProperties cfg;

    @GetMapping("/persons")
    List<Person> getAllPersons(RestTemplate restTemplate) {
        Person[] persons = restTemplate.getForObject(cfg.getClient(), Person[].class);
        List<Person> personList = new ArrayList<>(Arrays.asList(persons));
        return personList;
    }

    @PostMapping("/persons")
    Person createNewPerson(@RequestBody Person newPerson) {
        return restTemplate.postForEntity(cfg.getClient(), newPerson, Person.class).getBody();
    }

    @GetMapping("/persons/{id}")
    Person findPersonById(@PathVariable Long id) {
        return restTemplate.getForObject(cfg.getClient() + id, Person.class);
    }


    @PutMapping("/persons/{id}")
    Person replacePerson(@RequestBody Person newPerson, @PathVariable Long id) {
        restTemplate.put(cfg.getClient() + id, newPerson);
        return findPersonById(id);
    }


    @DeleteMapping("/persons/{id}")
    void deletePersonById(@PathVariable Long id) {
        restTemplate.delete("http://localhost:8080//persons/" + id);
    }
}
