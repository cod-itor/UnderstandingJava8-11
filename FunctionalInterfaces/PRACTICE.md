# Functional Interfaces - Practice Pack

Use `FunctionalInterfacesExamples.java` for solutions.

## How to run

- Open `FunctionalInterfaces/FunctionalInterfacesExamples.java`
- Add code in `main` or helper methods and run.

## 10 Exercises (easy → hard)

1. **Define SAM**: Create `@FunctionalInterface Op { int run(int a,int b); }` and a lambda for add; print `run(2,3)`.
2. **Predicate**: Make `Predicate<Integer> isEven` and test with 5 and 6; print results.
3. **Supplier**: Use `Supplier<String>` to provide a config URL; call `get()` twice and print.
4. **Consumer**: `Consumer<String> logger = s -> System.out.println("LOG "+s);` and log two messages.
5. **Function chain**: `Function<String,Integer> len` and `Function<Integer,Integer> square`; compose and apply to "hi!".
6. **BiFunction**: Combine first+last name into full name; print.
7. **UnaryOperator**: Normalize a string: trim, lowercase; apply to " Hello ".
8. **Comparator**: Sort `List.of("Zed","amy","Bob")` case-insensitively using `Comparator.comparing(String::toLowerCase)`.
9. **Custom + default**: Add a default method to `Op` for `andThen` to chain two operations; demonstrate add then multiply.
10. **Higher-order**: Write a method `static Op repeat(Op op, int times)` returning an op that applies `op` repeatedly; test with increment op applied 3 times to 1 (expect 4).

## Mini Project

**Rule Engine Lite**: Create a `List<Op>` discounts (e.g., 10% off, minus 5, floor at 0). Compose them with `andThen` and apply to a starting price. Print the final price and show how changing the list changes the outcome.
