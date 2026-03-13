import java.util.*;
import java.util.function.*;

/**
 * Lambda Expressions - Practical Examples
 * Demonstrates lambda syntax, functional interfaces, and real-world usage
 */
public class LambdaExpressions {

    // ===== LAMBDA SYNTAX =====
    
    /**
     * Example 1: No Parameters
     */
    public static void demonstrateNoParameters() {
        System.out.println("\n=== Lambda with No Parameters ===");
        
        // Runnable - executes with no parameters or return
        Runnable task1 = () -> System.out.println("Hello from lambda!");
        
        // Supplier - supplies a value
        Supplier<String> supplier = () -> "Generated value";
        
        System.out.println("Running task:");
        task1.run();
        
        System.out.println("Supplying value:");
        System.out.println(supplier.get());
    }
    
    /**
     * Example 2: Single Parameter (No Parentheses)
     */
    public static void demonstrateSingleParameter() {
        System.out.println("\n=== Lambda with Single Parameter ===");
        
        // Consumer - accepts one parameter, returns nothing
        Consumer<String> print = msg -> System.out.println("Message: " + msg);
        
        // Function - transforms one parameter to result
        Function<Integer, Integer> square = x -> x * x;
        
        // Predicate - tests condition
        Predicate<Integer> isEven = x -> x % 2 == 0;
        
        System.out.println("Consumer:");
        print.accept("Hello");
        
        System.out.println("Function:");
        System.out.println("5 squared = " + square.apply(5));
        
        System.out.println("Predicate:");
        System.out.println("4 is even? " + isEven.test(4));
        System.out.println("7 is even? " + isEven.test(7));
    }
    
    /**
     * Example 3: Multiple Parameters
     */
    public static void demonstrateMultipleParameters() {
        System.out.println("\n=== Lambda with Multiple Parameters ===");
        
        // BiFunction - takes two parameters, returns result
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        
        // BiConsumer - accepts two parameters, returns nothing
        BiConsumer<String, Integer> printWithCount = (msg, count) -> {
            for (int i = 0; i < count; i++) {
                System.out.println(msg);
            }
        };
        
        // Comparator - compares two values
        Comparator<String> byLength = (s1, s2) -> s1.length() - s2.length();
        
        System.out.println("BiFunction (add):");
        System.out.println("3 + 5 = " + add.apply(3, 5));
        
        System.out.println("BiConsumer (print with count):");
        printWithCount.accept("Java", 2);
        
        System.out.println("Comparator (by length):");
        List<String> names = new ArrayList<>(Arrays.asList("Alice", "Bob", "Christopher", "Dan"));
        Collections.sort(names, byLength);
        System.out.println("Sorted by length: " + names);
    }
    
    /**
     * Example 4: Multiple Statements (Block Lambda)
     */
    public static void demonstrateMultipleStatements() {
        System.out.println("\n=== Lambda with Multiple Statements ===");
        
        // Lambda with curly braces allows multiple statements
        Function<Integer, Integer> complexLogic = x -> {
            int squared = x * x;
            int doubled = squared * 2;
            System.out.println("  Processing: " + x + " -> " + squared + " -> " + doubled);
            return doubled;
        };
        
        System.out.println("Executing complex logic:");
        int result = complexLogic.apply(5);
        System.out.println("Final result: " + result);
    }
    
    /**
     * Example 5: Explicit Type Declaration
     */
    public static void demonstrateExplicitTypes() {
        System.out.println("\n=== Lambda with Explicit Types ===");
        
        // Types are usually inferred, but can be explicit
        BiFunction<Integer, Integer, Integer> multiply1 = (x, y) -> x * y;  // Inferred
        BiFunction<Integer, Integer, Integer> multiply2 = (Integer x, Integer y) -> x * y;  // Explicit
        
        System.out.println("With inferred types: 5 * 3 = " + multiply1.apply(5, 3));
        System.out.println("With explicit types: 5 * 3 = " + multiply2.apply(5, 3));
    }

