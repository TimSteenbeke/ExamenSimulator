package be.kdg.Simulator.ServiceCallers;

import be.kdg.Simulator.Domain.Ride;
import be.kdg.Simulator.Domain.Section;
import be.kdg.se3.services.railway.InfrastructureServiceProxy;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SectionLoader {
    private ScheduledExecutorService executorService = null;
    private InfrastructureServiceProxy ISP = new InfrastructureServiceProxy();
    private String url = "www.services4se3.com/railway/infrastructure/sections/";
    private final List<Section> sectionList = new ArrayList<>();
    private Ride ride;

    public void start() throws Exception {
        executorService=Executors.newScheduledThreadPool(sectionList.size());
        sectionList.forEach(section->
                executorService.scheduleAtFixedRate(
                        new Runnable() {
                            public void run() {
                                doSection(section);
                            }
                        },0,1000,TimeUnit.MILLISECONDS));
        System.out.println("ride: "+ this.ride.toString());

    }

    private void doSection(Section section){
        try {
            final JSONObject obj = new JSONObject(ISP.get(url+section.getSectionId()));
            int numberOfBlocks = obj.getInt("numberOfBlocks");
            int blockLength = obj.getInt("blockLength");
            String crossings=null;
            if(obj.toString().contains("crossing")){crossings=obj.getString("crossings");}
            boolean singleDirection =obj.getBoolean("singleDirection");
            String highBlocknumberSectionId=null;
            if(!singleDirection){
                highBlocknumberSectionId=obj.getString("highBlocknumberSectionId");
            }
            section.setInfoSection(numberOfBlocks,blockLength,crossings,singleDirection,highBlocknumberSectionId);
            this.ride.addSection(section);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addSection(Section section){
        this.sectionList.add(section);
    }

    public void addRide(Ride ride) {
        this.ride=ride;
    }

    public Ride getRide() {
        return this.ride;
    }
}
