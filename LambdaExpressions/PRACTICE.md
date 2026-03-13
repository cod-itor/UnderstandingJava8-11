# Lambda Expressions - Practice Pack

Use `LambdaExpressions.java` as a playground. Copy/paste tasks in `main` or add helper methods.

## How to run

- Open `LambdaExpressions/LambdaExpressions.java`
- Add your solutions inside `main` (or new static methods) and run.

## 10 Exercises (easy → hard)

1. **Add numbers**: Write a lambda `Adder` that sums two ints; print `Adder.apply(2, 5)`.
2. **String length**: Create a `Function<String,Integer>` that returns length; map over `List.of("hi","lambda")` and print.
3. **Filter even**: With `Predicate<Integer>`, keep even numbers from `List.of(1,2,3,4,5,6)` and print.
4. **Sort by length**: Sort `List.of("pear","fig","banana")` using a lambda comparator by length, then alphabetically.
5. **Transform names**: `List<String> names` → trim, uppercase, and add "!" using chained lambdas.
6. **Custom functional interface**: Define `@FunctionalInterface Operation { int run(int a,int b); }` and implement add/subtract/multiply lambdas; print results.
7. **Compose functions**: `Function<Integer,Integer> doubleIt` and `squareIt`; compose and apply to 3 (expect 36).
8. **Capture effectively-final**: Create a lambda that appends a suffix defined outside; show it compiles only if the variable isn’t reassigned.
9. **Exception handling**: Wrap a lambda that may throw `NumberFormatException` and handle it inside the lambda (print "bad number").
10. **Parallel list apply**: Given two lists of equal size, use a lambda in a loop to combine elements into `List<String>` like `"a-1"`.

## Mini Project

Build a **Calculator CLI** with lambdas:

- Register operations in `Map<String, Operation>` (e.g., "+", "-", "\*", "/").
- Read hard-coded expressions like `"3 * 4"` and pick the lambda by symbol.
- Print the result; handle unknown symbols with a default lambda that returns 0 and logs an error.

## Spring-flavored labs
1. Sort a list of `UserDto` by `createdAt` using a lambda comparator; return the list.
2. Filter a list of `OrderDto` to status = PAID using a lambda predicate; print ids.
3. Map a list of `Product` to uppercased names with a lambda; collect and print.
4. Build `BiFunction<String,Integer,String>` that returns `"{user} has {n} notifications"`; log it.
5. Create a `Runnable` lambda that simulates an async task (prints "sending email"); run it.
