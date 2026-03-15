# Lambda Expressions - Java 8

## 📚 Definition

A **Lambda Expression** is a short, anonymous function that can be passed around like any other value. It implements a functional interface (an interface with exactly one abstract method) and provides a concise way to write code that needs to be executed later.

**Syntax:**

```java
(parameters) -> expression
// or
(parameters) -> { statements; }
```

**Location:** Introduced in Java 8 as part of functional programming support

---

## 🎯 When to Use Lambda Expressions

### Use Lambdas When:

- ✅ You need a simple function-like behavior
- ✅ Passing logic to methods (callbacks, event handlers)
- ✅ Using functional interfaces (Runnable, Callable, etc.)
- ✅ Working with streams and collections
- ✅ You want concise, readable code
- ✅ Creating anonymous classes for single methods

### Avoid Lambdas When:

- ❌ Logic is complex (use named classes instead)
- ❌ Implementation has multiple methods
- ❌ Code needs to be reused in many places (use named class)
- ❌ Logic doesn't fit on a single line comfortably

---

## 🔧 Lambda Expression Syntax

### 1. **No Parameters**

```java
() -> System.out.println("Hello");

// Usage
Runnable task = () -> System.out.println("Hello");
task.run();
```

### 2. **Single Parameter**

```java
x -> x * 2

// Usage
Function<Integer, Integer> double = x -> x * 2;
int result = double.apply(5);  // 10
```

### 3. **Multiple Parameters**

```java
(x, y) -> x + y

// Usage
BiFunction<Integer, Integer, Integer> add = (x, y) -> x + y;
int result = add.apply(3, 5);  // 8
```

### 4. **Multiple Statements**

```java
(x, y) -> {
    int sum = x + y;
    return sum * 2;
}

// Usage
BiFunction<Integer, Integer, Integer> func = (x, y) -> {
    int sum = x + y;
    return sum * 2;
};
int result = func.apply(3, 5);  // 16
```

### 5. **Explicit Type Declaration**

```java
(Integer x) -> x * 2  // Types usually inferred

// With explicit types
(Integer x, Integer y) -> x + y
```

---

## 📊 Key Differences: Lambda vs Anonymous Classes

### Before Java 8 (Anonymous Class)

```java
// Creating a Runnable with anonymous class (verbose)
Runnable task = new Runnable() {
    @Override
    public void run() {
        System.out.println("Task executed");
    }
};

task.run();
```

**Issues:**

- Very verbose (5 lines for simple logic)
 //Verbose :using or expressed in more words than are needed.
- Lots of boilerplate
- Hard to read the actual logic
- Type safe but cumbersome

## 🧭 If you write this… you get this

- `() -> System.out.println("hi")`
     | → a `Runnable` that, when run, prints `hi`.
- `(a, b) -> a + b` assigned to `BiFunction<Integer,Integer,Integer>`
| → call `apply(2,3)` returns `5`.
- `name -> name.toUpperCase()` assigned to `Function<String,String>` 
|→ `apply("bob")` returns `"BOB"`.
- `(x, y) -> { int sum = x + y; return sum * 2; }`
 |→ multi-line lambda with local variables.
- `Comparator.comparing(UserDto::joinedAt)` 
|→ a comparator you can pass to `sort`/`sorted`.

### Common Spring-ish writes and results

- `list.forEach(u -> log.info(u.getEmail()));` → logs each email (side effect only).
- `users.stream().map(UserDto::getEmail).toList();` → returns new list of emails (no mutation of original).
- `Runnable task = () -> service.sendWelcome("ana"); task.run();` → invokes your service method once.
- `Supplier<String> baseUrl = () -> "https://api.example.com"; baseUrl.get();` → lazily returns the URL.

### After Java 8 (Lambda Expression)

```java
// Creating a Runnable with lambda (concise)
Runnable task = () -> System.out.println("Task executed");

task.run();
```

**Benefits:**

- Concise (1 line)
- Clear intent
- Easy to read
- Focus on the logic, not the syntax

---

## 💡 Why You Need Lambda Expressions

### 1. **Reduces Boilerplate Code**

```java
// Old way - 6 lines for simple logic
Collections.sort(list, new Comparator<String>() {
    public int compare(String a, String b) {
        return a.length() - b.length();
    }
});

// New way - 1 line
Collections.sort(list, (a, b) -> a.length() - b.length());
```

### 2. **Enables Functional Programming**

```java
// Treat functions as values
Function<Integer, Integer> square = x -> x * x;
Function<Integer, Integer> double = x -> x * 2;

// Pass functions to other functions
Integer result = apply(square, 5);  // 25
```

### 3. **Cleaner Stream Operations**

```java
// Without lambda (cumbersome)
List<Integer> evens = new ArrayList<>();
for (Integer num : numbers) {
    if (num % 2 == 0) {
        evens.add(num);
    }
}

// With lambda (clean)
List<Integer> evens = numbers.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList());
```

