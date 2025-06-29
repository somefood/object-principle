package problem.calls;

import org.junit.jupiter.api.Test;
import problem.reader.FakeReader;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class BillingCallCollectorTest {
    @Test
    public void collect() {
        BillingCallCollector callCollector = new BillingCallCollector(
                new FakeReader(
                        new Call("010-1111-2222", "010-3333-4444", TimeInterval.of(LocalDateTime.of(2024,1,1,0,0,0), LocalDateTime.of(2024,1,1,0,0,9))),
                        new Call("010-1111-2222", "010-3333-4444", TimeInterval.of(LocalDateTime.of(2024,1,2,0,0,0), LocalDateTime.of(2024,1,2,0,0,9))),
                        new Call("010-3333-4444", "010-5555-6666", TimeInterval.of(LocalDateTime.of(2024,1,3,0,0,0), LocalDateTime.of(2024,1,3,0,0,11)))
                ));

        assertThat(callCollector.collect("010-1111-2222").callDuration()).isEqualTo(Duration.ofSeconds(0));

        assertThat(callCollector.collect("010-3333-4444").callDuration()).isEqualTo(Duration.ofSeconds(11));
    }
}
