package be.kdg.Simulator.DomainLoader;

import be.kdg.Simulator.Domain.Section;
import be.kdg.se3.services.railway.InfrastructureServiceProxy;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SectionLoader {
    private Logger logger = LoggerFactory.getLogger(SectionLoader.class);
    private final String url = "www.services4se3.com/railway/infrastructure/sections/";
    private final InfrastructureServiceProxy ISP = new InfrastructureServiceProxy();

    //Function: makes call to InfrastructureServiceProxy (ISP) to load the sectionInfo for the given Section
    public void getSectionInfo(Section section) {
        try{
            String replay =ISP.get(url+section.getSectionId());
            //TODO delete sout
            //System.out.println("replay: " + replay);

            final JSONObject obj = new JSONObject(replay);
            int numberOfBlocks = obj.getInt("numberOfBlocks");
            int blockLength = obj.getInt("blockLength");
            String crossings="";
            if(obj.toString().contains("crossing")){
                crossings=obj.getString("crossings");
            }
            boolean singleDirection =obj.getBoolean("singleDirection");
            String highBlocknumberSectionId="";
            if(!singleDirection){
                highBlocknumberSectionId=obj.getString("highBlocknumberSectionId");
            }
            section.setInfoSection(numberOfBlocks,blockLength,crossings,singleDirection,highBlocknumberSectionId);
            loadBlocks(section);

        }catch(Exception e){
            //TODO: catch IOException (test with sectionId 11)
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadBlocks(Section section){
        new BlockLoader().loadBlocks(section);
    }

    //old code
    {
       /* private ScheduledExecutorService executorService = null;
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
    }*/

    }
}
