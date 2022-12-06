import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Getter
@Setter
public class GeographicRegion extends PanacheEntity {

    private String name;
    private String region;
    private String subRegion;
    private String flag;
    private String iso2;
    private String iso3;
    private String currency;
    @ManyToOne
    @JoinColumn(name = "parent_region_id")
    @JsonBackReference
    private GeographicRegion parentRegion;


    public static GeographicRegion findByName(String name){
        return find("name", name).firstResult();
    }

}
