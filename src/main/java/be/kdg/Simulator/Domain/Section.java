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
    private String highBlocknumberSectionIds;

    private List<Block> Blocks = new ArrayList<>();

    public Section(int sectionId, int speed) {
        this.sectionId = sectionId;
        this.speed = speed;
    }

    public void setInfoSection(int numberOfBlocks, int blockLength, String crossings, boolean singleDirection, String highBlocknumberSectionIds){
        this.numberOfBlocks = numberOfBlocks;
        this.blockLength=blockLength;
        this.crossings=crossings;
        this.singleDirection=singleDirection;
        this.highBlocknumberSectionIds=highBlocknumberSectionIds;
    }

    @Override
    public String toString() {
        return "Section{" +
                "sectionId=" + sectionId +
                ", speed=" + speed +
                ", numberOfBlocks=" + numberOfBlocks +
                ", blockLength=" + blockLength +
                ", crossings='" + crossings + '\'' +
                ", singleDirection=" + singleDirection +
                ", highBlocknumberSectionIds=" + highBlocknumberSectionIds +
                ", Blocks=" + Blocks +
                '}';
    }

    public int getSectionId() {
        return sectionId;
    }
}
