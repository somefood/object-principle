package problem.calls;

import org.junit.jupiter.api.Test;
import problem.reader.CsvRedisReader;
import problem.reader.JsonRedisReader;
import redis.clients.jedis.JedisPool;
import redis.embedded.RedisServer;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class CallHistoryWithRedisTest {
    @Test
    public void collect_with_json() throws Exception {
        RedisServer server = new RedisServer(6379);
        server.start();

        JedisPool jedisPool = new JedisPool("localhost", 6379);
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

        CallCollector callCollector = new CallCollector(new JsonRedisReader("phone:calls:json", jedisPool));

        CallHistory history = callCollector.collect("010-1111-2222");

        assertThat(history.callDuration()).isEqualTo(Duration.ofSeconds(159));

        server.stop();
    }

    @Test
    public void collect_with_csv() throws Exception {
        RedisServer server = new RedisServer(6379);
        server.start();

        JedisPool jedisPool = new JedisPool("localhost", 6379);
        jedisPool.getResource().set("phone:calls:csv",
                """
                010-1111-2222,010-3333-4444,2024-01-01T11:31:05,2024-01-01T11:31:25
                010-1111-2222,010-3333-4444,2024-01-02T09:10:01,2024-01-02T09:11:10
                010-3333-4444,010-5555-6666,2024-01-02T09:11:32,2024-01-02T09:11:50
                010-3333-4444,010-5555-6666,2024-01-03T20:01:30,2024-01-03T20:02:30
                010-1111-2222,010-5555-6666,2024-01-04T15:45:23,2024-01-04T15:46:33
                """);

        CallCollector callCollector = new CallCollector(new CsvRedisReader("phone:calls:csv",jedisPool));

        CallHistory history = callCollector.collect("010-1111-2222");

        assertThat(history.callDuration()).isEqualTo(Duration.ofSeconds(159));

        server.stop();
    }
}

