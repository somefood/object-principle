package event.problem;

import java.time.LocalDate;

public interface RecurringPlan {
    boolean includes(LocalDate day);
    void reschedule(LocalDate day);
}
