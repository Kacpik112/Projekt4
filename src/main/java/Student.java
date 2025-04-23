public class Student {
  private String name;
  private int age;
  private String lastName;

  public Student(String name, String lastName, int age){
    this.name = name;
    this.age = age;
    this.lastName = lastName;
  }

  public String getName() {return name;}
  public String getLastName() {return lastName;}
  public int getAge() {return age;}

  public String toString() {
    return name + " " + lastName + " " + Integer.toString(age);
  }

  public static Student parse(String str) {
    String[] data = str.split(" ");
    if (data.length != 3) {
      return new Student("Parse Error", "Parse Error", -1);
    }
    return new Student(data[0], data[1], Integer.parseInt(data[2]));
  }
}