import java.util.List;

public class CallCollector {

    private final Reader reader;

    public CallCollector(Reader reader) {
        this.reader = reader;
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
