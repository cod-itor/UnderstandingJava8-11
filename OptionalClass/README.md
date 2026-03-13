# Optional Class - Java 8

## 📚 Definition
**Optional** is a public final class introduced in Java 8 to deal with `NullPointerException` gracefully. It's a container object that may or may not contain a non-null value. An Optional is either:
- **Present**: Contains a non-null value
- **Empty**: Contains no value

**Location:** `java.util.Optional<T>`

---

## 🎯 Why Optional Exists

### The Problem: NullPointerException
Before Java 8, null checks were everywhere:

```java
// ❌ Traditional approach - verbose and error-prone
Person person = getPersonById(1);
if (person != null) {
    String name = person.getName();
    if (name != null) {
        System.out.println(name.toUpperCase());
    }
}
```

**Issues:**
- Repetitive null checks (NullPointerException is the most common runtime error)
- Deep nesting (pyramid of doom)
- Boilerplate code
- Easy to forget a null check

### The Solution: Optional
```java
// ✅ Modern approach - concise and safe
Optional<Person> person = getPersonById(1);
person.map(Person::getName)
      .ifPresent(name -> System.out.println(name.toUpperCase()));
```

**Benefits:**
- Explicit about "may contain no value"
- No NullPointerException possible
- Chainable operations
- Self-documenting code

---

## 🔧 Creating Optional Objects

### 1. **Optional.of(T value)** - Contains Non-Null Value
```java
Optional<String> name = Optional.of("Alice");
```
**Important:** Throws NullPointerException if value is null!
```java
Optional<String> invalid = Optional.of(null);  // ❌ Throws NullPointerException
```

### 2. **Optional.empty()** - Empty Optional
```java
Optional<String> empty = Optional.empty();
```

### 3. **Optional.ofNullable(T value)** - Safe Creation
```java
Optional<String> name = Optional.ofNullable(possiblyNull);
// Returns Optional.of(name) if not null
// Returns Optional.empty() if null
```

### 4. **Optional from Methods**
```java
List<String> list = Arrays.asList("Apple", "Banana");

// Stream's findFirst() returns Optional
Optional<String> first = list.stream()
    .filter(s -> s.startsWith("A"))
    .findFirst();  // Returns Optional
```

---

## 📊 Key Differences: Before and After Optional

### Before Java 8 (Traditional Approach)
```java
public String getPersonName(int id) {
    Person person = findPerson(id);
    
    // Multiple null checks - tedious!
    if (person != null) {
        Address address = person.getAddress();
        if (address != null) {
            String street = address.getStreet();
            if (street != null) {
                return street.toUpperCase();
            }
        }
    }
    return "Unknown";
}
```

### After Java 8 (Optional Approach)
```java
public String getPersonName(int id) {
    // Concise and safe!
    return findPersonOptional(id)
        .map(Person::getAddress)
        .map(Address::getStreet)
        .map(String::toUpperCase)
        .orElse("Unknown");
}
```

---

## 💡 Why You Need Optional

### 1. **Null Safety**
Prevents NullPointerException through explicit handling:
```java
// Traditional (risky)
String name = person.getName();  // Could be null, could throw NPE

// Optional (safe)
String name = person.map(Person::getName)
    .orElse("Unknown");  // Safe, returns default
```

### 2. **API Design**
Clearly communicates that a method might return no value:
```java
// Traditional - unclear if null is possible
public Person findById(int id) { ... }

// Optional - makes intent crystal clear
public Optional<Person> findById(int id) { ... }
```

### 3. **Functional Programming**
Enables chainable, declarative operations:
```java
user.map(User::getEmail)
    .filter(email -> email.contains("@"))
    .ifPresent(emailService::sendWelcome);
```

### 4. **Reduces Defensive Programming**
Less defensive null checking needed:
```java
// You don't need to write:
if (result != null && result.isValid()) { ... }

// Instead:
result.filter(r -> r.isValid())
      .ifPresent(r -> { ... });
```

---

## 🔄 Common Optional Operations

