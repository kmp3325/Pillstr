package resources;

import data.UserDAO;
import models.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by keegan on 4/20/15.
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private UserDAO userDAO;

    @Inject
    public UserResource(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GET
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @GET
    @Path("/{id}")
    public User get(@PathParam("id") int id) {
        return userDAO.get(id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") int id) {
        userDAO.delete(id);
    }

    @POST
    public int post(@QueryParam("name") String name, @QueryParam("password") String password) {
        return userDAO.insert(name, password);
    }

    @GET
    @Path("/-/{name}")
    public User get(@PathParam("name") String name) {
        return userDAO.get(name);
    }
}
