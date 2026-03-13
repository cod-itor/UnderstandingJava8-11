# Pattern Matching - Java 14, 16, 17

## 📚 Definition

**Pattern Matching** is a feature that simplifies type checking and data extraction by allowing you to test whether a value matches a pattern and bind the components of the value to variables, all in a concise way. It makes code more readable and reduces boilerplate.

**Key Versions:**

- **Java 14**: Introduced `instanceof` with type patterns (Preview)
- **Java 16**: Finalized `instanceof` patterns
- **Java 17**: Introduced switch patterns and guarded patterns

---

## 🎯 When to Use Pattern Matching

### Use Pattern Matching When:

- ✅ Checking object types before casting
- ✅ You need to extract values from complex objects
- ✅ Writing conditional logic based on types
- ✅ You want cleaner, more readable code
- ✅ Working with polymorphic collections
- ✅ Processing different data types in switch statements (Java 17+)

### Available In:

- ✅ instanceof expressions (Java 16+)
- ✅ switch statements (Java 17+)
- ✅ Can be combined with other patterns

---

## 🔧 Pattern Matching Evolution

### 1. **instanceof Patterns** (Java 14+)

#### Before Java 14: Traditional Type Checking

```java
Object obj = "Hello";

// ❌ Verbose and error-prone
if (obj instanceof String) {
    String str = (String) obj;  // Manual cast required
    System.out.println(str.toUpperCase());
}
```

**Issues:**

- Must cast after instanceof check
- Extra variable declaration
- Easy to forget the cast
- Verbose and repetitive

#### After Java 14: Pattern Matching

```java
Object obj = "Hello";

// ✅ Concise and safe
if (obj instanceof String str) {
    System.out.println(str.toUpperCase());
}
```

**Benefits:**

- Cast is automatic
- Variable is bound in one step
- Scope limited to the if block
- Type-safe and clean

### 2. **Guarded Patterns** (Java 17+)

Added ability to add conditions using `when` clause:

```java
// Before Java 17: Extra if statement
if (obj instanceof String) {
    String str = (String) obj;
    if (str.length() > 5) {
        System.out.println("Long string: " + str);
    }
}

// After Java 17: Guarded pattern
if (obj instanceof String str && str.length() > 5) {
    System.out.println("Long string: " + str);
}

// Or better - with when clause
if (obj instanceof String str when str.length() > 5) {
    System.out.println("Long string: " + str);
}
```

### 3. **Switch with Patterns** (Java 17+)

#### Before Java 17: Switch with Objects

```java
Object obj = ...;

// ❌ Not possible - can only switch on primitives/enums
// switch (obj) {  // Doesn't work with Object
//     case String s: ...
// }

// Instead, had to use if-else chains
if (obj instanceof String) {
    String str = (String) obj;
    // handle String
} else if (obj instanceof Integer) {
    Integer num = (Integer) obj;
    // handle Integer
} else if (obj instanceof Boolean) {
    Boolean bool = (Boolean) obj;
    // handle Boolean
}
```

#### After Java 17: Switch Patterns

```java
Object obj = ...;

// ✅ Clean pattern matching in switch
String result = switch (obj) {
    case String s -> "String: " + s;
    case Integer i -> "Number: " + i;
    case Boolean b -> "Boolean: " + (b ? "true" : "false");
    case null -> "null value";
    default -> "unknown type";
};
```

**Benefits:**

- Cleaner than if-else chains
- Type safety built-in
- Exhaustiveness checking
- More maintainable

---

## 💡 Why You Need Pattern Matching

### 1. **Less Boilerplate**

Reduces casting and type checking code:

```java
// Old way (3 lines)
if (obj instanceof String) {
    String str = (String) obj;
    process(str);
}

// New way (1 line)
if (obj instanceof String str) process(str);
```

### 2. **Type Safety**

Eliminates unsafe casts:

```java
// Old way - could cast incorrectly
Object obj = 123;
String str = (String) obj;  // ❌ ClassCastException at runtime!

// New way - type safe
Object obj = 123;
if (obj instanceof String str) {  // False, no casting
    // Won't execute
}
```

