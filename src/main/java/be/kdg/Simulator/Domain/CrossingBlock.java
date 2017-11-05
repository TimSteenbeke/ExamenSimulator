package be.kdg.Simulator.Domain;

public class CrossingBlock implements Block {

    private int blockId;
    private int blockLength;
    private boolean singleDirection;

    public CrossingBlock(int blockId, int blockLength, boolean singleDirection) {
        this.blockId = blockId;
        this.blockLength = blockLength;
        this.singleDirection = singleDirection;
    }

    @Override
    public String toString() {
        return "CrossingBlock{" +
                "blockId=" + blockId +
                ", blockLength=" + blockLength +
                ", singleDirection=" + singleDirection +
                '}';
    }
}
