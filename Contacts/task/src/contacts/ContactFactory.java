package contacts;

import java.util.Scanner;

public interface ContactFactory {
    Contact createContact(Scanner scanner);
}
