package problem.calls;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import problem.reader.CsvReader;
import problem.reader.CsvRedisReader;
import problem.reader.JsonReader;
import problem.reader.JsonRedisReader;
import redis.clients.jedis.JedisPool;
import redis.embedded.RedisServer;

import java.io.IOException;
import java.time.Duration;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CallCollectorWithReaderTest {
    private static RedisServer server;
    private static JedisPool jedisPool;

    @BeforeAll
    public static void prepareRedis() throws IOException {
        server = new RedisServer(6379);
        server.start();

        jedisPool = new JedisPool("localhost", 6379);
        jedisPool.getResource().set("phone:calls:json",
                """
                {
                  "calls": [
                    { "from": "010-1111-2222", "to": "010-3333-4444", "start": "2024-01-01T11:31:05", "end": "2024-01-01T11:31:25" },
                    { "from": "010-1111-2222", "to": "010-3333-4444", "start": "2024-01-02T09:10:01", "end": "2024-01-02T09:11:10" },
                    { "from": "010-3333-4444", "to": "010-5555-6666", "start": "2024-01-02T09:11:32", "end": "2024-01-02T09:11:50" },
                    { "from": "010-3333-4444", "to": "010-5555-6666", "start": "2024-01-03T20:01:30", "end": "2024-01-03T20:02:30" },
                    { "from": "010-1111-2222", "to": "010-5555-6666", "start": "2024-01-04T15:45:23", "end": "2024-01-04T15:46:33" }
                  ]
                }
                """);

        jedisPool.getResource().set("phone:calls:csv",
                """
                010-1111-2222,010-3333-4444,2024-01-01T11:31:05,2024-01-01T11:31:25
                010-1111-2222,010-3333-4444,2024-01-02T09:10:01,2024-01-02T09:11:10
                010-3333-4444,010-5555-6666,2024-01-02T09:11:32,2024-01-02T09:11:50
                010-3333-4444,010-5555-6666,2024-01-03T20:01:30,2024-01-03T20:02:30
                010-1111-2222,010-5555-6666,2024-01-04T15:45:23,2024-01-04T15:46:33
                """);
    }

    @AfterAll
    public static void closeRedis() throws IOException {
        server.stop();
    }

    public static Stream<Arguments> readers() {
        return Stream.of(
                Arguments.of(new CsvReader("calls.csv")),
                Arguments.of(new JsonReader("calls.json")),
                Arguments.of(new CsvRedisReader("phone:calls:csv", jedisPool)),
                Arguments.of(new JsonRedisReader("phone:calls:json", jedisPool)));
    }

    @ParameterizedTest
    @MethodSource("readers")
    public void collect(Reader reader) {
        CallCollector callCollector = new CallCollector(reader);

        CallHistory history = callCollector.collect("010-1111-2222");

        assertThat(history.callDuration()).isEqualTo(Duration.ofSeconds(159));
    }
}
