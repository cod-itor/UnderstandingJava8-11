import java.util.*;
import java.util.stream.*;

/**
 * Optional Class - Practical Examples
 * Demonstrates how to use Optional safely and effectively
 */
public class OptionalExamples {

    // ===== CREATING OPTIONAL =====
    
    /**
     * Example 1: Creating Optional objects
     * Three ways to create Optionals
     */
    public static void demonstrateCreatingOptional() {
        System.out.println("\n=== Creating Optional ===");
        
        // Method 1: Optional.of() - for non-null values only
        Optional<String> name = Optional.of("Alice");
        System.out.println("Optional.of(\"Alice\"): " + name);
        
        // Method 2: Optional.empty() - represents no value
        Optional<String> empty = Optional.empty();
        System.out.println("Optional.empty(): " + empty);
        
        // Method 3: Optional.ofNullable() - safe for potentially null
        String possiblyNull = null;
        Optional<String> safe = Optional.ofNullable(possiblyNull);
        System.out.println("Optional.ofNullable(null): " + safe);
        
        String notNull = "Bob";
        Optional<String> safe2 = Optional.ofNullable(notNull);
        System.out.println("Optional.ofNullable(\"Bob\"): " + safe2);
    }
    
    /**
     * Example 2: isPresent() vs isEmpty()
     */
    public static void demonstrateCheckingPresence() {
        System.out.println("\n=== Checking if Optional is Present ===");
        
        Optional<String> value = Optional.of("Hello");
        Optional<String> empty = Optional.empty();
        
        // Traditional approach: isPresent()
        System.out.println("value.isPresent(): " + value.isPresent());
        System.out.println("empty.isPresent(): " + empty.isPresent());
        
        // Modern approach: isEmpty() (Java 11+)
        System.out.println("value.isEmpty(): " + value.isEmpty());
        System.out.println("empty.isEmpty(): " + empty.isEmpty());
    }
    
    /**
     * Example 3: Getting values - different approaches
     */
    public static void demonstrateGettingValues() {
        System.out.println("\n=== Getting Values from Optional ===");
        
        Optional<String> present = Optional.of("Present");
        Optional<String> empty = Optional.empty();
        
        // ✅ ifPresent() - Recommended when you want to act on value
        System.out.println("Using ifPresent():");
        present.ifPresent(val -> System.out.println("  Value: " + val));
        empty.ifPresent(val -> System.out.println("  Value: " + val));  // Nothing printed
        
        // ✅ orElse() - Get value or default
        System.out.println("Using orElse():");
        System.out.println("  present.orElse(\"Default\"): " + present.orElse("Default"));
        System.out.println("  empty.orElse(\"Default\"): " + empty.orElse("Default"));
        
        // ✅ orElseGet() - Compute default if needed (lazy evaluation)
        System.out.println("Using orElseGet():");
        String result = empty.orElseGet(() -> {
            System.out.println("    Computing default...");
            return "Computed Default";
        });
        System.out.println("  Result: " + result);
        
        // ✅ get() - Direct access (throws if empty) - use with caution
        System.out.println("Using get():");
        try {
            System.out.println("  present.get(): " + present.get());
        } catch (NoSuchElementException e) {
            System.out.println("  ERROR: " + e.getMessage());
        }
        
        try {
            System.out.println("  empty.get(): " + empty.get());
        } catch (NoSuchElementException e) {
            System.out.println("  ERROR getting from empty Optional");
        }
    }
    
    /**
     * Example 4: map() - Transform values
     */
    public static void demonstrateMap() {
        System.out.println("\n=== Using map() to Transform ===");
        
        Optional<String> name = Optional.of("alice");
        
        // Transform to uppercase
        Optional<String> upperName = name.map(String::toUpperCase);
        System.out.println("name.map(String::toUpperCase): " + upperName);
        
        // Transform to length
        Optional<Integer> length = name.map(String::length);
        System.out.println("name.map(String::length): " + length);
        
        // Chained transformations
        Optional<Integer> result = name
            .map(String::toUpperCase)          // "ALICE"
            .map(String::length)               // 5
            .map(len -> len * 2);              // 10
        System.out.println("Chained transformations: " + result);
        
        // map() with empty Optional (nothing happens)
        Optional<String> empty = Optional.empty();
        Optional<String> result2 = empty.map(String::toUpperCase);
        System.out.println("empty.map(String::toUpperCase): " + result2);
    }
    
