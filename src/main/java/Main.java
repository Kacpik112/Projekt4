import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;

class Main {
  public static void main(String[] args) {
    try {
      Service s = new Service();
      Scanner scanner = new Scanner(System.in);
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

      boolean running = true;
      while (running) {
        System.out.println("\nWybierz opcję:");
        System.out.println("0 - Zakończ program");
        System.out.println("1 - Dodaj studenta");
        System.out.println("2 - Wyświetl wszystkich studentów");
        System.out.println("3 - Wyszukaj studentów po imieniu");
        System.out.println("4 - Usuń studenta");
        System.out.println("5 - Zaktualizuj dane studenta (zmień wiek)");
        System.out.print("Twój wybór: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
          case 0:
            System.out.println("Program został zakończony.");
            running = false;
            break;

          case 1:
            System.out.print("Podaj imię studenta: ");
            String name = scanner.nextLine();

            System.out.print("Podaj nazwisko studenta: ");
            String lastname = scanner.nextLine();

            int age = 0;
            boolean validAge = false;
            while (!validAge) {
              System.out.print("Podaj wiek studenta: ");
              try {
                age = Integer.parseInt(scanner.nextLine());
                validAge = true;
              } catch (NumberFormatException e) {
                System.out.println("Wiek musi być liczbą. Spróbuj ponownie.");
              }
            }

            // Walidacja daty urodzenia
            String birthDate = "";
            boolean validDate = false;
            while (!validDate) {
              System.out.print("Podaj datę urodzenia studenta (dd-MM-yyyy): ");
              String inputDate = scanner.nextLine();
              try {
                LocalDate date = LocalDate.parse(inputDate, formatter);
                if (date.isAfter(LocalDate.now())) {
                  System.out.println("Data urodzenia nie może być z przyszłości. Spróbuj ponownie.");
                } else {
                  birthDate = inputDate;
                  validDate = true;
                }
              } catch (DateTimeParseException e) {
                System.out.println("Niepoprawny format daty lub data nie istnieje. Spróbuj ponownie używając formatu dd-MM-yyyy.");
              }
            }

            s.addStudent(new Student(name, lastname, age, birthDate));
            System.out.println("Dodano studenta.");
            break;

          case 2:
            Collection<Student> students = s.getStudents();
            System.out.println("📋 Lista studentów:");
            for (Student current : students) {
              System.out.println(current);
            }
            break;

          case 3:
            System.out.print("Podaj imię do wyszukania: ");
            String searchName = scanner.nextLine();
            Collection<Student> foundStudents = s.findStudentByName(searchName);
            if (foundStudents.isEmpty()) {
              System.out.println("Brak studentów o podanym imieniu.");
            } else {
              System.out.println("Znaleziono następujących studentów:");
              for (Student student : foundStudents) {
                System.out.println(student);
              }
            }
            break;

          case 4:
            System.out.print("Podaj imię studenta do usunięcia: ");
            String delName = scanner.nextLine();
            System.out.print("Podaj nazwisko studenta do usunięcia: ");
            String delLastName = scanner.nextLine();

            s.removeStudent(delName, delLastName);
            System.out.println("Student (lub studenci) o podanych danych został/usunięty/(-e), jeśli istniał(-i).");
            break;

          case 5:
            System.out.print("Podaj imię studenta do aktualizacji: ");
            String updName = scanner.nextLine();
            System.out.print("Podaj nazwisko studenta do aktualizacji: ");
            String updLastName = scanner.nextLine();

            int newAge = 0;
            boolean validNewAge = false;
            while (!validNewAge) {
              System.out.print("Podaj nowy wiek studenta: ");
              try {
                newAge = Integer.parseInt(scanner.nextLine());
                validNewAge = true;
              } catch (NumberFormatException e) {
                System.out.println("Wiek musi być liczbą. Spróbuj ponownie.");
              }
            }

            int updateCount = s.updateStudentAge(updName, updLastName, newAge);
            if (updateCount == 0) {
              System.out.println("Brak studenta o podanych danych.");
            } else {
              System.out.println("Zaktualizowano wiek dla " + updateCount + " studenta(-ów).");
            }
            break;

          default:
            System.out.println("Niepoprawna opcja. Spróbuj ponownie.");
            break;
        }
      }

      scanner.close();

    } catch (IOException e) {
      System.out.println("Wystąpił błąd: " + e.getMessage());
    }
  }
}
