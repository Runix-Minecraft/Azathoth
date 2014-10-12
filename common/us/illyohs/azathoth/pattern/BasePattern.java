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
import net.minecraft.entity.player.EntityPlayer;

import us.illyohs.azathoth.math.Vector3;
import us.illyohs.azathoth.world.SigBlock;
import us.illyohs.azathoth.world.WorldXYZ;

public abstract class BasePattern {
    
    public BasePattern(){};

    /**
     * World patten template.
     */
    public abstract Block[][][] blockPattern();

    /**
     * Tells Azathoth if the pattern is flat
     * @return true or false
     */
    public abstract boolean isFlatPattern();

    protected HashMap<WorldXYZ, SigBlock> patternFormulae(WorldXYZ coords) {
        if(isFlatPattern()) {
            coords = coords.copyWithNewFacing(1);
            return patternToShape(blockPattern(), coords);
        }
        return null;
    }


    public void execute(WorldXYZ coords,  EntityPlayer player, Vector3 forward) {
        execute(coords, player);
    }

    /**
     * What you want to execute on pattern activation
     * @param coords are the world and xyz of the structure
     * @param player the player object
     */
    public abstract void execute(WorldXYZ coords,  EntityPlayer player);

    /**
     * 
     */
    protected boolean stampBlockPattern(HashMap<WorldXYZ, SigBlock> stamp, EntityPlayer player) {
        for (WorldXYZ target: stamp.keySet()){
            target.setBlockId(stamp.get(target));
            return true;
        }
        
        return false;
    }
    
    private HashMap<WorldXYZ, SigBlock> patternToShape(
            Block[][][] blockPattern, WorldXYZ coords) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * 
     */
    public static boolean isPatternAllowed(EntityPlayer player, BasePattern pattern) {
        return true;
        
    }
    
    public boolean isAssymetrical() {
        return false;
    }
    
}
