public class Door {
    public Door () {
        magneticSensor = new MagneticSensor();
        //...
    }
    {
        id = nextId++;
    }
    public void open() {
        this.state = State.OPEN;
    }
    public void close() {
        this.state = State.CLOSE;
    }
    public String getHeader(){
        return "d"+id;
    }
    public int getState(){
        if(this.state == State.CLOSE){
            return 0;
        }
        else {
            return 1;
        }
    }

    private MagneticSensor magneticSensor;
    private State state;
    private final int id;
    private static int nextId;
    static {
        nextId = 0;
    }
}
