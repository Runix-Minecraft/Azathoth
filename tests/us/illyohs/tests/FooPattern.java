package us.illyohs.tests;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.illyohs.azathoth.pattern.Pattern;
import us.illyohs.azathoth.world.WorldXYZ;

public class FooPattern extends Pattern{

    @Override
    protected Block[][][] patternTemplate() {
        Block STONE = Blocks.stone;
        Block RTOURCH = Blocks.redstone_torch;
        return new Block[][][]{{
            {STONE, STONE, STONE},
            {STONE, RTOURCH, STONE},
            {STONE, STONE, STONE}
        }};
    }

    @Override
    public boolean isFlatPatternOnly() {
        return true;
    }

    @Override
    public void execute(WorldXYZ coords, EntityPlayer player) {
        player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "MUCH PATTERN MUCH WOW ILLY IS SEXY"));
    }

}