    /**
     * Example 5: filter() - Keep only matching values
     */
    public static void demonstrateFilter() {
        System.out.println("\n=== Using filter() to Conditionally Keep Values ===");
        
        Optional<String> email = Optional.of("user@gmail.com");
        Optional<String> empty = Optional.empty();
        
        // Filter: keep only if contains "@"
        Optional<String> validEmail = email.filter(e -> e.contains("@"));
        System.out.println("email.filter(e -> e.contains(\"@\")): " + validEmail);
        
        // Filter: keep only if ends with gmail.com
        Optional<String> gmailOnly = email.filter(e -> e.endsWith("gmail.com"));
        System.out.println("email.filter(e -> e.endsWith(\"gmail.com\")): " + gmailOnly);
        
        // Filter that removes the value
        Optional<String> notGmail = email.filter(e -> e.endsWith("yahoo.com"));
        System.out.println("email.filter(e -> e.endsWith(\"yahoo.com\")): " + notGmail);  // empty
        
        // Filter on empty (returns empty)
        Optional<String> result = empty.filter(e -> e.contains("@"));
        System.out.println("empty.filter(...): " + result);
    }
    
    /**
     * Example 6: flatMap() - Handle Optional-returning functions
     */
    public static void demonstrateFlatMap() {
        System.out.println("\n=== Using flatMap() for Optional-Returning Functions ===");
        
        Optional<User> user = Optional.of(new User("Alice", "alice@example.com", Optional.of("NYC")));
        Optional<User> userNoCity = Optional.of(new User("Bob", "bob@example.com", Optional.empty()));
        Optional<User> noUser = Optional.empty();
        
        // Using map() (wrong for Optional-returning methods)
        System.out.println("❌ Using map() with Optional-returning method:");
        Optional<Optional<String>> wrongCity = user.map(User::getCity);
        System.out.println("  Result type: Optional<Optional<String>>");
        System.out.println("  Value: " + wrongCity);
        
        // Using flatMap() (correct)
        System.out.println("✅ Using flatMap() with Optional-returning method:");
        Optional<String> city = user.flatMap(User::getCity);
        System.out.println("  Result type: Optional<String>");
        System.out.println("  user city: " + city);
        System.out.println("  userNoCity city: " + userNoCity.flatMap(User::getCity));
        System.out.println("  noUser city: " + noUser.flatMap(User::getCity));
    }
    
    /**
     * Example 7: Chaining Operations - Safe Navigation
     */
    public static void demonstrateChaining() {
        System.out.println("\n=== Chaining Optional Operations ===");
        
        Optional<Person> person = Optional.of(
            new Person("Charlie", new Address("123 Main St", "Boston"))
        );
        Optional<Person> personNoAddress = Optional.of(
            new Person("Diana", null)
        );
        Optional<Person> noPerson = Optional.empty();
        
        System.out.println("Complex object navigation:");
        
        // Get city in uppercase
        Optional<String> cityUpper = person
            .map(Person::getAddress)
            .map(addr -> addr.getCity().toUpperCase());
        System.out.println("  person city (upper): " + cityUpper);
        
        Optional<String> cityUpper2 = personNoAddress
            .map(Person::getAddress)
            .map(addr -> addr.getCity().toUpperCase());
        System.out.println("  personNoAddress city: " + cityUpper2);  // empty
        
        Optional<String> cityUpper3 = noPerson
            .map(Person::getAddress)
            .map(addr -> addr.getCity().toUpperCase());
        System.out.println("  noPerson city: " + cityUpper3);  // empty
    }
    
    /**
     * Example 8: ifPresentOrElse() - Handle both cases
     */
    public static void demonstrateIfPresentOrElse() {
        System.out.println("\n=== Using ifPresentOrElse() ===");
        
        Optional<String> present = Optional.of("Success");
        Optional<String> empty = Optional.empty();
        
        System.out.println("When value is present:");
        present.ifPresentOrElse(
            val -> System.out.println("  ✓ Value found: " + val),
            () -> System.out.println("  ✗ No value")
        );
        
        System.out.println("When value is empty:");
        empty.ifPresentOrElse(
            val -> System.out.println("  ✓ Value found: " + val),
            () -> System.out.println("  ✗ No value")
        );
    }
    
    /**
     * Example 9: orElseThrow() - Fail fast on error
     */
    public static void demonstrateOrElseThrow() {
        System.out.println("\n=== Using orElseThrow() ===");
        
        Optional<String> present = Optional.of("Valid");
        Optional<String> empty = Optional.empty();
        
        System.out.println("With present value:");
        try {
            String value = present.orElseThrow(() -> 
                new IllegalArgumentException("Value must be present"));
            System.out.println("  Got value: " + value);
        } catch (IllegalArgumentException e) {
            System.out.println("  ERROR: " + e.getMessage());
        }
        
        System.out.println("With empty value:");
        try {
            String value = empty.orElseThrow(() -> 
                new IllegalArgumentException("Required value not found"));
            System.out.println("  Got value: " + value);
        } catch (IllegalArgumentException e) {
            System.out.println("  ERROR: " + e.getMessage());
        }
    }
    
