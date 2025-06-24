import java.util.List;

public class CallCollector {
    private CsvReader reader;

    public CallCollector() {
        this.reader = new CsvReader("calls.csv");
    }

    public CallHistory collect(String phone) {
        List<Call> calls = reader.read();

        CallHistory history = new CallHistory(phone);

        for (Call call : calls) {
            if (call.from().equals(phone)) {
                history.append(call);
            }
        }
        return history;
    }
}
