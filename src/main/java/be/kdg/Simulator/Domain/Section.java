package be.kdg.Simulator.Domain;

import be.kdg.Simulator.Domain.Block.Block;

import java.util.ArrayList;
import java.util.List;

public class Section {
    private final int sectionId;
    private int speed;

    private int numberOfBlocks;
    private int blockLength;
    private String crossings;
    private boolean singleDirection;
    private String highBlockNumberSectionId;

    private List<Block> blocks = new ArrayList<>();

    public Section(int sectionId, int speed) {
        this.sectionId = sectionId;
        this.speed = speed;
    }

    public void setInfoSection(int numberOfBlocks, int blockLength, String crossings, boolean singleDirection, String highBlockNumberSectionId) {
        this.numberOfBlocks = numberOfBlocks;
        this.blockLength = blockLength;
        this.crossings = crossings;
        this.singleDirection = singleDirection;
        this.highBlockNumberSectionId = highBlockNumberSectionId;
    }

    //Getters for all values
    public int getSectionId() {
        return sectionId;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getNumberOfBlocks() {
        return numberOfBlocks;
    }

    public int getBlockLength() {
        return blockLength;
    }

    public String getCrossings() {
        return crossings;
    }

    public String getHighBlockNumberSectionId() {
        return highBlockNumberSectionId;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public boolean isSingleDirection() {
        return singleDirection;
    }

    //block adder for list
    public void addBlock(Block block) {
        blocks.add(block);
    }

    public boolean hasBlocks() {
        if (!blocks.isEmpty()) return true;

        return false;
    }

    public boolean hasNextBlock(int index) {
        if (blocks.size() - 1 > index)
            return true;
        return false;
    }

    public void removeFirstBlock() {
        blocks.remove(0);
    }

    public void removeBlock(Block block) {
        blocks.remove(block);
    }

    @Override
    public String toString() {
        return "Section: { sectionId: " + sectionId + ", speed: " + speed + ", numberOfBlocks:" + numberOfBlocks + ", blockLength: " + blockLength +
                ", crossings: " + crossings + ", singleDirection: " + singleDirection + ", highBlockNumberSectionId: " + highBlockNumberSectionId + '}';
    }

    public String showBlocksToString() {
        return "blocks: {" + blocks + "}";
    }
}
