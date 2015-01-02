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
package us.illyohs.azathoth.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileOwnable extends TileEntity {
    
    protected String owner;
    protected EnumFacing direction;
     
    public TileOwnable() {
        direction = EnumFacing.SOUTH;
        owner = "";
        
    }
    
    public EnumFacing getDirection() {
        return direction;
    }
    
    public void setDirection(EnumFacing direction) {
        this.direction = direction;
    }
    
    public String getOwner() {
        return owner;
    }
    
    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound ntc) {
        super.readFromNBT(ntc);
        
        if(ntc.hasKey("owner")) {
            this.owner = ntc.getString("owner");
        }
        
//        if(ntc.hasKey("direction")) {
////            this.direction = EnumFacing.getOrientation(ntc.getByte("direction"));
//            this.direction = EnumFacing.
//        } 
    }
    
    @Override
    public void writeToNBT(NBTTagCompound ntc) {
        super.readFromNBT(ntc);
        
        ntc.setString("owner", owner);
//        ntc.setByte("owner", (byte) direction.ordinal());
    }
    
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound ntc = new NBTTagCompound();
        this.writeToNBT(ntc);
        return new S35PacketUpdateTileEntity(this.pos, -999, ntc);
    }
    
    @Override
    public void onDataPacket(NetworkManager nm, S35PacketUpdateTileEntity s35pute) {
        super.onDataPacket(nm, s35pute);
        this.readFromNBT(s35pute.getNbtCompound());
    }
    
}
