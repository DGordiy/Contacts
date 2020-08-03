package contacts;

public class MainActionMenu extends ActionMenu {
    MainActionMenu(PhoneBook phoneBook) {
        super(phoneBook);
    }

    @Override
    public void selectAction() {
        System.out.print("Enter action (add, list, search, count, exit):");
        String action = phoneBook.scanner.nextLine();
        switch (action.toUpperCase()) {
            case "ADD":
                phoneBook.addContact();
                break;
            case "LIST":
                phoneBook.showContactList();
                break;
            case "SEARCH":
                phoneBook.searchContacts();
                break;
            case "COUNT":
                phoneBook.countContacts();
                break;
            case "EXIT":
                return;
            default:
                System.out.println("Invalid command!\n");
        }

        selectAction();
    }
}
