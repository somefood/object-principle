package event.problem;

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
        if (!plan.includes(day)) {
            plan.reschedule(day);
            return false;
        }
        
        return true;
    }
}
