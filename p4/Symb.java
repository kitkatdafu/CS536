public class Symb {
    private String type;

    public static boolean TRIGGERED = false;
    
    public Symb(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }
    
    public String toString() {
        return type;
    }
}
