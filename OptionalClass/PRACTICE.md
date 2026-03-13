# Optional - Practice Pack

Use `OptionalClass/OptionalExamples.java` for solutions.

## How to run

- Open `OptionalExamples.java`
- Add code in `main` or helper methods and run.

## 10 Exercises (easy → hard)

1. **Create empty**: Make `Optional<String> empty = Optional.empty();` and print `isPresent`.
2. **of vs ofNullable**: Show `Optional.of("hi")` works and `Optional.ofNullable(null)` doesn’t throw; print both with `orElse("none")`.
3. **map chain**: `Optional.of("abc")` → upper-case → append "!" → print orElse.
4. **filter**: Given `Optional.of("bob@site.com")`, filter for `endsWith("@site.com")`; print if present.
5. **ifPresentOrElse**: For `Optional.empty()`, print "missing"; for non-empty, print value.
6. **orElseGet vs orElse**: Show the supplier runs only when empty (use a print inside supplier).
7. **orElseThrow**: Throw custom exception when empty; catch and print message.
8. **flatMap**: From `Optional<User>` where `User` has `Optional<String> email()`, get email safely.
9. **from stream**: Use `List.of(1,2,3).stream().filter(n->n>5).findFirst()` and handle with `orElse(-1)`.
10. **Combine Optionals**: Given `Optional<String> city` and `Optional<String> country`, build `city + ", " + country` if both present, else "UNKNOWN".

## Mini Project

**Safe Profile Printer**: Given a `User` with optional `address` and optional `zip`, print `"Name - city, zip"` only if all pieces are present. Use `map`, `flatMap`, and `orElse("INCOMPLETE")`.
