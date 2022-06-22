package client.controller;

import client.config.ConnectionUrlConfig;
import client.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/persons")
public class PersonController {
    private final RestTemplate restTemplate = new RestTemplate();
    private final ConnectionUrlConfig cfg;

    @GetMapping
    List<Person> getAllPersons(RestTemplate restTemplate) {
        Person[] persons = restTemplate.getForObject(cfg.getClient(), Person[].class);
        List<Person> personList = new ArrayList<>(Arrays.asList(persons));
        return personList;
    }

    @PostMapping
    Person createNewPerson(@RequestBody Person newPerson) {
        return restTemplate.postForEntity(cfg.getClient(), newPerson, Person.class).getBody();
    }

    @GetMapping("/{id}")
    Person findPersonById(@PathVariable Long id) {
        return restTemplate.getForObject(cfg.getClient() + id, Person.class);
    }


    @PutMapping("/{id}")
    Person replacePerson(@RequestBody Person newPerson, @PathVariable Long id) {
        restTemplate.put(cfg.getClient() + id, newPerson);
        return findPersonById(id);
    }


    @DeleteMapping("/{id}")
    void deletePersonById(@PathVariable Long id) {
        restTemplate.delete(cfg.getClient()  + id);
    }
}