    /**
     * Example 5b: If you write this... you get this (quick outcomes)
     */
    public static void demonstrateIfYouWriteThis() {
        System.out.println("\n=== If you write this... you get this (lambdas) ===");

        Runnable hi = () -> System.out.println("hi");
        hi.run(); // prints hi

        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        System.out.println("add 2+3 = " + add.apply(2, 3));

        Function<String, String> upper = name -> name.toUpperCase();
        System.out.println("upper('bob') = " + upper.apply("bob"));

        BiFunction<Integer, Integer, Integer> doubleSum = (x, y) -> {
            int sum = x + y;
            return sum * 2;
        };
        System.out.println("doubleSum(2,3) = " + doubleSum.apply(2, 3));

        Comparator<UserDto> byJoined = Comparator.comparing(UserDto::joinedAt);
        List<UserDto> users = new ArrayList<>(Arrays.asList(
            new UserDto("ana", "a@x.com", new Date(1_000_000)),
            new UserDto("bob", "b@x.com", new Date(2_000_000))
        ));
        users.sort(byJoined);
        System.out.println("sorted by joined: " + users);
    }
    
    // ===== FUNCTIONAL INTERFACES =====
    
    /**
     * Example 6: Using Built-in Functional Interfaces
     */
    public static void demonstrateBuiltInInterfaces() {
        System.out.println("\n=== Built-in Functional Interfaces ===");
        
        // All implement single abstract method interfaces
        Runnable run = () -> System.out.println("  Runnable: executes action");
        Supplier<String> supply = () -> "Supplied value";
        Consumer<String> consume = s -> System.out.println("  Consumer: " + s);
        Function<Integer, String> transform = n -> "Number: " + n;
        Predicate<Integer> testCondition = n -> n > 5;
        BiFunction<Integer, Integer, Integer> combine = (a, b) -> a + b;
        
        System.out.println("Runnable:");
        run.run();
        
        System.out.println("Supplier:");
        System.out.println("  " + supply.get());
        
        System.out.println("Consumer:");
        consume.accept("Test string");
        
        System.out.println("Function:");
        System.out.println("  " + transform.apply(42));
        
        System.out.println("Predicate:");
        System.out.println("  Is 7 > 5? " + testCondition.test(7));
        
        System.out.println("BiFunction:");
        System.out.println("  3 + 4 = " + combine.apply(3, 4));
    }
    
    /**
     * Example 7: Custom Functional Interface
     */
    public static void demonstrateCustomInterface() {
        System.out.println("\n=== Custom Functional Interface ===");
        
        // Using the custom Calculator interface
        Calculator add = (a, b) -> a + b;
        Calculator subtract = (a, b) -> a - b;
        Calculator multiply = (a, b) -> a * b;
        Calculator divide = (a, b) -> b != 0 ? a / b : 0;
        
        System.out.println("Using custom Calculator interface:");
        System.out.println("  10 + 5 = " + add.calculate(10, 5));
        System.out.println("  10 - 5 = " + subtract.calculate(10, 5));
        System.out.println("  10 * 5 = " + multiply.calculate(10, 5));
        System.out.println("  10 / 5 = " + divide.calculate(10, 5));
    }
    
    /**
     * Example 8: Sorting with Lambda
     */
    public static void demonstrateSortingWithLambda() {
        System.out.println("\n=== Sorting with Lambda ===");
        
        List<String> fruits = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry", "Date"));
        
        System.out.println("Original list: " + fruits);
        
        // Sort by alphabetical order
        Collections.sort(fruits, (a, b) -> a.compareTo(b));
        System.out.println("Sorted A-Z: " + fruits);
        
        // Sort by reverse alphabetical order
        Collections.sort(fruits, (a, b) -> b.compareTo(a));
        System.out.println("Sorted Z-A: " + fruits);
        
        // Sort by length
        Collections.sort(fruits, (a, b) -> a.length() - b.length());
        System.out.println("Sorted by length: " + fruits);
    }
    
    /**
     * Example 9: Stream Operations with Lambda
     */
    public static void demonstrateStreamOperations() {
        System.out.println("\n=== Stream Operations with Lambda ===");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Filter - keep only elements matching condition
        System.out.println("Even numbers:");
        numbers.stream()
            .filter(n -> n % 2 == 0)
            .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // Map - transform each element
        System.out.println("Each number squared:");
        numbers.stream()
            .map(n -> n * n)
            .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // Reduce - combine all elements
        System.out.println("Sum of all numbers:");
        int sum = numbers.stream()
            .reduce(0, (a, b) -> a + b);
        System.out.println(sum);
        
        // Combining operations
        System.out.println("Numbers > 3, squared, and summed:");
        int result = numbers.stream()
            .filter(n -> n > 3)
            .map(n -> n * n)
            .reduce(0, Integer::sum);
        System.out.println(result);
    }
    
