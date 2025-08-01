package solution.calls;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import solution.reader.FakeReader;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class LongestCallSelectorTest {
    @Test
    public void select_with_call_collector() {
        CallCollector collector =
                new CallCollector(
                        new FakeReader(
                                new Call("010-1111-2222", "010-3333-4444",
                                        TimeInterval.of(LocalDateTime.of(2024,1,1,0, 0,0), LocalDateTime.of(2024,1,1,0,0,5))),
                                new Call("010-1111-2222", "010-3333-4444",
                                        TimeInterval.of(LocalDateTime.of(2024,1,2,0, 0,0), LocalDateTime.of(2024,1,2,0,0,6))),
                                new Call("010-1111-2222", "010-5555-6666",
                                        TimeInterval.of(LocalDateTime.of(2024, 1, 3, 0, 0, 0), LocalDateTime.of(2024,1,3,0,0,7)))
                        ));

        Optional<Call> result = new LongestCallSelector().select("010-1111-2222", collector);

        assertThat(result).isPresent().map(Call::duration).get().isEqualTo(Duration.ofSeconds(7));
    }

    @Disabled("BillingCallCollector가 Collector를 치환할 수 없기 때문에 컴파일 에러 발생")
    @Test
    public void select_with_billing_call_collector() {
        BillingCallCollector collector =
                new BillingCallCollector(
                        new CallCollector(
                                new FakeReader(
                                        new Call("010-1111-2222", "010-3333-4444",
                                                TimeInterval.of(LocalDateTime.of(2024,1,1,0, 0,0), LocalDateTime.of(2024,1,1,0,0,5))),
                                        new Call("010-1111-2222", "010-3333-4444",
                                                TimeInterval.of(LocalDateTime.of(2024,1,2,0, 0,0), LocalDateTime.of(2024,1,2,0,0,6))),
                                        new Call("010-1111-2222", "010-5555-6666",
                                                TimeInterval.of(LocalDateTime.of(2024, 1, 3, 0, 0, 0), LocalDateTime.of(2024,1,3,0,0,7)))
                                )));

        // LongestCallSelector와 협력할 수 없도록 합성으로 변경
        // Optional<Call> result = new LongestCallSelector().select("010-1111-2222", collector);
    }
}
