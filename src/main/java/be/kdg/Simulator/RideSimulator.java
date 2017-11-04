package be.kdg.Simulator;

import be.kdg.Simulator.Domain.Ride;
import be.kdg.Simulator.ServiceCallers.RideRouter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RideSimulator {
    private final List<Ride> ridesList = new ArrayList<>();
    private ScheduledExecutorService executorService = null;

    public void addRide(Integer rideId, Integer dealay) {
        Ride ride = new Ride(rideId,dealay);
        new RideRouter().getRoute(ride);
        ridesList.add(ride);
    }

    public void simulate(){
        executorService= Executors.newScheduledThreadPool(ridesList.size());
        ridesList.forEach(ride-> executorService.schedule(
                new Runnable() {
                    public void run() {
                        //TODO: start Messaging
                    }
                },ride.getDelay(), TimeUnit.MILLISECONDS));


    }
}

