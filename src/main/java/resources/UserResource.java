package resources;

import data.UserDAO;
import logic.RemindersHandler;
import models.Reminder;
import models.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by keegan on 4/20/15.
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private UserDAO userDAO;
    private RemindersHandler remindersHandler;

    @Inject
    public UserResource(UserDAO userDAO, RemindersHandler remindersHandler) {
        this.userDAO = userDAO;
        this.remindersHandler = remindersHandler;
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
    public int post(@QueryParam("name") String name, @QueryParam("username") String username, @QueryParam("password") String password, @QueryParam("email") String email, @QueryParam("phone") int phone) {
        return userDAO.insert(name, username, password, email, phone);
    }

    @GET
    @Path("/-/by-name/{username}")
    public User get(@PathParam("username") String username) {
        return userDAO.getByUsername(username);
    }

    @GET
    @Path("/-/check-password/{username}/{password}")
    public boolean checkPassword(@PathParam("username") String username, @PathParam("password") String password) {
        Optional<User> user = Optional.of(userDAO.getByUsername(username));
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    @GET
    @Path("/{userId}/all-reminders-for-entire-week/{year}/{month}/{date}")
    public List<Reminder> getAllReminders(@PathParam("userId") int userId, @PathParam("year") int year, @PathParam("month") int month, @PathParam("date") int date) {
        return remindersHandler.getAllRemindersByUserId(userId, year, month, date);
    }
}
