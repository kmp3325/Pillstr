package data;

import com.hubspot.rosetta.jdbi.RosettaMapperFactory;
import models.Prescription;
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
public interface PrescriptionDAO {

    @SqlQuery("SELECT * FROM prescriptions")
    public List<Prescription> getAll();

    @SqlQuery("SELECT * FROM prescriptions WHERE id = :id")
    public Prescription get(@Bind("id") int id);

    @SqlUpdate("DELETE FROM prescriptions WHERE id = :id")
    public void delete(@Bind("id") int id);

    @SqlUpdate("INSERT INTO prescriptions (name, userId, displayName, quantity, notes) VALUES (:name, :userId, :displayName, :quantity, :notes)")
    @GetGeneratedKeys
    public int insert(@Bind("name") String name, @Bind("userId") int userId, @Bind("displayName") String displayName, @Bind("quantity") double quantity, @Bind("notes") String notes);
}
