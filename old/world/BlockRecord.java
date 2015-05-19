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
package us.illyohs.azathoth.world;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import us.illyohs.azathoth.math.Vector3;

/**
 *  This class keeps a record of a block at a location so it can be restored after a set delay.
 * It can be used by Domain to rebuild blocks destroyed by Creepers, Endermen or other players.
 * 
 * Use it like this:
 *  protected static DelayQueue<BlockRecord> phasedBlocks = new DelayQueue<BlockRecord>();
 * 
 *  private void phaseBlockAt(Vector3 coords) {
 *      BlockRecord record = new BlockRecord(60, coords, coords.getSigBlock());
 *      phasedBlocks.add(record);
 *  }
 *
 *  //ON BLOCK DESTROY EVENT
 *  phaseBlockAt(new Vector3( x, y, z));
 *  
 */

public class BlockRecord implements Delayed {
    public long expirationInMillis = 0; //exact expiration time is set when the object is constructed
    public Vector3 offset;
    public SigBlock block;
    
    public BlockRecord(int delayInSeconds, Vector3 displacement, SigBlock b){
        expirationInMillis = System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(delayInSeconds, TimeUnit.SECONDS);
        offset = displacement;
        block = b;
    }
    
    @Override
    public int compareTo(Delayed arg0) {
        return (int) (getDelay(TimeUnit.SECONDS) - arg0.getDelay(TimeUnit.SECONDS));
    }
    @Override
    public long getDelay(TimeUnit unit) {
        //the difference is simply figured from the current system time
        return unit.convert(expirationInMillis - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }
}