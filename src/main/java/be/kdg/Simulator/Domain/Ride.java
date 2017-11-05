package be.kdg.Simulator.Domain;

import java.util.ArrayList;
import java.util.List;

public class Ride {
    private int rideId;
    private int delay;
    private List<Section> sections = new ArrayList<>();

    public Ride(Integer rideId, Integer dealay) {
        this.rideId=rideId;
        this.delay=dealay;
    }

    public int getRideId() {
        return rideId;
    }

    public int getDelay() {
        return delay;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void addSection(Section section) {
        this.sections.add(section);
    }

    public boolean hasSections() {
        if(!sections.isEmpty())
            return true;

        return false;
    }

    public void removeFirstSection() {
        sections.remove(0);
    }
    public void removeSection(Section section){
        sections.remove(section);
    }

    @Override
    public String toString() {
        return "Ride: { rideId: " + rideId +", Delay: "+ delay + ", sections: " + sections +'}';
    }
}
