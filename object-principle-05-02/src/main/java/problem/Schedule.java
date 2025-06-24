package problem;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule {
    
    private static final int DAYS_IN_WEEK = 7;
    
    private String title;
    private LocalTime from;
    private Duration duration;
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
}
