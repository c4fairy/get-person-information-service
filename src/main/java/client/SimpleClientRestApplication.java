package client;

import org.springframework.web.client.RestTemplate;
import util.PersonUtil;

public class SimpleClientRestApplication {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        Person[] response = restTemplate
                .getForObject("http://localhost:8080//persons/", Person[].class);
        assert response != null;
        String[] surnames = PersonUtil.getPersonSurname(response);
        for (String surname : surnames) System.out.println("Person Surname:" + surname);
    }
}
