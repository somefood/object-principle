package problem.calls;

import java.time.Duration;

public class BillingCallCollector extends CallCollector {
    public BillingCallCollector(Reader reader) {
        super(reader);
    }

    @Override
    public CallHistory collect(String phone) {
        CallHistory history = super.collect(phone);

        CallHistory result = new CallHistory(phone);
        for(Call call : history.calls()) {
            if (call.duration().compareTo(Duration.ofSeconds(10)) >= 0) {
                result.append(call);
            }
        }

        return result;
    }
}
