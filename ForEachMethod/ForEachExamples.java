import java.util.*;

/**
 * forEach() Method - Practical Examples
 * Demonstrates various use cases and patterns
 */
public class ForEachExamples {

    // ===== BASIC FOREACH EXAMPLES =====
    
    /**
     * Example 1: Simple iteration with forEach()
     * Replaces traditional enhanced for loop
     */
    public static void demonstrateBasicForEach() {
        System.out.println("\n=== BASIC forEach() ===");
        
        List<String> fruits = Arrays.asList("Apple", "Banana", "Orange", "Mango");
        
        // Traditional way (still works)
        System.out.println("Traditional for loop:");
        for (String fruit : fruits) {
            System.out.println("  " + fruit);
        }
        
        // Modern way with forEach()
        System.out.println("forEach() method:");
        fruits.forEach(fruit -> System.out.println("  " + fruit));
    }
    
    /**
     * Example 2: forEach() with method references
     * Even more concise syntax
     */
    public static void demonstrateMethodReferences() {
        System.out.println("\n=== forEach() with Method References ===");
        
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Diana");
        
        // Lambda expression
        System.out.println("With lambda:");
        names.forEach(name -> System.out.println("  " + name));
        
        // Method reference (most concise)
        System.out.println("With method reference:");
        names.forEach(System.out::println);
    }
    
    /**
     * Example 3: forEach() on different collection types
     */
    public static void demonstrateForEachOnDifferentCollections() {
        System.out.println("\n=== forEach() on Different Collections ===");
        
        // List
        System.out.println("List:");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        numbers.forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // Set
        System.out.println("Set:");
        Set<String> uniqueColors = new HashSet<>(Arrays.asList("Red", "Green", "Blue"));
        uniqueColors.forEach(color -> System.out.print(color + " "));
        System.out.println();
        
        // Queue
        System.out.println("Queue:");
        Queue<Double> temperatures = new LinkedList<>(Arrays.asList(98.6, 101.2, 99.5));
        temperatures.forEach(temp -> System.out.print(temp + " "));
        System.out.println();
    }
    
    /**
     * Example 4: forEach() with complex operations
     */
    public static void demonstrateForEachWithComplexOperations() {
        System.out.println("\n=== forEach() with Complex Operations ===");
        
        List<Person> people = Arrays.asList(
            new Person("Alice", 30, "Engineer"),
            new Person("Bob", 25, "Designer"),
            new Person("Charlie", 35, "Manager")
        );
        
        // Simple property access
        System.out.println("Print details:");
        people.forEach(person -> 
            System.out.println("  " + person.getName() + " - " + person.getAge() + " years - " + person.getRole())
        );
        
        // Calling methods on objects
        System.out.println("\nGive everyone a promotion:");
        people.forEach(person -> person.promote());
        
        // After promotion
        people.forEach(person ->
            System.out.println("  " + person.getName() + " is now: " + person.getRole())
        );
    }
    
    /**
     * Example 5: forEach() with multiple statements
     * Using block lambda with curly braces
     */
    public static void demonstrateForEachWithMultipleStatements() {
        System.out.println("\n=== forEach() with Multiple Statements ===");
        
        List<Product> products = Arrays.asList(
            new Product("Laptop", 1000),
            new Product("Mouse", 25),
            new Product("Monitor", 400)
        );
        
        System.out.println("Process each product:");
        products.forEach(product -> {
            // Multiple statements in lambda
            double tax = product.getPrice() * 0.1;
            double total = product.getPrice() + tax;
            System.out.println("  " + product.getName() + ": $" + product.getPrice() + 
                             " + $" + String.format("%.2f", tax) + " tax = $" + String.format("%.2f", total));
        });
    }
    
    /**
     * Example 6: forEach() with conditional logic
     */
    public static void demonstrateForEachWithConditionals() {
        System.out.println("\n=== forEach() with Conditional Logic ===");
        
        List<Integer> scores = Arrays.asList(45, 78, 92, 55, 88, 95, 34);
        
        System.out.println("Students with passing grades (>= 70):");
        scores.forEach(score -> {
            String status = score >= 70 ? "PASS" : "FAIL";
            System.out.println("  Score: " + score + " -> " + status);
        });
    }
    
    /**
     * Example 7: forEach() with Maps
     * Different ways to iterate over maps
     */
    public static void demonstrateForEachWithMaps() {
        System.out.println("\n=== forEach() with Maps ===");
        
        Map<String, Integer> studentGrades = new HashMap<>();
        studentGrades.put("Alice", 95);
        studentGrades.put("Bob", 87);
        studentGrades.put("Charlie", 92);
        studentGrades.put("Diana", 78);
        
        // Iterate over entries (most efficient)
        System.out.println("Iterate over entries:");
        studentGrades.forEach((name, grade) ->
            System.out.println("  " + name + ": " + grade)
        );
        
        // Iterate over keys
        System.out.println("Iterate over keys:");
        studentGrades.keySet().forEach(name ->
            System.out.println("  " + name)
        );
        
        // Iterate over values
        System.out.println("Iterate over values:");
        studentGrades.values().forEach(grade ->
            System.out.println("  Grade: " + grade)
        );
    }
    
