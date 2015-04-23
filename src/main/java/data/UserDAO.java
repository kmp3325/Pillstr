package data;

import com.hubspot.rosetta.jdbi.RosettaMapperFactory;
import models.User;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;

/**
 * Created by keegan on 4/20/15.
 */
@RegisterMapperFactory(RosettaMapperFactory.class)
public interface UserDAO {

    @SqlQuery("SELECT * FROM users")
    public List<User> getAll();

    @SqlUpdate("INSERT INTO users (name, password) VALUES (:name, :password)")
    @GetGeneratedKeys
    public int insert(@Bind("name") String name, @Bind("password") String password);

    @SqlQuery("SELECT * FROM users WHERE id = :id LIMIT 1")
    public User get(@Bind("id") int id);

    @SqlUpdate("DELETE FROM users WHERE id = :id LIMIT 1")
    public void delete(@Bind("id") int id);

    @SqlQuery("SELECT * FROM users WHERE name = :name")
    public User get(@Bind("name") String name);
}
