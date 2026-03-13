# Java 8-11 Features - Quick Summary & Comparison

## 📋 At a Glance

### 🟢 Beginner recap (plain language)

- **Functional interface (SAM)**: An interface with exactly one abstract method. Add `@FunctionalInterface` so the compiler protects it. Example: `ArithmeticOperation { double operate(double a, double b); }`.
- **Lambda**: A short, nameless function. Write only the logic you need: `(a, b) -> a + b`.
- **Method reference**: Shorthand when a lambda just calls an existing method. Example: `people.sort(Person::compareByAge)` instead of `(a, b) -> Person.compareByAge(a, b)`.
- **Default method**: A method with a body inside an interface. Lets you evolve interfaces without breaking old implementations: `default void log() { ... }`.
- **Date-Time (java.time)**: Use `LocalDate/LocalDateTime` for human time, `Instant` for machine time, `ZonedDateTime` for time zones.
- **Base64**: Encode/decode bytes to text for transport. Use `Base64.getEncoder()` and specify UTF-8.

### 🚀 Day 0: run something now (2 minutes)

- Open `FunctionalInterfaces/FunctionalInterfacesExamples.java`, run it, and confirm the first lines print `3 + 4 = 7.0` and `Is 6 even? true`.
- Open `MethodReferences/MethodReferencesExamples.java`, run it, and confirm names print in alpha order.

| Feature                       | Version  | What It Does                          | Problem It Solves                    | Replaces/Improves         |
| ----------------------------- | -------- | ------------------------------------- | ------------------------------------ | ------------------------- |
| **Lambda Expressions**        | Java 8   | Anonymous functions passed as values  | Verbose anonymous classes            | Anonymous inner classes   |
| **Functional Interfaces**     | Java 8   | Single-abstract-method contracts      | Boilerplate interfaces for callbacks | Verbose custom interfaces |
| **Method References**         | Java 8   | Shorthand for lambdas calling methods | Noisy lambdas that just delegate     | Inline lambdas            |
| **Default Methods**           | Java 8   | Behavior inside interfaces            | Breaking API changes on new methods  | Manual mixins/abstract    |
| **forEach()**                 | Java 8   | Iterate with lambda expressions       | Boilerplate loop syntax              | Enhanced for loops        |
| **Stream API**                | Java 8   | Functional data processing pipelines  | Complex loop logic                   | Traditional nested loops  |
| **Optional**                  | Java 8   | Container for possibly-null values    | NullPointerException                 | Null checks everywhere    |
| **Date-Time API (java.time)** | Java 8   | Immutable, timezone-safe date/time    | `java.util.Date`/`Calendar` issues   | Legacy date classes       |
| **Base64 Utilities**          | Java 8   | Encode/decode binary for transport    | Unsafe ad-hoc encoders               | Custom/legacy encoders    |
| **Pattern Matching**          | Java 14+ | Type checking and binding in one step | Manual casting after instanceof      | instanceof + type cast    |

---

## 🎓 When to Use Each Feature

### Lambda Expressions

- **Use When**: You need to pass logic as a parameter
- **Example**: `list.sort((a, b) -> a - b)`
- **Benefit**: Reduces 5-10 lines to 1 line
- **Don't Use When**: Logic is too complex (10+ lines)
- **Try it**: `ArithmeticOperation add = (a,b)->a+b; System.out.println(add.operate(2,3));`

### forEach()

- **Use When**: Processing each element with same action
- **Example**: `items.forEach(System.out::println)`
- **Benefit**: Cleaner than for loops
- **Don't Use When**: You need break/continue or index
- **Try it**: `List.of("a","b").forEach(System.out::println);`

### Stream API

- **Use When**: Transforming/filtering large datasets
- **Example**: `list.stream().filter(x -> x > 5).map(x -> x * 2).collect(Collectors.toList())`
- **Benefit**: Powerful transformations, lazy evaluation
- **Don't Use When**: Simple one-off operations
- **Try it**: `List.of(1,2,3,4).stream().filter(n->n%2==0).forEach(System.out::println);`

### Optional

- **Use When**: Method might return no value
- **Example**: `findUser(id).map(User::getEmail).orElse("none")`
- **Benefit**: No more null checks, type-safe
- **Don't Use When**: Value always present
- **Try it**: `Optional.of("hi").map(String::toUpperCase).ifPresent(System.out::println);`

### Functional Interfaces

