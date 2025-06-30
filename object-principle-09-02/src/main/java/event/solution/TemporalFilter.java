package event.solution;

import java.time.LocalDate;
import java.util.Collection;

public interface TemporalFilter {
    Collection<LocalDate> apply(DateInterval interval);
}
