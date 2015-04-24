package data;

import com.hubspot.rosetta.jdbi.RosettaMapperFactory;
import models.PillEvent;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;

import java.util.List;

/**
 * Created by keegan on 4/23/15.
 */
@RegisterMapperFactory(RosettaMapperFactory.class)
public interface PillEventDAO {

    @SqlQuery("SELECT * FROM events")
    public List<PillEvent> getAll();

    @SqlQuery("SELECT * FROM events WHERE id = :id LIMIT 1")
    public PillEvent get(@Bind("id") int id);

    @SqlUpdate("INSERT INTO events (prescriptionId, day, hour, minute) VALUES (:prescriptionId, :day, :hour, :minute)")
    @GetGeneratedKeys
    public int insert(@Bind("prescriptionId") int prescriptionId, @Bind("day") int day, @Bind("hour") int hour, @Bind("minute") int minute);

    @SqlUpdate("UPDATE events SET day = :day WHERE id = :id")
    public void setDay(@Bind("id") int id, @Bind("day") int day);

    @SqlUpdate("UPDATE events SET hour = :hour WHERE id = :id")
    public void setHour(@Bind("id") int id, @Bind("hour") int hour);

    @SqlUpdate("UPDATE events SET minute = :minute WHERE id = :id")
    public void setMinute(@Bind("id") int id, @Bind("minute") int minute);

    @SqlQuery("SELECT * FROM events WHERE prescriptionId = :prescriptionId")
    public List<PillEvent> getAllByPrescriptionId(@Bind("prescriptionId") int prescriptionId);

    @SqlQuery("SELECT * FROM events WHERE prescriptionId = :prescriptionId AND day = :day")
    public List<PillEvent> getAllByPrescriptionIdAndDay(@Bind("prescriptionId") int prescriptionId, @Bind("day") int day);

    @SqlUpdate("DELETE FROM events WHERE id = :id LIMIT 1")
    public void delete(@Bind("id") int id);

    @SqlUpdate("DELETE FROM events WHERE prescriptionId = :prescriptionId")
    public void deleteByPrescriptionId(@Bind("prescriptionId") int prescriptionId);
}
