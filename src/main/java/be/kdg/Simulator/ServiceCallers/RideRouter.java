package be.kdg.Simulator.ServiceCallers;

import be.kdg.Simulator.Domain.Ride;
import be.kdg.Simulator.Domain.Section;
import be.kdg.se3.services.railway.RouteServiceProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

public class RideRouter {
    private ScheduledExecutorService executorService = null;
    private RouteServiceProxy RSP = new RouteServiceProxy();
    private SectionLoader sl = null;
    private String url = "www.services4se3.com/railway/route/";
    private final List<Integer> rides= new ArrayList<>();


    public void inputRide(int rideId){
        this.rides.add(rideId);
    }

    public void start() throws Exception {
        stop();
        executorService=Executors.newScheduledThreadPool(rides.size());
        rides.forEach(rideId-> executorService.scheduleAtFixedRate(
                new Runnable() {
                    public void run() {
                        doRide(rideId);
                    }
                },0,100,TimeUnit.MILLISECONDS));
    }

    private void doRide(int rideId){
        try {
            sl=new SectionLoader();
            Ride ride = new Ride(rideId);
            sl.addRide(ride);
            final JSONObject obj = new JSONObject(RSP.get(url+rideId));
            final JSONArray routeSections = obj.getJSONArray("routeSections");
            for(int i=0;i<routeSections.length();i++) {
                sl.addSection(routeSections.getJSONObject(i).getInt("section"), routeSections.getJSONObject(i).getInt("speed"));
            }

            sl.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop(){
//        System.out.println("stop rideRouter");
    }


}
