package solution.reader;

import solution.calls.Call;
import solution.calls.Reader;

import java.util.List;

public class FakeReader implements Reader {
    private List<Call> calls;

    public FakeReader(Call... calls) {
        this.calls = List.of(calls);
    }
    @Override
    public List<Call> read() {
        return calls;
    }
}

