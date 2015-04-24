package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
 * Created by keegan on 4/23/15.
 */
public class PillEvent {

    private int id;
    private int prescriptionId;
    private int day;
    private int hour;
    private int minute;

    public PillEvent(@JsonProperty("id") int id,
                     @JsonProperty("prescriptionId") int prescriptionId,
                     @JsonProperty("day") int day,
                     @JsonProperty("hour") int hour,
                     @JsonProperty("minute") int minute) {
        this.id = id;
        this.prescriptionId = prescriptionId;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public int getId() {
        return id;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("prescriptionId", prescriptionId)
                .add("day", day)
                .add("hour", hour)
                .add("minute", minute)
                .toString();
    }
}
