package be.kdg.Simulator.ServiceCallers;

import be.kdg.Simulator.Domain.Ride;
import be.kdg.se3.services.railway.RouteServiceProxy;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

public class RideRouter {
    private RouteServiceProxy RSP = new RouteServiceProxy();
    private String url = "www.services4se3.com/railway/route/";

    public void start(int rideId) throws Exception {
        final Runnable rideRuner = new Runnable() {
            public void run() {
                    try {
                        Ride ride = new Ride(rideId);
                        System.out.println("ride created: "+ ride.toString());
                        String test = RSP.get(url+rideId);
                        System.out.println("json string: " + test);

                        final JSONObject obj = new JSONObject(RSP.get(url+rideId));
                        final JSONArray routeSections = obj.getJSONArray("routeSections");
                        System.out.println("JSON object: " + obj.toString());
                        for(int i=0;i<routeSections.length();i++) {
                            new SectionLoader().start(routeSections.getJSONObject(i).getInt("section"), routeSections.getJSONObject(i).getInt("speed"));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

            }
        };
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(rideRuner,0,TimeUnit.MILLISECONDS);
    }

    public void stop(){

    }


}
