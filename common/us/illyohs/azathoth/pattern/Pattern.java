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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import us.illyohs.azathoth.math.Vector3;
import us.illyohs.azathoth.util.UtilMove;
import us.illyohs.azathoth.world.SigBlock;
import us.illyohs.azathoth.world.WorldXYZ;

/**
 * This is the basis of all patterns. In order to register all patterns
 * before the PostInitialization. To register in the pattern registry
 * 
 * Example: PatternResistry.registerPattern(new FooPattern); 
 *
 */
public abstract class Pattern {
    
    public Pattern() {}
    
    /**
     * The pattern template
     */
    protected abstract Block[][][] patternTemplate();
    
    public abstract boolean isFlatPatternOnly();
    
    public void execute(WorldXYZ coords, EntityPlayer player, Vector3 forward) {
        execute(coords, player);
    }
    
    /**
     * Executes the main function of a given Rune.  If the Rune is persistent, it will store XYZ and other salient
     * information for future use.  Each Rune class is responsible for keeping track of the information it needs in
     * a static class variable.
     * @param coords World and xyz that Rune was activated in.
     * @param player We pass the player instead of World so that runes can later affect the Player
     * @param forward 
     */
    public abstract void execute(WorldXYZ coords, EntityPlayer player);
    
    protected boolean stampBlockPattern(HashMap<WorldXYZ, SigBlock> stamp, EntityPlayer player) {
        for (WorldXYZ target : stamp.keySet()) 
            target.setBlockId(stamp.get(target));
            return true;
            //TODO: build permissions system/checking
    }
    
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
//                    shape.put(target, new SigBlock(pattern[y][z][x], 0));
                }
            }
        }
        return shape;
    }
    
    public WorldXYZ checkPattern(WorldXYZ coords) {
        HashMap<WorldXYZ, SigBlock> shape = patternFormulae(coords);
        if (!isAsymmetrical()) {
            if (patternOrientationMatches(coords, shape)) {
                return coords;
            } else {
                return null;
            }
            
        } else {
            for(int nTurns = 0; nTurns < 4; ++nTurns) {
                HashMap<WorldXYZ, SigBlock> newShape = UtilMove.rotateStructureInMemory(shape, coords, nTurns);
                if(patternOrientationMatches(coords, newShape)) {
                    switch(coords.face){
                        case 0: case 1: 
                            coords.face = (new ArrayList<Vector3>(Arrays.asList(Vector3.facing))).indexOf(Vector3.xzRotationOrder[nTurns]);
                            break;
                        case 2: case 3: 
                            coords.face = (new ArrayList<Vector3>(Arrays.asList(Vector3.facing))).indexOf(Vector3.xyRotationOrder[nTurns]);
                            break;
                        case 4: case 5: 
                            coords.face = (new ArrayList<Vector3>(Arrays.asList(Vector3.facing))).indexOf(Vector3.yzRotationOrder[nTurns]);
                            break;
                        }
                    return coords;
                    }

                }
            }
            return null;
        
        }
    
    public boolean patternOrientationMatches(WorldXYZ coords, HashMap<WorldXYZ, SigBlock> shape) {
        //TODO: 
        return false;
    }

    public boolean isAsymmetrical() {
        return false;
    }
}