- **Use When**: You need a contract for lambdas/method refs (SAM)
- **Example**: `Predicate<User> active = User::isActive;`
- **Benefit**: Compose behavior, enforce single responsibility
- **Don't Use When**: You need multiple abstract methods (use class instead)
- **Try it**: `@FunctionalInterface interface Op{int run(int a,int b);} Op mul=(a,b)->a*b; System.out.println(mul.run(2,4));`

### Method References

- **Use When**: Lambda just delegates to an existing method
- **Example**: `emails.forEach(System.out::println)`
- **Benefit**: Cleaner, more readable than equivalent lambda
- **Don't Use When**: You need inline logic or state
- **Try it**: `List.of("Ana","bob").stream().sorted(String::compareToIgnoreCase).forEach(System.out::println);`

### Default Methods

- **Use When**: Evolving interfaces without breaking implementers
- **Example**: `default void audit(String msg) { ... }`
- **Benefit**: Add behavior/mixins; reuse helpers
- **Don't Use When**: Behavior is highly stateful; prefer class
- **Try it**:
  ```java
  interface Greeter { default void hi(){ System.out.println("hi"); } }
  class G implements Greeter {}
  new G().hi();
  ```

### Date-Time API (java.time)

- **Use When**: Handling dates, times, zones, durations
- **Example**: `ZonedDateTime nowUtc = Instant.now().atZone(ZoneId.of("UTC"));`
- **Benefit**: Immutable, timezone-safe, better than `Date`
- **Don't Use When**: Working with legacy APIs without conversion
- **Try it**: `System.out.println(LocalDate.now().plusDays(7));`

### Base64 Utilities

- **Use When**: Need to transport binary/text safely (headers, tokens)
- **Example**: `Base64.getUrlEncoder().withoutPadding().encodeToString(bytes)`
- **Benefit**: Standard, safe encoders (basic/url/mime)
- **Don't Use When**: You need encryption (Base64 is encoding only)
- **Try it**: `String e=Base64.getEncoder().encodeToString("hi".getBytes(UTF_8)); System.out.println(e);`

### Pattern Matching

- **Use When**: Checking type and casting
- **Example**: `if (obj instanceof String s) { ... }`
- **Benefit**: Eliminates manual casting
- **Don't Use When**: Legacy code compatibility needed (Java < 16)
- **Try it**: `Object o="hey"; if(o instanceof String s){ System.out.println(s.length()); }

---

## 🔄 Feature Dependencies

```
Foundation
    ↓
Lambda Expressions (Java 8)
    ↓
Functional Interfaces (Java 8)
    ↓
├─→ Method References (Java 8)
├─→ Default Methods (Java 8)
├─→ forEach() Method (Java 8)
├─→ Stream API (Java 8)
├─→ Optional (Java 8)
├─→ Date-Time API (Java 8)
├─→ Base64 Utilities (Java 8)
└─→ (All together enable clearer modern Java)

Advanced
    ↓
Pattern Matching (Java 14+)
    ├─→ instanceof patterns (Java 16+)
    └─→ Switch patterns (Java 17+)
```

---

## 💾 Code Examples Comparison

### 1. Processing Numbers

**Before Java 8**

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
List<Integer> squared = new ArrayList<>();
for (Integer num : numbers) {
    if (num % 2 == 0) {
        squared.add(num * num);
    }
}
for (Integer num : squared) {
    System.out.println(num);
}
```

**Lines: 10 | Readability: Medium**

**Java 8+ (Stream + Lambda)**

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
numbers.stream()
    .filter(n -> n % 2 == 0)
    .map(n -> n * n)
    .forEach(System.out::println);
```

**Lines: 5 | Readability: High**

### 2. Null Safety

**Before Java 8**

```java
User user = findUser(id);
if (user != null) {
    String email = user.getEmail();
    if (email != null) {
        sendEmail(email);
    }
}
```

**Java 8+ (Optional)**

```java
findUserOptional(id)
    .map(User::getEmail)
    .ifPresent(this::sendEmail);
