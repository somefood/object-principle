package event.problem;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MonthlyPlanTest {
    @Test
    public void convert() {
        DateInterval interval = DateInterval.between(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 2));

        assertThatThrownBy(() -> {
            interval.filter(new MonthlyPlan(DayOfWeek.MONDAY, 3));
        });
    }
}
