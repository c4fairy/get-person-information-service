package client;

import client.model.Person;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import util.PersonUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ClientController {

    RestTemplate restTemplate = new RestTemplate();
    @GetMapping("/getAllPersons")
    List<Person> getAllPersons(RestTemplate restTemplate) {
        Person[] persons = restTemplate.getForObject("http://localhost:8080//persons/", Person[].class);
        List<Person> personList = new ArrayList<>(Arrays.asList(persons));
        return personList;
    }

    @PostMapping("/createNewPerson")
    Person createNewPerson(@RequestBody Person newPerson) {
        return restTemplate.postForEntity("http://localhost:8080//persons/", newPerson, Person.class).getBody();
    }


    @GetMapping("/findPersonById/{id}")
    Person findPersonById(@PathVariable Long id) {
        return restTemplate.getForObject("http://localhost:8080//persons/" + id, Person.class);
    }


    @PutMapping("/changePersonById/{id}")
    Person replacePerson(@RequestBody Person newPerson, @PathVariable Long id) {
       restTemplate.put("http://localhost:8080//persons/" + id, newPerson);
       return findPersonById(id);
    }


    @DeleteMapping("/deletePersonById/{id}")
    void deletePersonById(@PathVariable Long id){
        restTemplate.delete("http://localhost:8080//persons/" + id);
    }

    @GetMapping("/getPersonsSurname")
    String[] surnames(){
        Person[] response = restTemplate
                .getForObject("http://localhost:8080//persons", Person[].class);
        return PersonUtil.getPersonSurname(response);
    }
}
