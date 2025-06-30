package event.solution;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class WeeklyPlan implements RecurringPlan, TemporalFilter {
    private Set<DayOfWeek> dayOfWeeks;

    public WeeklyPlan(Set<DayOfWeek> dayOfWeeks) {
        this.dayOfWeeks = dayOfWeeks;
    }

    @Override
    public boolean includes(LocalDate day) {
        return dayOfWeeks.contains(day.getDayOfWeek());
    }

    @Override
    public WeeklyPlan reschedule(LocalDate day) {
        var copy = new HashSet<>(dayOfWeeks);
        copy.add(day.getDayOfWeek());
        return new WeeklyPlan(copy);
    }

    @Override
    public Collection<LocalDate> apply(DateInterval interval) {
        return interval.stream()
                .filter(this::includes)
                .collect(Collectors.toSet());
    }
}
