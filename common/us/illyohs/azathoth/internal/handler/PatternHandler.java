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
package us.illyohs.azathoth.internal.handler;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import us.illyohs.azathoth.pattern.BasePattern;
import us.illyohs.azathoth.pattern.PatternRegistry;
import us.illyohs.azathoth.math.Vector3;
import us.illyohs.azathoth.world.WorldXYZ;

public class PatternHandler {
    
    public void playerInteractEvent(PlayerInteractEvent event) {
        if(!event.entityPlayer.worldObj.isRemote) {
            PatternActivation(event.entityPlayer, new WorldXYZ(event.entityPlayer.worldObj, event.x, event.y, event.z));
        } else {
            return;
        }
    }

    /**
     * @param entityPlayer
     * @param worldXYZ
     */
    public void PatternActivation(EntityPlayer player, WorldXYZ coords) {

        
    }
    
    private Pair<BasePattern, Vector3> checkForAnyPattern(WorldXYZ corrds) {
        for (int i =0; i < PatternRegistry.regPattern.size(); i++) {
//            WorldXYZ result = PatternRegistry.regPattern.get(i).c
        }
        return null;
        
    }

}
