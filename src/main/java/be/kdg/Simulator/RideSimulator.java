package be.kdg.Simulator;

import be.kdg.Simulator.Domain.Ride;
import be.kdg.Simulator.DomainLoader.RideRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RideSimulator {
    private  Logger logger = LoggerFactory.getLogger(RideSimulator.class);
    private final List<Ride> ridesList = new ArrayList<>();
    private ScheduledExecutorService executorService = null;
    static{
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddmmyyyy_hhmmss");
        System.setProperty("current.date", dateFormat.format(new Date()));
    }

    public void addRide(Integer rideId, Integer dealay) {
        Ride ride = new Ride(rideId,dealay);
        new RideRouter().getRoute(ride);
        ridesList.add(ride);
        logger.info("Ride after getRoute: " + ride.toString());
    }

    public void simulate(){
        executorService= Executors.newScheduledThreadPool(ridesList.size());
        ridesList.forEach(ride-> executorService.schedule(
                new Runnable() {
                    public void run() {
                        //TODO: start Messaging using ride
                    }
                },ride.getDelay(), TimeUnit.MILLISECONDS));


    }

    public void stop(){
        if(executorService==null)
        executorService.shutdown();
    }
}

