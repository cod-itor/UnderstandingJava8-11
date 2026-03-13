import java.util.*;
import java.util.stream.*;

/**
 * Stream API - Practical Examples
 * Demonstrates all creation methods and common operations
 */
public class StreamAPIExamples {

    // ===== STREAM CREATION METHODS =====
    
    /**
     * Method 1: Stream.empty()
     * Creates an empty stream that contains no elements
     * Use case: When a method needs to return an empty stream instead of null
     */
    public static void demonstrateEmptyStream() {
        System.out.println("\n=== Stream.empty() ===");
        
        Stream<String> emptyStream = Stream.empty();
        
        // Count the empty stream (will be 0)
        long count = emptyStream.count();
        System.out.println("Empty stream count: " + count);
        
        // Real-world example: Safe return for optional results
        Stream<String> result = findUsersByRole("ADMIN").orElseGet(Stream::empty);
    }
    
    /**
     * Method 2: collection.stream()
     * Converts a collection (List, Set, etc.) into a stream
     * Use case: Most common method for working with existing collections
     */
    public static void demonstrateCollectionStream() {
        System.out.println("\n=== collection.stream() ===");
        
        // From a List
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.stream()
            .forEach(n -> System.out.println("Number: " + n));
        
        // From a Set
        Set<String> uniqueFruits = new HashSet<>(Arrays.asList("Apple", "Banana", "Orange"));
        uniqueFruits.stream()
            .sorted()
            .forEach(fruit -> System.out.println("Fruit: " + fruit));
    }
    
    /**
     * Method 3: Stream.of(Array[])
     * Creates a stream directly from an array
     * Use case: When you have an array that needs stream operations
     */
    public static void demonstrateStreamOf() {
        System.out.println("\n=== Stream.of(Array) ===");
        
        String[] colors = {"Red", "Green", "Blue"};
        
        Stream.of(colors)
            .filter(color -> !color.equals("Green"))
            .forEach(color -> System.out.println("Color: " + color));
        
        // You can also use Stream.of with individual elements
        Stream.of("Cat", "Dog", "Bird")
            .forEach(animal -> System.out.println("Animal: " + animal));
    }
    
    /**
     * Method 4: Stream.generate() and Stream.iterate()
     * Creates infinite streams (must use limit)
     */
    public static void demonstrateGeneratedStreams() {
        System.out.println("\n=== Stream.generate() and Stream.iterate() ===");
        
        // Generate: Creates infinite stream from supplier
        System.out.println("First 5 random numbers:");
        Stream.generate(Math::random)
            .limit(5)
            .forEach(n -> System.out.println("Random: " + String.format("%.2f", n)));
        
        // Iterate: Creates infinite stream with iteration function
        System.out.println("Counting from 1 to 5:");
        Stream.iterate(1, n -> n + 1)
            .limit(5)
            .forEach(n -> System.out.println("Count: " + n));
    }
    
    // ===== COMMON STREAM OPERATIONS =====
    
    /**
     * INTERMEDIATE OPERATIONS: filter(), map(), flatMap()
     * These are LAZY - they don't execute until a terminal operation is called
     */
    public static void demonstrateIntermediateOperations() {
        System.out.println("\n=== INTERMEDIATE OPERATIONS (Lazy) ===");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // 1. filter() - Keep only elements matching a condition
        System.out.println("Filter - Even numbers:");
        numbers.stream()
            .filter(n -> n % 2 == 0)
            .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // 2. map() - Transform each element
        System.out.println("Map - Square each number:");
        numbers.stream()
            .map(n -> n * n)
            .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // 3. flatMap() - Map and flatten (used when mapping returns streams)
        System.out.println("FlatMap - Explode lists:");
        List<List<Integer>> lists = Arrays.asList(
            Arrays.asList(1, 2),
            Arrays.asList(3, 4),
            Arrays.asList(5, 6)
        );
        lists.stream()
            .flatMap(List::stream)
            .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // 4. distinct() - Remove duplicates
        System.out.println("Distinct - Remove duplicates:");
        Arrays.asList(1, 2, 2, 3, 3, 3, 4).stream()
            .distinct()
            .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // 5. sorted() - Sort elements
        System.out.println("Sorted - Sort in reverse:");
        numbers.stream()
            .sorted(Collections.reverseOrder())
            .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // 6. limit() and skip()
        System.out.println("Limit and Skip - Get items 3-5:");
        numbers.stream()
            .skip(2)
            .limit(3)
            .forEach(n -> System.out.print(n + " "));
        System.out.println();
    }
    
