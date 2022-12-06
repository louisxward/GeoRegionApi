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


@QuarkusTest
public class GeographicRegionImport {

        @Inject
        private GeographicRegionRepository geographicRegionRepository;

        @Test
        @Transactional
        public void createRegions(){
                for(GeographicRegion region : geographicRegionRepository.listAll()){
                        System.out.println("---------");
                        System.out.println(region.getName());
                        GeographicRegion parentRegion = geographicRegionRepository.findByName(region.getRegion());
                        GeographicRegion parentSubRegion = geographicRegionRepository.findByName(region.getSubRegion());
                        if(region.getRegion() != null && parentRegion == null){
                                System.out.println("Create Parent Region");
                                parentRegion = new GeographicRegion();
                                parentRegion.setName(region.getRegion());
                                geographicRegionRepository.persist(parentRegion);
                        }
                        if(region.getSubRegion() != null && parentSubRegion == null){
                                System.out.println("Create Sub Region");
                                parentSubRegion = new GeographicRegion();
                                parentSubRegion.setName(region.getSubRegion());
                                parentSubRegion.setParentRegion(parentRegion);
                                geographicRegionRepository.persist(parentSubRegion);
                        }
                        if(parentSubRegion != null){
                                System.out.println("Set Region to Sub Region");
                                region.setParentRegion(parentSubRegion);
                                geographicRegionRepository.persist(parentSubRegion);
                        }
                        else if(parentRegion != null){
                                System.out.println("Set Region to Main Region");
                                region.setParentRegion(parentRegion);
                        }
                        geographicRegionRepository.persist(region);
                }
        }

        @Test
        @Transactional
        public void addOne() {
                GeographicRegion geographicRegion = new GeographicRegion();
                geographicRegion.setName("Oceania");
                geographicRegion.setRegion("Oceania");
                geographicRegionRepository.persist(geographicRegion);
        }

        @Test
        @Transactional
        public void callRegionApi() throws IOException, InterruptedException {
                var client = HttpClient.newHttpClient();
                var request = HttpRequest.newBuilder(
                                URI.create("https://restcountries.com/v3.1/all"))
                        .header("accept", "application/json")
                        .build();
                var response = client.send(request, HttpResponse.BodyHandlers.ofString());
                JSONArray arr = new JSONArray(response.body());
                for (int x = 0; x < arr.length(); x++) {
                        GeographicRegion geographicRegion = new GeographicRegion();
                        JSONObject obj = arr.getJSONObject(x);
                        geographicRegion.setName(obj.getJSONObject("name").getString("common"));
                        System.out.println(geographicRegion.getName());
                        geographicRegion.setIso2(obj.getString("cca2"));
                        geographicRegion.setIso3(obj.getString("cca3"));
                        geographicRegion.setRegion(obj.getString("region"));
                        try{
                                String subRegion = obj.getString("subregion");
                                geographicRegion.setSubRegion(subRegion);
                        } catch (Exception e){
                                System.out.println("No Sub Region");
                        }
                        geographicRegion.setFlag(obj.getJSONObject("flags").getString("png"));
                        try{
                                String currency = (String) obj.getJSONObject("currencies").names().get(0);
                                geographicRegion.setCurrency(currency);
                        } catch (Exception e){
                                System.out.println("No Currency");
                        }
                        geographicRegionRepository.persist(geographicRegion);
                }
        }
}

