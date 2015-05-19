package us.illyohs.azathoth.pattern;

import us.illyohs.azathoth.util.ExtendedPos;
import net.minecraftforge.fml.common.eventhandler.Event;

public class PattenActivationEvent extends Event {

    public String      modId;
    public String      patternName;
    public ExtendedPos ePos;
    
    /**
     * This event is fired when a pattern is activated
     * 
     */
    public PattenActivationEvent(String modId, String patternName, ExtendedPos ePos) {
        this.modId          = modId;
        this.patternName    = modId;
        this.ePos           = ePos;
    }

}