### 3. **Cleaner Logic Flow**

Makes complex conditional logic more readable:

```java
// Compare shapes area calculation

// Old way
public double getArea(Object shape) {
    if (shape instanceof Rectangle) {
        Rectangle r = (Rectangle) shape;
        return r.getWidth() * r.getHeight();
    } else if (shape instanceof Circle) {
        Circle c = (Circle) shape;
        return Math.PI * c.getRadius() * c.getRadius();
    } else if (shape instanceof Triangle) {
        Triangle t = (Triangle) shape;
        return t.getBase() * t.getHeight() / 2;
    }
    return 0;
}

// New way (Java 17+)
public double getArea(Object shape) {
    return switch (shape) {
        case Rectangle r -> r.getWidth() * r.getHeight();
        case Circle c -> Math.PI * c.getRadius() * c.getRadius();
        case Triangle t -> t.getBase() * t.getHeight() / 2;
        default -> 0;
    };
}
```

### 4. **Scope Limitation**

Variable is scoped to the block where it's used:

```java
if (obj instanceof String str) {
    System.out.println(str);  // str accessible here
}
// str is NOT accessible here - scope limited!
```

### 5. **Guard Clauses**

Combine type checking with additional conditions:

```java
// Check type AND condition in one statement
if (obj instanceof String str && str.length() > 0) {
    System.out.println("Non-empty string: " + str);
}
```

---

## 🔄 Pattern Matching Scenarios

### Scenario 1: Polymorphic Collections

#### Before Java 17

```java
List<Object> list = Arrays.asList("String", 42, true, 3.14);

for (Object item : list) {
    if (item instanceof String) {
        String s = (String) item;
        System.out.println("String: " + s.toUpperCase());
    } else if (item instanceof Integer) {
        Integer i = (Integer) item;
        System.out.println("Integer: " + (i * 2));
    } else if (item instanceof Boolean) {
        Boolean b = (Boolean) item;
        System.out.println("Boolean: " + !b);
    } else if (item instanceof Double) {
        Double d = (Double) item;
        System.out.println("Double: " + (d * 2));
    }
}
```

#### After Java 17

```java
List<Object> list = Arrays.asList("String", 42, true, 3.14);

for (Object item : list) {
    String result = switch (item) {
        case String s -> "String: " + s.toUpperCase();
        case Integer i -> "Integer: " + (i * 2);
        case Boolean b -> "Boolean: " + !b;
        case Double d -> "Double: " + (d * 2);
        default -> "Unknown";
    };
    System.out.println(result);
}
```

### Scenario 2: API Response Handling

#### Before

```java
public void handleResponse(Object response) {
    if (response instanceof ErrorResponse) {
        ErrorResponse err = (ErrorResponse) response;
        logger.error("Error: " + err.getErrorCode() + " - " + err.getMessage());
    } else if (response instanceof SuccessResponse) {
        SuccessResponse success = (SuccessResponse) response;
        System.out.println("Success: " + success.getData());
    } else if (response instanceof WarningResponse) {
        WarningResponse warn = (WarningResponse) response;
        System.out.println("Warning: " + warn.getMessage());
    }
}
```

#### After

```java
public void handleResponse(Object response) {
    switch (response) {
        case ErrorResponse err ->
            logger.error("Error: " + err.getErrorCode() + " - " + err.getMessage());
        case SuccessResponse success ->
            System.out.println("Success: " + success.getData());
        case WarningResponse warn ->
            System.out.println("Warning: " + warn.getMessage());
        default ->
            System.out.println("Unknown response type");
    }
}
```

### Scenario 3: Validation with Guards

#### Before

```java
String email = getEmail();

if (email != null && email instanceof String) {
    String e = (String) email;
    if (e.contains("@") && e.contains(".")) {
        System.out.println("Valid email: " + e);
    }
}
```

#### After (Java 17+)

```java
String email = getEmail();

if (email instanceof String e && e.contains("@") && e.contains(".")) {
    System.out.println("Valid email: " + e);
}
```

