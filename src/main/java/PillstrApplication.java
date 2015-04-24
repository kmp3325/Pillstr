import com.hubspot.dropwizard.guice.GuiceBundle;
import com.hubspot.rosetta.jdbi.RosettaMapperFactory;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.skife.jdbi.v2.DBI;
import resources.*;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

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

        registerResources(environment, TestResource.class, UserResource.class, PrescriptionResource.class, RemindersResource.class, PillEventResource.class);
        // Enable CORS headers
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

    private void registerResources(Environment environment, Class... resourceClasses) {
        for (Class resourceClass : resourceClasses) {
            environment.jersey().register(guiceBundle.getInjector().getInstance(resourceClass));
        }
    }
}
