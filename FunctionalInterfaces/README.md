# Functional Interfaces - Java 8

## 📚 Definition

A **Functional Interface** is an interface that contains **exactly one abstract method**. It may include any number of `default` or `static` methods, but only one abstract method (Single Abstract Method - SAM). Mark it with `@FunctionalInterface` to let the compiler enforce the rule.

### 🟢 Beginner quick start

1. Write an interface with **one** abstract method.
2. Add `@FunctionalInterface` so the compiler warns if you add another.
3. Create a lambda to implement it.

```java
@FunctionalInterface
interface ArithmeticOperation {
    double operate(double a, double b); // exactly one abstract method
}

ArithmeticOperation add = (a, b) -> a + b;
System.out.println(add.operate(3, 4)); // 7.0
```

---

## 🎯 Why They Matter

- ✅ Foundation for **lambda expressions** and **method references**
- ✅ Enable passing behavior as data (functional programming)
- ✅ Improve readability vs. anonymous inner classes
- ✅ Work seamlessly with Streams (`map`, `filter`, `reduce`, etc.)

---

## 🔧 Anatomy

```java
@FunctionalInterface
public interface Transformer<T, R> {
    R transform(T input);          // Single abstract method (SAM)

    default R logAndTransform(T input) { // Allowed default method
        System.out.println("Transforming: " + input);
        return transform(input);
    }

    static <T> Transformer<T, T> identity() { // Allowed static method
        return x -> x;
    }
}
```

**Key rules:**

- One and only one abstract method
- `default` and `static` methods are allowed
- `@FunctionalInterface` is optional but recommended (compile-time safety)

---

## 🧰 Common Built-in Functional Interfaces (`java.util.function`)

### What returns what? (quick cheat table)

| Interface           | SAM                | Returns…         | Use when you need to… | Mini example                       |
| ------------------- | ------------------ | ---------------- | --------------------- | ---------------------------------- |
| `Supplier<T>`       | `T get()`          | A value          | **Produce** something | `() -> UUID.randomUUID()`          |
| `Consumer<T>`       | `void accept(T)`   | Nothing (`void`) | **Use** a value       | `log::info`                        |
| `Function<T,R>`     | `R apply(T)`       | A different type | **Transform**         | `u -> u.getEmail()`                |
| `Predicate<T>`      | `boolean test(T)`  | `true/false`     | **Decide** / filter   | `p -> p > 0`                       |
| `UnaryOperator<T>`  | `T apply(T)`       | Same type        | **T -> T** transform  | `s -> s.trim()`                    |
| `BinaryOperator<T>` | `T apply(T,T)`     | Same type        | **T,T -> T** combine  | `(a,b) -> a+b`                     |
| `BiFunction<T,U,R>` | `R apply(T,U)`     | Any type         | **Mix two inputs**    | `(u, role) -> tag(u,role)`         |
| `BiConsumer<T,U>`   | `void accept(T,U)` | Nothing (`void`) | **Use two inputs**    | `(msg,ctx) -> audit(msg,ctx)`      |
| `Comparator<T>`     | `int compare(T,T)` | Ordering signal  | **Sort** / order      | `Comparator.comparing(User::name)` |

---

## 🚀 How to pick and use effectively

- Ask “Do I **produce**, **transform**, **decide**, **consume**, or **order**?” → match to Supplier / Function / Predicate / Consumer / Comparator.
- Keep lambdas tiny; move real logic to named methods and use **method references** (`this::normalize`), especially in Streams.
- Prefer **pure, stateless** lambdas; avoid mutating external state inside streams (use collectors instead).
- Compose instead of nesting: `predicateA.and(predicateB)`, `fn1.andThen(fn2)`, `comparator.thenComparing(...)`.
- Name intermediate variables to reveal intent:

```java
Predicate<User> isActive = User::active;
Function<User, String> toEmail = User::email;
List<String> activeEmails = users.stream().filter(isActive).map(toEmail).toList();
```

## 🔗 Streams + functional interfaces (tiny recipes)

- **Filter booleans** (Predicate): `users.stream().filter(User::isActive)`
- **Map/transform** (Function): `.map(User::getEmail)`
- **Peek/use side-effects** (Consumer): `.peek(u -> log.info(u.getId()))`
- **Reduce/combine** (BinaryOperator): `.reduce(0, Integer::sum)`
- **Sort** (Comparator): `.sorted(Comparator.comparing(User::getJoinedAt))`
- **Optional to stream**: `optionalUser.stream().map(User::getEmail)` (0 or 1 element)

