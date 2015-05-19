package us.illyohs.azathoth.pattern;

import java.util.ArrayList;

import us.illyohs.azathoth.util.ExtendedPos;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
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
        
        //Check to see if the player has punched a block
        if(event.entityPlayer.worldObj.isRemote && event.action == Action.RIGHT_CLICK_BLOCK) {
            //Search the surrounding blocks for a pattern
            patternMatcher(event.entityPlayer, new ExtendedPos(event.entityPlayer, (int)event.pos.getX(), (int)event.pos.getY(),(int)event.pos.getZ()));
            
        }
                //Do nothing if no pattern is found
    }

    private void patternMatcher(EntityPlayer entityPlayer, ExtendedPos extendedPos) {
        //Fire event and execute patter if pattern is found
        // TODO Auto-generated method stub
        
    }
}
