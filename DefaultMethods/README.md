# Default Methods - Java 8

## 💡 What Are Default Methods?

`default` methods let interfaces provide behavior alongside abstract contracts. They enable interface evolution without breaking existing implementations.

```java
interface Formatter {
    String format(String input);

    default String trimAndFormat(String input) {
        return format(input == null ? "" : input.trim());
    }
}
```

---

## 🤔 Why They Matter

- Add new behavior to interfaces without forcing all implementers to change
- Provide reusable helper methods close to the contract
- Enable mixin-like patterns (behavioral traits)

---

## ⚙️ Rules

- Declared with `default` keyword inside an interface
- Can be overridden by implementing classes
- Conflict resolution:
  1. **Class wins** over interface defaults
  2. If multiple interfaces provide the same default, the class **must override** to resolve ambiguity

---

## 🧭 Conflict Resolution Example

```java
interface Loggable { default void log() { System.out.println("loggable"); } }
interface Auditable { default void log() { System.out.println("auditable"); } }

class Service implements Loggable, Auditable {
    @Override public void log() { // must override to pick one
        Loggable.super.log();
    }
}
```

---

## 🌍 Real-World Uses

- **API evolution**: add `stream()` to `Collection` (Java 8) without breaking implementations.
- **Behavioral mixins**: add helpers like `retry`, `validate`, `paginate` in service interfaces.
- **Fluent builders**: defaults for chaining setters that return `this`.

---

## ✅ Tips

- Keep defaults small and side-effect free.
- Use `super` calls to reuse a specific interface's default: `InterfaceName.super.method()`.
- Document when overriding is expected to avoid surprises.
