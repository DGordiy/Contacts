package contacts;

import java.util.Scanner;

public class OrganizationFactory implements ContactFactory {
    @Override
    public Contact createContact(Scanner scanner) {
        return Organization.createContact(scanner);
    }
}
