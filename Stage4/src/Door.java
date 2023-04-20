public class Door {
    public Door () {
        magneticSensor = new MagneticSensor();
        this.state = State.CLOSE;
        magneticSensor.putMagnetNearSwitch();
    }
    {
        id = nextId++;
    }
    public void open() {
        this.state = State.OPEN;
        magneticSensor.moveMagnetAwayFromSwitch();

    }

    public void close() {
        this.state = State.CLOSE;
        magneticSensor.putMagnetNearSwitch();
    }
    public String getHeader(){
        return "d"+id;
    }
    public int getState(){
        if(this.state == State.CLOSE){
            return 1;
        }
        else {
            return 0;
        }
    }

    public MagneticSensor getMagneticSensor() {
        return magneticSensor;
    }
    public int getId() {
        return id;
    }

    private MagneticSensor magneticSensor;
    private State state;
    private final int id;
    private static int nextId;
    static {
        nextId = 0;
    }
}
