package Apps;

import Model.User;
import ModelDAO.UserDao;

import java.util.Scanner;

class AppUsersManager {
    //dlaczego tu musiby byc final?
    private static final Scanner SCANNER = new Scanner(System.in);

    static void userManager() {
        printMenu();
        boolean option = false;
        while (!option) {
            String choice = SCANNER.nextLine();
            switch (choice) {
                case "add":
                    addUser();
                    break;
                case "edit":
                    editUser();
                    break;
                case "delete":
                    deleteUser();
                    break;
                default:
                    System.out.print("spróbuj jeszcze raz: ");
            }
        }
    }

    private static void addUser() {
        System.out.print("Podaj imię nowego uzytkownika: ");
        String name = SCANNER.nextLine();
        System.out.print("Podaj mail: ");
        String mail = SCANNER.nextLine();
        System.out.print("Podaj hasło: ");
        String password = SCANNER.nextLine();
        System.out.print("Podaj numer grupy: ");
        int group = SCANNER.nextInt();
        User user = new User(name, mail, password, group);
        UserDao userDao = new UserDao();
        if (userDao.create(user) != null) {
            System.out.println(String.format("\nDodano nowego użytkownika: '%s'\n", name));
        }
    }

    private static void editUser() {
        UserDao userDao = new UserDao();
        printAll();
        System.out.print("\nWybierz id użytkownika do edycji: ");
        int id = SCANNER.nextInt();
        User user = userDao.read(id);
        if (user != null) {
            System.out.print("Nowe imię: ");
            user.setUserName(SCANNER.nextLine());
            System.out.print("Nowy email: ");
            user.setEmail(SCANNER.nextLine());
            System.out.print("Nowe hasło: ");
            user.setPassword(SCANNER.nextLine());
            System.out.print("Nowa grupa: ");
            user.setGroupNumber(SCANNER.nextInt());
            userDao.update(user);
            System.out.println();
        } else {
            System.out.println("Błędnie podany numer id\n");
        }
    }

    private static void deleteUser() {
        // usuwanie użytkownika przy użyciu klasy UserDao
        UserDao userDao = new UserDao();
        printAll();
        System.out.print("\nPodaj id: ");
        int id = SCANNER.nextInt();
        if (userDao.read(id) != null) {
            userDao.delete(id);
            System.out.println("Użytkownik usunięty");
        } else {
            System.out.println("Nie ma uzytkwoanika o takim numerze id\n");
        }
    }
    static void printMenu() {
        // wyświetlanie menu
        System.out.println("Wybierz opecje:\n" +
                "add – dodanie użytkownika,\n" +
                "edit – edycja użytkownika,\n" +
                "delete – usunięcie użytkownika,\n" +
                "return – powrót do menu głównego.\n");
        System.out.print("Wybierz: ");
    }



    static void printAll() {
        System.out.println("\nWszyscy użytkownicy: ");
        UserDao userDao = new UserDao();
        for (User user : userDao.findAll()) {
            int id = user.getId();
            String name = user.getUserName();
            String email = user.getEmail();
            int group = user.getGroupNumber();
            System.out.println(String.format("ID: %s, nazwa użytkownika: %s, email: %s, grupa: %s", id, name, email, group));
        }
    }
}