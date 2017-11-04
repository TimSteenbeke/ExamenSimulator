import be.kdg.Simulator.ServiceCallers.RideRouter;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class RideTest {
    private final List<Integer> rides = Arrays.asList(1,2,3,4,5,6,7);

    @Test
    public void doTestrun() throws Exception {
        for (int rideId:rides) {
            new RideRouter().start(rideId);
        }
        assertTrue("it should reach this point without crashing", true);
    }
}
