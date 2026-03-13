# Pattern Matching - Practice Pack

Use `PatternMatchingExamples.java` for solutions. Requires Java 16+ for instanceof patterns; Java 17+ for switch patterns.

## How to run

- Open `PatternMatchingExamples.java`
- Add code in `main` or helper methods and run with appropriate JDK.

## 10 Exercises (easy → hard)

1. **Instanceof bind**: `Object o = "hello";` use `instanceof String s` to print length.
2. **Number check**: Given `Object n = Integer.valueOf(5);`, pattern-match and double it.
3. **Guarded pattern**: `Object o = "abc";` if string length > 2, print upper-case.
4. **Multi-type if**: Handle `String`, `Integer`, `Double` with `instanceof` patterns and print type names.
5. **Switch basic**: `Object o = 1;` switch pattern on `Integer`, `String`, default.
6. **Switch guards**: On `Object o`, match `String s when s.length()>3` vs other strings vs default.
7. **Sealed-like handling**: Create a sealed-like hierarchy (or simple classes) `Circle`, `Square`; switch on shape and compute area.
8. **Null handling**: Show a switch with `case null -> ...` (Java 17+) and print message.
9. **Record patterns (if available)**: Define a record `Point(int x,int y)` and switch to extract x,y and print.
10. **Decompose nested**: Record `Box(Point p)`; switch and print `p.x + p.y`.

## Mini Project

**Event Router**: Given `Object event` that might be `UserCreated(String email)`, `PaymentFailed(String id,double amount)`, or others, use a switch pattern to route and print a message. Include a `default` to log unknown events.
