package us.illyohs.tests;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayer.EnumChatVisibility;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import us.illyohs.azathoth.pattern.Pattern;
import us.illyohs.azathoth.pattern.PatternRegistry;
import us.illyohs.azathoth.world.WorldXYZ;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid ="PatternTest",name = "Pattern Test", version = "NOPE")
public class PatternTest {
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        PatternRegistry.registerPattern(new FooPattern());
    }
    
    @EventHandler
    public void Init(FMLInitializationEvent event) {
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        
    }
    
    public class FooPattern extends Pattern {

        @Override
        protected Block[][][] patternTemplate() {
            Block RTORCH = Blocks.redstone_torch;
            Block STONE = Blocks.stone;
            return new Block[][][] {{
                        {STONE, STONE ,STONE},
                        {STONE, RTORCH, STONE},
                        {STONE, STONE ,STONE}
                    }};
        }

        @Override
        public boolean isFlatPatternOnly() {
            return true;
        }

        @Override
        public void execute(WorldXYZ coords, EntityPlayer player) {
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA +"HELLO IM A PATTERN"));
            
        }
        
    }

}
