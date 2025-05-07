public class Student {
  private String name;
  private String lastName;
  private int age;
  private String birthDate;

  // Konstruktor z dodatkowymi danymi (data urodzenia)
  public Student(String name, String lastName, int age, String birthDate) {
    this.name = name;
    this.lastName = lastName;
    this.age = age;
    this.birthDate = birthDate;
  }

  public String getName() {
    return name;
  }

  public String getLastName() {
    return lastName;
  }

  public int getAge() {
    return age;
  }

  public String getBirthDate() {
    return birthDate;
  }

  // Metoda toString wyświetla wszystkie dane o studencie
  @Override
  public String toString() {
    return name + " " + lastName + " " + age + " " + birthDate;
  }

  // Metoda parse konwertuje linijkę tekstu na obiekt Student.
  // Oczekujemy 4 pól rozdzielonych spacjami: imię, nazwisko, wiek, data urodzenia.
  public static Student parse(String str) {
    String[] data = str.split(" ");
    if (data.length != 4) {
      return new Student("Parse", "Error", -1, "Parse Error");
    }
    try {
      int age = Integer.parseInt(data[2]);
      return new Student(data[0], data[1], age, data[3]);
    } catch (NumberFormatException e) {
      return new Student("Parse", "Error", -1, "Parse Error");
    }
  }
}
