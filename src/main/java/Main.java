import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Main {
  public static void main(String[] args) {
    try {
      Service s = new Service();
      Scanner scanner = new Scanner(System.in);

      boolean tak = true;
      while (tak) {
        System.out.println("\nWybierz opcję:");
        System.out.println("1 - Dodaj studenta");
        System.out.println("2 - Wyświetl wszystkich studentów");
        System.out.print("Twój wybór: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
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
            var students = s.getStudents();
            System.out.println("📋 Lista studentów:");
            for (Student current : students) {
              System.out.println(current.toString());
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
