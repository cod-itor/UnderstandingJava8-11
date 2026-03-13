# Default Methods - Practice Pack

Use `DefaultMethods/DefaultMethodsExamples.java` for solutions.

## How to run

- Open `DefaultMethodsExamples.java`
- Add code in `main` or helper methods and run.

## 10 Exercises (easy → hard)

1. **Simple default**: Create interface `Greeter` with `default void hi(){System.out.println("hi");}`; implement and call.
2. **Override default**: Override `hi()` in the class to print custom text; call both (use `Greeter.super.hi()` inside override).
3. **Two interfaces, no conflict**: Implement `Ping { default void ping(){...}}` and `Pong { default void pong(){...}}`; call both.
4. **Conflict resolution**: Two interfaces each define `default void log()`; implement both and resolve by overriding and picking one via `Interface.super.log()`.
5. **Mixin helper**: Add `default void retry(Runnable r)` that retries once on exception; use it in a class.
6. **Fluent builder**: Add `default T withName(String n);` pattern to return `this`; implement and chain.
7. **Static vs default**: Add a static helper `Utils.format(String)` in the interface; call it from implementation.
8. **Extending interfaces**: Interface B extends A and overrides a default; show which one runs in the class.
9. **Diamond**: Interface A default, B extends A and overrides, C extends A and overrides; class implements B and C — resolve explicitly.
10. **Document intent**: Add Javadoc to a default method explaining when to override; verify readability.

## Mini Project

**Resilient Service Trait**: Build interfaces `Retryable` (default retry), `Auditable` (default audit), `Timed` (default time measurement). Implement a `Service` class combining them, and show a method call that prints audit, timing, and retries a failing action once.
