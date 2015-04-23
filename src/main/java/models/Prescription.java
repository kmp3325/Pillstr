package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
 * Created by keegan on 4/20/15.
 */
public class Prescription {

    private int id;
    private String name;
    private int userId;
    private String displayName;
    private double quantity;
    private String notes;
    private int day;
    private int hour;
    private int minute;
    private double dosage;
    private boolean remind;

    @JsonCreator
    public Prescription(@JsonProperty("id") int id,
                        @JsonProperty("name") String name,
                        @JsonProperty("userId") int userId,
                        @JsonProperty("displayName") String displayName,
                        @JsonProperty("quantity") double quantity,
                        @JsonProperty("notes") String notes,
                        @JsonProperty("day") int day,
                        @JsonProperty("hour") int hour,
                        @JsonProperty("minute") int minute,
                        @JsonProperty("dosage") double dosage,
                        @JsonProperty("remind") boolean remind) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.displayName = displayName;
        this.quantity = quantity;
        this.notes = notes;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.dosage = dosage;
        this.remind = remind;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getNotes() {
        return notes;
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

    public double getDosage() {
        return dosage;
    }

    public boolean getRemind() {
        return remind;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("userId", userId)
                .add("displayName", displayName)
                .add("quantity", quantity)
                .add("notes", notes)
                .add("day", day)
                .add("hour", hour)
                .add("minute", minute)
                .add("dosage", dosage)
                .add("remind", remind)
                .toString();
    }
}
