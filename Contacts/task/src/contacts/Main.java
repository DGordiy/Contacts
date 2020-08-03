package contacts;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        if (args.length > 0) {
            try {
                phoneBook.setDataFileName(args[0]);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        phoneBook.selectAction();
    }
}
