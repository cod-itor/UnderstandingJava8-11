# Date-Time API (java.time) - Java 8+

## 🧭 Key Types

- `LocalDate`, `LocalTime`, `LocalDateTime`: date/time without zone
- `ZonedDateTime`: date/time with zone
- `Instant`: machine timestamp (UTC)
- `Duration`: time-based amount (hours, seconds)
- `Period`: date-based amount (years, months, days)
- `DateTimeFormatter`: formatting/parsing

---

## ✨ Why `java.time`?

- Immutable and thread-safe
- Clear separation of human date-time vs. machine timestamps
- Better timezone handling vs. `java.util.Date` / `Calendar`

---

## 🛠️ Common Patterns

```java
LocalDate today = LocalDate.now();
LocalDate nextWeek = today.plusWeeks(1);

LocalDateTime meeting = LocalDateTime.of(2024, 5, 10, 15, 30);
ZonedDateTime meetingUtc = meeting.atZone(ZoneId.of("America/New_York")).withZoneSameInstant(ZoneId.of("UTC"));

Duration callLength = Duration.ofMinutes(42);
Period subscription = Period.ofMonths(6);

DateTimeFormatter iso = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
String formatted = meeting.format(iso);
LocalDateTime parsed = LocalDateTime.parse(formatted, iso);
```

---

## 🌍 Real-World Scenarios

- Scheduling across time zones (`withZoneSameInstant`)
- SLA / timeout calculations with `Duration`
- Subscription and trial periods with `Period`
- Log timestamps using `Instant` and `OffsetDateTime`

---

## 📌 Tips

- Use `Instant` for storage and comparisons; convert to `ZonedDateTime` for display.
- Normalize time zones at boundaries (e.g., database writes/reads).
- Prefer `DateTimeFormatter` with explicit patterns; avoid locale surprises.

---

## ⚠️ Pitfalls

- Avoid mixing legacy `Date`/`Calendar` without converting (`Date.from(instant)` / `date.toInstant()`).
- DST transitions: prefer `withZoneSameInstant` rather than manual offsets.
- Use `truncatedTo(ChronoUnit.MINUTES)` to align times when comparing.
