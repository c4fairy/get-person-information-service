package util;


import client.Person;

public class PersonUtil {
    public static String[] getPersonSurname(Person[] persons) {
        int count = persons.length;
        String[] surnames = new String[count];
        for (int i = 0; i < count; i++) {
            surnames[i] = persons[i].getName().split(" ")[1];
        }
        return surnames;
    }
}
