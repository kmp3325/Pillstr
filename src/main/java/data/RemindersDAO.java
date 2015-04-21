package data;

import com.hubspot.rosetta.jdbi.RosettaMapperFactory;
import models.Reminder;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;

import java.util.List;

/**
 * Created by keegan on 4/20/15.
 */
@RegisterMapperFactory(RosettaMapperFactory.class)
public interface RemindersDAO {

    @SqlQuery("SELECT * FROM reminders")
    public List<Reminder> getAll();

    @SqlQuery("SELECT * FROM reminders WHERE id = :id")
    public Reminder get(@Bind("id") int id);

    @SqlUpdate("DELETE FROM reminders WHERE id = :id")
    public void delete(@Bind("id") int id);

    @SqlUpdate("INSERT INTO reminders (prescriptionId, day, hour, minute, dosage) VALUES (:prescriptionId, :day, :hour, :minute, :dosage)")
    @GetGeneratedKeys
    public int insert(@Bind("prescriptionId") int prescriptionId, @Bind("day") int day, @Bind("hour") int hour, @Bind("minute") int minute, @Bind("dosage") double dosage);
}