### Scenario 4: Null Handling

#### Java 17 Improvement

```java
// Old way - still works
if (obj != null && obj instanceof String s) {
    // process s
}

// Java 17+ way - explicit null handling
if (obj instanceof String s) {
    // process s
} else if (obj instanceof null) {
    // handle null
}

// In switch (Java 17+)
switch (obj) {
    case String s -> System.out.println("String: " + s);
    case null -> System.out.println("null value");
    default -> System.out.println("Other");
}
```

---

## 📊 Key Improvements by Version

| Feature             | Java 14 | Java 16 | Java 17 |
| ------------------- | ------- | ------- | ------- |
| instanceof Patterns | Preview | Final   | ✅      |
| Basic Type Pattern  | ✅      | ✅      | ✅      |
| Guard Conditions    | ❌      | ❌      | ✅      |
| Switch Patterns     | ❌      | ❌      | Preview |
| null in switch      | ❌      | ❌      | ✅      |
| Type in switch      | ❌      | ❌      | ✅      |

---

## ⚠️ Important Rules

### 1. **instanceof Pattern Syntax**

```java
if (obj instanceof ClassName varName) {
    // varName is now ClassName type
    // Scope limited to this block
}
```

### 2. **Switch Pattern Exhaustiveness**

```java
// ✅ Exhaustive - covers all types
String result = switch (number) {
    case 1 -> "one";
    case 2 -> "two";
    case 3 -> "three";
    default -> "other";
};

// ❌ Not exhaustive if pattern type is Object
Object obj = ...;
String result = switch (obj) {
    case String s -> "string";
    case Integer i -> "integer";
    // Missing default - won't compile
};
```

### 3. **Guard Pattern Rules**

```java
// ✅ Correct - guard after variable binding
if (obj instanceof String s && s.length() > 5) { }

// ✅ Also works
if (obj instanceof String s) {
    if (s.length() > 5) { }
}

// ❌ Guard must come after pattern
if (s.length() > 5 && obj instanceof String s) { }  // s not defined yet
```

---

## 💾 Real-World Examples

### Example 1: JSON Processing

```java
public Object parseJson(String json) {
    Object obj = jsonParser.parse(json);

    return switch (obj) {
        case String s -> "Text: " + s;
        case Integer i -> "Number: " + i;
        case List<?> l -> "List with " + l.size() + " elements";
        case Map<?, ?> m -> "Map with " + m.size() + " entries";
        case null -> "null";
        default -> "Unknown: " + obj.getClass().getName();
    };
}
```

### Example 2: Data Validation

```java
public boolean isValid(Object data) {
    return switch (data) {
        case String s when s.length() > 0 -> true;
        case Integer i when i > 0 -> true;
        case Double d when d > 0.0 -> true;
        case null -> false;
        default -> false;
    };
}
```

### Example 3: Object Comparison

```java
public boolean equals(Object obj) {
    return switch (this) {
        case Point p when obj instanceof Point q ->
            p.x == q.x && p.y == q.y;
        case Rectangle r when obj instanceof Rectangle r2 ->
            r.width == r2.width && r.height == r2.height;
        default -> false;
    };
}
```

---

## 🎓 Key Takeaways

| Aspect           | Details                                          |
| ---------------- | ------------------------------------------------ |
| **What**         | Simplified type checking and extraction          |
| **When**         | Type checking, polymorphic code, switch on types |
| **Why**          | Less boilerplate, more readable, type-safe       |
| **Replaces**     | instanceof + cast + new variable (Java 14+)      |
| **Better for**   | Complex type hierarchies, API responses          |
| **Java Version** | 14+ (instanceof), 17+ (switch patterns)          |

---

## 🚀 Progression Path

1. **Java 14+**: Learn `instanceof String s` pattern
2. **Java 17+**: Learn switch patterns `case String s ->`
3. **Java 17+**: Add guards: `case String s when condition ->`
4. **Future**: Sealed classes + pattern matching for exhaustiveness

---

## 📖 Next Steps

See the example files in this folder for practical implementations!
