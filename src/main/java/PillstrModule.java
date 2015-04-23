import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import data.PillEventDAO;
import data.PrescriptionDAO;
import data.RemindersDAO;
import data.UserDAO;
import org.skife.jdbi.v2.DBI;

/**
 * Created by keegan on 4/18/15.
 */
public class PillstrModule implements Module {

    private DBI jdbi;

    @Override
    public void configure(Binder binder) {

    }

    public void setJdbi(DBI jdbi) {
        this.jdbi = jdbi;
    }

    @Provides
    @Named("test")
    public String providesTest(PillstrConfiguration pillstrConfiguration) {
        return pillstrConfiguration.getTest();
    }

    @Provides
    public UserDAO providesUserDAO() {
        return jdbi.onDemand(UserDAO.class);
    }

    @Provides
    public PrescriptionDAO providesPrescriptionDAO() {
        return jdbi.onDemand(PrescriptionDAO.class);
    }

    @Provides
    public RemindersDAO providesRemindersDAO() {
        return jdbi.onDemand(RemindersDAO.class);
    }

    @Provides
    public PillEventDAO providesPillEventDAO() {
        return jdbi.onDemand(PillEventDAO.class);
    }

}
