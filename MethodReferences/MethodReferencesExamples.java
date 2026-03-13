package MethodReferences;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MethodReferencesExamples {
    private static final Logger LOG = Logger.getLogger("MethodRefs");

    record User(String name, String email, LocalDate joined) {}

    public static void main(String[] args) {
        List<User> users = List.of(
            new User("Ana", "ana@company.com", LocalDate.of(2021, 5, 1)),
            new User("Bob", "bob@gmail.com", LocalDate.of(2020, 3, 15)),
            new User("Cara", "cara@company.com", LocalDate.of(2022, 1, 10))
        );

        // Static method reference
        Function<String, String> domain = MethodReferencesExamples::domainOf;
        System.out.println("Domain: " + domain.apply("ana@company.com"));

        // Bound instance method reference
        Consumer<String> info = LOG::info; // s -> LOG.info(s)
        users.stream().map(User::name).forEach(info);

        // Unbound instance method reference
        BiPredicate<String, String> equalsIgnoreCase = String::equalsIgnoreCase;
        System.out.println("same? " + equalsIgnoreCase.test("Hi", "hi"));

        // Constructor reference for factory/registry pattern
        Map<String, Supplier<List<String>>> listFactories = Map.of(
            "arrayList", ArrayList::new,
            "names", ArrayList::new
        );
        List<String> names = listFactories.get("names").get();
        names.add("Zoe");
        info.accept("Names: " + names);

        // Stream pipelines with method references
        List<String> companyEmails = users.stream()
            .map(User::email)                     // lambda would be u -> u.email()
            .filter(MethodReferencesExamples::isCompanyEmail)
            .sorted(String::compareToIgnoreCase)
            .toList();
        info.accept("Company emails: " + companyEmails);

        // Collectors with method references
        Map<String, Long> countByDomain = users.stream()
            .map(User::email)
            .collect(Collectors.groupingBy(MethodReferencesExamples::domainOf, Collectors.counting()));
        info.accept("Counts by domain: " + countByDomain);

        // Sorting with comparator method refs
        List<User> sorted = users.stream()
            .sorted(Comparator.comparing(User::joined))
            .toList();
        info.accept("Sorted by joined: " + sorted);
    }

    static boolean isCompanyEmail(String email) {
        return email != null && email.endsWith("@company.com");
    }

    static String domainOf(String email) {
        int at = email.indexOf('@');
        return at >= 0 ? email.substring(at + 1) : "";
    }
}
