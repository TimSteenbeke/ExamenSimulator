package be.kdg.Simulator;

import be.kdg.MessageBroker.MessageReceiver;
import be.kdg.Simulator.Domain.Ride;
import be.kdg.Simulator.DomainLoader.RideLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TrainRideSimulator {
    static {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-hhmmss");
        System.setProperty("current.date", dateFormat.format(new Date()));
    }

    private final List<Ride> ridesList = new ArrayList<>();
    private Logger logger = LoggerFactory.getLogger(TrainRideSimulator.class);
    private ScheduledExecutorService executorService;
    private RideExecutor rideExecutor = new RideExecutor();
    private RideLoader rideLoader = new RideLoader();

    //Adds rides to the list in the class
    public void addRide(Integer rideId, Integer delay) {
        Ride ride = new Ride(rideId, delay);
        logger.info("Ride created:  " + ride.getRideId());
        rideLoader.getRoute(ride);
        ridesList.add(ride);
    }

    //checks if executorservice is running
    public boolean isExecuting() {
        return !this.executorService.isTerminated();
    }

    //Starts simulating trainrides by executing rides with messages and receiving messages
    public void start(MessageReceiver msgR) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        logger.info("Start: " + timeStamp);

        this.executorService = Executors.newScheduledThreadPool(ridesList.size());
        ridesList.forEach(ride -> executorService.schedule(() -> {
            rideExecutor.executeRide(ride, msgR);


        }, ride.getDelay(), TimeUnit.MILLISECONDS));

    }

    //stops executorservice
    public void stop() {
        if (executorService == null) {
            executorService.shutdown();
        }
    }
}

