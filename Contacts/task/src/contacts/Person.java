package contacts;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

enum Gender {
    M,
    F
}

public class Person extends Contact {

    private String surname;

    private LocalDate birthDate;
    private Gender gender;

    private Person(){}

    private void setBirthDate(String birthDate) {
        try {
            this.birthDate = LocalDate.parse(birthDate);
        } catch (Exception e) {
            this.birthDate = null;
            System.out.println("Bad birth date!");
        }
    }

    private void setGender(String gender) {
        if (gender.toUpperCase().matches("[MF]")) {
            this.gender = Gender.valueOf(gender.toUpperCase());
        } else {
            this.gender = null;
            System.out.println("Bad gender!");
        }
    }

    public String getBirthDate() {
        if (birthDate != null) {
            return birthDate.toString();
        } else {
            return "[no data]";
        }
    }

    public String getGender() {
        if (gender != null) {
            return gender.toString();
        } else {
            return "[no data]";
        }
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }

    @Override
    protected void showAdditionalInfo() {
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Birth date: " + getBirthDate());
        System.out.println("Gender: " + getGender());
    }

    public static Person createContact(Scanner scanner) {
        Person person = new Person();

        System.out.print("Enter the name:");
        person.setName(scanner.nextLine());
        System.out.print("Enter the surname:");
        person.surname = scanner.nextLine();
        System.out.print("Enter the birth date:");
        person.setBirthDate(scanner.nextLine());
        System.out.print("Enter the gender (M, F):");
        person.setGender(scanner.nextLine());
        System.out.print("Enter the number:");
        person.setPhoneNumber(scanner.nextLine());

        return person;
    }

    @Override
    public void editData(Scanner scanner) {
        System.out.print("Select a field (name, surname, birth, gender, number):");
        String fieldName = scanner.nextLine();
        String newValue;
        switch (fieldName.toUpperCase()) {
            case "NAME":
                System.out.print("Enter name:");
                newValue = scanner.nextLine();
                setName(newValue);
                break;
            case "SURNAME":
                System.out.print("Enter surname:");
                newValue = scanner.nextLine();
                surname = newValue;
                break;
            case "BIRTH":
                System.out.print("Enter birth date:");
                newValue = scanner.nextLine();
                setBirthDate(newValue);
                break;
            case "GENDER":
                System.out.print("Enter gender:");
                newValue = scanner.nextLine();
                setGender(newValue);
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
        lst.add("surname");
        lst.add("birthDate");
        lst.add("gender");

        return lst;
    }

    @Override
    public String fieldToString(String fieldName) {
        switch (fieldName) {
            case "surname":
                return surname;
            case "birthDate":
                return getBirthDate();
            case "gender":
                return getGender();
            default:
                return super.fieldToString(fieldName);
        }
    }
}
