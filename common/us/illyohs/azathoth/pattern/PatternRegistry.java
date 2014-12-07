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
import java.util.HashSet;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import us.illyohs.azathoth.math.Vector3;
import us.illyohs.azathoth.world.WorldXYZ;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

public class PatternRegistry {
    
    private ArrayList<Pattern> patRegistry = new ArrayList<Pattern>();
    
    public void registerPattern(Pattern pattern) {
        patRegistry.add(pattern);
    }
    
    public void registerPattern(String modid, String name, Pattern pattern) {
        //TODO: organize the pattern registry by mods 
    }
    
    public void playerInteractEvent(PlayerInteractEvent event) {
        if (event.entityPlayer.worldObj.isRemote) return;
        if (!event.entityPlayer.worldObj.isRemote && event.action == Action.RIGHT_CLICK_BLOCK && event.action != Action.RIGHT_CLICK_AIR) {
            
        }
    }
    
    public void possiblePatternActivation(EntityPlayer player, WorldXYZ coords) {
        Pair<Pattern, Vector3> matchingPatternInfp = checkForAnyPattern(coords);
        if (matchingPatternInfp != null) {
//            Pattern
            //TODO: finish possible activations 
        }
    }

    private Pair<Pattern, Vector3> checkForAnyPattern(WorldXYZ coords) {
        for (int i = 0; i< patRegistry.size(); i++ ) {
            WorldXYZ result = patRegistry.get(i).checkPattern(new WorldXYZ(coords));
            if (result != null) {
                Vector3 forward = Vector3.facing[result.face];
                return new MutablePair<Pattern, Vector3>(patRegistry.get(i), forward);
            }
        }
        
        return null;
    }
    
    
    
    
}
