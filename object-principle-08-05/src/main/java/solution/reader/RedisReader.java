package solution.reader;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import solution.calls.AbstractReader;
import solution.calls.Parser;

import java.util.Arrays;
import java.util.List;

public class RedisReader extends AbstractReader {
    private JedisPool jedisPool;

    public RedisReader(String path, Parser parser, JedisPool jedisPool) {
        super(path, parser);
        this.jedisPool = jedisPool;
    }

    @Override
    protected List<String> readLines(String path) {
        try(Jedis jedis = jedisPool.getResource()) {
            String json = jedis.get(path);
            return Arrays.stream(json.split("\\n")).toList();
        }
    }
}