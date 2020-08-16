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

    // Max
    System.out.println("--------------------");

     // Optional<Person> max = people.stream()
     //        .max(Comparator.comparing(Person::getAge))
    people.stream()
            .max(Comparator.comparing(Person::getAge))
            .ifPresent(System.out::println); // Person{name='Zelda Brown', age=120, gender=FEMALE}

    // Min
    System.out.println("--------------------");
    people.stream()
            .min(Comparator.comparing(Person::getAge))
            .ifPresent(System.out::println); // Person{name='Anna Cook', age=7, gender=FEMALE}

    // Group
    System.out.println("--------------------");
    Map<Gender, List<Person>> groupByGender = people.stream()
            .collect(Collectors.groupingBy(Person::getGender));

    groupByGender.forEach((gender, people1) ->{
      System.out.println(gender);
      people1.forEach(System.out::println);
      System.out.println();
    });

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
