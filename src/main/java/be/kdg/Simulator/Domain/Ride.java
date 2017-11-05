package be.kdg.Simulator.Domain;

import java.util.ArrayList;
import java.util.List;

public class Ride {
    private int rideId;
    private int delay;
    private List<Section> rideSections = new ArrayList<>();

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

    public void addSection(Section section) {
        this.rideSections.add(section);
    }

    @Override
    public String toString() {
        return "Ride: { rideId: " + rideId +", Delay: "+ delay + ", rideSections: " + rideSections +'}';
    }

    public List<Section> getRideSections() {
        return rideSections;
    }

    public boolean hasSections() {
        if(!rideSections.isEmpty())
            return true;

        return false;
    }

    public void dropFirstSection() {
        rideSections.remove(0);
    }
}
