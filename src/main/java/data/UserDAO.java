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

    @SqlUpdate("INSERT INTO users (name, username, password, email, phone) VALUES (:name, :username, :password, :email, :phone)")
    @GetGeneratedKeys
    public int insert(@Bind("name") String name, @Bind("username") String username, @Bind("password") String password, @Bind("email") String email, @Bind("phone") int phone);

    @SqlQuery("SELECT * FROM users WHERE id = :id LIMIT 1")
    public User get(@Bind("id") int id);

    @SqlUpdate("DELETE FROM users WHERE id = :id LIMIT 1")
    public void delete(@Bind("id") int id);

    @SqlQuery("SELECT * FROM users WHERE username = :username")
    public User getByUsername(@Bind("username") String username);

    @SqlUpdate("UPDATE users SET name = :name WHERE id = :id")
    void setName(int id, String name);

    @SqlUpdate("UPDATE users SET email = :email WHERE id = :id")
    void setEmail(int id, String email);

    @SqlUpdate("UPDATE users SET password = :password WHERE id = :id")
    void setPassword(int id, String password);

    @SqlUpdate("UPDATE users SET username = :username WHERE id = :id")
    void setUsername(int id, String username);

    @SqlUpdate("UPDATE users SET phone = :phone WHERE id = :id")
    void setPhone(int id, int phone);
}
