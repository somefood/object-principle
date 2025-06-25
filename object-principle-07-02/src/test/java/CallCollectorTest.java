import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class CallCollectorTest {
    @Test
    public void collect() {
        CallCollector callCollector = new CallCollector(new JsonReader("calls.json"));
        CallHistory history = callCollector.collect("010-1111-2222");

        assertThat(history.callDuration()).isEqualTo(Duration.ofSeconds(159));
    }
}
