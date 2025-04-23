/*
Kod bazowy programu Commit4_0: 
• Program dodaje do prostej bazy danych (pliku db.txt) dane odnośnie Studentów.
• Studenci dodawani są w klasie Main.
• Wszyscy studenci są wypisywani na końcu klasy Main.
• Klasa Service obsługuje o dczyt i zapis do pliku bazy danych.
• Klasa Student reprezentuje pojedynczego studenta (Imię, Wiek).
*/
 
import java.io.IOException;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    try {
      Service s = new Service();
      Scanner scanner = new Scanner(System.in);
      int choice;

      do {
        System.out.println("\n1 - Dodaj studenta");
        System.out.println("2 - Wyświetl wszystkich studentów");
        System.out.println("3 - Zakończ program");
        System.out.print("Wybierz opcję: ");

        choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
          case 1:
            System.out.println("Podaj imię studenta:");
            String name = scanner.nextLine();

            System.out.println("Podaj wiek studenta:");
            int age = scanner.nextInt();

            s.addStudent(new Student(name, age));
            System.out.println("Dodano studenta!");
            break;

          case 2:
            System.out.println("Lista wszystkich studentów:");
            s.getStudents().forEach(student -> System.out.println(student.ToString()));
            break;

          case 3:
            System.out.println("Program zakończony.");
            break;

          default:
            System.out.println("Nieprawidłowa opcja! Spróbuj ponownie.");
        }
      } while (choice != 3);

      scanner.close();
    } catch (IOException e) {
      System.out.println("Błąd: " + e.getMessage());
    }
  }
}
