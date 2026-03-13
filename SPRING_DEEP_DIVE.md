# Spring Deep Dive: Lambdas, Functional Interfaces, Streams, Method References

Teach-yourself guide with beginner-first language, Spring-specific patterns, and hands-on steps.

## How to use this guide
- Work top to bottom. Copy/paste the tiny snippets into the existing example files or a scratch class.
- After each section, do the practice tasks. Keep outputs small and visible in the console.
- When it says “In Spring”, imagine you’re inside a controller/service/repository.

---

## 1) Lambda Expressions (baby steps)
**What**: A tiny unnamed function you can pass around.
**Shape**: `(inputs) -> { body }`

### In Spring (why you care)
- Controllers/services: concise sorting/filtering of DTOs.
- Schedulers/executors: pass behavior without creating a class.
- Event listeners: inline handling of application events.

### 3-minute starter
```java
// Smallest lambda
java.util.function.Function<Integer, Integer> doubleIt = n -> n * 2;
System.out.println(doubleIt.apply(5)); // 10

// With two parameters
java.util.function.BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
System.out.println(add.apply(3, 4)); // 7
```

### Practice (Spring-flavored)
1) Sort a list of `UserDto` by `createdAt` using a lambda comparator.
2) Filter a list of `OrderDto` to only PAID orders, then print their ids.
3) Build a lambda `BiFunction<String,Integer,String>` that returns `"{name} has {count} items"` and apply it.
4) Create a `Runnable` lambda that logs "ping" and run it.
5) Map a list of product names to upper-case with a lambda; collect to a list.

### Level up
- Replace any anonymous class in your code with a lambda where it’s a single abstract method.
- Keep lambdas short; if more than ~5 lines, extract a method.

---

## 2) Functional Interfaces (SAM) in Spring
**What**: An interface with exactly one abstract method (SAM). Perfect target for lambdas/method refs.

### In Spring (why you care)
- `Predicate`, `Function`, `Consumer` everywhere in streams.
- Custom callbacks: retry policies, validators, mappers.
- Strategy injection: pass behavior into services without new classes.

### 3-minute starter
```java
@FunctionalInterface
interface PriceRule { double apply(double price); }
PriceRule discount = p -> p * 0.9;
PriceRule tax = p -> p * 1.07;
System.out.println(tax.apply(discount.apply(100))); // 96.3
```

### Practice (Spring-flavored)
1) Define `@FunctionalInterface Validator<T> { boolean isValid(T t); }` and validate emails.
2) Create `RetryPolicy` as `Function<Throwable, Boolean>`; return true only for `IOException`.
3) Build `Mapper<S,T>` and map a `UserEntity` to `UserDto`.
4) Use `Predicate<OrderDto>` to keep only high-value orders (`total > 100`).
5) Compose two `Function<String,String>`: trim then lowercase; apply to "  Hello  ".

### Level up
- Add default methods for composition (e.g., `Validator.and`).
- Use built-ins first (`Predicate`, `Function`, `Supplier`, `Consumer`, `BiFunction`, `Comparator`).

---

## 3) Stream API (your daily workhorse)
**What**: A fluent pipeline for collection/data processing.

### In Spring (why you care)
- Transform repository results before returning DTOs.
- Aggregate metrics (counts, sums, groupings) for responses.
- Clean up nulls/empties with Optional + streams.

### 3-minute starter
```java
List<Integer> nums = List.of(1,2,3,4,5);
List<Integer> evensSquared = nums.stream()
    .filter(n -> n % 2 == 0)
    .map(n -> n * n)
    .toList();
System.out.println(evensSquared); // [4, 16]
```

### Practice (Spring-flavored)
1) From `List<UserDto> users`, collect the emails of active users sorted alphabetically.
2) From `List<OrderDto>`, total the amounts of PAID orders (use `mapToDouble` or `reduce`).
3) Group orders by status into a `Map<Status, List<OrderDto>>`.
4) Partition users into `{premium: [...], regular: [...]}` by a boolean flag.
5) Given `List<String> tags`, produce a distinct, lowercased, sorted list.
6) From `List<UserDto>`, build `Map<String, Long>` counting users per country.
7) Safe extract: `List<Optional<String>> emailsOpt` → flatMap Optionals → sorted list of emails.
8) Chain Optional + stream: `Optional<UserDto>` → `map(UserDto::getEmail)` → `stream()` → collect.
9) Use `peek` to debug a pipeline that filters + maps; print intermediate values.
10) Handle empty: get the max order total or return 0 if list is empty.

### Level up
- Prefer `toList()` (Java 16+) or `Collectors.toList()`; avoid mutating sources.
- Watch boxing: use `mapToInt/mapToDouble` for numeric ops.

---

## 4) Method References (readability boost)
**What**: Shortcuts when a lambda just calls an existing method.
**Shapes**: `Class::static`, `instance::method`, `Class::instanceMethod`, `Class::new`.

### In Spring (why you care)
- Cleaner stream pipelines (`map(User::getEmail)` instead of `(u)->u.getEmail()`).
- Constructor refs for factories/registries.
- Reusable validators/normalizers in controllers/services.

### 3-minute starter
```java
List<String> names = List.of("Ana","bob","Cara");
names.stream()
    .sorted(String::compareToIgnoreCase)
    .forEach(System.out::println);
```

### Practice (Spring-flavored)
1) Map users to emails with `UserDto::getEmail` and print.
2) Filter emails with `EmailValidator::isCompanyEmail` (static ref).
3) Sort orders by `OrderDto::getCreatedAt` using a comparator with method refs.
4) Collect names into `new ArrayList<>()` via `Collectors.toCollection(ArrayList::new)`.
5) Convert `List<String>` to `List<Integer>` with `Integer::parseInt`; handle errors separately.
6) Use `String::isBlank` to filter blanks out of a list of form inputs.
7) Use a constructor ref `UserDto::new` to map names to DTOs.
8) Chain: `users.stream().map(UserDto::getEmail).filter(EmailValidator::isValid).forEach(System.out::println);`
9) Logger: `Logger log = Logger.getLogger("demo"); list.forEach(log::info);`
10) Domain extractor: `emails.stream().map(EmailUtils::domainOf).forEach(System.out::println);`

### Level up
- Swap lambdas to method refs when they only forward to an existing method.
- Keep business logic in named methods; use method refs to wire them.

---

## Path to “pro” checklist
- Can explain what a functional interface is and name the common JDK ones from memory.
- Can write a 5-step stream pipeline (filter/map/sort/collect) without looking up docs.
- Can refactor lambdas to method references for readability and back when clarity needs custom logic.
- Can handle null/empty safely with Optional + streams.
- Can add small, pure helper methods and reference them (improves testability).

## Suggested daily drills (10 minutes)
- Pick one list/DTO in your current code; rewrite processing with a stream + method refs.
- Replace one anonymous class with a lambda + @FunctionalInterface (if custom).
- Add one unit test that asserts a stream pipeline result.

## Where to put code
- Use the existing example files (`LambdaExpressions.java`, `FunctionalInterfacesExamples.java`, `StreamAPIExamples.java`, `MethodReferencesExamples.java`).
- For Spring flavor, mimic service methods: create small lists, process, and print. No Spring bootstrapping required for practice.
