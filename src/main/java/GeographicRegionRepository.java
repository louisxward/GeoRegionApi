import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class GeographicRegionRepository  implements PanacheRepository<GeographicRegion> {

    public GeographicRegion findByName(String name){
        return find("name", name).firstResult();
    }

}
