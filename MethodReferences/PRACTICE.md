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

## Spring-flavored labs

1. Map `List<UserDto>` to emails using `UserDto::getEmail`; print.
2. Filter emails with `EmailValidator::isCompanyEmail` (static ref) and print.
3. Sort `List<OrderDto>` by `OrderDto::getCreatedAt` using comparator with method refs.
4. Collect names into `new ArrayList<>()` via `Collectors.toCollection(ArrayList::new)`.
5. Convert `List<String>` ids to integers with `Integer::parseInt`; handle errors separately.
6. Filter out blank form inputs with `String::isBlank` in a stream.
7. Use constructor ref `UserDto::new` to map names to DTOs; print.
8. Chain refs: `users.stream().map(UserDto::getEmail).filter(EmailValidator::isValid).forEach(System.out::println);`
9. Logger: `Logger log = Logger.getLogger("demo"); names.forEach(log::info);`
10. Extract domains: `emails.stream().map(EmailUtils::domainOf).forEach(System.out::println);`
