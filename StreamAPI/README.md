# Stream API - Java 8

## 📚 Definition

A **Stream** is a sequence of objects from a source that supports aggregate operations (filter, map, collect, print, etc.). It's part of the `java.util.stream` package and represents a functional programming approach to processing data.

---

## 🎯 When to Use Streams

### Use Streams When:

- ✅ Processing collections of data (filtering, mapping, sorting)
- ✅ You need to chain multiple operations efficiently
- ✅ Working with large datasets where lazy evaluation is beneficial
- ✅ You want more readable, declarative code
- ✅ Performing aggregate operations (count, sum, average, grouping)
- ✅ Working with parallel processing needs

### Avoid Streams When:

- ❌ Simple one-time operations (use loops instead)
- ❌ Performance is critical on very small datasets
- ❌ You need to reuse the stream (streams are one-time use)

---

## 🔧 Stream Creation Methods

### 1. **Stream.empty()** - Empty Stream

```java
Stream<String> emptyStream = Stream.empty();
```

**When to use:** When you need to return a stream that has no elements, avoiding null checks.

### 2. **collection.stream()** - From Collections

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
Stream<Integer> stream = numbers.stream();
```

**When to use:** Converting any List, Set, or Collection to a stream for processing.

### 3. **Stream.of(Array[])** - From Array

```java
String[] fruits = {"Apple", "Banana", "Orange"};
Stream<String> stream = Stream.of(fruits);
```

**When to use:** Working with arrays that need to be processed as streams.

### 4. **Collection.parallelStream()** - Parallel Processing

```java
Stream<Integer> parallelStream = numbers.parallelStream();
```

**When to use:** For CPU-intensive operations on large datasets where parallel processing can improve performance.

---

## 🏗️ Common Stream Operations

### **Intermediate Operations** (Lazy - don't execute until terminal operation)

- `filter()` - Filter elements based on condition
- `map()` - Transform each element
- `flatMap()` - Map and flatten results
- `distinct()` - Remove duplicates
- `sorted()` - Sort elements
- `limit()` - Limit to N elements
- `skip()` - Skip first N elements

### **Terminal Operations** (Eager - triggers execution)

- `collect()` - Collect results into a collection
- `forEach()` - Process each element
- `reduce()` - Combine all elements into one
- `count()` - Count elements
- `findFirst()` - Get first element
- `allMatch()` / `anyMatch()` - Check conditions
- `toArray()` - Convert to array

---

## 📊 Key Differences: Stream API vs Traditional Loops

### Before Java 8 (Traditional Approach)

```java
// Without Stream API
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
List<Integer> evenNumbers = new ArrayList<>();

for (Integer num : numbers) {
    if (num % 2 == 0) {
        evenNumbers.add(num);
    }
}

for (Integer num : evenNumbers) {
    System.out.println(num);
}
```

**Issues:**

- ❌ Verbose and repetitive
- ❌ Imperative style (HOW to do it)
- ❌ Hard to parallelize
- ❌ More error-prone

### After Java 8 (Stream Approach)

```java
// With Stream API
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

numbers.stream()
    .filter(num -> num % 2 == 0)
    .forEach(System.out::println);
```

**Benefits:**

- ✅ Concise and readable
- ✅ Declarative style (WHAT to do)
- ✅ Easy to parallelize with `.parallelStream()`
- ✅ Composable operations

---

## 💡 Why You Need Streams

### 1. **Readability**

The declarative approach makes code intent clear at first glance.

### 2. **Lazy Evaluation**

Operations don't execute until a terminal operation is called, improving performance:

```java
// Only creates stream, no execution
Stream<Integer> stream = numbers.stream()
    .filter(n -> {
        System.out.println("Filtering: " + n);
        return n > 2;
    })
    .map(n -> {
        System.out.println("Mapping: " + n);
        return n * 2;
    });

// Only NOW does the processing happen
stream.forEach(System.out::println);
```

### 3. **Chainability**

Multiple operations can be chained elegantly:

```java
numbers.stream()
    .filter(n -> n > 2)
    .map(n -> n * 2)
    .sorted()
    .collect(Collectors.toList());
```

### 4. **Functional Programming**

Enables functional programming paradigms in Java.

### 5. **Performance**

- Parallel streams can leverage multi-core processors
- Lazy evaluation skips unnecessary work

---

## 🔄 Stream Lifecycle

```
Source → Intermediate Operations → Terminal Operation → Result
         (lazy)                     (eager)
```

**Important:** Once a terminal operation is called, the stream is consumed and cannot be reused.

```java
Stream<Integer> stream = numbers.stream();
stream.filter(n -> n > 2).count();  // Works
stream.filter(n -> n < 5).count();  // ❌ IllegalStateException!
```

---

## 📈 Real-World Use Cases

### 1. **Filtering and Collecting**

```java
List<String> cities = Arrays.asList("NYC", "LA", "Chicago", "Boston");
List<String> citiesStartingWithC = cities.stream()
    .filter(c -> c.startsWith("C"))
    .collect(Collectors.toList());
// Result: [Chicago]
```

### 2. **Mapping and Transforming**

```java
List<Integer> prices = Arrays.asList(100, 200, 300);
List<Double> discountedPrices = prices.stream()
    .map(p -> p * 0.9)
    .collect(Collectors.toList());
// Result: [90.0, 180.0, 270.0]
```

### 3. **Aggregation Operations**

```java
int sum = numbers.stream()
    .filter(n -> n > 2)
    .mapToInt(Integer::intValue)
    .sum();
```

### 4. **Grouping Data**

```java
List<String> fruits = Arrays.asList("apple", "apricot", "banana", "blueberry");
Map<Character, List<String>> grouped = fruits.stream()
    .collect(Collectors.groupingBy(f -> f.charAt(0)));
// Result: {a=[apple, apricot], b=[banana, blueberry]}
```

---

## ⚠️ Common Mistakes

### ❌ Mistake 1: Reusing a Stream

```java
Stream<Integer> stream = numbers.stream();
stream.forEach(System.out::println);  // Works
stream.filter(n -> n > 2);            // ❌ IllegalStateException
```

### ✅ Solution: Create new stream or chain operations

```java
numbers.stream()
    .forEach(System.out::println);
numbers.stream()
    .filter(n -> n > 2)
    .forEach(System.out::println);
```

### ❌ Mistake 2: Forgetting Terminal Operation

```java
numbers.stream()
    .filter(n -> n % 2 == 0);  // Nothing happens!
```

### ✅ Solution: Add terminal operation

```java
numbers.stream()
    .filter(n -> n % 2 == 0)
    .forEach(System.out::println);  // Now it works
```

---

## 🎓 Key Takeaways

| Aspect                    | Details                                                    |
| ------------------------- | ---------------------------------------------------------- |
| **What**                  | Functional way to process data collections                 |
| **When**                  | Complex data transformations, filtering, aggregation       |
| **Why**                   | Cleaner code, better performance, supports parallelization |
| **Replaces**              | Traditional for/while loops with collections               |
| **Not a replacement for** | Simple loops, immediate side effects, small datasets       |

---

## 📖 Next Steps

See the example files in this folder for practical implementations!
