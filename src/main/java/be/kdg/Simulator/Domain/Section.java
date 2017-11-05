package be.kdg.Simulator.Domain;

import java.util.ArrayList;
import java.util.List;

public class Section {
    private int sectionId;
    private int speed=0;

    private int numberOfBlocks;
    private int blockLength;
    private String crossings;
    private boolean singleDirection;
    private String highBlocknumberSectionId;

    private List<Block> blocks = new ArrayList<>();

    public Section(int sectionId, int speed) {
        this.sectionId = sectionId;
        this.speed = speed;
    }

    public void setInfoSection(int numberOfBlocks, int blockLength, String crossings, boolean singleDirection, String highBlocknumberSectionId){
        this.numberOfBlocks = numberOfBlocks;
        this.blockLength=blockLength;
        this.crossings=crossings;
        this.singleDirection=singleDirection;
        this.highBlocknumberSectionId =highBlocknumberSectionId;
    }

    public int getSectionId() {
        return sectionId;
    }

    public int getNumberOfBlocks() {
        return numberOfBlocks;
    }

    public String getCrossings() {
        return crossings;
    }

    public int getBlockLength() {
        return blockLength;
    }

    public boolean isSingleDirection() {
        return singleDirection;
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    public String showBlocks(){
        return "blocks: {" + blocks + "}";
    }


    @Override
    public String toString() {
        return "Section: { sectionId: " + sectionId +", speed: " + speed + ", numberOfBlocks:" + numberOfBlocks +", blockLength: " + blockLength +
                ", crossings: " + crossings + ", singleDirection: " + singleDirection +", highBlocknumberSectionId: " + highBlocknumberSectionId + '}';
    }
}
