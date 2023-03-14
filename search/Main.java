package search;

import search.searchStrategy.Searcher;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static List<Person> people = new ArrayList<>();

    static Map<String, Set<Integer>> keywordMaps = new HashMap<>();


    public static void main(String[] args) {
        startProgram(args[1]);
    }

    private static void startProgram(String filePath) {
        registerPeople(filePath);
        showMenu();
    }

    private static void showMenu() {
        String userInput;
        do {
            System.out.println("=== Menu ===");
            System.out.println("1. Find a person");
            System.out.println("2. Print all people");
            System.out.println("0. Exit");
            userInput = scan.nextLine();
            System.out.println();

            switch (userInput) {
                case "1" -> findPeople();
                case "2" -> printAllPeople();
                case "0" -> System.out.println("Bye!");
                default -> System.out.println("Incorrect option! Try again.");
            }
            System.out.println();
        } while (!"0".equals(userInput));
    }

    private static void printAllPeople() {
        System.out.println("=== List of people ===");
        people.forEach(System.out::println);
    }

    private static void findPeople() {
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        String searchStrategy = scan.nextLine();
        Searcher searcher = new Searcher(searchStrategy);
        System.out.println();

        System.out.println("Enter a name or email to search all suitable people.");
        String userTerm = scan.nextLine();
        List<Person> foundPeople = searcher.executeSearch(userTerm, keywordMaps, people);
        if (foundPeople.size() > 0) {
            System.out.printf("%s %s found:%n", foundPeople.size(), foundPeople.size() > 1 ? "people" : "person");
            foundPeople.forEach(System.out::println);
        } else {
            System.out.println("No matching people found.");
        }
    }

    private static void registerPeople(String filePath) {
        try (Scanner fileScan = new Scanner(new File(filePath))) {
            int index = 0;
            while (fileScan.hasNext()) {
                String[] fields = fileScan.nextLine().split(" ");
                String firstName = fields[0];
                String lastName = fields[1];
                if (fields.length == 3) {
                    String emailAddress = fields[2];
                    people.add(new Person(firstName, lastName, emailAddress));
                } else {
                    people.add(new Person(firstName, lastName));
                }
                mapKeywords(fields, index);
                index++;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void mapKeywords(String[] fields, int index) {
        for (String field: fields) {
            Set<Integer> keyValues;
            String fieldToLowerCase = field.toLowerCase();
            if (keywordMaps.containsKey(fieldToLowerCase)) {
                keyValues = keywordMaps.get(fieldToLowerCase);
            } else {
                keyValues = new HashSet<>();
            }
            keyValues.add(index);
            keywordMaps.put(fieldToLowerCase, keyValues);
        }
    }
}
