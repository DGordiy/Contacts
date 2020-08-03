package contacts;

public class ContactActionMenu extends ActionMenu {

    ContactActionMenu(PhoneBook phoneBook) {
        super(phoneBook);
    }

    @Override
    public void selectAction() {
        System.out.print("Enter action (edit, delete, menu):");
        String action = phoneBook.scanner.nextLine();
        switch (action.toUpperCase()) {
            case "EDIT":
                phoneBook.editContact();
                break;
            case "DELETE":
                phoneBook.removeContact();
                break;
            case "MENU":
                break;
            default:
                System.out.println("Invalid command!");
        }
    }
}
