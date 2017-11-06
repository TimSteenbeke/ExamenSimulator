package be.kdg.Simulator.DomainLoader;

import be.kdg.Simulator.Domain.Section;
import be.kdg.se3.services.railway.InfrastructureServiceProxy;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SectionLoader {
    private final String url = "www.services4se3.com/railway/infrastructure/sections/";
    private final InfrastructureServiceProxy ISP = new InfrastructureServiceProxy();
    private Logger logger = LoggerFactory.getLogger(SectionLoader.class);

    //makes call to InfrastructureServiceProxy (ISP) to load the sectionInfo for the given Section
    public void getSectionInfo(Section section) {
        String replay = null;
        try {
            replay = ISP.get(url + section.getSectionId());
        } catch (IOException e) {
            logger.error("IOException" + e.getLocalizedMessage());
        }
        if(!replay.isEmpty()) {
            try {
                final JSONObject obj = new JSONObject(replay);
                int numberOfBlocks = obj.getInt("numberOfBlocks");
                int blockLength = obj.getInt("blockLength");
                String crossings = "";
                if (obj.toString().contains("crossing")) {
                    crossings = obj.getString("crossings");
                }
                boolean singleDirection = obj.getBoolean("singleDirection");
                String highBlocknumberSectionId = "";
                if (!singleDirection) {
                    highBlocknumberSectionId = obj.getString("highBlocknumberSectionId");
                }
                section.setInfoSection(numberOfBlocks, blockLength, crossings, singleDirection, highBlocknumberSectionId);
            } catch (JSONException e) {
                logger.error("JSONException " + e.getLocalizedMessage());
            }
            logger.info("Section: " + section.getSectionId() + " loaded with info");
            loadBlocks(section);
        }
    }

    private void loadBlocks(Section section) {
        new BlockLoader().loadBlocks(section);
    }
}
