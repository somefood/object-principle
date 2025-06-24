package solution;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ScheduleJson {
    private Schedule schedule;
    private ObjectMapper mapper;
    
    public ScheduleJson(Schedule schedule) {
        this.schedule = schedule;
        this.mapper = initializeMapper();
    }

    private ObjectMapper initializeMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        
        return mapper;
    }

    public String toJson() throws JsonProcessingException {
        return mapper.writeValueAsString(schedule);
    }
}
