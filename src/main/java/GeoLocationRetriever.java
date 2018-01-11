import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PlaceDetailsRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.GeocodedWaypoint;
import com.google.maps.model.PlaceDetails;

import java.io.IOException;

public class GeoLocationRetriever {

    public static void main(String[] args) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyCNSozFeMak9XuWCijSWOqKAVDJDVinHNg")
                .build();
        DirectionsResult results = null;
        try {
            results = DirectionsApi.getDirections(context,"40.772331,-73.946743","40.760758,-73.97567").await();
            GeocodedWaypoint geocodedWaypoint[]= results.geocodedWaypoints;
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        PlaceDetailsRequest placeDetailsRequest = new PlaceDetailsRequest(context);
        try {
            PlaceDetails placeDetails;
                    placeDetails = placeDetailsRequest.placeId(results.geocodedWaypoints[0].placeId).await();

            System.out.println(gson.toJson(placeDetails));
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        System.out.println(gson.toJson(results));
    }
}
