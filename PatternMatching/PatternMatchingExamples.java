/**
 * Pattern Matching - Practical Examples
 * Demonstrates instanceof patterns (Java 16+) and switch patterns (Java 17+)
 * 
 * Note: Switch patterns require Java 17 or later
 */
public class PatternMatchingExamples {

    // ===== INSTANCEOF PATTERNS (Java 16+) =====
    
    /**
     * Example 1: Traditional instanceof (before Java 14)
     */
    public static void demonstrateTraditionalInstanceof() {
        System.out.println("\n=== Traditional instanceof (Before Java 14) ===");
        
        Object obj = "Hello Pattern Matching";
        
        // ❌ Old verbose way
        if (obj instanceof String) {
            String str = (String) obj;  // Manual cast required
            System.out.println("String value: " + str);
            System.out.println("Length: " + str.length());
        }
        
        System.out.println("Issues with this approach:");
        System.out.println("  - Verbose and repetitive");
        System.out.println("  - Must remember to cast");
        System.out.println("  - Extra variable declaration");
        System.out.println("  - Error-prone");
    }
    
    /**
     * Example 2: Modern instanceof Pattern (Java 16+)
     */
    public static void demonstrateModernInstanceof() {
        System.out.println("\n=== Modern instanceof Pattern (Java 16+) ===");
        
        Object obj = "Hello Pattern Matching";
        
        // ✅ New concise way
        if (obj instanceof String str) {
            // str is automatically cast to String
            System.out.println("String value: " + str);
            System.out.println("Length: " + str.length());
            System.out.println("Uppercase: " + str.toUpperCase());
        }
        
        System.out.println("Benefits:");
        System.out.println("  - Concise and readable");
        System.out.println("  - Cast happens automatically");
        System.out.println("  - Variable scope limited to if block");
        System.out.println("  - Type-safe");
    }
    
    /**
     * Example 3: instanceof Pattern Scope
     */
    public static void demonstratePatternScope() {
        System.out.println("\n=== Pattern Variable Scope ===");
        
        Object obj = "Test";
        
        // Variable 'str' is only accessible within the if block
        if (obj instanceof String str) {
            System.out.println("Inside if: " + str);
            System.out.println("  (str is accessible here)");
        }
        
        // ❌ str is NOT accessible here
        // System.out.println(str);  // Compile error!
        System.out.println("Outside if: str is not accessible");
        
        // This prevents accidentally using wrong type
    }
    
    /**
     * Example 4: Checking Multiple Types
     */
    public static void demonstrateMultipleTypes() {
        System.out.println("\n=== Checking Multiple Types ===");
        
        Object[] values = {
            "String",
            42,
            3.14,
            true,
            new StringBuilder("Builder")
        };
        
        for (Object obj : values) {
            String type = identifyType(obj);
            System.out.println(type);
        }
    }
    
    private static String identifyType(Object obj) {
        if (obj instanceof String s) {
            return "String: " + s + " (length: " + s.length() + ")";
        } else if (obj instanceof Integer i) {
            return "Integer: " + i + " (squared: " + (i * i) + ")";
        } else if (obj instanceof Double d) {
            return "Double: " + d + " (as int: " + (int)d + ")";
        } else if (obj instanceof Boolean b) {
            return "Boolean: " + b + " (negated: " + !b + ")";
        } else if (obj instanceof StringBuilder sb) {
            return "StringBuilder: " + sb.toString() + " (length: " + sb.length() + ")";
        } else {
            return "Unknown type: " + obj.getClass().getSimpleName();
        }
    }
    
    /**
     * Example 5: instanceof with Conditions (Guard)
     */
    public static void demonstrateGuardCondition() {
        System.out.println("\n=== instanceof with Conditions (Guard) ===");
        
        Object[] items = {"A", "Apple", "AB", "Banana", "C", 123};
        
        System.out.println("Strings with length > 3:");
        for (Object item : items) {
            // ✅ Combine instanceof pattern with additional condition
            if (item instanceof String s && s.length() > 3) {
                System.out.println("  - " + s);
            }
        }
    }
    
    /**
     * Example 6: instanceof Pattern in Ternary
     */
    public static void demonstratePatternInTernary() {
        System.out.println("\n=== Pattern in Ternary Operator ===");
        
        Object obj = "Pattern Matching";
        
        // ✅ Can use pattern in ternary
        String result = obj instanceof String s 
            ? "String with " + s.length() + " chars" 
            : "Not a string";
        System.out.println("Result: " + result);
    }
    
    /**
     * Example 7: Polymorphic Collection Processing
     */
    public static void demonstratePolymorphicCollections() {
        System.out.println("\n=== Processing Polymorphic Collections ===");
        
        java.util.List<Object> shapes = java.util.Arrays.asList(
            new Circle(5),
            new Rectangle(4, 6),
            new Triangle(3, 8),
            new Circle(3),
            new Rectangle(2, 2)
        );
        
        System.out.println("Calculate areas:");
        for (Object shape : shapes) {
            if (shape instanceof Circle c) {
                double area = Math.PI * c.radius * c.radius;
                System.out.println("  Circle: area = " + String.format("%.2f", area));
            } else if (shape instanceof Rectangle r) {
                double area = r.width * r.height;
                System.out.println("  Rectangle: area = " + String.format("%.2f", area));
            } else if (shape instanceof Triangle t) {
                double area = t.base * t.height / 2;
                System.out.println("  Triangle: area = " + String.format("%.2f", area));
            }
        }
    }
    
