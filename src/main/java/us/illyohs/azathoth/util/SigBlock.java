package us.illyohs.azathoth.util;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;


public class SigBlock {
    
    public IBlockState state;
    public Block       block;

    public SigBlock(Block block, IBlockState state) {
        this.block = block;
        this.state = state;
    }
    
    public SigBlock(IBlockState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof SigBlock) {
            return block == ((SigBlock) other).block && state == ((SigBlock) other).state;
        } else if (other instanceof Block) {
            return block.equals((Block) other); // can't get meta from block without coordinates
        }
        
        if (other instanceof SigBlock) {
            return state == ((SigBlock) other).state;
        }
        return false;
    }

}
