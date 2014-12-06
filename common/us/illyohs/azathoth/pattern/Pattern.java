/**
 *  Copyright (c) 2014, Runix-Minecraft
 *  All rights reserved.
 *  
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *  
 *  * Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *    
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *  
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 *  AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 *  IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 *  DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 *  CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 *  OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 *  OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
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
