package us.illyohs.azathoth.util;

import us.illyohs.azathoth.math.Vector3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

//This class seems like alot of what we're doing is duplicating BlockPos might
//be best to trim down as the idea here is to extend BlockPos with as with
//with as few duplications & helpers as possible
public class ExtendedPos extends BlockPos {

    public int              posX, posY, posZ;
    public int              face;
    private int             dimID = -500000;
    private transient World world = null;
    
    public ExtendedPos(int x, int y, int z) {
        super(x, y, z);
        setWorld(defaultWorld());
    }
    
    
    public ExtendedPos( World world, int x, int y, int z, int face) {
        super(x, y, z);
        setWorld(world);
    }
    
    public ExtendedPos(EntityPlayer player) {
        super((int)(player.posX + 0.5), (int) (player.posY -1), (int) (player.posZ + 0.5));
        setWorld(player.worldObj);
    }
    
    public ExtendedPos(ExtendedPos pos) {
        super(pos);
        if (pos instanceof ExtendedPos) {
            this.setWorld(((ExtendedPos) pos).getWorld());
        } else {
            this.setWorld(defaultWorld());
        }
    }


    public ExtendedPos copyWithNewFaceing(int face) {
        ExtendedPos ep  = new ExtendedPos(this);
        ep.face         = face;
        return ep;
    }
    public ExtendedPos rotate(ExtendedPos referencePoint, boolean counterClockwise) {
        Vector3 d = new Vector3(referencePoint, this);// determine quadrant elative to reference
        int direction = counterClockwise ? -1 : 1;

        if (referencePoint.face == 1 || referencePoint.face == 0) { // UP or DOWN, xz rotation
            return referencePoint.offset(direction * -d.z, d.y, direction * d.x, face);
        }
        if (referencePoint.face == 2 || referencePoint.face == 3) { // North South, XY rotation
            return referencePoint.offset(direction * d.y, direction * -d.x, d.z, face);
        }
        
        return referencePoint.offset(d.x, direction * -d.z, direction * d.y, face); // East or West YZ rotation
    }
    
    //Don't think this is needed see BlockPos
    public ExtendedPos offset(int dX, int dY, int dZ) {
        return new ExtendedPos(this.getWorld(), this.getX() + dX, this.getY() + dY, this.getZ() + dZ, face);
    }

    //Don't think this is needed see BlockPos
    public ExtendedPos offset(int dX, int dY, int dZ, int facing) {
        return new ExtendedPos(this.getWorld(), this.getX() + dX, this.getY() + dY, this.getZ() + dZ, facing);
    }

    //Don't think this is needed see BlockPos
    public ExtendedPos offset(Vector3 delta) {
        return new ExtendedPos(this.getWorld(), getX() + delta.x, getY() + delta.y, getZ() + delta.z, face);
    }

    //Don't think this is needed see BlockPos
    public ExtendedPos offsetWorld(Vector3 delta, World dem) {
        return new ExtendedPos (dem, getX() + delta.x, getY() + delta.y, getZ() + delta.z, face);
    }

    public int getDimensionID() {
        if (getWorld() == null) {
            setWorld(defaultWorld());
        }
        return 0;
    }

    @Override
    public boolean equals(Object otherObj) {
        if (!(otherObj instanceof BlockPos)) {
            return false;
        } else {
            ExtendedPos other = (ExtendedPos)otherObj;
            if (this.getX() == other.posX && this.getY() == other.posY && this.getZ() == other.posZ) {
                if(other instanceof ExtendedPos) { 
                    return ((ExtendedPos) other).getWorld() == this.getWorld();
                } else { //NOTE: This does not compare the face of each coordinate
                    return true;
                }
            }
            return false;
        }
    }

    private World defaultWorld() {
        return MinecraftServer.getServer().worldServerForDimension(0);
    }

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
