package contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneBook implements MenuSelectable, Serializable {
    private static final long serialVersionUID = 1L;

    private transient List<Contact> contactList;
    public final transient Scanner scanner;

    private final transient ActionMenu actionMenu;
    private final transient ActionMenu searchActionMenu;
    private final transient ActionMenu contactActionMenu;
    private final transient ActionMenu listActionMenu;

    private transient Contact currentContact = null;
    private transient List<Contact> searchResult = null;

    private transient String dataFileName = "phonebook.db";

    public PhoneBook() {
        this.contactList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.actionMenu = new MainActionMenu(this);
        this.searchActionMenu = new SearchActionMenu(this);
        this.contactActionMenu = new ContactActionMenu(this);
        this.listActionMenu = new ListActionMenu(this);
    }

    public final void addContact() {
        Contact newContact = Contact.createContact(scanner);
        if (newContact != null) {
            contactList.add(newContact);
            saveChanges();
        }
    }

    /*public final void removeContact() {
        if (contactList.isEmpty()) {
            System.out.println("No records to remove!");
            return;
        }

        showContactList();
        System.out.print("Select a record:");
        int index = Integer.parseInt(scanner.nextLine());
        if (index > 0 && index <= contactList.size()) {
            contactList.remove(index - 1);
            System.out.println("The record removed!");
        } else {
            System.out.println("Incorrect index of record!");
        }
    }

     */

    public final void removeContact() {
        if (contactList.remove(currentContact)) {
            System.out.println("The record removed!");
        } else {
            System.out.println("Error! The record not removed!");
        }
    }

    public final void showContactList() {
        if (contactList.isEmpty()) {
            System.out.println("Contact list is empty!\n");
            return;
        }

        int index = 0;
        for (Contact contact : contactList) {
            System.out.println(++index + ". " + contact);
        }
        System.out.println();

        listActionMenu.selectAction();
    }

    /*public final void showInfo() {
        showContactList();
        if (contactList.isEmpty()) {
            return;
        }

        System.out.print("Enter index to show info:");

        int index = Integer.parseInt(scanner.nextLine());
        if (index > 0 && index <= contactList.size()) {
            contactList.get(index - 1).showInfo();
        } else {
            System.out.println("Incorrect index of record!");
        }
    }*/

    public final void showInfo(int index) {
        if (searchResult == null || index > 0 && index <= searchResult.size()) {
            if (searchResult != null) {
                currentContact = searchResult.get(index - 1);
            } else {
                currentContact = contactList.get(index - 1);
            }

            currentContact.showInfo();

            contactActionMenu.selectAction();
        } else {
            System.out.println("Incorrect index of record!");
        }
    }

    public final void countContacts() {
        System.out.println("The Phone Book has " + contactList.size() + " records.\n");
    }

    /*public final void editContact() {
        if (contactList.isEmpty()) {
            System.out.println("No records to edit!");
            return;
        }

        showContactList();
        System.out.print("Select a record:");
        int index = Integer.parseInt(scanner.nextLine());
        if (index > 0 && index <= contactList.size()) {
            Contact contact = contactList.get(index - 1);
            contact.editData(scanner);
        } else {
            System.out.println("Incorrect index of record!");
        }

    }

     */

    public final void editContact() {
        currentContact.editData(scanner);
        saveChanges();
        currentContact.showInfo();
        contactActionMenu.selectAction();
    }

    public final void searchContacts() {
        System.out.print("Enter search query:");
        String query = scanner.nextLine();

        //Results
        makeSearchResult(query);
        System.out.println("Found " + searchResult.size() + " results:");
        for (int i = 0; i < searchResult.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, searchResult.get(i)));
        }
        System.out.println();

        if (searchResult.size() > 0) {
            searchActionMenu.selectAction();
        }
    }

    @Override
    public void selectAction() {
        actionMenu.selectAction();
    }

    public void makeSearchResult(String query) {
        searchResult = new ArrayList<>();

        for (Contact contact : contactList) {
            for (String fieldName : contact.recordFields()) {
                if (contact.fieldToString(fieldName).toUpperCase().matches("(?i).*" + query + ".*")) {
                    searchResult.add(contact);
                }
            }
        }
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();

        Object obj = ois.readObject();
        while (obj != null) {
            contactList.add((Contact) obj);
        }
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        for (Contact contact : contactList) {
            oos.writeObject(contact);
        }
    }

    private void saveChanges() {
        try (FileOutputStream fos = new FileOutputStream(dataFileName); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            writeObject(oos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDataFileName(String dataFileName) throws Exception {
        try (FileInputStream fis = new FileInputStream(dataFileName); ObjectInputStream ois = new ObjectInputStream(fis)) {
            readObject(ois);
        } catch (Exception e) {
            throw e;
        }

        this.dataFileName = dataFileName;
    }
}
