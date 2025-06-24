package event.valueobject;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule {
    private String name;
    private LocalTime from;
    private Duration duration;
    private RecurringPlan expression;

    public Schedule(String name, LocalTime from, Duration duration, RecurringPlan expression) {
        this.name = name;
        this.from = from;
        this.duration = duration;
        this.expression = expression;
    }

    public boolean includes(LocalDate day) {
        return expression.includes(day);
    }

    public void reschedule(LocalDate day) {
        this.expression = expression.reschedule(day);
    }
}
