package event.solution;

import java.time.LocalDate;
import java.util.Collection;

public interface RecurringPlan {
    boolean includes(LocalDate day);
    RecurringPlan reschedule(LocalDate day);
}
