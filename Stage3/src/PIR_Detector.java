public class PIR_Detector extends Sensor{
    public PIR_Detector(){

        //

    }
    {
        id = nextId++;
    }

    public void inArea(Person p) { //booleano? (si es así, puede llamar otra función que obtenga el ángulo y distancia donde está la persona)
        //Que reconozca que esta dentro del area
        /*if(detectar_persona() == True){ //pseudocódigo 
            int X = p.getXx();
            int Y = p.getYy();
            System.out.println("posición de la persona: ", X, Y);

            //podemos usar estas coordenadas para hacer una función que calcule ángulos no?
            //como un triángulo rectángulo, con medidas x,y entonces como tangente(alfa)=cateto opuesto/cateto adyacente podemos usar y=cateto opuesto
            //y cateto adyacente = x entonces quedaría alfa = arctan(y/x)

            float alfa = arctan(p.getYy/p.getXx);
            System.out.println("ángulo de la persona: ", alfa, "°"); 
        }
        */
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
