# Method References - Java 8

## 🚀 What Are They?
A **method reference** is a shorthand for a lambda that calls an existing method. It improves readability and reuses named behavior.

Syntax patterns:
1. `ClassName::staticMethod`
2. `instance::instanceMethod` (bound instance)
3. `ClassName::instanceMethod` (unbound; first arg becomes the receiver)
4. `ClassName::new` (constructor reference)

---

## 🌱 Examples by Type
### 1) Static Method Reference
```java
Function<String, Integer> parse = Integer::parseInt;
```

### 2) Bound Instance Method Reference
```java
Logger logger = Logger.getLogger("demo");
Consumer<String> info = logger::info; // equivalent to s -> logger.info(s)
```

### 3) Unbound Instance Method Reference
```java
BiPredicate<String, String> equalsIgnoreCase = String::equalsIgnoreCase;
```

### 4) Constructor Reference
```java
Supplier<List<String>> listFactory = ArrayList::new;
Function<String, User> toUser = User::new; // calls new User(String name)
```

---

## 🌍 Real-World Use Cases
- **Stream pipelines** for mapping, filtering, reducing while staying readable
- **Factory patterns** via constructor references
- **Event handlers / callbacks** using existing methods instead of inline lambdas
- **Collecting** with `Collectors.toMap` / `groupingBy` using method refs

---

## 🧭 Stream Pipeline Examples
```java
List<String> emails = users.stream()
    .map(User::email)
    .filter(MethodReferences::isCompanyEmail)
    .sorted(String::compareToIgnoreCase)
    .toList();
```

```java
Map<String, Long> countsByDomain = emails.stream()
    .collect(Collectors.groupingBy(MethodReferences::domainOf, Collectors.counting()));
```

---

## ⚠️ Tips & Pitfalls
- Prefer method references when the lambda would only call an existing method; otherwise keep the lambda for clarity.
- For unbound refs (`Class::instanceMethod`), remember the first argument becomes `this`.
- Constructor refs shine with factories: `Map<String, Supplier<Service>> registry = Map.of("a", ServiceA::new, "b", ServiceB::new);`

---

## ✅ Takeaways
- Method references are just readable shortcuts for certain lambdas.
- They work with functional interfaces (Supplier, Function, Consumer, etc.).
- Combine them with streams, collectors, and factories for clean code.
