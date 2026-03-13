# Functional Interfaces - Java 8

## 📚 Definition

A **Functional Interface** is an interface that contains **exactly one abstract method**. It may include any number of `default` or `static` methods, but only one abstract method (Single Abstract Method - SAM). Mark it with `@FunctionalInterface` to let the compiler enforce the rule.

### 🟢 Beginner quick start
1) Write an interface with **one** abstract method.
2) Add `@FunctionalInterface` so the compiler warns if you add another.
3) Create a lambda to implement it.

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

| Interface           | SAM                | Typical Use          | Example                 |
| ------------------- | ------------------ | -------------------- | ----------------------- |
| `Supplier<T>`       | `T get()`          | Produce a value      | Lazy config loader      |
| `Consumer<T>`       | `void accept(T)`   | Act on a value       | Logging, sending emails |
| `Function<T,R>`     | `R apply(T)`       | Transform a value    | DTO mapping             |
| `Predicate<T>`      | `boolean test(T)`  | Boolean checks       | Validation, filters     |
| `UnaryOperator<T>`  | `T apply(T)`       | Transform same type  | Normalize strings       |
| `BinaryOperator<T>` | `T apply(T,T)`     | Combine two values   | Reduce sums             |
| `BiFunction<T,U,R>` | `R apply(T,U)`     | Transform two inputs | Merge records           |
| `BiConsumer<T,U>`   | `void accept(T,U)` | Act on two inputs    | Audit logging           |
| `Comparator<T>`     | `int compare(T,T)` | Ordering             | Sorting collections     |

---

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
