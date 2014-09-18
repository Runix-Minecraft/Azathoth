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
package us.illyohs.azathoth.math;

import us.illyohs.azathoth.world.WorldXYZ;
import net.minecraftforge.common.util.ForgeDirection;


public class Vector3 {// extends paulscode.sound.Vector3D.{
    public static final Vector3 UP =    new Vector3(0, 1,0);
    public static final Vector3 DOWN =  new Vector3(0,-1,0);
    public static final Vector3 NORTH = new Vector3(0,0,-1);
    public static final Vector3 EAST =  new Vector3( 1,0,0);
    public static final Vector3 SOUTH = new Vector3(0,0, 1);
    public static final Vector3 WEST =  new Vector3(-1,0,0);
    public static final Vector3 NONE =  new Vector3(0,0,0);
    //These map to the minecraft block face, such that you're standing on the side they are pointing
    public static final Vector3[] facing = {DOWN, UP, NORTH, SOUTH, WEST, EAST};
    //use like this: WorldXYZ point = point.offset(Vector3.facing[point.face])
    public static final String[] faceString = {"Down", "Up", "North", "South", "West", "East"};
    //Converts a side to the opposite side. This is the same as XOR'ing it with 1.
    public static final int[] oppositeSide = new int[] {1, 0, 3, 2, 5, 4};
//    public static final ArrayList<Integer> xzRotationOrder = 
//            (ArrayList<Integer>) Arrays.asList(new Integer(2), new Integer(5), new Integer(3), new Integer(4));  
    public static Vector3[] xzRotationOrder = {Vector3.NORTH, Vector3.EAST, Vector3.SOUTH, Vector3.WEST,};
    public static Vector3[] xyRotationOrder = {Vector3.UP, Vector3.EAST, Vector3.DOWN, Vector3.WEST,};
    public static Vector3[] yzRotationOrder = {Vector3.NORTH, Vector3.UP, Vector3.SOUTH, Vector3.DOWN,};
    
    public int x;
    public int y;
    public int z;
    
    public Vector3(int mx, int my, int mz) {
        x = mx;
        y = my;
        z = mz;
    }
    
    /** Returns a difference vector such that reference + vector = destination */
    public Vector3(WorldXYZ reference, WorldXYZ destination){
        x = destination.posX - reference.posX; 
        y = destination.posY - reference.posY;
        z = destination.posZ - reference.posZ;
    }
    
    public Vector3(ForgeDirection face) {
        x = face.offsetX;
        y = face.offsetY;
        z = face.offsetZ;
    }

    public String toString() {
        return "\"x\":"+x + ", \"y\":" + y + ", \"z\":" + z;
    }

    public Vector3 multiply(int m) {
        return new Vector3(x*m, y*m, z*m);
    }
}
