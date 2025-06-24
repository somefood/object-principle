package solution;

import java.time.LocalDate;

public interface RecurringPlan {
    boolean includes(LocalDate day);
}
