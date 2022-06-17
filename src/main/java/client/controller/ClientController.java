package client.controller;

import client.config.ConfigProperties;
import client.model.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ClientController {
    RestTemplate restTemplate = new RestTemplate();
    @Value("${url.client}")
    private String url;

    @GetMapping("/persons")
    List<Person> getAllPersons(RestTemplate restTemplate) {
        Person[] persons = restTemplate.getForObject(url, Person[].class);
        List<Person> personList = new ArrayList<>(Arrays.asList(persons));
        return personList;
    }

    @PostMapping("/persons")
    Person createNewPerson(@RequestBody Person newPerson) {
        return restTemplate.postForEntity(url, newPerson, Person.class).getBody();
    }

    @GetMapping("/persons/{id}")
    Person findPersonById(@PathVariable Long id) {
        return restTemplate.getForObject(url + id, Person.class);
    }


    @PutMapping("/persons/{id}")
    Person replacePerson(@RequestBody Person newPerson, @PathVariable Long id) {
        restTemplate.put(url + id, newPerson);
        return findPersonById(id);
    }


    @DeleteMapping("/persons/{id}")
    void deletePersonById(@PathVariable Long id) {
        restTemplate.delete("http://localhost:8080//persons/" + id);
    }
}
