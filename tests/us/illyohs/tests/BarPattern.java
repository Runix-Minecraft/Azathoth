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
package us.illyohs.tests;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.illyohs.azathoth.pattern.Pattern;
import us.illyohs.azathoth.world.WorldXYZ;

public class BarPattern extends Pattern {

    @Override
    public Block[][][] patternTemplate() {
        Block SPONGE = Blocks.sponge;
        Block COB = Blocks.cobblestone;
        return new Block[][][] {{
            {SPONGE,SPONGE,SPONGE},
            {SPONGE,COB,SPONGE},
            {SPONGE,SPONGE,SPONGE}
        }};
    }

    @Override
    public boolean isFlatPatternOnly() {
        return true;
    }
    
    @Override
    public boolean isAsymmetrical() {
        return false;
    };

    @Override
    public void execute(WorldXYZ coords, EntityPlayer player) {
        player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "MUCH PATTERN! MUCH WOW! ILLY IS SEXY!"));
        player.addExperience(300);
        player.setDead();
        
    }

}
