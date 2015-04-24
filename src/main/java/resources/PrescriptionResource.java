package resources;

import com.google.common.base.Optional;
import data.PrescriptionDAO;
import logic.RemindersHandler;
import models.Prescription;
import models.Reminder;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by keegan on 4/20/15.
 */
@Path("/prescriptions")
@Produces(MediaType.APPLICATION_JSON)
public class PrescriptionResource {

    private PrescriptionDAO prescriptionDAO;
    private RemindersHandler remindersHandler;

    @Inject
    public PrescriptionResource(PrescriptionDAO prescriptionDAO, RemindersHandler remindersHandler) {
        this.prescriptionDAO = prescriptionDAO;
        this.remindersHandler = remindersHandler;
    }

    @GET
    public List<Prescription> getAll() {
        return prescriptionDAO.getAll();
    }

    @GET
    @Path("/{id}")
    public Prescription get(@PathParam("id") int id) {
        return prescriptionDAO.get(id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") int id) {
        prescriptionDAO.delete(id);
    }

    @POST
    public int insert(@QueryParam("name") String name, @QueryParam("userId") int userId, @QueryParam("displayName") String displayName, @QueryParam("quantity") double quantity, @QueryParam("notes") String notes, @QueryParam("dosage") double dosage, @QueryParam("remind") boolean remind) {
        return prescriptionDAO.insert(name, userId, displayName, quantity, notes, dosage, remind);
    }

    @GET
    @Path("/-/by-userId/{userId}")
    public List<Prescription> getByUserId(@PathParam("userId") int userId, @QueryParam("remind") Optional<Boolean> remind) {
        if (remind.isPresent()) {
            return prescriptionDAO.getByUserIdAndRemind(userId, remind.get());
        }
        return prescriptionDAO.getByUserId(userId);
    }

    @DELETE
    @Path("/-/by-userId/{userId}")
    public void deleteByUserId(@PathParam("userId") int userId) {
        prescriptionDAO.deleteByUserId(userId);
    }

    @PUT
    @Path("/{id}")
    public void put(@PathParam("id") int id,
                    @QueryParam("name") Optional<String> name,
                    @QueryParam("userId") Optional<Integer> userId,
                    @QueryParam("displayName") Optional<String> displayName,
                    @QueryParam("quantity") Optional<Integer> quantity,
                    @QueryParam("notes") Optional<String> notes,
                    @QueryParam("dosage") Optional<Double> dosage,
                    @QueryParam("remind") Optional<Boolean> remind) {

        if (name.isPresent()) prescriptionDAO.setName(id, name.get());
        if (userId.isPresent()) prescriptionDAO.setUserId(id, userId.get());
        if (displayName.isPresent()) prescriptionDAO.setDisplayName(id, displayName.get());
        if (quantity.isPresent()) prescriptionDAO.setQuantity(id, quantity.get());
        if (notes.isPresent()) prescriptionDAO.setNotes(id, notes.get());
        if (dosage.isPresent()) prescriptionDAO.setDosage(id, dosage.get());
        if (remind.isPresent()) prescriptionDAO.setRemind(id, remind.get());
    }

    @GET
    @Path("/{id}/reminders-for-date/{time}")
    public List<Reminder> getByTime(@PathParam("id") int id, @PathParam("time") long time) {
        return remindersHandler.generateReminders(id, time);
    }

    @DELETE
    @Path("/{id}/reminders-past-time/{time}")
    public void deletePastTime(@PathParam("id") int id, @PathParam("time") long time) {
        remindersHandler.deletePastTime(id, time);
    }
}
