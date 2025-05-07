import java.util.Collection;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Service {

  public void addStudent(Student student) throws IOException {
    var f = new FileWriter("db.txt", true);
    var b = new BufferedWriter(f);
    b.append(student.toString());
    b.newLine();
    b.close();
  }

  public Collection<Student> getStudents() throws IOException {
    var ret = new ArrayList<Student>();
    var f = new FileReader("db.txt");
    var reader = new BufferedReader(f);
    String line = "";
    while (true) {
      line = reader.readLine();
      if (line == null)
        break;
      ret.add(Student.parse(line));
    }
    reader.close();
    return ret;
  }

  /**
   * Wyszukuje studentów po imieniu.
   *
   * @param name Imię, według którego szukamy studentów (ignorując wielkość liter).
   * @return Kolekcja studentów z pasującym imieniem.
   * @throws IOException Jeżeli wystąpi problem odczytu bazy danych.
   */
  public Collection<Student> findStudentByName(String name) throws IOException {
    Collection<Student> allStudents = getStudents();
    Collection<Student> result = new ArrayList<>();
    for (Student student : allStudents) {
      if (student.getName().equalsIgnoreCase(name)) {
        result.add(student);
      }
    }
    return result;
  }

  /**
   * Usuwa studenta (lub studentów) na podstawie imienia oraz nazwiska.
   *
   * @param name Imię studenta do usunięcia.
   * @param lastName Nazwisko studenta do usunięcia.
   * @throws IOException Jeżeli wystąpi problem odczytu lub zapisu pliku.
   */
  public void removeStudent(String name, String lastName) throws IOException {
    Collection<Student> students = getStudents();
    Collection<Student> updatedStudents = new ArrayList<>();

    // Dodajemy tylko te wpisy, które nie odpowiadają podanym danym.
    for (Student student : students) {
      if (!(student.getName().equalsIgnoreCase(name) && student.getLastName().equalsIgnoreCase(lastName))) {
        updatedStudents.add(student);
      }
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter("db.txt", false))) {
      for (Student student : updatedStudents) {
        writer.write(student.toString());
        writer.newLine();
      }
    }
  }

  /**
   * Aktualizuje wiek studenta/ów po podaniu imienia i nazwiska.
   * Dla każdego studenta spełniającego kryteria tworzy nowy obiekt z nowym wiekiem, a resztę danych zachowuje.
   *
   * @param name Imię studenta do aktualizacji.
   * @param lastName Nazwisko studenta do aktualizacji.
   * @param newAge Nowy wiek studenta.
   * @return Liczba zaktualizowanych rekordów.
   * @throws IOException Jeżeli wystąpi problem odczytu lub zapisu pliku.
   */
  public int updateStudentAge(String name, String lastName, int newAge) throws IOException {
    Collection<Student> students = getStudents();
    Collection<Student> updatedStudents = new ArrayList<>();
    int updateCount = 0;

    for (Student student : students) {
      if (student.getName().equalsIgnoreCase(name) && student.getLastName().equalsIgnoreCase(lastName)) {
        // Tworzymy nowy obiekt z nowym wiekiem, zachowując pozostałe dane.
        Student updatedStudent = new Student(student.getName(), student.getLastName(), newAge, student.getBirthDate());
        updatedStudents.add(updatedStudent);
        updateCount++;
      } else {
        updatedStudents.add(student);
      }
    }

    // Nadpisujemy plik "db.txt" zaktualizowaną listą studentów.
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("db.txt", false))) {
      for (Student s : updatedStudents) {
        writer.write(s.toString());
        writer.newLine();
      }
    }
    return updateCount;
  }
}
