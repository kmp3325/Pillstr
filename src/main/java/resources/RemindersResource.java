package resources;

import data.RemindersDAO;
import models.Reminder;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by keegan on 4/20/15.
 */
@Path("/reminders")
@Produces(MediaType.APPLICATION_JSON)
public class RemindersResource {

    private RemindersDAO remindersDAO;

    @Inject
    public RemindersResource(RemindersDAO remindersDAO) {
        this.remindersDAO = remindersDAO;
    }

    @GET
    public List<Reminder> getAll() {
        return remindersDAO.getAll();
    }

    @GET
    @Path("/{id}")
    public Reminder get(@PathParam("id") int id) {
        return remindersDAO.get(id);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") int id) {
        remindersDAO.delete(id);
    }

    @POST
    public int post(@QueryParam("prescriptionId") int prescriptionId, @QueryParam("day") int day, @QueryParam("hour") int hour, @QueryParam("minute") int minute, @QueryParam("dosage") double dosage) {
        return remindersDAO.insert(prescriptionId, day, hour, minute, dosage);
    }

}
