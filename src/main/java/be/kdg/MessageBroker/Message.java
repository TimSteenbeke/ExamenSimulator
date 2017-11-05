package be.kdg.MessageBroker;

import be.kdg.Simulator.Domain.Block.Block;
import be.kdg.Simulator.Domain.Ride;
import be.kdg.Simulator.Domain.Section;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Message {
    private Logger logger = LoggerFactory.getLogger(Message.class);

    public void createMessage(Ride ride, Section section, Block block, int delayTime) {
     //TODO: create message with rideId,SectionId,BlockNr,timestamp
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        logger.info("Message: rideId=" + ride.getRideId() + ", sectionId=" + section.getSectionId() + ", blockNr=" + block.getBlockId() + ", delayTime=" + delayTime +", TimeStamp: " + timeStamp);

    }
}
