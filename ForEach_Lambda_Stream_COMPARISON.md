# forEach vs Lambda Expressions vs Stream API

Quick comparisons with when-to-use, code, and expected output. Spring-flavored where helpful.

## TL;DR: when to pick what

- **Lambda**: The shape of a function you pass around. You still need a target (forEach, stream ops, Runnable, Comparator).
- **forEach**: Use to _perform an action on every element_. No transformations or early returns. Good for printing/logging/side effects.
- **Stream API**: Use for _pipelines_ (filter/map/sort/collect). Prefer when you need to transform or aggregate data.

## Side-by-side: print vs transform vs aggregate

### 1) Just do something with each item (forEach)

```java
List<String> names = List.of("Ana", "Bob", "Cara");
names.forEach(System.out::println);
```

**Output**

```
Ana
Bob
Cara
```

**Use when**: side effects (log, send, print), no need to transform or collect.

### 2) Transform a list (Stream map + collect)

```java
List<String> names = List.of("Ana", "Bob", "Cara");
List<String> upper = names.stream()
    .map(String::toUpperCase)
    .toList();
System.out.println(upper);
```

**Output**

```
[ANA, BOB, CARA]
```

**Use when**: you need a new list/structure from the data.

### 3) Filter + sort + output (Stream pipeline)

```java
List<Integer> nums = List.of(5, 2, 9, 4);
nums.stream()
    .filter(n -> n % 2 == 0)
    .sorted()
    .forEach(System.out::println);
```

**Output**

```
2
4
```

**Use when**: multiple steps (filter/map/sort) before output.

### 4) Aggregate (Stream reduce/collect)

```java
List<Integer> nums = List.of(5, 2, 9, 4);
int sum = nums.stream().mapToInt(Integer::intValue).sum();
System.out.println(sum);
```

**Output**

```
20
```

**Use when**: totals, averages, groupings, counts.

---

## Real-world-ish Spring service snippets

### Return DTOs filtered and sorted (Streams)

```java
List<UserDto> getActiveUsersSortedByJoinDate(List<UserDto> users) {
    return users.stream()
        .filter(UserDto::isActive)
        .sorted(Comparator.comparing(UserDto::joinedAt))
        .toList();
}
```

### Log each incoming request header (forEach)

```java
void logHeaders(Map<String, String> headers, Logger log) {
    headers.forEach((k, v) -> log.info(k + ": " + v));
}
```

### Build a response summary (Streams + method refs)

```java
String summarizeOrders(List<OrderDto> orders) {
    double total = orders.stream()
        .filter(o -> o.status() == Status.PAID)
        .mapToDouble(OrderDto::amount)
        .sum();

    List<String> emails = orders.stream()
        .map(OrderDto::buyerEmail)
        .filter(Objects::nonNull)
        .map(String::toLowerCase)
        .distinct()
        .sorted()
        .toList();

    return "paidTotal=" + total + ", buyers=" + emails;
}
```

### Compare: imperative loop vs stream

**Loop**

```java
List<String> emails = new ArrayList<>();
for (OrderDto o : orders) {
    if (o.status() == Status.PAID && o.buyerEmail() != null) {
        String e = o.buyerEmail().toLowerCase();
        if (!emails.contains(e)) emails.add(e);
    }
}
Collections.sort(emails);
```

**Stream (clearer)**

```java
List<String> emails = orders.stream()
    .filter(o -> o.status() == Status.PAID)
    .map(OrderDto::buyerEmail)
    .filter(Objects::nonNull)
    .map(String::toLowerCase)
    .distinct()
    .sorted()
    .toList();
```

---

## Cheat sheet

- Use **forEach** for side effects only. Don’t mutate the source list inside.
- Use **Streams** for filter/map/sort/collect/reduce. They return new data.
- Lambdas/method refs are just the _shape_ of the function you pass to forEach/stream/etc.
- If you need indexes or breaks, prefer loops; streams/forEach don’t support break/continue directly.

---

## Quick practice with expected output

1. `List.of("a","b")` → `forEach(System.out::println)` → prints `a` then `b`.
2. Uppercase transform: `List.of("a","b").stream().map(String::toUpperCase).toList()` → `[A, B]`.
3. Filter evens and sum: `List.of(1,2,3,4).stream().filter(n->n%2==0).mapToInt(Integer::intValue).sum()` → `6`.
4. Sort names case-insensitively and print: `List.of("Zed","amy","Bob").stream().sorted(String::compareToIgnoreCase).forEach(System.out::println);` → `amy`, `Bob`, `Zed`.
5. Distinct domains: `List.of("a@x.com","b@y.com","c@x.com").stream().map(s->s.split("@")[1]).distinct().sorted().toList()` → `[x.com, y.com]`.
