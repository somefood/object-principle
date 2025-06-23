import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule {
    
    private static final int DAYS_IN_WEEK = 7;
    
    private String title;
    @JsonFormat(pattern = "HH:mm") private LocalTime from;
    @JsonFormat(pattern = "MINUTES") private Duration duration;
    private Integer ordinalWeek;
    private DayOfWeek dayOfWeek;

    public Schedule(String title, LocalTime from, Duration duration, DayOfWeek dayOfWeek, Integer ordinalWeek) {
        this.title = title;
        this.from = from;
        this.duration = duration;
        this.dayOfWeek = dayOfWeek;
        this.ordinalWeek = ordinalWeek;
    }
    
    public boolean includes(LocalDate day) {
        if (!day.getDayOfWeek().equals(dayOfWeek)) {
            return false;
        }
        
        return (day.getDayOfMonth() / DAYS_IN_WEEK) + 1 == ordinalWeek;
    }
    
    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        
        return mapper.writeValueAsString(this);
    }
}
