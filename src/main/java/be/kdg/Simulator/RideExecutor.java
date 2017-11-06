package be.kdg.Simulator;

import be.kdg.MessageBroker.Message;
import be.kdg.MessageBroker.MessageReceiver;
import be.kdg.Simulator.Domain.Block.Block;
import be.kdg.Simulator.Domain.Block.CrossingBlock;
import be.kdg.Simulator.Domain.Ride;
import be.kdg.Simulator.Domain.Section;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RideExecutor {
    private final int BLOCKSBEFORECROSSING = 2;
    private final int BLOCKSAFTERCROSSING = 2;

    private Logger logger = LoggerFactory.getLogger(RideExecutor.class);
    private List<Block> pastBlocks = new ArrayList<>();
    private List<Block> upcomingBlocks = new ArrayList<>();
    private Message message = new Message();
    private List<String> failingCrossings = new ArrayList<>();

    private void setFailingCrossings() {
        failingCrossings.add("1-7");
        failingCrossings.add("7-9");
    }

    public void executeRide(Ride ride, MessageReceiver msgR) {
        setFailingCrossings();
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        logger.info("Ride Executed: " + ride.getRideId() + " timestamp: " + timeStamp);

        while (ride.hasSections()) {
            Section section = ride.getSections().get(0);
            while (section.hasNextBlock(0)) {
                /*if(msgR.getMessages().size()>0){
                    CheckMsgR(msgR,ride,section);
                }*/
                Block block = section.getBlocks().get(0);
                long delayTime = block.getBlockLength() / section.getSpeed() * 1000;
                editUpcomingBlocks(ride);
                try {
                    Thread.sleep(delayTime);
                } catch (InterruptedException e) {
                    logger.error("InterruptedException" + e.getLocalizedMessage());
                }
                editPastBlocks(block);
                detectionMessage(ride.getRideId(), block.getSectionIdBlock(), block.getBlockId());
                section.removeFirstBlock();
            }
            ride.removeFirstSection();
        }

        Thread.currentThread().interrupt();
    }

    private void CheckMsgR(MessageReceiver msgR, Ride ride, Section section) {
        msgR.getMessages().forEach(msg -> {
            if (msg.contains("<sectionId>" + section.getSectionId())) {
                section.setSpeed(Integer.parseInt(msg.substring(msg.indexOf("<speed>") + 7, msg.indexOf("</speed>"))));
            } else if (msg.contains("<RideId" + ride.getRideId())) {
                System.out.println("stop msg");
                Thread.currentThread().interrupt();
            }
        });
    }

    public void editUpcomingBlocks(Ride ride) {
        int upcomingSection = 0;
        int upcomingBlock = 1;
        boolean end = false;
        while (upcomingBlocks.size() < BLOCKSBEFORECROSSING && !end) {
            upcomingBlocks.add(ride.getSections().get(upcomingSection).getBlocks().get(upcomingBlock));
            upcomingBlock++;
            if (upcomingBlock >= ride.getSections().get(upcomingSection).getBlocks().size() && ride.hasNextSection(upcomingSection)) {
                upcomingSection++;
                upcomingBlock = 0;
            } else if (!ride.hasNextSection(upcomingSection)) {
                end = true;
            }
        }
        if (upcomingBlocks.get(upcomingBlocks.size() - 1).getClass() == CrossingBlock.class) {
            Block messageBlock = upcomingBlocks.get(upcomingBlocks.size() - 1);
            signalMessage(messageBlock.getSectionIdBlock(), messageBlock.getBlockId(), 1);
        }
        System.out.println("editUpcomingBlocks upcoming blocks: " + upcomingBlocks.toString());
        upcomingBlocks.remove(0);
    }

    public void editPastBlocks(Block block) {
        pastBlocks.add(block);
        if (pastBlocks.size() > BLOCKSAFTERCROSSING) {
            if (pastBlocks.get(0).getClass() == CrossingBlock.class) {
                Block messageBlock = pastBlocks.get(0);
                signalMessage(messageBlock.getSectionIdBlock(), messageBlock.getBlockId(), 0);
            }
            pastBlocks.remove(0);
        }
        System.out.println("editPastBlocks pastBlocks:" + pastBlocks.toString());
    }

    public void signalMessage(int sectionId, int blockNr, int signal) {
        String fail = sectionId + "-" + blockNr;
        if (!failingCrossings.contains(fail)) {
            System.out.println("signalMessage");
            message.signalMessage(sectionId, blockNr, signal);
        }
    }

    public void detectionMessage(int rideId, int sectionId, int blockNr) {
        System.out.println("detectionMessage");
        message.detectionMessage(rideId, sectionId, blockNr);

    }
}