    /**
     * TERMINAL OPERATIONS: collect(), forEach(), reduce()
     * These are EAGER - they trigger immediate execution
     */
    public static void demonstrateTerminalOperations() {
        System.out.println("\n=== TERMINAL OPERATIONS (Eager) ===");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        
        // 1. forEach() - Process each element
        System.out.println("forEach - Print all:");
        numbers.stream().forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // 2. collect() - Gather results into a collection
        System.out.println("collect - Create new list:");
        List<Integer> squared = numbers.stream()
            .map(n -> n * n)
            .collect(Collectors.toList());
        System.out.println("Squared: " + squared);
        
        // 3. reduce() - Combine all elements into one
        System.out.println("reduce - Sum all elements:");
        int sum = numbers.stream()
            .reduce(0, Integer::sum);
        System.out.println("Sum: " + sum);
        
        // 4. count() - Count elements
        System.out.println("count - Count elements:");
        long count = numbers.stream()
            .filter(n -> n > 2)
            .count();
        System.out.println("Elements > 2: " + count);
        
        // 5. findFirst() - Get first element
        System.out.println("findFirst - First element > 3:");
        Optional<Integer> first = numbers.stream()
            .filter(n -> n > 3)
            .findFirst();
        first.ifPresent(n -> System.out.println("Found: " + n));
        
        // 6. anyMatch(), allMatch(), noneMatch() - Check conditions
        System.out.println("anyMatch - Any even number?");
        boolean hasEven = numbers.stream().anyMatch(n -> n % 2 == 0);
        System.out.println("Has even: " + hasEven);
    }

    /**
     * Quick "If you write this... you get this" (runnable outcomes)
     */
    public static void demonstrateIfYouWriteThis() {
        System.out.println("\n=== If you write this... you get this (streams) ===");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> evens = numbers.stream()
            .filter(n -> n % 2 == 0)
            .toList();
        System.out.println("list.stream().filter(n -> n % 2 == 0).toList() => " + evens + " | original: " + numbers);

        List<String> names = Arrays.asList("ana", "bob");
        List<String> upper = names.stream()
            .map(String::toUpperCase)
            .toList();
        System.out.println("list.stream().map(String::toUpperCase).toList() => " + upper);

        List<Integer> unsorted = Arrays.asList(5, 1, 3, 2);
        System.out.print("list.stream().sorted().forEach(...) => ");
        unsorted.stream().sorted().forEach(n -> System.out.print(n + " "));
        System.out.println();

        List<OrderDto> orders = Arrays.asList(
            new OrderDto(Status.PAID, 10.5),
            new OrderDto(Status.PENDING, 5.0),
            new OrderDto(Status.PAID, 7.5)
        );
        double totalPaid = orders.stream()
            .filter(o -> o.status() == Status.PAID)
            .mapToDouble(OrderDto::amount)
            .sum();
        System.out.println("orders.stream().filter(PAID).mapToDouble(amount).sum() => " + totalPaid);

        List<String> emails = Arrays.asList("a@x.com", "b@y.com", "c@x.com");
        List<String> domains = emails.stream()
            .map(e -> e.split("@")[1])
            .distinct()
            .sorted()
            .toList();
        System.out.println("emails.stream().map(domain).distinct().sorted().toList() => " + domains);

        Optional<UserDto> userOpt = Optional.of(new UserDto("anna", true, "anna@acme.com"));
        List<String> collectedEmails = userOpt.stream()
            .filter(UserDto::isActive)
            .map(UserDto::getEmail)
            .toList();
        System.out.println("Optional<UserDto>.stream().filter(active).map(email).toList() => " + collectedEmails);

        List<String> messy = Arrays.asList(" one ", null, "two");
        List<String> cleaned = messy.stream()
            .filter(Objects::nonNull)
            .map(String::trim)
            .toList();
        System.out.println("list.stream().filter(nonNull).map(trim).toList() => " + cleaned);
    }
    
    /**
     * PRACTICAL EXAMPLE: Process a list of students
     */
    public static void demonstrateRealWorldExample() {
        System.out.println("\n=== REAL-WORLD EXAMPLE: Student Processing ===");
        
        List<Student> students = Arrays.asList(
            new Student("Alice", 95),
            new Student("Bob", 75),
            new Student("Charlie", 88),
            new Student("Diana", 92),
            new Student("Eve", 70)
        );
        
        // Get all students with grade >= 85 sorted by name
        System.out.println("High-performing students (grade >= 85):");
        students.stream()
            .filter(s -> s.getGrade() >= 85)
            .sorted(Comparator.comparing(Student::getName))
            .forEach(s -> System.out.println("  - " + s.getName() + ": " + s.getGrade()));
        
        // Calculate average grade
        double averageGrade = students.stream()
            .mapToInt(Student::getGrade)
            .average()
            .orElse(0);
        System.out.println("\nAverage Grade: " + String.format("%.1f", averageGrade));
        
        // Group students by performance level
        Map<String, List<Student>> grouped = students.stream()
            .collect(Collectors.groupingBy(s -> 
                s.getGrade() >= 90 ? "Excellent" : 
                s.getGrade() >= 80 ? "Good" : "Needs Improvement"
            ));
        System.out.println("\nGrouped by performance:");
        grouped.forEach((level, studs) -> 
            System.out.println("  " + level + ": " + studs.stream().map(Student::getName).collect(Collectors.joining(", ")))
        );
    }
    
