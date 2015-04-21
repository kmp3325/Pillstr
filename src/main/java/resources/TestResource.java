package resources;

import com.google.inject.name.Named;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by keegan on 4/17/15.
 */

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
public class TestResource {

    private String test;

    @Inject
    public TestResource(@Named("test") String test) {
        this.test = test;
    }

    @GET
    public String get() {
        return String.format("200 OK %s", test);
    }

}
