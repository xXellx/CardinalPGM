package in.twizmwaz.cardinal.regions.type;

import in.twizmwaz.cardinal.regions.Region;
import in.twizmwaz.cardinal.regions.parsers.EmptyParser;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class EmptyRegion extends Region {

    public EmptyRegion() {
    }

    public EmptyRegion(EmptyParser parser) {
    }

    @Override
    public boolean contains(BlockRegion region) {
        return false;
    }

    @Override
    public boolean contains(PointRegion region) {
        return false;
    }

    @Override
    public PointRegion getRandomPoint() {
        return null;
    }

    @Override
    public BlockRegion getCenterBlock() {
        return null;
    }

    @Override
    public List<Block> getBlocks() {
        return new ArrayList<Block>();
    }


}