    /**
     * Example 10: Method References (Shorthand for Lambda)
     */
    public static void demonstrateMethodReferences() {
        System.out.println("\n=== Method References ===");
        
        List<String> strings = Arrays.asList("apple", "banana", "cherry");
        
        System.out.println("Method references are shorthand for lambdas:");
        System.out.println("Lambda: x -> System.out.println(x)");
        System.out.println("Method ref: System.out::println");
        System.out.println();
        
        // 1. Static method reference
        System.out.println("Static method reference:");
        List<String> numbers = Arrays.asList("1", "2", "3");
        numbers.stream()
            .map(Integer::parseInt)
            .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // 2. Instance method reference
        System.out.println("Instance method reference:");
        strings.stream()
            .map(String::toUpperCase)
            .forEach(s -> System.out.print(s + " "));
        System.out.println();
        
        // 3. Constructor reference
        System.out.println("Constructor reference:");
        Supplier<ArrayList> createList = ArrayList::new;
        ArrayList list = createList.get();
        System.out.println("Created: " + list);
        
        // 4. Bound instance method reference
        System.out.println("Bound instance method reference:");
        String prefix = "Item: ";
        Consumer<String> consumer = s -> System.out.println(prefix + s);
        strings.forEach(consumer);
    }
    
    /**
     * Example 11: Callbacks with Lambda
     */
    public static void demonstrateCallbacks() {
        System.out.println("\n=== Lambda Callbacks ===");
        
        // Simulate loading data with callback
        loadUserData(userId -> {
            System.out.println("Data loading complete for user: " + userId);
            System.out.println("Processing user data...");
        });
    }
    
    private static void loadUserData(Consumer<Integer> callback) {
        System.out.println("Starting data load...");
        try {
            Thread.sleep(500);  // Simulate delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.accept(12345);  // Call with result
    }
    
    /**
     * Example 12: Functional Composition
     */
    public static void demonstrateFunctionalComposition() {
        System.out.println("\n=== Functional Composition ===");
        
        // Create multiple functions
        Function<Integer, Integer> addTwo = x -> x + 2;
        Function<Integer, Integer> multiplyByThree = x -> x * 3;
        
        System.out.println("Composing functions:");
        System.out.println("addTwo: 5 -> " + addTwo.apply(5));
        System.out.println("multiplyByThree: 5 -> " + multiplyByThree.apply(5));
        
        // Compose: apply addTwo first, then multiplyByThree
        System.out.println("Compose (first addTwo, then multiply):");
        Function<Integer, Integer> composed = addTwo.andThen(multiplyByThree);
        System.out.println("5 -> " + addTwo.apply(5) + " -> " + composed.apply(5));
    }
    
    /**
     * Example 13: Real-world: Filtering Data
     */
    public static void demonstrateDataFiltering() {
        System.out.println("\n=== Real-World: Data Filtering ===");
        
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", 75000),
            new Employee("Bob", 45000),
            new Employee("Charlie", 85000),
            new Employee("Diana", 55000),
            new Employee("Eve", 95000)
        );
        
        // Get high earners
        System.out.println("Employees earning > 60000:");
        employees.stream()
            .filter(emp -> emp.salary > 60000)
            .map(emp -> emp.name)
            .forEach(name -> System.out.println("  - " + name));
    }
    
    /**
     * Example 14: Threading with Lambda
     */
    public static void demonstrateThreading() {
        System.out.println("\n=== Threading with Lambda ===");
        
        // Old way (verbose)
        // Thread t1 = new Thread(new Runnable() {
        //     public void run() {
        //         System.out.println("Running in thread");
        //     }
        // });
        
        // New way (concise)
        Thread t1 = new Thread(() -> {
            System.out.println("Thread 1: Starting");
            try { Thread.sleep(100); } catch (Exception e) {}
            System.out.println("Thread 1: Done");
        });
        
        Thread t2 = new Thread(() -> {
            System.out.println("Thread 2: Starting");
            try { Thread.sleep(50); } catch (Exception e) {}
            System.out.println("Thread 2: Done");
        });
        
        t1.start();
        t2.start();
        
        try { t1.join(); t2.join(); } catch (InterruptedException e) {}
    }
    