### **Checking if Present**
```java
Optional<String> value = Optional.of("Hello");

// Method 1: isPresent()
if (value.isPresent()) {
    System.out.println(value.get());
}

// Method 2: isEmpty() (Java 11+)
if (!value.isEmpty()) {
    System.out.println(value.get());
}

// Method 3: ifPresent() (Preferred)
value.ifPresent(System.out::println);
```

### **Getting Value**
```java
Optional<String> value = Optional.of("Hello");

// get() - Throws NoSuchElementException if empty
String result = value.get();  // "Hello"

// getOrElse() - Returns default if empty
String result = value.orElse("Default");  // "Hello"

// orElseGet() - Computes default if empty
String result = value.orElseGet(() -> "Computed");

// orElseThrow() - Throws custom exception if empty
String result = value.orElseThrow(() -> new IllegalStateException("No value"));
```

### **Transforming Value**
```java
Optional<String> name = Optional.of("alice");

// map() - Transform if present
Optional<String> upper = name.map(String::toUpperCase);
// Result: Optional["ALICE"]

// flatMap() - Transform returning Optional
Optional<User> user = name.flatMap(this::findUserByName);
// Result: Optional<User>

// filter() - Keep if condition matches
Optional<String> withA = name.filter(n -> n.contains("a"));
// Result: Optional["alice"]
```

### **Chaining Operations**
```java
Optional<Person> person = findPerson(1);

person
    .map(Person::getEmail)                    // Optional<String>
    .filter(email -> email.endsWith("@gmail.com"))  // Optional<String>
    .map(String::toUpperCase)                 // Optional<String>
    .ifPresent(System.out::println);          // Print if present
```

---

## 📈 Real-World Use Cases

### 1. **Database Query Results**
```java
// Traditional
User user = userRepository.findById(123);
if (user != null) {
    System.out.println(user.getEmail());
}

// With Optional
userRepository.findById(123)
    .map(User::getEmail)
    .ifPresent(System.out::println);
```

### 2. **Configuration Values**
```java
// Traditional
String timeout = properties.get("timeout");
int value = timeout != null ? Integer.parseInt(timeout) : 30;

// With Optional
int value = properties.get("timeout")
    .flatMap(t -> {
        try {
            return Optional.of(Integer.parseInt(t));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    })
    .orElse(30);
```

### 3. **API Methods Returning Optional**
```java
public class UserService {
    // Clear that result might be empty
    public Optional<User> authenticate(String username, String password) {
        User user = findUser(username);
        return user != null && validatePassword(user, password)
            ? Optional.of(user)
            : Optional.empty();
    }
}

// Usage
userService.authenticate("alice", "password123")
    .ifPresentOrElse(
        user -> login(user),
        () -> showLoginError("Invalid credentials")
    );
```

### 4. **Filtering Collections**
```java
List<String> emails = users.stream()
    .map(User::getEmail)
    .filter(Optional::isPresent)
    .map(Optional::get)
    .collect(Collectors.toList());

// Better approach with flatMap
List<String> emails = users.stream()
    .flatMap(user -> user.getEmail().stream())
    .collect(Collectors.toList());
```

### 5. **Method Chaining with Safe Navigation**
```java
// Complex object navigation
Optional<String> city = company.getAddress()
    .map(Address::getCity)
    .orElse("Unknown");

// Even complex: person -> address -> coordinates -> latitude
Optional<Double> latitude = person
    .flatMap(Person::getAddress)
    .flatMap(Address::getCoordinates)
    .map(Coordinates::getLatitude);
```

---

## 🏗️ Optional Methods Overview

### **Terminal Operations** (Return non-Optional)
| Method | Returns | When Used |
|--------|---------|-----------|
| `ifPresent(Consumer)` | void | Execute action if value exists |
| `ifPresentOrElse()` | void | Execute action or default |
| `get()` | T | Get value (throws if empty) |
| `orElse(T)` | T | Get value or default |
| `orElseGet(Supplier)` | T | Get value or compute |
| `orElseThrow()` | T | Get value or throw |

