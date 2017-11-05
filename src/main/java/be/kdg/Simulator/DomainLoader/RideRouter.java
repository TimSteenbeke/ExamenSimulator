package be.kdg.Simulator.DomainLoader;


import be.kdg.Simulator.Domain.Ride;
import be.kdg.Simulator.Domain.Section;
import be.kdg.se3.services.railway.RouteServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class RideRouter {
    private Logger logger = LoggerFactory.getLogger(RideRouter.class);
    private final String url = "www.services4se3.com/railway/route/";
    private final RouteServiceProxy RSP = new RouteServiceProxy(); //TODO: creat new RSP for each ride ?
    private List<Section> sections = new ArrayList<>();

    //Function: makes call to RouteServiceProxy (RSP) to load the Route for the given Ride
    public void getRoute(Ride ride) {
        try {
            String replay = RSP.get(url+ride.getRideId());
            logger.info("ride: " + ride.getRideId() + " loaded");
            readReplay(replay);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sections.forEach(section->{
            loadSection(section);
            ride.addSection(section);
        });
    }

    //Reads JSON replay, creates sections and adds them to the section list
    private void readReplay(String replay) {
        try{
        final JSONObject obj = new JSONObject(replay);
        final JSONArray routeSections = obj.getJSONArray("routeSections");
        for (int i = 0; i < routeSections.length(); i++) {
            int sectionId = routeSections.getJSONObject(i).getInt("section");
            int speed = routeSections.getJSONObject(i).getInt("speed");
            if (speed != 0 && sectionId != 0) {
                Section section = new Section(sectionId, speed);
                sections.add(section);
            }
        }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Function: Get's section info for the given section
    public void loadSection(Section section) {
        new SectionLoader().getSectionInfo(section);
    }
    // Old code
    {/*private ScheduledExecutorService executorService = null;
    private RouteServiceProxy RSP = new RouteServiceProxy();
    private SectionLoader sl = null;
    private String url = "www.services4se3.com/railway/route/";
    private final List<Integer> rides= new ArrayList<>();


    public void inputRide(int rideId, int rideDelay){
        //TODO rideDalay verder gebruiken om u ride aan te maken
        this.rides.add(rideId);
    }

    public void start() throws Exception {
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
    }*/

    }
}
