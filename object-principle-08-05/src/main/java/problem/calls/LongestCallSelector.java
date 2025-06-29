package problem.calls;

import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;

public class LongestCallSelector {
    public Optional<CallHistory> select(List<String> phones, CallCollector collector) {
        return phones.stream().map(collector::collect).max(comparing(CallHistory::callDuration));
    }
}

