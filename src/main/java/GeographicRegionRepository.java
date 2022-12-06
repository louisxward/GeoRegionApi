import io.quarkus.hibernate.orm.panache.PanacheRepository;


import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.List;

//@NamedQueries({
//        @NamedQuery(name = "GeographicRegionRepository.findTreeById", query = "select * from geographicRegion"),
//        @NamedQuery(name = "GeographicRegionRepository.findByName2", query = "from Person where name = ?1")
//})

@ApplicationScoped
public class GeographicRegionRepository  implements PanacheRepository<GeographicRegion> {

    public GeographicRegion findByName(String name){
        return find("name", name).firstResult();
    }

//    public List<GeographicRegion> findTreeById(Long id){
//        return list("#GeographicRegionRepository.findTreeById", id);
//    }
//
//    public GeographicRegion findByName2(String name){
//        return find("#GeographicRegionRepository.findByName2", name).firstResult();
//    }

}
