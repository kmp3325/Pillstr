package resources;

import com.google.common.base.Optional;
import data.PillEventDAO;
import models.PillEvent;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by keegan on 4/23/15.
 */
@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
public class PillEventResource {

    private PillEventDAO pillEventDAO;

    @Inject
    public PillEventResource(PillEventDAO pillEventDAO) {
        this.pillEventDAO = pillEventDAO;
    }

    @GET
    public List<PillEvent> getAll() {
        return pillEventDAO.getAll();
    }

    @GET
    @Path("/{id}")
    public PillEvent get(@PathParam("id") int id) {
        return pillEventDAO.get(id);
    }

    @POST
    public int post(@QueryParam("prescriptionId") int prescriptionId, @QueryParam("day") int day, @QueryParam("hour") int hour, @QueryParam("minute") int minute) {
        return pillEventDAO.insert(prescriptionId, day, hour, minute);
    }

    @PUT
    @Path("/{id}")
    public void put(@PathParam("id") int id,
                    @QueryParam("day") Optional<Integer> day,
                    @QueryParam("hour") Optional<Integer> hour,
                    @QueryParam("minute") Optional<Integer> minute) {
        if (day.isPresent()) pillEventDAO.setDay(id, day.get());
        if (hour.isPresent()) pillEventDAO.setHour(id, hour.get());
        if (minute.isPresent()) pillEventDAO.setMinute(id, minute.get());
    }

    @GET
    @Path("/-/by-prescriptionId/{prescriptionId}")
    public List<PillEvent> getByPrescriptionId(@PathParam("prescriptionId") int prescriptionId) {
        return pillEventDAO.getAllByPrescriptionId(prescriptionId);
    }
}
