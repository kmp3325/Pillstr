package resources;

import com.google.common.base.Optional;
import data.PrescriptionDAO;
import models.Prescription;

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

    @Inject
    public PrescriptionResource(PrescriptionDAO prescriptionDAO) {
        this.prescriptionDAO = prescriptionDAO;
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
    public int insert(@QueryParam("name") String name, @QueryParam("userId") int userId, @QueryParam("displayName") String displayName, @QueryParam("quantity") double quantity, @QueryParam("notes") String notes, @QueryParam("day") int day, @QueryParam("hour") int hour, @QueryParam("minute") int minute, @QueryParam("dosage") double dosage, @QueryParam("remind") boolean remind) {
        return prescriptionDAO.insert(name, userId, displayName, quantity, notes, day, hour, minute, dosage, remind);
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
                    @QueryParam("day") Optional<Integer> day,
                    @QueryParam("hour") Optional<Integer> hour,
                    @QueryParam("minute") Optional<Integer> minute,
                    @QueryParam("dosage") Optional<Double> dosage,
                    @QueryParam("remind") Optional<Boolean> remind) {

        if (name.isPresent()) prescriptionDAO.setName(id, name.get());
        if (userId.isPresent()) prescriptionDAO.setUserId(id, userId.get());
        if (displayName.isPresent()) prescriptionDAO.setDisplayName(id, displayName.get());
        if (quantity.isPresent()) prescriptionDAO.setQuantity(id, quantity.get());
        if (notes.isPresent()) prescriptionDAO.setNotes(id, notes.get());
        if (day.isPresent()) prescriptionDAO.setDay(id, day.get());
        if (hour.isPresent()) prescriptionDAO.setHour(id, hour.get());
        if (minute.isPresent()) prescriptionDAO.setMinute(id, minute.get());
        if (dosage.isPresent()) prescriptionDAO.setDosage(id, dosage.get());
        if (remind.isPresent()) prescriptionDAO.setRemind(id, remind.get());
    }
}
