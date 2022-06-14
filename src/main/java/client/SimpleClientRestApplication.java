package client;

import org.springframework.web.client.RestTemplate;
import util.PersonUtil;

public class SimpleClientRestApplication {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        Person person3 = new Person("Name3 Surname3", "Some address3");
        Person person4 = new Person("Name4 Surname4", "Some address4");
        Person person5 = new Person("Name5 Surname5", "Some address5");
        restTemplate.postForEntity("http://localhost:8080//persons/", person3, Person.class);
        restTemplate.postForEntity("http://localhost:8080//persons/", person4, Person.class);
        restTemplate.put("http://localhost:8080//persons/3", person5);
        Person[] response = restTemplate
                .getForObject("http://localhost:8080//persons/", Person[].class);
        assert response != null;
        String[] surnames = PersonUtil.getPersonSurname(response);
        for (String surname : surnames) System.out.println("Person Surname:" + surname);
    }
}
