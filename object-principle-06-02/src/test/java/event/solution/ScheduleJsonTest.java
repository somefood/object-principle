package event.solution;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;

public class ScheduleJsonTest {
    @Test
    public void to_json_day_of_week_in_month() throws JsonProcessingException, JSONException {
        ScheduleJson scheduleJson = new ScheduleJson(
                new Schedule(
                        "미팅", LocalTime.of(13, 0), Duration.ofHours(1),
                        new MonthlyPlan(DayOfWeek.MONDAY, 2)));

        JSONAssert.assertEquals("""
                {"name":"미팅","from":"13:00","duration":60,"plan":{"dayOfWeek":"MONDAY","ordinal":2}}""",
                scheduleJson.toJson(),
                JSONCompareMode.LENIENT);
    }

    @Test
    public void to_json_day_of_weeks() throws JsonProcessingException, JSONException {
        ScheduleJson scheduleJson = new ScheduleJson(
                new Schedule(
                        "미팅", LocalTime.of(13, 0), Duration.ofHours(1),
                        new WeeklyPlan(Set.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY))));

        JSONAssert.assertEquals("""
                {"name":"미팅","from":"13:00","duration":60,"plan":{"dayOfWeeks":["MONDAY","TUESDAY"]}}""",
                scheduleJson.toJson(),
                JSONCompareMode.LENIENT);
    }
}