### End-to-end example

```java
Comparator<User> byStatusThenJoined = Comparator
    .comparing(User::status)
    .thenComparing(User::joinedAt);

Function<User, String> toEmail = User::email;
Predicate<User> isVerified = User::isVerified;

List<String> emails = users.stream()
    .filter(isVerified)      // Predicate
    .sorted(byStatusThenJoined) // Comparator
    .map(toEmail)            // Function
    .toList();
```

## 📘 Tutorials & references (skim first)

- Official Java docs: [`java.util.function` package](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html)
- Baeldung primer: [Guide to Java 8 Functional Interfaces](https://www.baeldung.com/java-8-functional-interfaces)
- JetBrains guide: [Lambdas and Functional Interfaces in Java](https://www.jetbrains.com/guide/java/tutorials/lambdas/)
- Stream cookbook: [Baeldung Stream API](https://www.baeldung.com/java-8-streams)

## 🧭 If you write this… you get this (functional interfaces)

- `Predicate<String> nonEmpty = s -> !s.isEmpty();` → `nonEmpty.test("hi")` gives `true`.
- `Function<String,Integer> len = String::length;` → `len.apply("hey")` gives `3`.
- `Consumer<String> logIt = System.out::println;` → `logIt.accept("ok")` prints `ok`.
- `Supplier<UUID> ids = UUID::randomUUID;` → `ids.get()` yields a new UUID each call.
- `Comparator<User> byName = Comparator.comparing(User::name);` → `.sorted(byName)` orders users by name.

## 🏗️ Creating Your Own Functional Interface

```java
@FunctionalInterface
public interface PriceRule {
    double apply(double price);
}

PriceRule weekendDiscount = p -> p * 0.9;
PriceRule tax = p -> p * 1.07;

// Compose
Function<Double, Double> finalPrice =
    ((Function<Double, Double>) weekendDiscount::apply)
        .andThen(tax::apply);

System.out.println(finalPrice.apply(100.0)); // 96.3
```

### Composition Helpers

Most functional interfaces provide helpers:

- `Function`: `andThen`, `compose`
- `Predicate`: `and`, `or`, `negate`
- `Comparator`: `thenComparing`, `reversed`

---

## 🌍 Real-World Practices

1. **Validation Pipelines**

```java
Predicate<User> isAdult = u -> u.age() >= 18;
Predicate<User> isActive = User::active;
Predicate<User> isVerifiedEmail = u -> u.email().endsWith("@company.com");

Predicate<User> canLogin = isAdult.and(isActive).and(isVerifiedEmail);

users.stream()
     .filter(canLogin)
     .forEach(loginService::allow);
```

2. **Strategy Injection**

```java
interface RetryPolicy extends Function<Throwable, Boolean> {}
RetryPolicy networkRetry = ex -> ex instanceof IOException;

void fetchWithRetry(RetryPolicy policy) { /* ... */ }
```

3. **Mapping Layers (DTO ↔ Entity)**

```java
Function<UserEntity, UserDto> toDto = e -> new UserDto(e.id(), e.name());
Function<UserDto, UserEntity> toEntity = d -> new UserEntity(d.id(), d.name());
```

4. **Comparator Chains**

```java
Comparator<Order> byAmount = Comparator.comparing(Order::amount);
Comparator<Order> byCreated = Comparator.comparing(Order::createdAt);

orders.stream()
      .sorted(byAmount.reversed().thenComparing(byCreated))
      .forEach(System.out::println);
```

---

## ❌ Common Mistakes & Fixes

- **Multiple abstract methods** → Compilation fails with `@FunctionalInterface`
- **Forgetting generics** → Use parameterized types to avoid raw type warnings
- **Overusing lambdas** → If logic is long/complex, use a named class or method ref
- **Stateful lambdas** → Prefer stateless; if stateful, ensure thread-safety

---

## 🧪 Quick Exercises

- Write a `Predicate<String>` to validate strong passwords.
- Create a `Function<String, String>` that normalizes names (trim, capitalize).
- Build a `Comparator<Employee>` sorting by role then tenure.

---

## ✅ Key Takeaways

- Exactly one abstract method; `@FunctionalInterface` enforces it.
- Backbone of lambdas and method references.
- Compose them to build readable, testable pipelines.
