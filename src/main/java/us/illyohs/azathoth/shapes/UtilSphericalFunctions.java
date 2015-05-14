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
package us.illyohs.azathoth.shapes;

import java.util.HashSet;

import net.minecraft.world.World;
import us.illyohs.azathoth.world.WorldPos;

public class UtilSphericalFunctions {

    public static boolean radiusCheck(int x, int y, int z, int rd) {
        return ((x * x) + (y * y) + (z * z) < ((rd + 0.5) * (rd + 0.5)));
    }
    
    public static HashSet<WorldPos> getSphere (WorldPos coords, int radius) {
        float r_squared = (float)((radius + 0.5) * (radius + 0.5));
        World world = coords.getWorld();
        HashSet<WorldPos> returnvalues = new HashSet<WorldPos>();
        //loop needs to cap at the top and bottom of the world
        int bottom = Math.max(-radius - 1,  -1*(coords.posY - 1));
        int top = Math.min(radius + 1, (world.getHeight() - 1 - coords.posY));
        for (int y = bottom; y < top; y++)  {
            for (int z = -radius-1; z < radius+1; z++) {
                for (int x = -radius-1; x < radius+1; x++) {
                    if((x * x) + (y * y) + (z * z) < r_squared) {
                        returnvalues.add(new WorldPos(world, coords.posX + x, coords.posY + y, coords.posZ + z, x));
                    }
                }
            }
        }
        return returnvalues;
    }
	
	public static HashSet<WorldPos> getShell(WorldPos center, int radius){
	    //Josiah: I wrote this so it's probably got holes...
	    HashSet<WorldPos> bigSphere = getSphere(center, radius);
	    HashSet<WorldPos> smallerSphere = getSphere(center, radius-1);
        bigSphere.removeAll(smallerSphere);
	    return bigSphere;
	}
}