### **Intermediate Operations** (Return Optional)
| Method | Returns | Use Case |
|--------|---------|----------|
| `map(Function)` | Optional | Transform value |
| `flatMap(Function)` | Optional | Transform returning Optional |
| `filter(Predicate)` | Optional | Keep if condition true |

### **Utility Operations**
| Method | Returns | Use Case |
|--------|---------|----------|
| `isPresent()` | boolean | Check if has value |
| `isEmpty()` | boolean | Check if empty (Java 11+) |
| `stream()` | Stream | Convert to stream (Java 9+) |

---

## ⚠️ Common Mistakes

### ❌ Mistake 1: Using Optional for Everything
```java
// ❌ Don't do this
public Optional<String> getFirstName() {
    return Optional.of("Alice");  // Never empty, why use Optional?
}

// ✅ Use Optional only when value might be absent
public Optional<String> findNickname(User user) {
    // Nickname is optional
}
```

### ✅ Solution:
Use Optional only for values that can actually be absent.

### ❌ Mistake 2: Using get() Without Checking
```java
Optional<String> value = getValue();

// ❌ Will throw NoSuchElementException if empty
String result = value.get();
```

### ✅ Solution:
```java
// ✅ Use one of these instead
String result = value.orElse("Default");
String result = value.orElseThrow();
if (value.isPresent()) {
    String result = value.get();
}
```

### ❌ Mistake 3: Not Using map/flatMap
```java
// ❌ Verbose
Optional<Person> person = findPerson(1);
Optional<String> email = Optional.empty();
if (person.isPresent()) {
    email = person.get().getEmail();  // Still might be Optional
}
```

### ✅ Solution:
```java
// ✅ Concise
Optional<String> email = findPerson(1)
    .flatMap(Person::getEmail);
```

### ❌ Mistake 4: Chaining Optionals Incorrectly
```java
// ❌ Wrong - map returns Optional<Optional<T>>
Optional<String> email = person.map(Person::getEmail);
// Results in Optional<Optional<String>>

// ✅ Right - flatMap unwraps
Optional<String> email = person.flatMap(Person::getEmail);
// Results in Optional<String>
```

### ❌ Mistake 5: Using Optional as Method Parameter
```java
// ❌ Generally avoid this
public void processUser(Optional<User> user) { ... }

// ✅ Use this instead
public void processUser(User user) { ... }
public void processUserOptionally(Optional<User> user) { ... }

// ✅ Or better yet, have separate methods
public void processUser(User user) { ... }
public void processIfUserExists(Optional<User> user) {
    user.ifPresent(this::processUser);
}
```

---

## 📊 When to Use Optional vs Null Check

| Scenario | Use Optional? | Why |
|----------|---------------|-----|
| Method might not have result | ✅ Yes | Clear intent |
| Method parameter | ❌ No | Use separate method or default |
| Always has value | ❌ No | Unnecessary wrapper |
| Complex object graph | ✅ Yes | Safe navigation |
| Collection element | ❌ No | Use Stream filtering |
| Default values needed | ✅ Yes | Built-in support |

---

## 🔄 Optional vs NullPointerException

### The Old Way (Error-Prone)
```java
public String getUserEmail(int userId) {
    User user = database.findUser(userId);        // Could be null
    if (user == null) return null;                 // Defensive check
    
    Address address = user.getAddress();           // Could be null
    if (address == null) return null;
    
    String email = address.getEmail();             // Could be null
    if (email == null) return "no-email";
    
    return email;
}
```

### The New Way (Robust)
```java
public String getUserEmail(int userId) {
    return database.findUserOptional(userId)
        .flatMap(User::getAddress)
        .map(Address::getEmail)
        .orElse("no-email");
}
```

---

## 🎓 Key Takeaways

| Aspect | Details |
|--------|---------|
| **What** | Container for possibly-absent values, replaces nulls |
| **When** | Methods that may not have a result |
| **Why** | Prevents NullPointerException, clearer code intent |
| **Replaces** | Null checking and defensive programming |
| **Not for** | Parameters, always-present values, collection elements |
| **Best Practices** | Use map/flatMap/filter, avoid get() without checks |

---

## 📖 Next Steps
See the example files in this folder for practical implementations!
