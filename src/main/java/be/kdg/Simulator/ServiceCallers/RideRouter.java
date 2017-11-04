package be.kdg.Simulator.ServiceCallers;


import be.kdg.Simulator.Domain.Ride;
import be.kdg.Simulator.Domain.Section;
import be.kdg.se3.services.railway.RouteServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONArray;
import org.json.JSONObject;


public class RideRouter {
    private Logger logger = LoggerFactory.getLogger(RideRouter.class);
    private String url = "www.services4se3.com/railway/route/";
    private RouteServiceProxy RSP = new RouteServiceProxy();

    public void getRoute(Ride ride) {
        try{
            final JSONObject obj = new JSONObject(RSP.get(url+ride.getRideId()));
            System.out.println("RSP.get: " + RSP.get(url+ride.getRideId()));
            final JSONArray routeSections = obj.getJSONArray("routeSections");
            for(int i=0;i<routeSections.length();i++){
                Section section = new Section(routeSections.getJSONObject(i).getInt("section"), routeSections.getJSONObject(i).getInt("speed"));
                ride.addSection(section);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*private ScheduledExecutorService executorService = null;
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
