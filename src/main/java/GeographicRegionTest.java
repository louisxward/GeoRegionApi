import io.quarkus.test.junit.QuarkusTest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


@QuarkusTest
public class GeographicRegionTest {

        @Inject
        private GeographicRegionRepository geographicRegionRepository;

        @Test
        public void testFindByName(){
                GeographicRegion result = geographicRegionRepository.findByName("Micronesia");
                System.out.println(result.getId() + " " + result.getName());
        }

        @Test
        public void testFindParentRegionByName(){
                GeographicRegion result = geographicRegionRepository.findParentRegionByName("Micronesia");
                System.out.println(result.getId() + " " + result.getName());
        }
}

