package contacts;

abstract class ActionMenu {

    protected PhoneBook phoneBook;

    private ActionMenu() {}

    ActionMenu(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }

    abstract void selectAction();

}