```

### 3. Type Checking

**Before Java 16**

```java
if (obj instanceof String) {
    String str = (String) obj;
    System.out.println(str.toUpperCase());
}
```

**Java 16+ (Pattern Matching)**

```java
if (obj instanceof String str) {
    System.out.println(str.toUpperCase());
}
```

---

## 🎯 Practice pack (10 tasks, easy → hard)

1. Lambda warm-up: Write `ArithmeticOperation sub = (a,b)->a-b;` and print `sub.operate(10,3)`.
2. forEach: Print each name uppercased from `List.of("ana","bob","cara")` using `forEach` + method ref.
3. Functional interface: Create `@FunctionalInterface interface Check { boolean ok(int n); }` and a lambda that returns true for even numbers.
4. Method ref: Sort `List.of("Zed","amy","Bob")` case-insensitively with `String::compareToIgnoreCase`.
5. Optional: Wrap a nullable email with `Optional.ofNullable`, map to upper-case, and `orElse("NO EMAIL")`.
6. Default method: Add a `default void hello(){ System.out.println("hello"); }` to an interface and call it from an implementing class.
7. Stream API: Given `List<Integer> nums = List.of(3, 7, 2, 8, 5);` filter >4, double them, collect to a list, print.
8. Date-Time: Print next Monday’s date using `LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY))`.
9. Base64: Encode the string `"id:42"` with URL encoder (no padding) and decode it back.
10. Mini-project (combine features): Build a tiny “user report” method that
    - Filters active users (Predicate + stream)
    - Maps to emails (method reference)
    - Normalizes with Optional (skip missing emails)
    - Prints sorted unique emails (stream + forEach)

### 🔧 Project ideas (weekend-friendly)

- **CLI Todo Manager**: Uses streams for filtering, Optional for safe lookups, Base64 for exporting/importing data, java.time for due dates.
- **Pricing Engine**: Functional interfaces for pricing rules, default methods for common helpers, method refs in stream pipelines.
- **Log Analyzer**: Stream logs, use pattern matching (if on Java 17) to branch on event types, format timestamps with java.time.

### 4. Polymorphic Processing

**Before Java 17**

```java
if (response instanceof ErrorResponse) {
    ErrorResponse err = (ErrorResponse) response;
    logger.error(err.getMessage());
} else if (response instanceof SuccessResponse) {
    SuccessResponse success = (SuccessResponse) response;
    processData(success.getData());
}
```

**Java 17+ (Switch Patterns)**

```java
switch (response) {
    case ErrorResponse err -> logger.error(err.getMessage());
    case SuccessResponse success -> processData(success.getData());
    default -> logger.info("Unknown response");
}
```

---

## 🎯 Real-World Scenarios

### Scenario 1: User Authentication

```java
// Get user, validate password, return Optional
public Optional<User> authenticate(String username, String password) {
    return userRepository.findByUsername(username)
        .filter(user -> validatePassword(user, password));
}

// Use it
authenticate("alice", "pass123")
    .map(User::getId)
    .ifPresentOrElse(
        this::createSession,
        () -> showError("Invalid credentials")
    );
```

### Scenario 2: Data Import Processing

```java
// Read CSV, parse, validate, save
csvReader.readLines()
    .stream()
    .map(DataParser::parse)
    .filter(data -> data.isValid())
    .map(this::enrichWithDefaults)
    .forEach(database::save);
