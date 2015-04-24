package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
 * Created by keegan on 4/20/15.
 */
public class Reminder {

    private int id;
    private int prescriptionId;
    private boolean taken;
    private long time;

    @JsonCreator
    public Reminder(@JsonProperty("id") int id,
                    @JsonProperty("prescriptionId") int prescriptionId,
                    @JsonProperty("taken") boolean taken,
                    @JsonProperty("time") long time) {
        this.id = id;
        this.prescriptionId = prescriptionId;
        this.taken = taken;
        this.time = time;
    }


    public int getId() {
        return id;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public boolean getTaken() {
        return taken;
    }

    public long getTime() {
        return time;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("prescriptionId", prescriptionId)
                .add("taken", taken)
                .add("time", time)
                .toString();
    }
}
