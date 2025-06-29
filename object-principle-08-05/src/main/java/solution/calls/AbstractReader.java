package solution.calls;

import java.util.List;

public abstract class AbstractReader implements Reader {
    private String path;
    private Parser parser;

    public AbstractReader(String path, Parser parser) {
        this.path = path;
        this.parser = parser;
    }

    public List<Call> read() {
        List<String> lines = readLines(path);
        return parser.parse(lines);
    }

    abstract protected List<String> readLines(String path);
}