### 4. **Better for Event Handling**

```java
// Old way
button.setOnClickListener(new OnClickListener() {
    public void onClick(View v) {
        showMessage("Clicked!");
    }
});

// New way
button.setOnClickListener(v -> showMessage("Clicked!"));
```

### 5. **Enables Lazy Evaluation**

```java
// Function is not executed until needed
Supplier<Integer> expensive = () -> computeExpensiveValue();

// Only compute when explicitly asked for
if (needsValue) {
    Integer value = expensive.get();
}
```

---

## 🔄 Functional Interfaces (Required for Lambda)

A **Functional Interface** is an interface with exactly ONE abstract method. Lambda expressions implement these interfaces.

### Common Functional Interfaces:

| Interface           | Method         | Purpose                     | Example                          |
| ------------------- | -------------- | --------------------------- | -------------------------------- |
| `Runnable`          | `run()`        | Execute with no args/return | `() -> System.out.println("Hi")` |
| `Supplier<T>`       | `get()`        | Supply a value              | `() -> "Hello"`                  |
| `Consumer<T>`       | `accept(T)`    | Accept value, no return     | `x -> System.out.println(x)`     |
| `Function<T,R>`     | `apply(T)`     | Transform T to R            | `x -> x * 2`                     |
| `Predicate<T>`      | `test(T)`      | Test and return boolean     | `x -> x > 5`                     |
| `BiFunction<T,U,R>` | `apply(T,U)`   | Two inputs, one output      | `(x,y) -> x + y`                 |
| `Comparator<T>`     | `compare(T,T)` | Compare two values          | `(a,b) -> a - b`                 |

### Creating Custom Functional Interface:

```java
@FunctionalInterface
public interface Calculator {
    int calculate(int a, int b);
}

// Usage
Calculator add = (a, b) -> a + b;
Calculator subtract = (a, b) -> a - b;

System.out.println(add.calculate(10, 5));      // 15
System.out.println(subtract.calculate(10, 5)); // 5
```

---

## 📈 Real-World Use Cases

### 1. **Sorting with Lambda**

```java
List<String> names = Arrays.asList("Charlie", "Alice", "Bob", "Diana");

// Sort by name length
Collections.sort(names, (a, b) -> a.length() - b.length());
System.out.println(names);  // [Bob, Alice, Charlie, Diana]

// Sort alphabetically
Collections.sort(names, String::compareTo);
System.out.println(names);  // [Alice, Bob, Charlie, Diana]
```

### 2. **Stream Operations**

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// Filter and transform
numbers.stream()
    .filter(n -> n % 2 == 0)           // Keep only even
    .map(n -> n * n)                   // Square each
    .forEach(n -> System.out.println(n)); // Print
// Output: 4, 16, 36, 64, 100
```

### 3. **Thread Creation**

```java
// Old way
Thread t = new Thread(new Runnable() {
    public void run() {
        System.out.println("Running in thread");
    }
});

// New way
Thread t = new Thread(() -> System.out.println("Running in thread"));
t.start();
```

### 4. **Event Handlers (GUI)**

```java
// Button click handler
button.setOnAction(event -> handleButtonClick());

// Mouse hover
label.setOnMouseEntered(event -> label.setStyle("-fx-background-color: yellow;"));
```

### 5. **Callbacks**

```java
public void loadData(String url, Consumer<String> callback) {
    // Simulate loading
    String data = "Loaded: " + url;
    callback.accept(data);
}

// Usage
loadData("api.example.com", result -> System.out.println(result));
```

### 6. **Filtering Data**

```java
List<Employee> employees = getEmployees();

// Get all employees earning more than 50000
List<Employee> wellPaid = employees.stream()
    .filter(emp -> emp.getSalary() > 50000)
    .collect(Collectors.toList());
```

### 7. **Method References (Shorthand Lambda)**

```java
// Lambda version
list.forEach(item -> System.out.println(item));

// Method reference version (even more concise)
list.forEach(System.out::println);

// Converting to uppercase
strings.forEach(s -> System.out.println(s.toUpperCase()));
strings.forEach(String::toUpperCase);
```

---

## 🏗️ Method References (Lambda Shorthand)

Method references are a shorthand for lambdas that call a specific method:

### Types of Method References:

### 1. **Static Method Reference**

```java
// Lambda
Function<Integer, String> toStr = x -> String.valueOf(x);

// Method reference
Function<Integer, String> toStr = String::valueOf;
```

### 2. **Instance Method Reference**

```java
String str = "Hello";

// Lambda
Consumer<String> print = s -> System.out.println(s);

// Method reference
Consumer<String> print = System.out::println;
```

### 3. **Constructor Reference**

```java
// Lambda
Supplier<ArrayList> supplier = () -> new ArrayList<>();

// Constructor reference
Supplier<ArrayList> supplier = ArrayList::new;
```

### 4. **Bound Instance Method Reference**

```java
String str = "hello";