    /**
     * LAZY EVALUATION EXAMPLE
     * Shows how intermediate operations are not executed until a terminal operation is called
     */
    public static void demonstrateLazyEvaluation() {
        System.out.println("\n=== LAZY EVALUATION ===");
        System.out.println("Notice how filter and map are NOT called until forEach is executed:");
        
        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5).stream()
            .filter(n -> {
                System.out.println("  [FILTER] Checking: " + n);
                return n > 2;
            })
            .map(n -> {
                System.out.println("  [MAP] Doubling: " + n);
                return n * 2;
            });
        
        System.out.println("Stream created but nothing happened yet!\n");
        System.out.println("Now calling forEach (terminal operation):");
        stream.forEach(n -> System.out.println("  [RESULT] " + n));
    }
    
    /**
     * PARALLEL STREAMS
     * Uses multiple threads for processing
     * Use only for CPU-intensive operations on large datasets
     */
    public static void demonstrateParallelStreams() {
        System.out.println("\n=== PARALLEL STREAMS ===");
        
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            numbers.add(i);
        }
        
        System.out.println("Sequential sum:");
        long sum1 = numbers.stream()
            .mapToLong(n -> {
                // Simulate work
                try { Thread.sleep(10); } catch (Exception e) {}
                return n;
            })
            .sum();
        System.out.println("Sum: " + sum1);
        
        System.out.println("\nParallel sum (faster for large datasets):");
        long sum2 = numbers.parallelStream()
            .mapToLong(n -> {
                // Simulate work
                try { Thread.sleep(10); } catch (Exception e) {}
                return n;
            })
            .sum();
        System.out.println("Sum: " + sum2);
    }
    
    /**
     * MISTAKE 1: Trying to reuse a stream
     * This will throw an IllegalStateException
     */
    public static void demonstrateStreamMistake1() {
        System.out.println("\n=== COMMON MISTAKE 1: Reusing a Stream ===");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        Stream<Integer> stream = numbers.stream();
        
        System.out.println("First usage:");
        stream.forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        System.out.println("Second usage - will throw exception:");
        try {
            stream.filter(n -> n > 1).forEach(System.out::println);
        } catch (IllegalStateException e) {
            System.out.println("ERROR: " + e.getMessage());
            System.out.println("SOLUTION: Create a new stream for each operation");
        }
    }
    
    /**
     * MISTAKE 2: Forgetting a terminal operation
     * The intermediate operations won't execute without it
     */
    public static void demonstrateStreamMistake2() {
        System.out.println("\n=== COMMON MISTAKE 2: Forgetting Terminal Operation ===");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        
        System.out.println("WITHOUT terminal operation (nothing happens):");
        numbers.stream()
            .filter(n -> n % 2 == 0);  // ❌ Nothing happens!
        System.out.println("(Nothing printed)");
        
        System.out.println("\nWITH terminal operation (now it works):");
        numbers.stream()
            .filter(n -> n % 2 == 0)
            .forEach(System.out::println);  // ✅ Now it works!
    }
    
    // ===== HELPER CLASSES =====
    
    static class Student {
        private String name;
        private int grade;
        
        Student(String name, int grade) {
            this.name = name;
            this.grade = grade;
        }
        
        String getName() { return name; }
        int getGrade() { return grade; }
    }

    static class OrderDto {
        private final Status status;
        private final double amount;

        OrderDto(Status status, double amount) {
            this.status = status;
            this.amount = amount;
        }

        Status status() { return status; }
        double amount() { return amount; }
    }

    enum Status { PAID, PENDING }

    static class UserDto {
        private final String name;
        private final boolean active;
        private final String email;

        UserDto(String name, boolean active, String email) {
            this.name = name;
            this.active = active;
            this.email = email;
        }

        boolean isActive() { return active; }
        String getEmail() { return email; }
        public String toString() { return name + "(" + email + ")"; }
    }
    
    static Optional<Stream<String>> findUsersByRole(String role) {
        if ("ADMIN".equals(role)) {
            return Optional.of(Stream.of("User1", "User2", "User3"));
        }
        return Optional.empty();
    }
    
    // ===== MAIN METHOD =====
    
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║      STREAM API - COMPREHENSIVE DEMO      ║");
        System.out.println("╚══════════════════════════════════════════╝");
        
        // Stream Creation Methods
        demonstrateEmptyStream();
        demonstrateCollectionStream();
        demonstrateStreamOf();
        demonstrateGeneratedStreams();
        
        // Stream Operations
        demonstrateIntermediateOperations();
        demonstrateTerminalOperations();
    demonstrateIfYouWriteThis();
        
        // Real-World Example
        demonstrateRealWorldExample();
        
        // Advanced Concepts
        demonstrateLazyEvaluation();
        demonstrateParallelStreams();
        
        // Common Mistakes
        demonstrateStreamMistake1();
        demonstrateStreamMistake2();
    }
}
