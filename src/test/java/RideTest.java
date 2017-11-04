import be.kdg.Simulator.RideSimulator;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class RideTest {
    private final Map<Integer,Integer> trainRides = new HashMap<>();
    RideSimulator rs = new RideSimulator();

    @Test
    public void doTestrun() throws Exception {
        trainRides.put(31,5000);
        trainRides.put(42,10000);
        trainRides.put(33,10000);
        trainRides.keySet().forEach(rideId-> {
            rs.addRide(rideId, trainRides.get(rideId));
        });
        rs.simulate();
        Thread.sleep(20000);
        assertTrue("it should reach this point without crashing", true);

    }
}
