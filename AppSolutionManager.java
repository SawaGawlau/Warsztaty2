package Apps;


import Model.Solution;
import ModelDAO.SolutionDao;

import java.util.Scanner;

class AppSolutionManager {
    private static final Scanner SCANNER = new Scanner(System.in);

    static void solution() {
        System.out.println();
        printMenu();
        boolean option = false;
        while (!option) {
            String choice = SCANNER.nextLine();
            switch (choice) {
                    case "add":
                    addUserToExercise();
                    break;
                case "view":
                    int userId = chooseUserId();
                    viewUserSolutions(userId);
                    break;
                default:
                    System.out.print("spróbuj jeszcze raz: ");
            }
        }
    }

    private static void addUserToExercise() {
        int userId = chooseUserId();
        int exerciseId = printAllExercises();
        Solution solution = new Solution(exerciseId, userId);
        SolutionDao solutionDao = new SolutionDao();
        solutionDao.create(solution);
        System.out.println("Do użytkownika dodano nowe zadanie");
    }

    static void viewUserSolutions(int userId) {
        System.out.println("Użytkownik posiada następujące rozwiązania: ");
        SolutionDao solutionDao = new SolutionDao();
        for (Solution solution : solutionDao.findAllByUserId(userId)) {
            String description = solution.getDescription();
            if (description == null) {
                description = "Brak rozwiązania";
            }
            int exerciseId = solution.getExercise_id();
            System.out.println(String.format("ID zadania: %s, rozwiązanie: %s", exerciseId, description));
        }
        System.out.println();
    }


    private static int chooseUserId() {
        AppUsersManager.printAll();
        System.out.print("Wybierz id: ");
        int id = SCANNER.nextInt();
        return id;
    }

    private static int printAllExercises() {
        ExerciseManager.printAll();
        System.out.print("Wybierz id: ");
        int id = SCANNER.nextInt();
        return id;
    }

    static void printMenu() {
        System.out.println("Wybierz opecje:\n" +
                "add – dodanie użytkownika,\n" +
                "edit – edycja użytkownika,\n" +
                "delete – usunięcie użytkownika,\n" +
                "return – powrót do menu głównego.\n");
        System.out.print("Wybierz: ");
    }


}