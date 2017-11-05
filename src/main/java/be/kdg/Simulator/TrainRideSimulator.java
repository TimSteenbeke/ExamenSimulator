package be.kdg.Simulator;

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
    private  Logger logger = LoggerFactory.getLogger(TrainRideSimulator.class);
    private final List<Ride> ridesList = new ArrayList<>();
    private ScheduledExecutorService executorService = null;
    private RideExecutor re = new RideExecutor();
    static{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-hhmmss");
        System.setProperty("current.date", dateFormat.format(new Date()));
    }

    public void addRide(Integer rideId, Integer delay) {
        Ride ride = new Ride(rideId,delay);
        logger.info("Ride created:  " + ride.getRideId());
        new RideLoader().getRoute(ride);
        ridesList.add(ride);
    }

    public void executeRide(Ride ride){
        //TODO send start message
        re.executeRide(ride);
    }

    public void start(){
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        logger.info("Start: " + timeStamp);
        executorService= Executors.newScheduledThreadPool(ridesList.size());
        ridesList.forEach(ride-> executorService.schedule(
                new Runnable() {
                    public void run() {
                        //TODO: start Messaging using ride
                        executeRide(ride);

                    }
                },ride.getDelay(), TimeUnit.MILLISECONDS));


    }

    public void stop(){
        if(executorService==null)
        executorService.shutdown();
    }
}

