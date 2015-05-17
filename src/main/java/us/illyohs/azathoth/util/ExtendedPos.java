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
package us.illyohs.azathoth.util;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ExtendedPos extends BlockPos {

    public int              posX, posY, posZ;
    public int              face;
    private int             dimID = -500000;
    private transient World world = null;
    
    public ExtendedPos(int x, int y, int z) {
        super(x, y, z);
        setWorld(defaultWorld());
    }
    
    public ExtendedPos(int x, int y, int z, World world) {
        super(x, y, z);
        setWorld(world);
    }
    
    public int getDimensionID() {
        if (getWorld() == null) {
            setWorld(defaultWorld());
        }
        return 0;
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
