public class PIR_Detector extends Sensor{
    public PIR_Detector(){

    }
    {
        id = nextId++;
    }

    public void inArea() {
        //Que reconozca que esta dentro del area
    }
    @Override
    public State getState() {
        return state;
    }
    /*
    Agregar metodos de detector
     */
    public int getId() {
        return id;
    }

    private State state;
    private final int id;



    private static int nextId;
    static {
        nextId = 0;
    }
}
}
