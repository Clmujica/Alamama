public class PIR_Detector extends Sensor{
    public PIR_Detector(double coordenada_x, double coordenada_y, double phi, double theta, double radio){ //aca las posiciones del PIR las leemos del .txt o se las damos arbitrariamente?
        this.coordenada_x = coordenada_x;
        this.coordenada_y = coordenada_y;
        this.phi = phi;
        this.theta = theta;
        this.radio = radio;
    }
    {
        id = nextId++;
    }

    @Override
    public State getState() {
        return state;
    }
    /*
    Agregar metodos de detector
     */

    public String getHeader(){
        return "PIR";
    }

    public int getId() {
        return id;
    }

    private double coordenada_x;
    private double coordenada_y;
    private double phi;
    private double theta;
    private double radio;
    private State state;
    private final int id;

    private static int nextId;
    static {
        nextId = 0;
    }
}

