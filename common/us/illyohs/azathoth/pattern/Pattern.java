package us.illyohs.azathoth.pattern;

import java.util.HashMap;

import net.minecraft.block.Block;

import us.illyohs.azathoth.world.SigBlock;
import us.illyohs.azathoth.world.WorldXYZ;

public abstract class Pattern {
    
    public Pattern() {}
    
    /**
     * The pattern 
     */
    protected abstract Block[][][] patternTemplate();
    
    public abstract boolean isFlatPatternOnly();
    
    protected HashMap<WorldXYZ, SigBlock> patternFormulae(WorldXYZ coords) {
        if (isFlatPatternOnly())
            coords = coords.copyWithNewFacing(1);
            return patternToShape(patternTemplate(), coords);

    }

    private HashMap<WorldXYZ, SigBlock> patternToShape(Block[][][] pattern, WorldXYZ centerPoint) {
        HashMap<WorldXYZ, SigBlock> shape = new HashMap<WorldXYZ, SigBlock>();
        for (int y = 0; y < pattern.length; y++) {
            for (int z = 0; z < pattern[y].length; z++) {
                for (int x = 0; x < pattern[y][z].length; x++) {
                    WorldXYZ target;
                    
                    switch(centerPoint.face) {
                    case 1: //laying flat activated from top or bottom
                    case 0:
                        target = centerPoint.offset(-pattern[y][z].length / 2 + x,  -y,  -pattern[y].length / 2 + z);//TODO: clockwise vs CCW?
                        break;
                    case 2://NORTH or SOUTH which points along the z axis
                    case 3://this means that flat patterns (XZ pattern) will extend along XY
                        target = centerPoint.offset(-pattern[y][z].length / 2 + x,  pattern[y].length / 2 - z,  -y );//TODO: +y for SOUTH
                        break;
                    case 4://WEST or EAST facing
                    case 5://flat patterns extend along the ZY plane
                        target = centerPoint.offset(-y,  pattern[y][z].length / 2 - x,  -pattern[y].length / 2 + z);
                        break;
                    default:
                        System.err.println("Block facing not recognized: " + centerPoint.face + " should be 0-5.");
                        target = centerPoint;
                    }
                }
            }
        }
        return shape;
    }
    
}
