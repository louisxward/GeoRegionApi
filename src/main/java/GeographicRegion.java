
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class GeographicRegion{
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    private String region;
    private String subRegion;
    private String flag;
    private String iso2;
    private String iso3;
    private String currency;
    @ManyToOne
    @JoinColumn(name = "parent_region_id")
    private GeographicRegion parentRegion;
}
