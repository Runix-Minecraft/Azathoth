package us.illyohs.azathoth.pattern;


public class PatternMeta {

    String      modId;
    Pattern pattern;

    public PatternMeta(String modId, Pattern pattern) {
        this.modId      = modId;
        this.pattern    = pattern;
    }
    
    /**
     * @return the modId
     */
    public String getModId() {
        return modId;
    }
    
    /**
     * @return the pattern
     */
    public Pattern getPattern() {
        return pattern;
    }
    
    /**
     * @param modId the modId to set
     */
    public void setModId(String modId) {
        this.modId = modId;
    }
    
    /**
     * @param pattern the pattern to set
     */
    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

}
