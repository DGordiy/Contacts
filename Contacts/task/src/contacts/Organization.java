package contacts;

import java.util.List;
import java.util.Scanner;

public class Organization extends Contact {

    private String address;

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    protected void showAdditionalInfo() {
        System.out.println("Organization name: " + name);
        System.out.println("Address: " + address);
    }

    public static Organization createContact(Scanner scanner) {
        Organization organization = new Organization();

        System.out.print("Enter the organization name:");
        organization.setName(scanner.nextLine());
        System.out.print("Enter the address:");
        organization.address = scanner.nextLine();
        System.out.print("Enter the number:");
        organization.setPhoneNumber(scanner.nextLine());

        return organization;
    }

    @Override
    public void editData(Scanner scanner) {
        System.out.print("Select a field (name, address, number):");
        String fieldName = scanner.nextLine();
        String newValue;
        switch (fieldName.toUpperCase()) {
            case "NAME":
                System.out.print("Enter organization name:");
                newValue = scanner.nextLine();
                setName(newValue);
                break;
            case "ADDRESS":
                System.out.print("Enter address:");
                newValue = scanner.nextLine();
                setAddress(newValue);
                break;
            case "NUMBER":
                System.out.print("Enter number:");
                newValue = scanner.nextLine();
                setPhoneNumber(newValue);
                break;
            default:
                System.out.println("Incorrect field name!");
                return;
        }

        commitEditing();
    }

    @Override
    public List<String> recordFields() {
        List lst = super.recordFields();
        lst.add("address");

        return lst;
    }

    @Override
    public String fieldToString(String fieldName) {
        switch (fieldName) {
            case "address":
                return address;
            default:
                return super.fieldToString(fieldName);
        }
    }
}
