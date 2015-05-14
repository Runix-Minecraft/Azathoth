/**
 * Copyright (c) 2014, Anthony Anderson(Lord Illyohs)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package us.illyohs.azathoth.world;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import us.illyohs.azathoth.math.Vector3;

public class WorldPos extends BlockPos {

    private transient World world;
    private int             dimID;
    private int             face;

    public int              posX;
    public int              posY;
    public int              posZ;
    
    public BlockPos         pos;

    /**
     * @param x
     * @param y
     * @param z
     */
    public WorldPos(double x, double y, double z) {
        super(x, y, z);
        this.setWorld(defaultWorld());
    }

    public WorldPos(int x, int y, int z) {
        super(x, y, z);
        this.setWorld(defaultWorld());
    }

    public WorldPos(World world, int x, int y, int z, int face) {
        super(x, y, z);
        this.setWorld(world);
        this.face = face;
    }

    public WorldPos(EntityPlayer player) {
        super((int) (player.posX + .5), (int) (player.posY - 1), (int) (player.posZ + .5));
        setWorld(player.worldObj);
    }

    public WorldPos(BlockPos otherGuy) {
        super(otherGuy);
        if (otherGuy instanceof WorldPos) {
            this.setWorld(((WorldPos) otherGuy).getWorld());
        } else {
            this.setWorld(defaultWorld());
        }
    }

    // Is this needed with BlockPos now with blockpos
    public WorldPos offset(int dX, int dY, int dZ) {
        return new WorldPos(this.getWorld(), this.getX() + dX, this.getY() + dY, this.getZ() + dZ, face);
    }

    public WorldPos offset(int dX, int dY, int dZ, int facing) {
        return new WorldPos(this.getWorld(), this.getX() + dX, this.getY() + dY, this.getZ() + dZ, facing);
    }

    public WorldPos offset(Vector3 delta) {
        return new WorldPos(this.getWorld(), getX() + delta.x, getY() + delta.y, getZ() + delta.z, face);
    }

    public WorldPos offsetWorld(Vector3 delta, World dem) {
        return new WorldPos(dem, getX() + delta.x, getY() + delta.y, getZ() + delta.z, face);
    }

    /**
     * Like offset() but for facing instead. Returning a new instance avoids
     * side-effecting
     */
    public WorldPos copyWithNewFacing(int face) {
        WorldPos n = new WorldPos(this);
        n.face = face;
        return n;
    }

    public WorldPos rotate(WorldPos referencePoint, boolean counterClockwise) {
        Vector3 d = new Vector3(referencePoint, this);// determine quadrant
                                                      // relative to reference
        int direction = counterClockwise ? -1 : 1;
       /*handle facing rotation:
         int index = Vector3.xzRotationOrder.indexOf(new Integer(referencePoint.face));
             if(index > -1) //not up or down
                 face = Vector3.xzRotationOrder.get( (index+direction ) % 4 );
         Josiah: you have no idea how hard it was to get this one line of code
        */
        if (referencePoint.face == 1 || referencePoint.face == 0)// UP or DOWN, xz rotation
            return referencePoint.offset(direction * -d.z, d.y, direction * d.x, face);
        if (referencePoint.face == 2 || referencePoint.face == 3)// North South, XY rotation
            return referencePoint.offset(direction * d.y, direction * -d.x, d.z, face);
        // East or West YZ rotation
        return referencePoint.offset(d.x, direction * -d.z, direction * d.y, face);
    }
    
    @Override
    public boolean equals(Object otherObj) {
        if (!(otherObj instanceof BlockPos)){
            return false;
        }else{
            WorldPos other = (WorldPos)otherObj;
            if (this.getX() == other.posX && this.getY() == other.posY && this.getZ() == other.posZ){
                if(other instanceof WorldPos) { 
                    return ((WorldPos) other).getWorld() == this.getWorld();
                }else { //NOTE: This does not compare the face of each coordinate
                    return true;
                }
            }
            return false;
        }
    }

    public SigBlock getSigBlock() {
        return new SigBlock(getBlock(), getState());
    }

    //Simple wrapper method for getBlockID()
    public Block getBlock() {
        return getWorld().getBlockState(pos).getBlock();
    }

    //Sister function to getBlockID() for meta values.
    public IBlockState getState() {
        return getWorld().getBlockState(pos);
    }

    /**
     * Simple wrapper method for setBlockID()
     * @param blockID
     * @return true if successful
     */
    public boolean setBlockIdAndUpdate(Block blockID){
        if(blockID == Blocks.bedrock || getBlock() == Blocks.bedrock) {            
            return false; //You cannot delete or place bedrock
        }
//        return this.getWorld().setBlock(posX, posY, posZ, blockID);
        return getWorld().setBlockState(pos, (IBlockState) getState().getBlock());
    }

    public boolean setBlockId(SigBlock sig){
        if(sig.equals(Blocks.bedrock) || getBlock() == Blocks.bedrock) {
            return false; //You cannot delete or place bedrock
        }
//        return this.getWorld().setBlock(posX, posY, posZ, sig.blockID, sig.meta, 2);
        return getWorld().setBlockState(pos, sig.state);
        //NOTE: Use last arg 3 if you want a block update.
    }

    public boolean setBlock(Block blockID, IBlockState state){
        if(blockID == Blocks.bedrock || getBlock() == Blocks.bedrock) {
            return false; //You cannot delete or place bedrock            
        }
//        return this.getWorld().setBlock(posX, posY, posZ, blockID, meta, 3);
        return getWorld().setBlockState(pos, (IBlockState) state.getBlock());
    }

    public String toString(){//this is designed to match the GSON output
        return "{\"dimensionID\":"+dimID+",\"face\":"+face+",\"posX\":"+posX+",\"posY\":"+posY+",\"posZ\":"+posZ+"}";
//        return "(" + posX + "," + posY +  "," + posZ + ")";
    }

    public int getDimensionID() {
        if (getWorld() == null) {
            setWorld(defaultWorld());
        }
        return 0;
    }

    /**
     * @return
     */
    private World defaultWorld() {
        return MinecraftServer.getServer().worldServerForDimension(0);
    }

    /**
     * 
     */
    public void setWorld(World world) {
        this.world = world;
        dimID = getDimensionID();
    }

    public void setWorld(int dimension) {
        world = MinecraftServer.getServer().worldServerForDimension(dimension);
        dimID = getDimensionID();
    }

    public World getWorld() {
        if (world == null) {
            setWorld(dimID);
        }
        return world;
    }
    
}
