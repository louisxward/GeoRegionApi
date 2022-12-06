import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    public List<GeographicRegion> list() {
        return GeographicRegion.listAll();
    }

    public GeographicRegion findByName(String name) {
        return GeographicRegion.findByName(name);
    }

}
