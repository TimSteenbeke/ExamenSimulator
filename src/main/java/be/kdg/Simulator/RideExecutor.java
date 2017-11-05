package be.kdg.Simulator;

import be.kdg.MessageBroker.Message;
import be.kdg.Simulator.Domain.Block.Block;
import be.kdg.Simulator.Domain.Ride;
import be.kdg.Simulator.Domain.Section;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RideExecutor {
    private Logger logger = LoggerFactory.getLogger(RideExecutor.class);

    public void executeRide(Ride ride) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        logger.info("Ride Executed: " + ride.getRideId() + " timestamp: " + timeStamp);
        while(ride.hasSections()){
            Section section = ride.getSections().get(0);
            int speed = section.getSpeed();
            int blockLenght = section.getBlockLength();
            int delayTime= blockLenght/speed;
            while(section.hasBlocks()){
                Block block = section.getBlocks().get(0);
                try {
                    Thread.sleep(delayTime*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Message().createMessage(ride,section,block,delayTime);
                //System.out.println("dropping block: " + block);
                section.removeFirstBlock();
            }
            //System.out.println("dropping section: " + section);
            ride.removeFirstSection();
        }

    }
}
