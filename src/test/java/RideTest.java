import be.kdg.Simulator.Ride;

import java.util.Arrays;
import java.util.List;

public class RideTest {
    private final List<Integer> rides = Arrays.asList(1,2,3,4,5,6,7);

    public void main(){
        for (int rideId:rides) {
            Ride ride = new Ride(rideId);
        }
    }
}
