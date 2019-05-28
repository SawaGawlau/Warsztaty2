package Apps;
import Model.Exercise;
import ModelDAO.ExerciseDao;

import java.util.Scanner;

class ExerciseManager {
    private static final Scanner SCANNER = new Scanner(System.in);

    static void exerciseManager() {
        printMenu();
        boolean option = false;
        while (!option) {
            String choice = SCANNER.nextLine();
            switch (choice) {
                case "add":
                    addExercise();
                    break;
                case "edit":
                    editExercise();
                    break;
                case "delete":
                    deleteExercise();
                    break;
                default:
                    System.out.print("spróbuj jeszcze raz: ");
            }
        }
    }

    private static void addExercise() {
        System.out.print("Podaj tytuł zadania: ");
        String title = SCANNER.nextLine();
        System.out.print("Podaj opis: ");
        String description = SCANNER.nextLine();
        Exercise exercise = new Exercise(title, description);
        ExerciseDao exerciseDao = new ExerciseDao();
        exerciseDao.create(exercise);
        System.out.println("Dodano nowe zadanie");
    }

    private static void editExercise() {
        ExerciseDao exerciseDao = new ExerciseDao();
        exerciseDao.printAll();
        System.out.print("\nPodaj id zadania do edycji: ");
        int id = SCANNER.nextInt();
        Exercise exercise = exerciseDao.read(id);
        if (exercise != null) {
            System.out.print("Podaj tytuł: ");
            exercise.setTitle(SCANNER.nextLine());
            System.out.print("Podaj opis: ");
            exercise.setDescription(SCANNER.nextLine());
            exerciseDao.update(exercise);
            System.out.println("\nZadanie zostało zmienione\n");
        } else {
            System.out.println("Błędnie podany numer id\n");
        }
    }

    private static void deleteExercise() {
        ExerciseDao exerciseDao = new ExerciseDao();
        printAll();
        System.out.println("Podaj id: ");
        int id=SCANNER.nextInt();
        if (exerciseDao.read(id) != null) {
            exerciseDao.delete(id);
            System.out.println("Zadanie zostało usunięte");
        } else {
            System.out.println("Błędnie  numer");
        }
    }

    private static void printMenu() {
        // wyświetlanie menu
        System.out.println("Wybierz jedną z opcji:\n" +
                "add – dodanie zadania,\n" +
                "edit – edycja zadania,\n" +
                "delete – usunięcie zadania,\n" +
                "return – powrót do menu głównego.\n");
        System.out.print("Wybierz: ");
    }


    static void printAll() {
        System.out.println("\nWszystkie zadania: ");
        ExerciseDao exerciseDao = new ExerciseDao();
        for (Exercise exercise : exerciseDao.printAll()) {
            int id = exercise.getId();
            String title = exercise.getTitle();
            String description = exercise.getDescription();
            System.out.println(String.format("ID: %s, tytuł: %s, opis: %s", id, title, description));
        }
    }
}