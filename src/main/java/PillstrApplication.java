import com.hubspot.dropwizard.guice.GuiceBundle;
import com.hubspot.rosetta.jdbi.RosettaMapperFactory;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import resources.PrescriptionResource;
import resources.RemindersResource;
import resources.TestResource;
import resources.UserResource;

/**
 * Created by keegan on 4/17/15.
 */
public class PillstrApplication extends Application<PillstrConfiguration> {

    private GuiceBundle guiceBundle;
    private PillstrModule pillstrModule;

    public static void main(String[] args) throws Exception {
        new PillstrApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<PillstrConfiguration> bootstrap) {
        pillstrModule = new PillstrModule();
        guiceBundle = GuiceBundle.<PillstrConfiguration>newBuilder()
                .addModule(pillstrModule)
                .setConfigClass(PillstrConfiguration.class)
                .build();

        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(PillstrConfiguration pillstrConfiguration, Environment environment) throws Exception {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, pillstrConfiguration.getDataSourceFactory(), "mysql");
        jdbi.registerMapper(new RosettaMapperFactory());

        pillstrModule.setJdbi(jdbi);

        registerResources(environment, TestResource.class, UserResource.class, PrescriptionResource.class, RemindersResource.class);
    }

    private void registerResources(Environment environment, Class... resourceClasses) {
        for (Class resourceClass : resourceClasses) {
            environment.jersey().register(guiceBundle.getInjector().getInstance(resourceClass));
        }
    }
}
