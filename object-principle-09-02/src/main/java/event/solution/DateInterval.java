package event.solution;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.stream.Stream;

public class DateInterval {
    private LocalDate start;
    private LocalDate end;

    public static DateInterval between(LocalDate start, LocalDate end) {
        return new DateInterval(start, end);
    }

    private DateInterval(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public Stream<LocalDate> stream() {
        return Stream.iterate(start, date -> date.plusDays(1))
                .limit(start.until(end, ChronoUnit.DAYS)+1);
    }

    public Collection<LocalDate> filter(TemporalFilter filter) {
        return filter.apply(this);
    }
}
