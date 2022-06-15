package client;

import client.model.Person;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientController {

    @GetMapping("/getAllPersons")
    List<Person> getAllPersons(RestTemplate restTemplate) {
        Person[] persons = restTemplate.getForObject("http://localhost:8080//persons/", Person[].class);
        List<Person> personList = new ArrayList<>(Arrays.asList(persons));
        return personList;
    }

    @PostMapping("/createNewPerson")
    Person createNewPerson(@RequestBody Person newPerson, RestTemplate restTemplate) {
        return restTemplate.postForEntity("http://localhost:8080//persons/", newPerson, Person.class).getBody();
    }


    @GetMapping("/findPersonById")
    Person findPersonById(@PathVariable Long id, RestTemplate restTemplate) {
        return restTemplate.getForObject("http://localhost:8080//persons/" + id, Person.class);
    }


    @PutMapping("/changePersonById")
    Person replacePerson(@RequestBody Person newPerson, @PathVariable Long id, RestTemplate restTemplate) {
       restTemplate.put("http://localhost:8080//persons/" + id, newPerson);
       return findPersonById(id, restTemplate);
    }


    @DeleteMapping("/deletePersonById")
    void deletePersonById(@PathVariable Long id, RestTemplate restTemplate){
        restTemplate.delete("http://localhost:8080//persons/" + id);
    }
}
