public class Door {
    public Door () {
        magneticSensor = new MagneticSensor();
        ...
    }
    {
        id = nextId++;
    }
    public void open() {
        ...
    }
    public void close() {
        ...
    }
    public String getHeader(){
        return "d"+id;
    }
    public int getState(){
        ...
    }

    private MagneticSensor magneticSensor;
    private State state;
    private final int id;
    private static int nextId;
    static {
        nextId = 0;
    }
}
