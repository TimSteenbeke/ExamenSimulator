package be.kdg.Simulator.Domain;

public class DetectionBlock implements Block {

    private int blockId;
    private int blockLength;
    private boolean singleDirection;

    public DetectionBlock(int blockId, int blockLength, boolean singleDirection) {
        this.blockId = blockId;
        this.blockLength = blockLength;
        this.singleDirection = singleDirection;
    }

    @Override
    public String toString() {
        return "DetectionBlock{" +
                "blockId=" + blockId +
                ", blockLength=" + blockLength +
                ", singleDirection=" + singleDirection +
                '}';
    }
}
