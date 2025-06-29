package calls;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CallHistory {
    private String phone;
    private List<Call> calls = new ArrayList<>();

    public CallHistory(String phone) {
        this.phone = phone;
    }

    public void append(Call call) {
        this.calls.add(call);
    }

    public String phone() {
        return phone;
    }

    public Duration callDuration() {
        return calls.stream().map(Call::duration).reduce(Duration.ZERO, Duration::plus);
    }

    public List<Call> calls() {
        return Collections.unmodifiableList(calls);
    }
}
