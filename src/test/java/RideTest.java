import be.kdg.Simulator.TrainRideSimulator;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class RideTest {
    private final Map<Integer,Integer> trainRides = new HashMap<>();
    TrainRideSimulator rs = new TrainRideSimulator();

    @Test
    public void doTestrun() throws Exception {
        trainRides.put(1,1000);
        //trainRides.put(2,2000);
        //trainRides.put(3,1000);
        trainRides.keySet().forEach(rideId-> {
            rs.addRide(rideId, trainRides.get(rideId));
        });
        rs.start();
        //TODO fix thread running
        Thread.sleep(40000);
        rs.stop();
        assertTrue("it should reach this point without crashing", true);

    }
}
