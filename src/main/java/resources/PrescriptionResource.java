package resources;

import data.PrescriptionDAO;
import models.Prescription;

import javax.inject.Inject;
import javax.ws.rs.*;
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
    public int insert(@QueryParam("name") String name, @QueryParam("userId") int userId, @QueryParam("displayName") String displayName, @QueryParam("quantity") double quantity, @QueryParam("notes") String notes) {
        return prescriptionDAO.insert(name, userId, displayName, quantity, notes);
    }

    @GET
    @Path("/-/by-userId/{userId}")
    public List<Prescription> getByUserId(@PathParam("userId") int userId) {
        return prescriptionDAO.getByUserId(userId);
    }

}
