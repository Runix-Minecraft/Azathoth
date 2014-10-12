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
package us.illyohs.azathoth.test;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import us.illyohs.azathoth.pattern.BasePattern;
import us.illyohs.azathoth.pattern.Pattern;
import us.illyohs.azathoth.world.WorldXYZ;
import cpw.mods.fml.common.Mod;

@Mod(name = "testmod", modid = "testmodid", version = "0.0.0.0.NOPE")
public class TestMod {

    @Pattern(modid = "testmodid", name = "testpattern", isFake = true)
    public class boopPattern extends BasePattern {

        @Override
        public Block[][][] blockPattern() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public boolean isFlatPattern() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean isAssymetrical() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public void execute(WorldXYZ coords, EntityPlayer player) {
            // TODO Auto-generated method stub

        }

        @Override
        public boolean isPatternAllowed(EntityPlayer player, BasePattern pattern) {
            // TODO Auto-generated method stub
            return false;
        }

    }
}