    /**
     * Example 10: or() - Provide alternative Optional (Java 9+)
     */
    public static void demonstrateOr() {
        System.out.println("\n=== Using or() ===");
        
        Optional<String> primary = Optional.empty();
        Optional<String> secondary = Optional.of("Fallback");
        
        Optional<String> result = primary.or(() -> secondary);
        System.out.println("Empty primary, fallback to secondary: " + result);
        
        result = Optional.of("Primary").or(() -> secondary);
        System.out.println("Primary exists, no fallback needed: " + result);
    }
    
    /**
     * Example 11: stream() - Convert to Stream (Java 9+)
     */
    public static void demonstrateStream() {
        System.out.println("\n=== Using stream() ===");
        
        Optional<String> present = Optional.of("Item1");
        Optional<String> empty = Optional.empty();
        
        System.out.println("Stream from present Optional:");
        present.stream().forEach(item -> System.out.println("  - " + item));
        
        System.out.println("Stream from empty Optional:");
        empty.stream().forEach(item -> System.out.println("  - " + item));
        
        // Practical use: flatten Optional in stream
        List<Optional<String>> items = Arrays.asList(
            Optional.of("A"),
            Optional.empty(),
            Optional.of("B"),
            Optional.of("C")
        );
        
        System.out.println("Flatten Optionals in stream:");
        items.stream()
            .flatMap(Optional::stream)
            .forEach(item -> System.out.println("  - " + item));
    }
    
    /**
     * Example 12: Real-world use case - Database lookup
     */
    public static void demonstrateDatabaseLookup() {
        System.out.println("\n=== Real-World: Database Lookup ===");
        
        UserRepository repo = new UserRepository();
        
        // Lookup user and get email
        String email = repo.findUserById(1)
            .map(User::getEmail)
            .orElse("user@default.com");
        System.out.println("User 1 email: " + email);
        
        String email2 = repo.findUserById(999)
            .map(User::getEmail)
            .orElse("user@default.com");
        System.out.println("User 999 email: " + email2);
        
        // Conditional action
        System.out.println("Sending welcome email:");
        repo.findUserById(1)
            .filter(u -> u.isActive())
            .ifPresentOrElse(
                u -> System.out.println("  Sending email to: " + u.getEmail()),
                () -> System.out.println("  User not found or inactive")
            );
    }
    
    /**
     * Example 13: Real-world use case - Configuration with defaults
     */
    public static void demonstrateConfiguration() {
        System.out.println("\n=== Real-World: Configuration ===");
        
        ConfigService config = new ConfigService();
        
        // Get timeout with default
        int timeout = config.getIntProperty("app.timeout")
            .orElse(30);
        System.out.println("Timeout setting: " + timeout + "s");
        
        // Get URL with validation
        String apiUrl = config.getStringProperty("api.url")
            .filter(url -> url.startsWith("https://"))
            .orElseThrow(() -> new IllegalStateException("Invalid API URL"));
        System.out.println("API URL: " + apiUrl);
    }
    
    /**
     * Example 14: Common Mistake 1 - Using get() unsafely
     */
    public static void demonstrateMistake1() {
        System.out.println("\n=== COMMON MISTAKE 1: Unsafe get() ===");
        
        Optional<String> value = Optional.empty();
        
        System.out.println("❌ This will throw NoSuchElementException:");
        try {
            String result = value.get();  // ❌ Don't do this
            System.out.println("  Result: " + result);
        } catch (NoSuchElementException e) {
            System.out.println("  ERROR: NoSuchElementException thrown!");
            System.out.println("  'get()' is unsafe for empty Optional");
        }
        
        System.out.println("✅ Safe alternatives:");
        System.out.println("  value.orElse(\"default\"): " + value.orElse("default"));
        System.out.println("  value.orElseThrow(): throws only if needed");
        value.ifPresent(v -> System.out.println("  value.ifPresent(): " + v));
    }
    
