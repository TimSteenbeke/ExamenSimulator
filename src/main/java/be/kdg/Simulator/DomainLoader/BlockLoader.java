package be.kdg.Simulator.DomainLoader;

import be.kdg.Simulator.Domain.Block.Block;
import be.kdg.Simulator.Domain.Block.CrossingBlock;
import be.kdg.Simulator.Domain.Block.DetectionBlock;
import be.kdg.Simulator.Domain.Section;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlockLoader {
    private Logger logger = LoggerFactory.getLogger(BlockLoader.class);

    //
    public void loadBlocks(Section section) {
        List<Integer> crossingsList = new ArrayList<>();
        if(!section.getCrossings().isEmpty()){
            List<String> crossingsString = Arrays.asList(section.getCrossings().split("-"));
            crossingsString.forEach(string->{
                crossingsList.add(Integer.parseInt(string));
            });
        }

        for (int i = 1;i<=section.getNumberOfBlocks();i++){
            Block block;
            if(crossingsList.contains(i)){
                block = new CrossingBlock(i,section.getBlockLength(),section.isSingleDirection(),section.getSectionId());
            }else{
                block = new DetectionBlock(i,section.getBlockLength(),section.isSingleDirection(),section.getSectionId());
            }
            section.addBlock(block);
        }
        logger.info(section.getBlocks().size() + " blocks loaded");
    }
}
