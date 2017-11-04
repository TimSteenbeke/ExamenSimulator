import be.kdg.Simulator.ServiceCallers.RideRouter;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class RideTest {
    private final List<Integer> rides = Arrays.asList(1,2,3,4,5,6,7);
    RideRouter rr = new RideRouter();

    @Test
    public void doTestrun() throws Exception {
        rides.forEach(rideId-> {
            rr.inputRide(rideId);
        });
        rr.start();
        Thread.sleep(1000);
        assertTrue("it should reach this point without crashing", true);
    }
}