    /**
     * Example 15: Common Mistake 2 - map() vs flatMap()
     */
    public static void demonstrateMistake2() {
        System.out.println("\n=== COMMON MISTAKE 2: map() vs flatMap() ===");
        
        Optional<User> user = Optional.of(new User("Alice", "alice@example.com", Optional.of("NYC")));
        
        System.out.println("❌ Using map() with Optional-returning method:");
        Optional<Optional<String>> wrong = user.map(u -> u.getCity());
        System.out.println("  Result: Optional<Optional<String>>" + wrong);
        
        System.out.println("✅ Using flatMap() with Optional-returning method:");
        Optional<String> correct = user.flatMap(u -> u.getCity());
        System.out.println("  Result: Optional<String>" + correct);
    }
    
    /**
     * Example 16: Common Mistake 3 - Optional as parameter
     */
    public static void demonstrateMistake3() {
        System.out.println("\n=== COMMON MISTAKE 3: Optional as Parameter ===");
        
        System.out.println("❌ Avoid Optional as method parameter:");
        System.out.println("  public void process(Optional<String> value) { ... }");
        System.out.println("  Callers need to wrap arguments unnecessarily");
        
        System.out.println("✅ Better approaches:");
        System.out.println("  1. public void process(String value) { ... }");
        System.out.println("  2. Separate methods for optional handling");
        System.out.println("  3. Use overloading");
    }
    
    /**
     * Example 17: Optional with Collections
     */
    public static void demonstrateOptionalWithCollections() {
        System.out.println("\n=== Optional with Collections ===");
        
        List<String> items = Arrays.asList("apple", "banana", "cherry");
        
        System.out.println("Finding first item starting with 'b':");
        Optional<String> found = items.stream()
            .filter(item -> item.startsWith("b"))
            .findFirst();
        System.out.println("  Found: " + found.orElse("not found"));
        
        System.out.println("Finding any item starting with 'x':");
        Optional<String> notFound = items.stream()
            .filter(item -> item.startsWith("x"))
            .findAny();
        System.out.println("  Found: " + notFound.orElse("not found"));
    }
    
    // ===== HELPER CLASSES =====
    
    static class User {
        private String name;
        private String email;
        private Optional<String> city;
        private boolean active = true;
        
        User(String name, String email, Optional<String> city) {
            this.name = name;
            this.email = email;
            this.city = city;
        }
        
        String getName() { return name; }
        String getEmail() { return email; }
        Optional<String> getCity() { return city; }
        boolean isActive() { return active; }
    }
    
    static class Person {
        private String name;
        private Address address;
        
        Person(String name, Address address) {
            this.name = name;
            this.address = address;
        }
        
        String getName() { return name; }
        Address getAddress() { return address; }
    }
    
    static class Address {
        private String street;
        private String city;
        
        Address(String street, String city) {
            this.street = street;
            this.city = city;
        }
        
        String getStreet() { return street; }
        String getCity() { return city; }
    }
    
    static class UserRepository {
        private Map<Integer, User> database = new HashMap<>();
        
        UserRepository() {
            database.put(1, new User("Alice", "alice@example.com", Optional.of("NYC")));
            database.put(2, new User("Bob", "bob@example.com", Optional.empty()));
        }
        
        Optional<User> findUserById(int id) {
            return Optional.ofNullable(database.get(id));
        }
    }
    
    static class ConfigService {
        private Map<String, String> config = new HashMap<>();
        
        ConfigService() {
            config.put("app.timeout", "60");
            config.put("api.url", "https://api.example.com");
        }
        
        Optional<String> getStringProperty(String key) {
            return Optional.ofNullable(config.get(key));
        }
        
        Optional<Integer> getIntProperty(String key) {
            return Optional.ofNullable(config.get(key))
                .flatMap(value -> {
                    try {
                        return Optional.of(Integer.parseInt(value));
                    } catch (NumberFormatException e) {
                        return Optional.empty();
                    }
                });
        }
    }
    
    // ===== MAIN METHOD =====
    
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║  OPTIONAL CLASS - COMPREHENSIVE DEMO      ║");
        System.out.println("╚══════════════════════════════════════════╝");
        
        // Creating and Checking
        demonstrateCreatingOptional();
        demonstrateCheckingPresence();
        demonstrateGettingValues();
        
        // Transforming
        demonstrateMap();
        demonstrateFilter();
        demonstrateFlatMap();
        
        // Advanced Operations
        demonstrateChaining();
        demonstrateIfPresentOrElse();
        demonstrateOrElseThrow();
        demonstrateOr();
        demonstrateStream();
        
        // Real-World Examples
        demonstrateDatabaseLookup();
        demonstrateConfiguration();
        demonstrateOptionalWithCollections();
        
        // Common Mistakes
        demonstrateMistake1();
        demonstrateMistake2();
        demonstrateMistake3();
    }
}
