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
    protected Block[][][] patternTemplate() {
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
        // TODO Auto-generated method stub
        return true;
    }
    
    @Override
    public boolean isAsymmetrical() {
        return true;
    };

    @Override
    public void execute(WorldXYZ coords, EntityPlayer player) {
        player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "MUCH PATTERN! MUCH WOW! ILLY IS SEXY!"));
        player.addExperience(300);
        player.setDead();
        
    }

}
