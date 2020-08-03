package contacts;

public class SearchActionMenu extends ActionMenu {
    SearchActionMenu(PhoneBook phoneBook) {
        super(phoneBook);
    }

    @Override
    public void selectAction() {
        System.out.print("Enter action ([number], back, again):");
        String action = phoneBook.scanner.nextLine();

        switch (action.toUpperCase()) {
            case "BACK":
                System.out.println();
                return;
            case "AGAIN":
                phoneBook.searchContacts();
                break;
            default:
                phoneBook.showInfo(Integer.parseInt(action));
        }

        System.out.println();
    }
}
