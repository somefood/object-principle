package event.solution;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule {
    private String name;
    private LocalTime from;
    private Duration duration;
    private RecurringPlan plan;

    public Schedule(String name, LocalTime from, Duration duration, RecurringPlan plan) {
        this.name = name;
        this.from = from;
        this.duration = duration;
        this.plan = plan;
    }

    public boolean includes(LocalDate day) {
        return plan.includes(day);
    }

    public void adjust(LocalDate day) {
        plan.reschedule(day);
    }
}
