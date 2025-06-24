package event.solution;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ScheduleTest {
    @Test
    public void day_of_weeks_in_month_includes() {
        Schedule schedule = new Schedule(
                "월간회의",
                LocalTime.of(14, 0),
                Duration.ofHours(1),
                new MonthlyPlan(DayOfWeek.MONDAY, 2));

        assertThat(schedule.includes(LocalDate.of(2025, 1, 16))).isFalse();
        assertThat(schedule.includes(LocalDate.of(2025, 1, 16))).isFalse();
    }

    @Test
    public void reschedule() {
        Schedule schedule = new Schedule(
                "월간회의",
                LocalTime.of(14, 0),
                Duration.ofHours(1),
                new MonthlyPlan(DayOfWeek.MONDAY, 2));
        assertThat(schedule.includes(LocalDate.of(2025, 1, 16))).isFalse();

        schedule.adjust(LocalDate.of(2025, 1, 16));
        assertThat(schedule.includes(LocalDate.of(2025, 1, 16))).isTrue();
    }
}
