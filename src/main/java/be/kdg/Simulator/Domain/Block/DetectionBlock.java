package be.kdg.Simulator.Domain.Block;

public class DetectionBlock implements Block {

    private int blockId;
    private int blockLength;
    private boolean singleDirection;
    private int sectionIdBlock;

    public DetectionBlock(int blockId, int blockLength, boolean singleDirection, int sectionIdBlock) {
        this.blockId = blockId;
        this.blockLength = blockLength;
        this.singleDirection = singleDirection;
        this.sectionIdBlock = sectionIdBlock;
    }

    @Override
    public int getBlockId() {
        return blockId;
    }

    @Override
    public int getBlockLength() {
        return blockLength;
    }

    @Override
    public boolean isSingleDirection() {
        return singleDirection;
    }

    @Override
    public int getSectionIdBlock() {
        return sectionIdBlock;
    }
}
