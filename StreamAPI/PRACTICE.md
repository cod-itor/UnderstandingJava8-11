# Stream API - Practice Pack

Use `StreamAPI/StreamAPIExamples.java` for solutions.

## How to run

- Open `StreamAPIExamples.java`
- Add code in `main` or helper methods and run.

## 10 Exercises (easy → hard)

1. **Filter evens**: `List.of(1,2,3,4,5)` → filter even, print.
2. **Map to lengths**: `List.of("apple","kiwi")` → map to length, collect to list, print.
3. **Distinct + sort**: `List.of(3,1,2,3,2)` → distinct, sort, collect, print.
4. **Limit + skip**: From `Stream.iterate(1, n->n+1)`, skip 5, limit 3, print.
5. **Any/all/none match**: On `List.of("Ana","Bob","cara")`, check any startsWith "A", all length>2, none empty; print booleans.
6. **Grouping**: Group `List.of("red","blue","brown","black")` by first letter; print map.
7. **Partitioning**: Partition ints by even/odd into a map of lists; print.
8. **Reduce sum**: Sum `List.of(10,20,30)` with reduce; print.
9. **FlatMap**: From `List.of(List.of(1,2), List.of(3))` flatten to one list; print.
10. **Collector composition**: From `List.of("ana@x.com","bob@y.com","amy@y.com")`, group by domain and count.

## Mini Project

**Simple Report Builder**: Given a list of orders `(id, amount, status)`:

- Filter status = "PAID"
- Map to amounts
- Compute total, average, max
- Collect payer emails (if present) distinct and sorted
- Print a summary string.