    /**
     * Example 8: Real-world - API Response Handling
     */
    public static void demonstrateApiResponseHandling() {
        System.out.println("\n=== Real-World: API Response Handling ===");
        
        java.util.List<Object> responses = java.util.Arrays.asList(
            new SuccessResponse("User data loaded", new java.util.HashMap<>()),
            new ErrorResponse("404", "Not Found"),
            new WarningResponse("Data may be stale"),
            new SuccessResponse("Settings saved", new java.util.HashMap<>())
        );
        
        System.out.println("Processing API responses:");
        for (Object response : responses) {
            handleResponse(response);
        }
    }
    
    private static void handleResponse(Object response) {
        if (response instanceof SuccessResponse sr) {
            System.out.println("  ✓ Success: " + sr.message);
            System.out.println("    Data: " + sr.data.size() + " fields");
        } else if (response instanceof ErrorResponse er) {
            System.out.println("  ✗ Error [" + er.code + "]: " + er.message);
        } else if (response instanceof WarningResponse wr) {
            System.out.println("  ⚠ Warning: " + wr.message);
        }
    }
    
    /**
     * Example 9: Type Casting Safety
     */
    public static void demonstrateTypeSafety() {
        System.out.println("\n=== Type Safety ===");
        
        Object obj1 = "String value";
        Object obj2 = 123;
        
        // ✅ Safe - will correctly identify type
        if (obj1 instanceof String s) {
            System.out.println("obj1 is String: " + s);
        } else {
            System.out.println("obj1 is not String");
        }
        
        // ✅ Safe - will correctly identify type
        if (obj2 instanceof Integer i) {
            System.out.println("obj2 is Integer: " + i);
        } else {
            System.out.println("obj2 is not Integer");
        }
        
        System.out.println("Pattern matching prevents unsafe casts!");
    }
    
    /**
     * Example 10: Common Mistake - Wrong Order
     */
    public static void demonstrateMistake1() {
        System.out.println("\n=== COMMON MISTAKE: Order Matters ===");
        
        Object obj = "Test";
        
        System.out.println("❌ Wrong - guard condition before pattern:");
        System.out.println("   if (obj.length() > 0 && obj instanceof String s) { }");
        System.out.println("   // ERROR: obj not yet bound to s");
        
        System.out.println("\n✅ Correct - pattern first, then condition:");
        if (obj instanceof String s && s.length() > 0) {
            System.out.println("   Condition: " + s + " (length > 0)");
        }
    }
    
    /**
     * Example 11: Inheritance and instanceof
     */
    public static void demonstrateInheritance() {
        System.out.println("\n=== Pattern Matching with Inheritance ===");
        
        java.util.List<Animal> animals = java.util.Arrays.asList(
            new Dog("Buddy", "German Shepherd"),
            new Cat("Whiskers", "Tabby"),
            new Dog("Max", "Golden Retriever"),
            new Cat("Mittens", "Siamese")
        );
        
        System.out.println("Processing animals:");
        for (Animal animal : animals) {
            if (animal instanceof Dog d) {
                System.out.println("  Dog: " + d.name + " (" + d.breed + ")");
            } else if (animal instanceof Cat c) {
                System.out.println("  Cat: " + c.name + " (" + c.color + ")");
            }
        }
    }
    
    /**
     * Example 12: Switch with Type Patterns (Java 17+)
     * Note: This requires Java 17+
     */
    public static void demonstrateSwitchPatterns() {
        System.out.println("\n=== Switch with Type Patterns (Java 17+) ===");
        
        Object[] values = {"String", 42, 3.14, true, null};
        
        System.out.println("Processing values with switch patterns:");
        for (Object obj : values) {
            // This requires Java 17+
            String result = describeObject(obj);
            System.out.println("  " + result);
        }
    }
    
    private static String describeObject(Object obj) {
        // Java 17+ switch patterns
        try {
            return switch (obj) {
                case String s -> "String: \"" + s + "\" (length: " + s.length() + ")";
                case Integer i -> "Integer: " + i + " (even: " + (i % 2 == 0) + ")";
                case Double d -> "Double: " + d + " (positive: " + (d > 0) + ")";
                case Boolean b -> "Boolean: " + b;
                case null -> "null value";
                default -> "Unknown: " + obj.getClass().getSimpleName();
            };
        } catch (Exception e) {
            // Fallback for Java < 17
            return fallbackDescribe(obj);
        }
    }
    
