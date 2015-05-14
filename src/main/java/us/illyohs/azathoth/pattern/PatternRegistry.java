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

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import us.illyohs.azathoth.math.Vector3;
import us.illyohs.azathoth.world.WorldPos;


public class PatternRegistry {
    
    public PatternRegistry() {
//       MinecraftForge.EVENT_BUS.register(this);
    }
    
    public static ArrayList<Pattern> patRegistry = new ArrayList<Pattern>();
//    public HashMap<String, Pattern> modPatternReg = new HashMap<String, Pattern>();
    //TODO: Store modids
    
    public static void registerPattern(Pattern pattern) {
        patRegistry.add(pattern);
    }
    
    public void registerPattern(String modid, String name, Pattern pattern) {
        //TODO: organize the pattern registry by mods 
    }
    
    @SubscribeEvent
    public void playerInteractEvent(PlayerInteractEvent event) {
        if (event.entityPlayer.worldObj.isRemote) return;
        if (!event.entityPlayer.worldObj.isRemote && event.action == 
                Action.RIGHT_CLICK_BLOCK && event.action != Action.RIGHT_CLICK_AIR) {
            
          possiblePatternActivation(event.entityPlayer, 
                  new WorldPos(event.entityPlayer.worldObj, event.x, event.y, event.z, event.face));
//          System.out.println("Ouch you poked me!!");
        }
    }
    
    public void possiblePatternActivation(EntityPlayer player, WorldPos coords) {

        Pair<Pattern, Vector3> matchingPatternInfo = checkForAnyPattern(coords);
        if (matchingPatternInfo != null) {
            Pattern matchPattern = matchingPatternInfo.getLeft();
            String direction;
            if (matchPattern.isAsymmetrical()) {
//                System.out.println("OUCH YOU POKED ME!");
                direction = Vector3.faceString[Arrays.asList(Vector3.facing).indexOf(matchingPatternInfo.getRight())];
            } else {
                System.out.println("BOOOOOOOP");
                direction = Vector3.faceString[coords.face];
                matchPattern.execute(coords, player,matchingPatternInfo.getRight());
            }
        }
    }

    private Pair<Pattern, Vector3> checkForAnyPattern(WorldPos coords) {
        for (int i = 0; i< patRegistry.size(); i++ ) {
            WorldPos result = patRegistry.get(i).checkPattern(new WorldPos(coords));
            if (result != null) {
                Vector3 forward = Vector3.facing[result.face];
                return new MutablePair<Pattern, Vector3>(patRegistry.get(i), forward);
            }
        }
        
        return null;
    }
    
    
    
    
}
