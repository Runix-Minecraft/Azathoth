package us.illyohs.tests;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
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

@Mod(modid ="TestPattern", dependencies = "required-after:Azathoth")
public class TestPattern {

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        PatternRegistry.registerPattern(new FooPatten());
        PatternRegistry.registerPattern(new BarPattern());

    }

    @EventHandler
    public void Init(FMLInitializationEvent event) {

    }

    @EventHandler
    public void PostInit(FMLPostInitializationEvent event) {

    }

    public class FooPatten extends Pattern {

        @Override
        public Block[][][] patternTemplate() {
            Block STONE = Blocks.stone;
            Block RTOURCH = Blocks.cobblestone;
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
        public boolean isAsymmetrical() {
            return true;
        }

        @Override
        public void execute(WorldXYZ coords, EntityPlayer player) {
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "MUCH PATTERN! MUCH WOW! ILLY IS SEXY!"));
        }
    }

    public class BarPattern extends Pattern {

        @Override
        public Block[][][] patternTemplate() {
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
            return true;
        }

        @Override
        public boolean isAsymmetrical() {
            return false;
        };

        @Override
        public void execute(WorldXYZ coords, EntityPlayer player) {
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD + "MUCH PATTERN! MUCH WOW! ILLY IS SEXY!"));
            player.addExperience(300);
            player.setDead();

        }

    }
}