    /**
     * Example 15: Common Mistake 1 - Variable Scope
     */
    public static void demonstrateMistake1() {
        System.out.println("\n=== COMMON MISTAKE 1: Variable Scope ===");
        
        int x = 10;  // Must be effectively final
        
        Consumer<Integer> consumer = num -> System.out.println("x + num = " + (x + num));
        
        System.out.println("✅ This works - x is not modified:");
        consumer.accept(5);
        
        System.out.println("❌ This would fail if uncommented:");
        System.out.println("x = 20;  // ERROR - x must be effectively final");
        System.out.println("consumer.accept(5);");
    }
    
    /**
     * Example 16: Common Mistake 2 - Overusing Lambda
     */
    public static void demonstrateMistake2() {
        System.out.println("\n=== COMMON MISTAKE 2: Complex Logic in Lambda ===");
        
        System.out.println("❌ Too complex for lambda:");
        System.out.println("BiFunction<Integer, Integer, Integer> calc = (a, b) -> {");
        System.out.println("    int sum = a + b;");
        System.out.println("    int product = a * b;");
        System.out.println("    return sum * product + (sum > product ? 1 : 0);");
        System.out.println("};");
        System.out.println();
        System.out.println("✅ Better to use named method:");
        System.out.println("BiFunction<Integer, Integer, Integer> calc = ");
        System.out.println("    CalculatorUtil::complexCalculation;");
    }
    
    /**
     * Example 17: Common Mistake 3 - Wrong Return Type
     */
    public static void demonstrateMistake3() {
        System.out.println("\n=== COMMON MISTAKE 3: Wrong Functional Interface ===");
        
        List<String> items = Arrays.asList("A", "B", "C");
        
        System.out.println("❌ Wrong - Predicate should return boolean:");
        System.out.println("items.stream().filter(s -> System.out.println(s))");
        System.out.println("// ERROR: Predicate must return boolean, not void");
        System.out.println();
        
        System.out.println("✅ Correct - Use forEach instead:");
        items.forEach(s -> System.out.println(s));
    }
    
    /**
     * Example 18: Edge Cases
     */
    public static void demonstrateEdgeCases() {
        System.out.println("\n=== Edge Cases ===");
        
        // Empty parameter list in parentheses
        Runnable r1 = () -> System.out.println("No params (with parens)");
        
        // Lambda with no type specified
        java.util.function.BiFunction<Integer, Integer, Integer> f = (a, b) -> a + b;
        System.out.println("Types inferred: 5 + 3 = " + f.apply(5, 3));
        
        // Lambda in if condition
        System.out.println("Lambda in if condition:");
        Predicate<Integer> isPositive = x -> x > 0;
        if (isPositive.test(5)) {
            System.out.println("  5 is positive");
        }
    }
    
    // ===== HELPER CLASSES =====
    
    @FunctionalInterface
    interface Calculator {
        int calculate(int a, int b);
    }
    
    static class Employee {
        String name;
        double salary;
        
        Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }
    }
    
    // ===== MAIN METHOD =====
    
    public static void main(String[] args) {
        System.out.println("╔═════════════════════════════════════════╗");
        System.out.println("║  LAMBDA EXPRESSIONS - COMPREHENSIVE DEMO ║");
        System.out.println("╚═════════════════════════════════════════╝");
        
        // Lambda Syntax
        demonstrateNoParameters();
        demonstrateSingleParameter();
        demonstrateMultipleParameters();
        demonstrateMultipleStatements();
        demonstrateExplicitTypes();
        
        // Functional Interfaces
        demonstrateBuiltInInterfaces();
        demonstrateCustomInterface();
        
        // Practical Uses
        demonstrateSortingWithLambda();
        demonstrateStreamOperations();
        demonstrateMethodReferences();
        
        // Real-World Examples
        demonstrateCallbacks();
        demonstrateFunctionalComposition();
        demonstrateDataFiltering();
        demonstrateThreading();
        
        // Edge Cases and Mistakes
        demonstrateEdgeCases();
        demonstrateMistake1();
        demonstrateMistake2();
        demonstrateMistake3();
    }
}
