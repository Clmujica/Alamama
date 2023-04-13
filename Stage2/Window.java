public class Window {
    public Window() {
        magneticSensor = new MagneticSensor();
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
        return "w"+id;
    }
    public int getState(){
        if(this.state == State.CLOSE){
            return 0;
        }
        else {
            return 1;
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
    private static int nextId=0;
}
