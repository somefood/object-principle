package problem;

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
    void to_json_dayofweek_ordinal() throws JsonProcessingException, JSONException {
        ScheduleJson schedule = new ScheduleJson(new Schedule("미팅", LocalTime.of(13, 0), Duration.ofHours(1), 2, DayOfWeek.MONDAY));

        JSONAssert.assertEquals("""
                {"title":"미팅","from":"13:00","duration":60,"dayOfWeek":"MONDAY","ordinal":2}""",
                schedule.toJson(),
                JSONCompareMode.LENIENT);
    }

    @Test
    public void to_json_dayofweeks() throws JsonProcessingException, JSONException {
        ScheduleJson schedule = new ScheduleJson(new Schedule("데일리 스크럼", LocalTime.of(9, 0), Duration.ofMinutes(15), Set.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY)));
        JSONAssert.assertEquals("""
                {"title":"데일리 스크럼","from":"09:00","duration":15,"dayOfWeeks":["MONDAY","TUESDAY"]}""",
                schedule.toJson(),
                JSONCompareMode.LENIENT);
    }
}
