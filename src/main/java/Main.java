import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    List<Person> people = getPeople();

    // Imperative approach ❌
    /*
    List<Person> females = new ArrayList<>();

    for (Person person : people) {
      if (person.getGender().equals(Gender.FEMALE)) {
        females.add(person);
      }
    }
    females.forEach(System.out::println);
    */

    // Declarative approach ✅
    // Filter
    System.out.println("--------------------");
    List<Person> females = people.stream()
            .filter(person -> person.getGender().equals(Gender.FEMALE))
            .collect(Collectors.toList());
    females.forEach(System.out::println);

    // Sort
    System.out.println("--------------------");
    List<Person> sorted = people.stream()
            // .sorted(Comparator.comparing(Person::getAge))
            .sorted(Comparator.comparing(Person::getAge).reversed())
            .collect(Collectors.toList());
    sorted.forEach(System.out::println);

    // All match
    System.out.println("--------------------");
    boolean allMatch = people.stream()
            // .allMatch(person -> person.getAge() > 5); // true
            .allMatch(person -> person.getAge() > 8);
    System.out.println(allMatch); // false

    // Any match
    System.out.println("--------------------");
    boolean anyMatch = people.stream()
            .anyMatch(person -> person.getAge() > 8);
    System.out.println(anyMatch); // true

    // None match
    System.out.println("--------------------");
    boolean noneMatch = people.stream()
            .noneMatch(person -> person.getName().equals("Suzuki"));
    System.out.println(noneMatch); // true


  }
  private static List<Person> getPeople() {
    return List.of(
        new Person("Antonio", 20, Gender.MALE),
        new Person("Alina Smith", 33, Gender.FEMALE),
        new Person("Helen White", 57, Gender.FEMALE),
        new Person("Alex Boz", 14, Gender.MALE),
        new Person("Jamie Goa", 99, Gender.MALE),
        new Person("Anna Cook", 7, Gender.FEMALE),
        new Person("Zelda Brown", 120, Gender.FEMALE)
    );
  }

}
