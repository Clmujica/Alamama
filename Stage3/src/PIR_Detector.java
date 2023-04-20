public class PIR_Detector extends Sensor{
    public PIR_Detector(double coordenada_x, double coordenada_y, double phi, double theta, double radio){ //aca las posiciones del PIR las leemos del .txt o se las damos arbitrariamente?
        this.coordenada_x = coordenada_x;
        this.coordenada_y = coordenada_y;
        this.phi = phi;
        this.theta = theta;
        this.radio = radio;
        this.state = SwitchState.CLOSE;
    }
    {
        id = nextId++;
    }

    /* Talvez no sea necesario, porque no le cambiamos el estado
    @Override
    public State getState() {
        return state;
    }
    */

    /*
    Agregar metodos de detector
     */
    public void inArea(Person p){
        double L = p.getLength(this.getCoordenada_x(), this.getCoordenada_y());//Se le entrega la posicion de el pir al metodo Len
        if (L < this.getRadio()) {//Si su distancia es menor que el radio del cono, significa que PUEDE estar dentro
            double XX = p.getXx() - this.getCoordenada_x();
            double a = 0;
            //Esto para calcular el alfa (angulo de la posicion de persona respecto a pir)
            if ( XX > 0) {//Si su posicion x positiva, puede estar en el cuadrante 1 o 4
                if ((p.getYy() - this.getCoordenada_y()) < 0) {//Cuadrante 4
                    a = java.lang.Math.toDegrees(java.lang.Math.asin(((XX / L)))) + 270;//Esta en grados hexadecimales
                } else {//Cuadrante 1
                    a = java.lang.Math.toDegrees(java.lang.Math.acos((XX / L)));//Esta en grados hexadecimales
                }

            } else {//Si su posicion y positiva, puede estar en el cuadrante 2 o 3
                if ((p.getYy() - this.getCoordenada_y()) < 0) {//Cuadrante 3
                    a = java.lang.Math.toDegrees(java.lang.Math.acos(((-XX) / L))) + 180;//-XX para que se haga positivo y tenga sentido en el cálculo
                } else {//Cuadrante 2
                    a = java.lang.Math.toDegrees(java.lang.Math.asin(((-XX) / L))) + 90;//Lo mismo aquí
                }
            }
            //Comprobar si el angulo se encuentra en la zona
            if (a <= (this.getTheta() + this.getPhi())) {//Ve si esta mas arriba del cono
                if(a >=this.getPhi()){// si esta mas abajo del cono
                    System.out.println("Estamos en el área");
                    this.open();//abrira el sensor
                }
            }
        }else {
            //Esto representa que no se encuntra en la zona
            System.out.println("No estamos en el área");
            if(this.getState() == SwitchState.OPEN)this.close();
        }
    }


public SwitchState getState(){
        return this.state;
}

public void open() {
        this.state = SwitchState.OPEN;
        }

public void close() {
        this.state = SwitchState.CLOSE;
        }

    public String getHeader(){
        return "Pir";
    }

    public int getId() {
        return id;
    }

    private double coordenada_x;

    public double getCoordenada_x() {
        return coordenada_x;
    }

    public double getCoordenada_y() {
        return coordenada_y;
    }

    public double getPhi() {
        return phi;
    }

    public double getTheta() {
        return theta;
    }

    public double getRadio() {
        return radio;
    }

    private double coordenada_y;
    private double phi;
    private double theta;
    private double radio;
    private SwitchState state;
    private final int id;

    private static int nextId;
    static {
        nextId = 0;
    }
}