    /**
     * Example 8: forEach() with Streams
     * Natural extension of stream operations
     */
    public static void demonstrateForEachWithStreams() {
        System.out.println("\n=== forEach() with Streams ===");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Filter, transform, then print
        System.out.println("Even numbers doubled:");
        numbers.stream()
            .filter(n -> n % 2 == 0)
            .map(n -> n * 2)
            .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // Complex stream with forEach
        System.out.println("Numbers > 5, sorted, and printed:");
        numbers.stream()
            .filter(n -> n > 5)
            .sorted()
            .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // Quick "if you write this… you get this" samples
        System.out.println("New list with evens doubled (stream -> collect):");
        List<Integer> evensDoubled = numbers.stream()
            .filter(n -> n % 2 == 0)
            .map(n -> n * 2)
            .toList();
        System.out.println(evensDoubled); // [4, 8, 12, 16, 20]

        System.out.println("Distinct sorted domains from emails:");
        List<String> emails = Arrays.asList("a@x.com", "b@y.com", "c@x.com");
        List<String> domains = emails.stream()
            .map(e -> e.split("@")[1])
            .distinct()
            .sorted()
            .toList();
        System.out.println(domains); // [x.com, y.com]
    }
    
    /**
     * Example 9: forEach() with Exception Handling
     */
    public static void demonstrateForEachWithExceptionHandling() {
        System.out.println("\n=== forEach() with Exception Handling ===");
        
        List<String> urls = Arrays.asList("http://google.com", "http://invalid", "http://github.com");
        
        System.out.println("Attempting to fetch URLs:");
        urls.forEach(url -> {
            try {
                System.out.println("  Fetching: " + url);
                // Simulated fetch - would throw exception for "invalid"
                if (url.contains("invalid")) {
                    throw new Exception("Invalid URL");
                }
                System.out.println("    ✓ Success");
            } catch (Exception e) {
                System.out.println("    ✗ Error: " + e.getMessage());
            }
        });
    }
    
    /**
     * Example 10: COMMON MISTAKE - Trying to break out of forEach()
     * This will NOT compile
     */
    public static void demonstrateForEachMistake1() {
        System.out.println("\n=== COMMON MISTAKE 1: Can't Break from forEach() ===");
        
        List<String> items = Arrays.asList("A", "B", "C", "D", "E");
        
        // ❌ This won't compile:
        // items.forEach(item -> {
        //     if (item.equals("C")) {
        //         break;  // Compile error!
        //     }
        //     System.out.println(item);
        // });
        
        System.out.println("❌ Can't use break in forEach lambda");
        System.out.println("✅ Solution 1: Use traditional for loop");
        
        for (String item : items) {
            if (item.equals("C")) {
                break;
            }
            System.out.println("  " + item);
        }
        
        System.out.println("✅ Solution 2: Use stream().takeWhile()");
        items.stream()
            .takeWhile(item -> !item.equals("C"))
            .forEach(item -> System.out.println("  " + item));
    }
    