    private static String fallbackDescribe(Object obj) {
        if (obj instanceof String s) {
            return "String: \"" + s + "\" (length: " + s.length() + ")";
        } else if (obj instanceof Integer i) {
            return "Integer: " + i + " (even: " + (i % 2 == 0) + ")";
        } else if (obj instanceof Double d) {
            return "Double: " + d + " (positive: " + (d > 0) + ")";
        } else if (obj instanceof Boolean b) {
            return "Boolean: " + b;
        } else if (obj == null) {
            return "null value";
        }
        return "Unknown: " + obj.getClass().getSimpleName();
    }
    
    /**
     * Example 13: Pattern Matching with Custom Objects
     */
    public static void demonstrateCustomObjects() {
        System.out.println("\n=== Pattern Matching with Custom Objects ===");
        
        Object obj = new Person("Alice", 30);
        
        if (obj instanceof Person p) {
            System.out.println("Person found:");
            System.out.println("  Name: " + p.getName());
            System.out.println("  Age: " + p.getAge());
            
            // Combine with condition
            if (p.getAge() >= 18) {
                System.out.println("  Status: Adult");
            }
        }
    }
    
    /**
     * Example 14: Validation with Pattern Matching
     */
    public static void demonstrateValidation() {
        System.out.println("\n=== Validation with Pattern Matching ===");
        
        Object[] inputs = {
            "valid@email.com",
            "invalid-email",
            "",
            123,
            null
        };
        
        System.out.println("Validating inputs:");
        for (Object input : inputs) {
            boolean valid = isValidEmail(input);
            System.out.println("  " + String.format("%-20s", String.valueOf(input)) + 
                             ": " + (valid ? "✓ Valid" : "✗ Invalid"));
        }
    }
    
    private static boolean isValidEmail(Object input) {
        return input instanceof String email && 
               email.contains("@") && 
               email.contains(".") &&
               email.length() > 5;
    }
    
    /**
     * Example 15: Pattern Matching in Method Parameters
     */
    public static void demonstrateProcessing() {
        System.out.println("\n=== Processing Different Types ===");
        
        processValue("Hello World");
        processValue(100);
        processValue(3.14);
        processValue(true);
        processValue(null);
    }
    
    private static void processValue(Object value) {
        if (value instanceof String s) {
            System.out.println("Processing String: " + s.toLowerCase());
        } else if (value instanceof Integer i) {
            System.out.println("Processing Integer: " + (i * 2));
        } else if (value instanceof Double d) {
            System.out.println("Processing Double: " + (d * 2));
        } else if (value instanceof Boolean b) {
            System.out.println("Processing Boolean: " + !b);
        } else if (value == null) {
            System.out.println("Processing null");
        } else {
            System.out.println("Processing unknown: " + value.getClass().getSimpleName());
        }
    }
    
    // ===== HELPER CLASSES =====
    
    static class Shape {}
    
    static class Circle extends Shape {
        double radius;
        Circle(double radius) { this.radius = radius; }
    }
    
    static class Rectangle extends Shape {
        double width, height;
        Rectangle(double width, double height) { 
            this.width = width; 
            this.height = height;
        }
    }
    
    static class Triangle extends Shape {
        double base, height;
        Triangle(double base, double height) { 
            this.base = base; 
            this.height = height;
        }
    }
    
    static class SuccessResponse {
        String message;
        java.util.Map<String, String> data;
        SuccessResponse(String message, java.util.Map<String, String> data) {
            this.message = message;
            this.data = data;
        }
    }
    
    static class ErrorResponse {
        String code, message;
        ErrorResponse(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }
    
    static class WarningResponse {
        String message;
        WarningResponse(String message) { this.message = message; }
    }
    
    static class Animal {
        String name;
        Animal(String name) { this.name = name; }
    }
    
    static class Dog extends Animal {
        String breed;
        Dog(String name, String breed) { 
            super(name); 
            this.breed = breed;
        }
    }
    
    static class Cat extends Animal {
        String color;
        Cat(String name, String color) { 
            super(name); 
            this.color = color;
        }
    }
    
    static class Person {
        private String name;
        private int age;
        Person(String name, int age) { 
            this.name = name; 
            this.age = age;
        }
        String getName() { return name; }
        int getAge() { return age; }
    }
    
    // ===== MAIN METHOD =====
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║  PATTERN MATCHING - COMPREHENSIVE DEMO (Java 16+) ║");
        System.out.println("╚════════════════════════════════════════════════╝");
        System.out.println("(Java 17+ features will use fallback)");
        
        // Basic Examples
        demonstrateTraditionalInstanceof();
        demonstrateModernInstanceof();
        demonstratePatternScope();
        
        // Using Pattern Matching
        demonstrateMultipleTypes();
        demonstrateGuardCondition();
        demonstratePatternInTernary();
        
        // Advanced Examples
        demonstratePolymorphicCollections();
        demonstrateApiResponseHandling();
        demonstrateTypeSafety();
        demonstrateInheritance();
        
        // Pattern Matching with Switch (Java 17+)
        demonstrateSwitchPatterns();
        
        // Real-World Scenarios
        demonstrateCustomObjects();
        demonstrateValidation();
        demonstrateProcessing();
        
        // Common Mistakes
        demonstrateMistake1();
    }
}
