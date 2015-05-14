package us.illyohs.azathoth.pattern;

import java.util.ArrayList;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PatternRegistry {
    
    public PatternRegistry() {
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    public static ArrayList<PatternMeta> patReg = new ArrayList<PatternMeta>();

    public static void registerPattern(String modId, Pattern pattern) {
        patReg.add(new PatternMeta(modId, pattern));
    }
    
    @SubscribeEvent
    public void playerInteractEvent(PlayerInteractEvent event) {
        
    }
}
