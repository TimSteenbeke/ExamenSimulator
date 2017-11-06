import be.kdg.Simulator.TrainRideSimulator;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class RideTest {
    private final Map<Integer,Integer> trainRides = new HashMap<>();


    @Test
    public void doTestrun() throws Exception {
        TrainRideSimulator rs = new TrainRideSimulator();
        trainRides.put(1,1000);
        //trainRides.put(2,2000);
        //trainRides.put(3,5000);
        //trainRides.put(4,10000);
        //trainRides.put(5,7000);
        //trainRides.put(13,12000);
        trainRides.keySet().forEach(rideId-> {
            rs.addRide(rideId, trainRides.get(rideId));
        });
        rs.start();
        //TODO fix thread running
        while (rs.isExecuting()) {
            rs.stop();
        }
        assertTrue("it should reach this point without crashing", true);

    }
}