    /**
     * Example 11: COMMON MISTAKE - Modifying collection during iteration
     */
    public static void demonstrateForEachMistake2() {
        System.out.println("\n=== COMMON MISTAKE 2: Modifying Collection During Iteration ===");
        
        List<String> items = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        
        System.out.println("❌ This causes ConcurrentModificationException:");
        try {
            items.forEach(item -> {
                if (item.equals("B")) {
                    items.remove(item);  // ❌ Don't do this!
                }
            });
        } catch (Exception e) {
            System.out.println("   ERROR: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        
        System.out.println("✅ Solution 1: Create new list");
        List<String> items1 = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        List<String> filtered = new ArrayList<>();
        items1.forEach(item -> {
            if (!item.equals("B")) {
                filtered.add(item);
            }
        });
        System.out.println("   Result: " + filtered);
        
        System.out.println("✅ Solution 2: Use Iterator");
        List<String> items2 = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        Iterator<String> it = items2.iterator();
        while (it.hasNext()) {
            String item = it.next();
            if (item.equals("B")) {
                it.remove();  // Safe removal
            }
        }
        System.out.println("   Result: " + items2);
    }
    
    /**
     * Example 12: COMMON MISTAKE - forEach() doesn't provide index
     */
    public static void demonstrateForEachMistake3() {
        System.out.println("\n=== COMMON MISTAKE 3: No Index in forEach() ===");
        
        List<String> items = Arrays.asList("Apple", "Banana", "Cherry", "Date");
        
        System.out.println("❌ forEach() gives no index:");
        System.out.println("   items.forEach(item -> System.out.println(index + \": \" + item));");
        System.out.println("   // ERROR: index is not defined!");
        
        System.out.println("\n✅ Solution 1: Use traditional for loop");
        for (int i = 0; i < items.size(); i++) {
            System.out.println("   " + i + ": " + items.get(i));
        }
        
        System.out.println("✅ Solution 2: Use IntStream.range()");
        java.util.stream.IntStream.range(0, items.size())
            .forEach(i -> System.out.println("   " + i + ": " + items.get(i)));
    }
    
    /**
     * Example 13: forEach() with variable scope
     */
    public static void demonstrateForEachVariableScope() {
        System.out.println("\n=== forEach() and Variable Scope ===");
        
        List<String> words = Arrays.asList("Hello", "World", "Java");
        int[] count = {0};  // Use array to modify in lambda
        
        System.out.println("Count words and their lengths:");
        words.forEach(word -> {
            count[0]++;
            System.out.println("  Word " + count[0] + ": " + word + " (length: " + word.length() + ")");
        });
    }
    
    /**
     * Example 14: Real-world scenario - Processing User Data
     */
    public static void demonstrateRealWorldExample() {
        System.out.println("\n=== REAL-WORLD EXAMPLE: Processing User Data ===");
        
        List<User> users = Arrays.asList(
            new User("alice@example.com", "Alice", true),
            new User("bob@example.com", "Bob", false),
            new User("charlie@example.com", "Charlie", true),
            new User("diana@example.com", "Diana", true)
        );
        
        System.out.println("Send email to active users:");
        users.stream()
            .filter(User::isActive)
            .forEach(user -> {
                System.out.println("  Sending welcome email to: " + user.getEmail());
                System.out.println("    Subject: Welcome, " + user.getName() + "!");
                // emailService.send(user.getEmail(), "Welcome!");
            });
    }
    
    /**
     * Example 15: forEach() Performance Comparison
     */
    public static void demonstratePerformance() {
        System.out.println("\n=== Performance Comparison ===");
        
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            numbers.add(i);
        }
        
        // Traditional for loop
        long start1 = System.nanoTime();
        int sum1 = 0;
        for (int num : numbers) {
            sum1 += num;
        }
        long time1 = System.nanoTime() - start1;
        
        // forEach method
        long start2 = System.nanoTime();
        int[] sum2 = {0};
        numbers.forEach(num -> sum2[0] += num);
        long time2 = System.nanoTime() - start2;
        
        System.out.println("Traditional for loop: " + (time1 / 1000000) + " ms");
        System.out.println("forEach() method: " + (time2 / 1000000) + " ms");
        System.out.println("(Times are similar - choose based on readability)");
    }
    
    // ===== HELPER CLASSES =====
    
    static class Person {
        private String name;
        private int age;
        private String role;
        
        Person(String name, int age, String role) {
            this.name = name;
            this.age = age;
            this.role = role;
        }
        
        String getName() { return name; }
        int getAge() { return age; }
        String getRole() { return role; }
        
        void promote() {
            role = role + " (Senior)";
        }
    }
    
    static class Product {
        private String name;
        private double price;
        
        Product(String name, double price) {
            this.name = name;
            this.price = price;
        }
        
        String getName() { return name; }
        double getPrice() { return price; }
    }
    
    static class User {
        private String email;
        private String name;
        private boolean active;
        
        User(String email, String name, boolean active) {
            this.email = email;
            this.name = name;
            this.active = active;
        }
        
        String getEmail() { return email; }
        String getName() { return name; }
        boolean isActive() { return active; }
    }
    
    // ===== MAIN METHOD =====
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  forEach() METHOD - COMPREHENSIVE DEMO  ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        // Basic Examples
        demonstrateBasicForEach();
        demonstrateMethodReferences();
        demonstrateForEachOnDifferentCollections();
        
        // Advanced Examples
        demonstrateForEachWithComplexOperations();
        demonstrateForEachWithMultipleStatements();
        demonstrateForEachWithConditionals();
        
        // Different Contexts
        demonstrateForEachWithMaps();
        demonstrateForEachWithStreams();
        demonstrateForEachWithExceptionHandling();
        
        // Common Mistakes
        demonstrateForEachMistake1();
        demonstrateForEachMistake2();
        demonstrateForEachMistake3();
        
        // Advanced Topics
        demonstrateForEachVariableScope();
        demonstratePerformance();
        
        // Real-World Example
        demonstrateRealWorldExample();
    }
}
