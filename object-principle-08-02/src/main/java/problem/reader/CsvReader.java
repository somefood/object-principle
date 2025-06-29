package problem.reader;

import problem.calls.AbstractReader;
import problem.calls.Call;
import problem.calls.TimeInterval;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class CsvReader extends AbstractReader {

    protected CsvReader(String path) {
        super(path);
    }

    @Override
    public List<Call> parse(List<String> lines) {
        return lines.stream()
                .map(this::parseCall)
                .collect(Collectors.toList());
    }

    private Call parseCall(String line) {
        String[] tokens = line.split(",");
        return new Call(tokens[0].trim(), tokens[1].trim(), TimeInterval.of(parseDateTime(tokens[2]), parseDateTime(tokens[3])));
    }

    private LocalDateTime parseDateTime(String token) {
        return LocalDateTime.parse(token.trim(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
