package solution.calls;

import java.time.Duration;

public class BillingCallCollector {

    private final CallCollector callCollector;

    public BillingCallCollector(CallCollector callCollector) {
        this.callCollector = callCollector;
    }

    public CallHistory collect(String phone) {
        CallHistory history = callCollector.collect(phone);

        CallHistory result = new CallHistory(phone);
        for(Call call : history.calls()) {
            if (call.duration().compareTo(Duration.ofSeconds(10)) >= 0) {
                result.append(call);
            }
        }

        return result;
    }
}
