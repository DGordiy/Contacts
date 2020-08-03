package contacts;

import java.util.Scanner;

public class PersonFactory implements ContactFactory{
    @Override
    public Person createContact(Scanner scanner) {
        return Person.createContact(scanner);
    }
}
