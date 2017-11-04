package be.kdg.Simulator.ServiceCallers;

import be.kdg.Simulator.Domain.Section;
import be.kdg.se3.services.railway.InfrastructureServiceProxy;
import org.json.JSONObject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SectionLoader {
    private InfrastructureServiceProxy ISP;
    private String url = "www.services4se3.com/railway/infrastructure/sections/";

    public void start(int sectionId, int speed) throws Exception {
        final Runnable sectionRun = new Runnable() {
            public void run() {
                Section section = new Section(sectionId,speed);
                try {
                    final JSONObject obj = new JSONObject(ISP.get(url+sectionId));
                    if(obj.getBoolean("singleDirection")){
                        section.setInfoSection(obj.getInt("numberOfBlocks"),obj.getInt("blockLength"),obj.getString("crossings"), obj.getBoolean("singleDirection"));
                    } else{
                        section.setInfoSection(obj.getInt("numberOfBlocks"),obj.getInt("blockLength"),obj.getString("crossings"), obj.getBoolean("singleDirection"),obj.getInt("highBlocknumberSectionIds"));
                    }
                    System.out.print("section loaded : " + section.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(sectionRun,0, TimeUnit.MILLISECONDS);
    }

}
