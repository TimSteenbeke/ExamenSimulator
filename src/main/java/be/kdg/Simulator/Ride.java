package be.kdg.Simulator;

import java.util.ArrayList;
import java.util.List;

public class Ride {
    private int rideId;
    private List<Section> rideSections = new ArrayList<>();

    public Ride(int rideId) {
        this.rideId = rideId;
    }

    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    public List<Section> getRideSections() {
        return rideSections;
    }

    public void setRideSections(List<Section> rideSections) {
        this.rideSections = rideSections;
    }
}
