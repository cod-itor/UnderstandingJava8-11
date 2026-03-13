package DateTimeAPI;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class DateTimeExamples {
    public static void main(String[] args) {
        // LocalDate / LocalDateTime
        LocalDate today = LocalDate.now();
        LocalDate nextBilling = today.plusMonths(1).withDayOfMonth(1);
        System.out.println("Next billing date: " + nextBilling);

        LocalDateTime meeting = LocalDateTime.of(2024, 5, 10, 15, 30);
        ZonedDateTime meetingNy = meeting.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime meetingUtc = meetingNy.withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println("Meeting NY:  " + meetingNy);
        System.out.println("Meeting UTC: " + meetingUtc);

        // Duration and Period
        Duration callLength = Duration.ofMinutes(42).plusSeconds(15);
        Period trialPeriod = Period.ofDays(14);
        System.out.println("Call length: " + callLength);
        System.out.println("Trial ends: " + today.plus(trialPeriod));

        // Instant for machine time
        Instant now = Instant.now();
        Instant slaDeadline = now.plus(2, ChronoUnit.HOURS);
        System.out.println("Now (UTC): " + now);
        System.out.println("SLA deadline: " + slaDeadline);

        // Formatting & parsing
        DateTimeFormatter human = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");
        String humanReadable = meetingNy.format(human);
        LocalDateTime parsed = LocalDateTime.parse("2024-05-10T15:30", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println("Human: " + humanReadable);
        System.out.println("Parsed: " + parsed);

        // Aligning times (truncate)
        Instant aligned = now.truncatedTo(ChronoUnit.MINUTES);
        System.out.println("Aligned to minute: " + aligned);

        // Grouping events by day
        List<Instant> events = List.of(
            now.minusSeconds(60),
            now.minus(2, ChronoUnit.HOURS),
            now.plus(1, ChronoUnit.DAYS)
        );

        events.stream()
              .collect(java.util.stream.Collectors.groupingBy(ev -> ev.atZone(ZoneId.of("UTC")).toLocalDate()))
              .forEach((day, list) -> System.out.println(day + " -> " + list.size() + " event(s)"));
    }
}
