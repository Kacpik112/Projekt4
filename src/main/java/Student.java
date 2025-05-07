public class Student {
  private String name;
  private String lastName;
  private int age;
  private String birthDate;   // Pole przechowujące datę urodzenia

  // Konstruktor z dodatkowym parametrem daty urodzenia
  public Student(String name, String lastName, int age, String birthDate) {
    this.name = name;
    this.lastName = lastName;
    this.age = age;
    this.birthDate = birthDate;
  }

  public String getName() { return name; }
  public String getLastName() { return lastName; }
  public int getAge() { return age; }
  public String getBirthDate() { return birthDate; }

  // Metoda toString wykorzystuje nową informację – datę urodzenia
  public String toString() {
    return name + " " + lastName + " " + age + " " + birthDate;
  }

  // Metoda parse oczekuje czterech pól rozdzielonych spacjami:
  // imię, nazwisko, wiek, data urodzenia.
  public static Student parse(String str) {
    String[] data = str.split(" ");
    if (data.length != 4) {
      return new Student("Parse", "Error", -1, "Parse Error");
    }
    return new Student(data[0], data[1], Integer.parseInt(data[2]), data[3]);
  }
}
