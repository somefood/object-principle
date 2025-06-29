package solution.calls;

import org.junit.jupiter.api.Test;
import solution.reader.FakeReader;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class LongestCallSelectorTest {
    @Test
    public void select_with_call_collector() {
        LongestCallSelector selector = new LongestCallSelector();

        CallCollector collector =
                new CallCollector(
                        new FakeReader(
                                new Call("010-1111-2222", "010-3333-4444",
                                        TimeInterval.of(LocalDateTime.of(2024,1,1,0, 0,0), LocalDateTime.of(2024,1,1,0,0,9))),
                                new Call("010-1111-2222", "010-3333-4444",
                                        TimeInterval.of(LocalDateTime.of(2024,1,2,0, 0,0), LocalDateTime.of(2024,1,2,0,0,9))),
                                new Call("010-3333-4444", "010-5555-6666",
                                        TimeInterval.of(LocalDateTime.of(2024, 1, 3, 0, 0, 0), LocalDateTime.of(2024,1,3,0,0,11)))
                        ));

        Optional<CallHistory> result = selector.select(List.of("010-1111-2222", "010-3333-4444"), collector);

        assertThat(result).isPresent().map(CallHistory::phone).get().isEqualTo("010-1111-2222");
    }
}
