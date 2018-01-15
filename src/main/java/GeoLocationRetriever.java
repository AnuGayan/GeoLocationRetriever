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
        splitIntoSteps(context, "40.772331,-73.946743","40.760758,-73.97567");
        //persist origin and destination here
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//        PlaceDetailsRequest placeDetailsRequest = new PlaceDetailsRequest(context);
//        try {
//            PlaceDetails placeDetails;
//                    placeDetails = placeDetailsRequest.placeId(results.geocodedWaypoints[0].placeId).await();
//
//            System.out.println(gson.toJson(placeDetails));
//        } catch (ApiException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        System.out.println(gson.toJson(results));
    }

    public static void splitIntoSteps(GeoApiContext context, String origin, String destination) {
        try {
            DirectionsResult results = null;
            results = DirectionsApi.getDirections(context, origin, destination).await();
//            results.routes[0].legs[0].steps
            DirectionsRoute[] directionsRoutes = results.routes;
            if (directionsRoutes.length > 0){
                DirectionsLeg[] directionsLeg = directionsRoutes[0].legs;
                if (directionsLeg.length > 0){
                    DirectionsStep[] directionsSteps = directionsLeg[0].steps;
                    long totalDuration = 0;
                    for (int i=0; i < directionsSteps.length ; i++) {
                        totalDuration = totalDuration + directionsSteps[i].duration.inSeconds;
                    }
                    for (int i=0; i < directionsSteps.length ; i++) {
                        if (i < directionsSteps.length - 1) {
                            //start time is calculated by pro-rating trip time according to the step duration
                            //persist directionsSteps[i].endLocation
                        }


                    }
                }
            }
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
