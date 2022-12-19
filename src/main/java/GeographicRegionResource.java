import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.inject.Inject;


@Path("/geographicRegions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GeographicRegionResource {
    @Inject
    private GeographicRegionRepository geographicRegionRepository;

    @GET
    @Path("/all")
    public List<GeographicRegion> list() {
        return geographicRegionRepository.listAll();
    }

    @GET
    @Path("/name/{name}")
    public GeographicRegion findByName(@PathParam("name") String name) {
        return geographicRegionRepository.findByName(name);
    }

    @GET
    @Path("/id/{id}")
    public GeographicRegion findById(@PathParam("id") Long id) {
        return geographicRegionRepository.findById(id);
    }

    @GET
    @Path("/findChildrenById/{id}")
    public List<GeographicRegion> findChildrenById(@PathParam("id") Long id) {
        return geographicRegionRepository.list("parent_region_id", id);
    }



}
