package contacts;

public class ListActionMenu extends ActionMenu {

    ListActionMenu(PhoneBook phoneBook) {
        super(phoneBook);
    }

    @Override
    public void selectAction() {
        System.out.print("Enter action ([number], back):");
        String action = phoneBook.scanner.nextLine();

        switch (action.toUpperCase()) {
            case "BACK":
                System.out.println();
                return;
            default:
                phoneBook.showInfo(Integer.parseInt(action));
        }

        System.out.println();
    }
}
