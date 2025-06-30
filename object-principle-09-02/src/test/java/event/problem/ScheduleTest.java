package event.problem;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class ScheduleTest {
    @Test
    public void day_of_weeks_includes() {
        Schedule schedule = new Schedule(
                "미팅", LocalTime.of(13, 0), Duration.ofHours(1),
                new WeeklyPlan(new HashSet<>(Set.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY))));

        assertThat(schedule.includes(LocalDate.of(2024, 7, 3))).isFalse();
        assertThat(schedule.includes(LocalDate.of(2024, 7, 10))).isFalse();
    }

    @Test
    public void reschedule() {
        Schedule schedule = new Schedule(
                "미팅", LocalTime.of(13, 0), Duration.ofHours(1),
                new WeeklyPlan(new HashSet<>(Set.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY))));

        LocalDate date = LocalDate.of(2024, 7, 3);
        if (!schedule.includes(date)) {
            schedule.reschedule(date);
        }

        assertThat(schedule.includes(date)).isTrue();
    }
}
