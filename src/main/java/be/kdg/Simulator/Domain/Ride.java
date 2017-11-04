package be.kdg.Simulator.Domain;

import java.util.ArrayList;
import java.util.List;

public class Ride {
    private int rideId;
    private List<Section> rideSections = new ArrayList<>();

    public Ride(int rideId) {
        this.rideId = rideId;
    }


    @Override
    public String toString() {
        return "Ride{" +
                "rideId=" + rideId +
                ", rideSections=" + rideSections +
                '}';
    }
}
