package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

abstract class Contact implements Serializable {
    private static final long serialVersionUID = 2L;

    protected String name;
    private String phoneNumber;

    private LocalDateTime timeCreated;
    private LocalDateTime timeLastEdit;

    public Contact() {
        this.phoneNumber = "";
        timeCreated = LocalDateTime.now();
        timeLastEdit = timeCreated;
    }

    abstract protected void showAdditionalInfo();
    abstract public void editData(Scanner scanner);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return hasNumber() ? phoneNumber : "[no number]";
    }

    public void setPhoneNumber(String phoneNumber) {
        if (isCorrectNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = "";
            System.out.println("Wrong number format!");
        }
    }

    public boolean hasNumber() {
        return !phoneNumber.isEmpty();
    }

    private boolean isCorrectNumber(String number) {
        return number.matches("^(\\+?(\\d|\\([0-9a-zA-Z]{2,}\\))|[0-9a-zA-Z]{2,})([ -]([0-9a-zA-Z]{2,}|\\([0-9a-zA-Z]{2,}\\)))?([ -]([0-9a-zA-Z]{2,}))*$")
                && !number.matches(".*\\(.*\\(.*");
    }

    protected void setTimeLastEdit() {
        timeLastEdit = LocalDateTime.now();
    }

    public void showInfo() {
        showAdditionalInfo();

        System.out.println("Number: " + getPhoneNumber());
        System.out.println("Time created: " + timeCreated.withNano(0));
        System.out.println("Time last edit: " + timeLastEdit.withNano(0));

        System.out.println();
    }

    public static Contact createContact(Scanner scanner) {
        System.out.print("Enter the type (person, organization):");
        String contactType = scanner.nextLine();

        ContactFactory contactFactory = null;

        switch (contactType.toUpperCase()) {
            case "PERSON":
                contactFactory = new PersonFactory();
                break;
            case "ORGANIZATION":
                contactFactory = new OrganizationFactory();
                break;
            default:
                System.out.println("Incorrect contact type!\n");
                return null;
        }

        Contact newContact = contactFactory.createContact(scanner);
        System.out.println("The record added.\n");

        return newContact;
    }

    public List<String> recordFields() {
        List lst = new ArrayList<>();
        lst.add("name");
        lst.add("phoneNumber");

        return lst;
    }

    public String fieldToString(String fieldName) {
        switch (fieldName) {
            case "name":
                return name;
            case "phoneNumber":
                return phoneNumber;
            default:
                return "";
        }
    }

    protected void commitEditing() {
        setTimeLastEdit();
        System.out.println("Saved");
    }
}
