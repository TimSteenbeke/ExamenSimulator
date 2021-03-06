package be.kdg.MessageBroker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Message {
    private Logger logger = LoggerFactory.getLogger(Message.class);
    private MessageSender ms = new MessageSender();
    private String timeStamp, xmlMessage;

    //Creates detection message and put's on Queue
    public void detectionMessage(int rideId, int sectionId, int blockNr) {
        timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        xmlMessage = "<detectionMessage> " +
                "<rideId>" + rideId + "</rideId>" +
                "<sectionId>" + sectionId + "</sectionId>" +
                "<blockNr>" + blockNr + "</blockNr>" +
                "<TimeStamp>" + timeStamp + "<TimeStamp>" +
                "</detectionMessage>";
        logger.info("Message:" + xmlMessage);
        sendMessage(xmlMessage);
    }

    //creates signal message and put's on queue
    public void signalMessage(int sectionId, int blockNr, int signal) {
        timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        xmlMessage = "<signalMessage> " +
                "<sectionId>" + sectionId + "</sectionId>" +
                "<blockNr>" + blockNr + "</blockNr>" +
                "<signal>" + signal + "</signal>" +
                "<TimeStamp>" + timeStamp + "<TimeStamp>" +
                "</signalMessage>";
        logger.info("Message: " + xmlMessage);
        sendMessage(xmlMessage);
    }

    //put's messages on the queue
    private void sendMessage(String message) {
        try {
            ms.sendMessage(message);
        } catch (Exception e) {
            logger.error("Exception" + e.toString());
        }
    }

}
