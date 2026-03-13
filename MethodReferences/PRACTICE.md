# Method References - Practice Pack

Use `MethodReferencesExamples.java` for solutions.

## How to run

- Open `MethodReferences/MethodReferencesExamples.java`
- Add code in `main` or helper methods and run.

## 10 Exercises (easy → hard)

1. **Static ref**: Replace `(s) -> Integer.parseInt(s)` with `Integer::parseInt` on `List.of("1","2")` and print ints.
2. **Bound instance**: Create a `Logger` and use `logger::info` in a `forEach` to print names.
3. **Unbound instance**: Sort `List.of("a","B","c")` with `String::compareToIgnoreCase`.
4. **Constructor ref**: `Supplier<List<String>> maker = ArrayList::new;` make a list and add two items; print.
5. **To-string ref**: Map `List.of(1,2,3)` to strings using `Object::toString` and print.
6. **Domain extractor**: Given `List<String> emails`, map with `MethodReferencesExamples::domainOf` and print domains.
7. **Predicate ref**: Use `String::isBlank` as a method reference to filter blanks from a list.
8. **Collector ref**: Use `Collectors.toList()` with a method reference? (Instead, practice mapping: `names.stream().collect(Collectors.toCollection(ArrayList::new))`).
9. **Chained refs**: `users.stream().map(User::email).filter(MethodReferencesExamples::isCompanyEmail).forEach(System.out::println);`
10. **Constructor ref with args**: `Function<String, User> toUser = User::new;` create three users from names; print.

## Mini Project

**Email Directory**: Given users with `name` and `email`, build a map domain → sorted list of names:

- Map to emails (`User::email`), filter company emails (`isCompanyEmail`), map to domain (`domainOf`), group by domain with names, sort each list, print.