// Lambda
Function<String, Boolean> contains = s -> str.contains(s);

// Method reference
Function<String, Boolean> contains = str::contains;
```

---

## ⚠️ Common Mistakes

### ❌ Mistake 1: Complex Logic in Lambda

```java
// ❌ Hard to read
BiFunction<Integer, Integer, Integer> calculate = (a, b) -> {
    int sum = a + b;
    int product = a * b;
    return sum * product + (sum > product ? 1 : 0);
};

// ✅ Better - use named class
public class Calculation {
    public static Integer calculate(int a, int b) {
        int sum = a + b;
        int product = a * b;
        return sum * product + (sum > product ? 1 : 0);
    }
}

BiFunction<Integer, Integer, Integer> calc = Calculation::calculate;
```

### ❌ Mistake 2: Variable Scope Issues

```java
int x = 10;

// ❌ This won't work - x is modified
Consumer<Integer> consumer = num -> System.out.println(x + num);
x = 20;  // ERROR - x must be effectively final

// ✅ This works - x is not modified
final int x = 10;  // explicitly final
Consumer<Integer> consumer = num -> System.out.println(x + num);
```

### ❌ Mistake 3: Wrong Functional Interface

```java
// ❌ Predicate returns boolean, not prints
list.stream()
    .filter(item -> System.out.println(item))  // ERROR
    .collect(Collectors.toList());

// ✅ Use forEach for printing
list.forEach(item -> System.out.println(item));

// ✅ Or use proper Predicate
list.stream()
    .filter(item -> item != null)
    .forEach(System.out::println);
```

### ❌ Mistake 4: Ignoring Exceptions

```java
// ❌ Exception not handled
list.forEach(item -> risky Operation(item));  // Throws exception

// ✅ Handle exceptions
list.forEach(item -> {
    try {
        riskyOperation(item);
    } catch (Exception e) {
        System.err.println("Error: " + e.getMessage());
    }
});
```

### ❌ Mistake 5: Shadowing Variables

```java
int x = 5;

// ❌ Variable name conflicts
list.forEach(x -> System.out.println(x));  // x shadows outer x
System.out.println(x);  // Still 5, but confusing!

// ✅ Use clear variable names
list.forEach(item -> System.out.println(item));
```

---

## 🎓 Lambda vs Method References vs Anonymous Classes

| Aspect            | Anonymous Class | Lambda          | Method Reference         |
| ----------------- | --------------- | --------------- | ------------------------ |
| **Lines of Code** | Multiple        | 1-3             | 1                        |
| **Readability**   | Low             | High            | Highest                  |
| **Performance**   | OK              | Good            | Best                     |
| **Reusability**   | Once            | Once            | Can be reused            |
| **When to Use**   | Complex logic   | Simple behavior | Calling existing methods |

---

## 🔄 Evolution: Anonymous Class → Lambda → Method Reference

```java
// 1. Anonymous Class (before Java 8)
Comparator<String> comp1 = new Comparator<String>() {
    @Override
    public int compare(String a, String b) {
        return Integer.compare(a.length(), b.length());
    }
};

// 2. Lambda (Java 8+)
Comparator<String> comp2 = (a, b) -> Integer.compare(a.length(), b.length());

// 3. Method Reference (Java 8+, most concise)
Comparator<String> comp3 = Comparator.comparingInt(String::length);
```

---

## 🎯 Important Lambda Rules

### 1. **Effectively Final Variables**

Variables from outer scope must be `final` or effectively final:

```java
int x = 10;  // Must not be reassigned
list.forEach(item -> System.out.println(x + item));  // OK
x = 20;  // ERROR - x is no longer effectively final
```

### 2. **Return Type Inference**

Return type is inferred from context:

```java
// Compiler knows return type should be Integer
Function<Integer, Integer> square = x -> x * x;
```

### 3. **Exception Handling**

Unchecked exceptions can be thrown, checked exceptions need handling:

```java
// ✅ Unchecked (RuntimeException) - OK
Consumer<String> print = s -> System.out.println(s);

// ❌ Checked exception - needs handling
Consumer<String> read = s -> { throw new IOException("Error"); };  // ERROR

// ✅ With try-catch
Consumer<String> read = s -> {
    try { throw new IOException("Error"); }
    catch (IOException e) { e.printStackTrace(); }
};
```

---

## 🎓 Key Takeaways

| Aspect       | Details                                             |
| ------------ | --------------------------------------------------- |
| **What**     | Anonymous function for concise code                 |
| **When**     | Short operations, callbacks, streams                |
| **Why**      | Reduces boilerplate, enables functional programming |
| **Replaces** | Anonymous inner classes                             |
| **Requires** | Functional interface (1 abstract method)            |
| **Best For** | Simple single-line operations                       |
| **Not For**  | Complex multi-line logic                            |

---

## 📖 Next Steps

See the example files in this folder for practical implementations!
