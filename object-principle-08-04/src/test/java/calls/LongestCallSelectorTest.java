package calls;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import reader.CsvReader;
import reader.JsonReader;

import java.time.Duration;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LongestCallSelectorTest {
    public static Stream<Arguments> readers() {
        return Stream.of(
                Arguments.of(new CsvReader("calls.csv")),
                Arguments.of(new JsonReader("calls.json")));
    }

    @ParameterizedTest
    @MethodSource("readers")
    public void select_with_call_collector(Reader reader) {
        CallCollector collector = new CallCollector(reader);

        Optional<Call> result = new LongestCallSelector().select("010-1111-2222", collector);

        assertThat(result).isPresent().map(Call::duration).get().isEqualTo(Duration.ofSeconds(70));
    }

    @ParameterizedTest
    @MethodSource("readers")
    public void select_with_billing_call_collector(Reader reader) {
        BillingCallCollector collector = new BillingCallCollector(new CallCollector(reader));

        Optional<Call> result = new LongestCallSelector().select("010-1111-2222", collector);

        assertThat(result).isPresent().map(Call::duration).get().isEqualTo(Duration.ofSeconds(70));
    }
}
