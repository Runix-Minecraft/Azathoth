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
package us.illyohs.azathoth.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;

import us.illyohs.azathoth.math.Vector3;
import us.illyohs.azathoth.world.SigBlock;
import us.illyohs.azathoth.world.WorldPos;

public class UtilMove {
    
    public static HashMap<WorldPos, WorldPos> xzRotation(Collection<WorldPos> startingShape, WorldPos centerPoint, boolean counterClockwise){
        //centerPoint is also the axis of rotation
        HashMap<WorldPos, WorldPos> rotationMapping = new HashMap<WorldPos, WorldPos>();
        for( WorldPos point : startingShape ){
            rotationMapping.put(point, point.rotate(centerPoint, counterClockwise));//flip sign on X
        }
        return rotationMapping;
    }
    
    public static boolean lookingRightOfCenterBlock(EntityPlayer player, WorldPos referencePoint) {
        float yaw = player.rotationYawHead;//assumption: you're looking at the block you right clicked
        yaw = (yaw > 0.0) ? yaw  : yaw + 360.0F; //Josiah: minecraft yaw wanders into negatives sometimes...
        double opposite = player.posZ - referencePoint.posZ - .5;
        double adjacent = player.posX - referencePoint.posX - .5;
        double angle = Math.toDegrees(Math.atan( opposite / adjacent )) + 90.0;
        if( adjacent > 0.0)
            angle += 180.0;
//        System.out.println("Rune: " + angle + "  Yaw: " + yaw + " = " + (angle - yaw));
        if( ((angle - yaw) < 180.0 && (angle - yaw) > 0.0) || //the difference between the angle to the reference
                ((angle - yaw) < -180.0 && (angle - yaw) > -360.0) )//and the angle we're looking determines left/right
            return true;
        else
            return false;
    }
    
    public static HashMap<WorldPos, WorldPos> displaceShape(Collection<WorldPos> set, WorldPos startPoint, WorldPos destinationCenter) {
        HashMap<WorldPos, WorldPos> moveMapping = new HashMap<WorldPos, WorldPos>();
        Vector3 displacement = new Vector3(startPoint, destinationCenter);
        for(WorldPos point : set)
            moveMapping.put(point, point.offsetWorld(displacement, destinationCenter.getWorld()));
        return moveMapping;
    }
    
    public static HashMap<WorldPos, SigBlock> rotateStructureInMemory(HashMap<WorldPos, SigBlock> shape, WorldPos center, int nTurns) {
        HashMap<WorldPos, SigBlock> startShape = new HashMap<WorldPos, SigBlock>(shape);
        
        for(int turnNumber = 0; turnNumber < nTurns; ++turnNumber) {
            HashMap<WorldPos, WorldPos> move = UtilMove.xzRotation(startShape.keySet(), center, false);

            HashMap<WorldPos, SigBlock> newShape = new HashMap<WorldPos, SigBlock>();//blank variable for swapping purposes
            for(WorldPos origin : move.keySet()) {
                WorldPos destination = move.get(origin);
                newShape.put(destination, startShape.get(origin));
            }
            startShape = newShape;
        }
        return startShape;
    }
    
    public static HashMap<WorldPos, SigBlock> scanBlocksInShape(Set<WorldPos> shape) {
        HashMap<WorldPos, SigBlock> actualBlocks = new HashMap<WorldPos, SigBlock>();
        for(WorldPos point : shape) {
            actualBlocks.put(point, point.getSigBlock());
        }
        return actualBlocks;
    }

}
