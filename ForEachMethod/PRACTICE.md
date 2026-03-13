# forEach() - Practice Pack

Use `ForEachMethod/ForEachExamples.java` for solutions.

## How to run

- Open `ForEachMethod/ForEachExamples.java`
- Add code to `main` or helper methods and run.

## 10 Exercises (easy → hard)

1. **Print items**: `List.of("a","b","c")` → print each with `forEach(System.out::println)`.
2. **Index + value**: Print index and value for `List.of("red","green","blue")` using a simple counter outside the lambda.
3. **Uppercase**: Print each string uppercased inside `forEach` using a lambda.
4. **Filter then forEach**: On `List.of(1,2,3,4,5)`, stream, filter even, forEach print.
5. **Map iteration**: For `Map.of("us",1,"uk",2)`, use `forEach((k,v)->...)` to print `k=v`.
6. **Mutable accumulation**: Sum numbers with an `AtomicInteger` inside `forEach` and print the sum.
7. **List mutation caution**: Show why modifying the list inside `forEach` throws `ConcurrentModificationException` (comment the failing code) and then do it safely by collecting first.
8. **Conditional print**: In `forEach`, only print strings longer than 3 chars (use `if` inside lambda).
9. **Nested forEach**: Given `List<List<Integer>>`, print all numbers in one line separated by spaces.
10. **Exception handling**: In `forEach`, catch `NumberFormatException` when parsing `List.of("1","x","3")` and print an error per bad value.

## Mini Project

**Color Counter**: Given `List<String> colors` with duplicates, use `forEach` to populate a `Map<String,Integer>` of counts, then print results. Avoid `getOrDefault` by using `merge` in the map.
