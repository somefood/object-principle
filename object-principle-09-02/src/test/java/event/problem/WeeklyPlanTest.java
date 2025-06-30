package event.problem;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class WeeklyPlanTest {
    @Test
    public void convert() {
        DateInterval interval = DateInterval.between(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 7));

        Collection<LocalDate> dates = interval.filter(new WeeklyPlan(Set.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY)));

        assertThat(dates).containsAll(Set.of(LocalDate.of(2025, 1, 6), LocalDate.of(2025,1,7)));
    }
}