```

### Scenario 3: API Response Handler

```java
public void handleResponse(Object response) {
    switch (response) {
        case SuccessResponse r when r.getCode() == 200 ->
            procesSuccess(r.getData());
        case SuccessResponse r when r.getCode() == 201 ->
            processCreated(r.getLocation());
        case ErrorResponse r when r.getCode() >= 500 ->
            handleServerError(r);
        case ErrorResponse r ->
            handleClientError(r);
        case null -> logger.warn("Null response");
        default -> logger.error("Unknown response type");
    }
}
```

---

## ⚠️ Common Anti-Patterns

| Anti-Pattern                      | Problem             | Solution                             |
| --------------------------------- | ------------------- | ------------------------------------ |
| Complex lambdas (10+ lines)       | Hard to read        | Use named method + method reference  |
| Optional for always-present       | Unnecessary wrapper | Only use when value can be absent    |
| Reusing streams                   | Throws exception    | Create new stream each time          |
| Nested flatMap chains             | Pyramid of doom     | Break into intermediate variables    |
| foreach() with exceptions         | Ugly try-catch      | Use proper Stream exception handling |
| Pattern matching for simple types | Overkill            | Use traditional instanceof           |

---

## 📊 Performance Notes

| Feature             | Performance | Notes                                   |
| ------------------- | ----------- | --------------------------------------- |
| Lambda              | Excellent   | No overhead vs anonymous class          |
| forEach()           | Excellent   | Optimized by compiler                   |
| Stream (sequential) | Good        | Slight overhead for small datasets      |
| Stream (parallel)   | Excellent   | Use for large datasets + CPU-bound work |
| Optional            | Good        | Minimal overhead                        |
| Pattern Matching    | Excellent   | Compiled to optimal bytecode            |

**Note**: Always measure real performance - these features enable better optimization than traditional code often does.

---

## 🚀 Migration Path

### Phase 1: Learn Foundations

1. Understand Lambda Expressions
2. Learn forEach() Method
3. Practice Stream API basics

### Phase 2: Adopt in Projects

1. Replace loops with forEach() + Stream
2. Use Optional instead of null checks
3. Refactor comparators to lambdas

### Phase 3: Modernize Further

1. Adopt Pattern Matching (Java 14+)
2. Use switch expressions (Java 14+)
3. Leverage Records (Java 16+)

---

## 🎓 Key Takeaways

1. **Lambda Expressions** = Foundation for modern Java
2. **Stream API** = Powerful data transformation
3. **forEach()** = Clean iteration
4. **Optional** = Null-safe programming
5. **Pattern Matching** = Modern type checking

All work together to make Java:

- ✅ More Readable
- ✅ More Concise
- ✅ More Type-Safe
- ✅ Less Error-Prone

---

## 📈 Adoption Timeline

```
2014: Java 8 Released
├─ Lambda Expressions introduced
├─ Stream API introduced
├─ forEach() introduced
└─ Optional introduced
   ↓
2016: Java 9-10 (incremental)
   ↓
2019: Java 12-13 (Switch Expressions preview)
   ↓
2020: Java 14-15
├─ instanceof patterns (preview)
└─ Records (preview)
   ↓
2021: Java 16+
├─ instanceof patterns (final)
├─ Records (final)
└─ Sealed classes (final)
   ↓
2021: Java 17
├─ Switch patterns (preview)
└─ Guarded patterns (preview)
```

---

## 💡 Philosophy Behind These Features

**Java 8+** represents a shift in Java's philosophy:

1. **From Verbose to Concise**: Less boilerplate, more intent
2. **From Imperative to Declarative**: Say WHAT not HOW
3. **From Unsafe to Safe**: Compiler help prevents errors
4. **From Single-threaded to Parallel**: Easier parallelization
5. **From Objects to Functions**: First-class function support

---

## 🔗 How They Connect

```
Problem: Writing clear, concise, type-safe Java code
   ↓
Solution Stack:
   ├─ Lambda Expressions
   │  └─ Enables passing behavior
   │
   ├─ Stream API
   │  ├─ Built on Lambdas
   │  └─ Transform data functionally
   │
   ├─ forEach()
   │  ├─ Uses Lambdas
   │  └─ Clean iteration
   │
   ├─ Optional
   │  ├─ Complements Stream
   │  └─ Replaces nulls safely
   │
   └─ Pattern Matching
      ├─ Modern syntax
      └─ Type-safe processing
```

---

## ✅ Mastery Checklist

- [ ] Can write and read lambda expressions
- [ ] Understand functional interfaces
- [ ] Can chain stream operations
- [ ] Know difference between intermediate and terminal operations
- [ ] Understand Optional.map() vs flatMap()
- [ ] Can use pattern matching with instanceof
- [ ] Know when to use each feature
- [ ] Can spot anti-patterns
- [ ] Can refactor old code to use new features
- [ ] Understand performance implications

---

## 🎓 Next Level: Advanced Topics

After mastering these basics, explore:

1. **Reactive Streams** - Asynchronous data processing
2. **Sealed Classes** - Type hierarchies
3. **Records** - Data classes
4. **Virtual Threads** - Lightweight concurrency
5. **Text Blocks** - Multiline strings

---

## 📚 Learning Resources

- **Official**: docs.oracle.com/javase/tutorial
- **Books**: "Java 8 in Action" (Manning)
- **Practice**: LeetCode, HackerRank Java challenges
- **Reference**: JavaDoc for java.util.stream

---

## 🎯 Remember

> "These features exist to make you write better code. Use them wisely."

- **Write for readability first**
- **Performance is rarely the bottleneck**
- **Choose the right tool for the job**
- **Mix old and new as appropriate**
- **Keep learning!**

---

**You've now got the complete guide! Time to start practicing! 🚀**
