package be.kdg.Simulator;

import be.kdg.Simulator.block.Block;

import java.util.ArrayList;
import java.util.List;

public class Section {
    private int sectionId;
    private int speed=0;

    private int numberOfBlocks;
    private int blockLength;
    private String crossings;
    private boolean singleDirection;
    private int highBlocknumberSectionIds;
    private List<Block> Blocks = new ArrayList<>();

    public Section(int sectionId, int speed) {
        this.sectionId = sectionId;
        this.speed = speed;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
