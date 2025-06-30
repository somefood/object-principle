package event.problem;

import java.time.LocalDate;
import java.util.Collection;

public interface RecurringPlan {
    boolean includes(LocalDate day);
    RecurringPlan reschedule(LocalDate day);

    Collection<LocalDate> apply(DateInterval interval);
}
