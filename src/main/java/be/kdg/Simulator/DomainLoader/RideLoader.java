package be.kdg.Simulator.DomainLoader;


import be.kdg.Simulator.Domain.Ride;
import be.kdg.Simulator.Domain.Section;
import be.kdg.se3.services.railway.RouteServiceProxy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class RideLoader {
    private final String url = "www.services4se3.com/railway/route/";
    private final RouteServiceProxy RSP = new RouteServiceProxy(); //TODO: creat new RSP for each ride ?
    private Logger logger = LoggerFactory.getLogger(RideLoader.class);
    private List<Section> sections = new ArrayList<>();

    //makes call to RouteServiceProxy (RSP) to load the Route for the given Ride
    public void getRoute(Ride ride) {
        String replay = null;
        try {
            replay = RSP.get(url + ride.getRideId());
        } catch (IOException e) {
            logger.error("IOException " + e.getLocalizedMessage());
        }
        logger.info("ride: " + ride.getRideId() + " loaded");
        readReplay(replay);
        sections.forEach(section -> {
            loadSection(section);
            ride.addSection(section);
        });
    }

    //Reads JSON replay, creates sections and adds them to the section list
    private void readReplay(String replay) {
        final JSONObject obj;
        try {
            obj = new JSONObject(replay);
            final JSONArray routeSections = obj.getJSONArray("routeSections");
            for (int i = 0; i < routeSections.length(); i++) {
                int sectionId = routeSections.getJSONObject(i).getInt("section");
                int speed = routeSections.getJSONObject(i).getInt("speed");
                if (speed != 0 && sectionId != 0) {
                    Section section = new Section(sectionId, speed);
                    sections.add(section);
                }
            }
        } catch (JSONException e) {
            logger.error("JSONException " + e.getLocalizedMessage());
        }

    }

    //Get's section info for the given section
    public void loadSection(Section section) {
        new SectionLoader().getSectionInfo(section);
    }
}
