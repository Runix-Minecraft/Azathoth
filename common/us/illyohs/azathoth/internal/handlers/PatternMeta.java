package us.illyohs.azathoth.internal.handlers;

public class PatternMeta {
    
    String owner;
    String name;
    
    boolean isfake;
    
    public PatternMeta(String owner, String name, Boolean isfake) {
        this.owner = owner;
        this.name = name;
        this.isfake = isfake;
    }

}
