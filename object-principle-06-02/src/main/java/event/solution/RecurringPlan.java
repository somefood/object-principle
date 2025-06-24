package event.solution;

import java.time.LocalDate;

public interface RecurringPlan {
    boolean includes(LocalDate day);
    void reschedule(LocalDate day);
}
