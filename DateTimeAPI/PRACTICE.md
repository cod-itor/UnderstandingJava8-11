# Date-Time API (java.time) - Practice Pack

Use `DateTimeAPI/DateTimeExamples.java` for solutions.

## How to run

- Open `DateTimeExamples.java`
- Add code in `main` or helper methods and run.

## 10 Exercises (easy → hard)

1. **Today**: Print `LocalDate.now()`.
2. **Add days**: Print date 7 days from now.
3. **Format**: Format `LocalDateTime.now()` as `yyyy-MM-dd HH:mm` and print.
4. **Parse**: Parse `"2026-03-14T10:15"` into `LocalDateTime` and print hour.
5. **Zone conversion**: Take `LocalDateTime.now()` at `America/New_York`, convert to `UTC` with `withZoneSameInstant`, print both.
6. **Duration**: Create a `Duration` of 90 minutes; add to `Instant.now()` and print result.
7. **Period**: Given a start date, add `Period.ofMonths(6)` and print end date.
8. **Truncate**: Print `Instant.now().truncatedTo(ChronoUnit.MINUTES)`.
9. **Next Monday**: Use `TemporalAdjusters.next(DayOfWeek.MONDAY)` from today and print.
10. **Group events**: Given a list of `Instant`, group by day (`toLocalDate` in UTC) and print counts.

## Mini Project

**Meeting Scheduler**:

- Input: meeting `LocalDateTime` in `America/New_York`
- Convert to UTC and `Asia/Singapore`
- Print friendly strings with a formatter `EEE, dd MMM yyyy HH:mm z`
- Add a 45-minute duration and show end times in both zones.
