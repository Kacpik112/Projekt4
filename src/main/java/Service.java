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
   * Usuwa studenta (lub studentów) na podstawie imienia i nazwiska.
   * Odczytuje wszystkich studentów, filtruje listę usuwając obiekty, które mają
   * podane imię oraz nazwisko, a następnie nadpisuje plik db.txt pozostałymi danymi.
   *
   * @param name Imię studenta do usunięcia.
   * @param lastName Nazwisko studenta do usunięcia.
   * @throws IOException Jeżeli wystąpi problem odczytu lub zapisu pliku.
   */
  public void removeStudent(String name, String lastName) throws IOException {
    // Odczytaj wszystkich studentów z pliku
    Collection<Student> students = getStudents();
    Collection<Student> updatedStudents = new ArrayList<>();

    // Dodaj do nowej kolekcji tylko tych studentów, którzy nie odpowiadają podanym danym
    for (Student student : students) {
      if (!(student.getName().equalsIgnoreCase(name) && student.getLastName().equalsIgnoreCase(lastName))) {
        updatedStudents.add(student);
      }
    }

    // Nadpisz plik "db.txt" danymi z kolekcji updatedStudents
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("db.txt", false))) {
      for (Student student : updatedStudents) {
        writer.write(student.toString());
        writer.newLine();
      }
    }
  }
}
