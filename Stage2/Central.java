import java.util.ArrayList;

public class Central {
    public Central(){
        zone0 = new ArrayList<Sensor>();
        isArmed = false;
        siren = null;
    }
    public void arm() {
        isArmed=true;
    }//Armar to'o????
    public void disarm() {//talvez más
        isArmed=false;
        //...
        //Desarmar to'o
    }
    public void setSiren(Siren s) {
        siren =s;
    }
    public void addNewSensor(Sensor s){
        zone0.add(s);
    }
    public void checkZone(){//Chequea que los estados de los sensores
        if(isArmed)
        {
            int e = 0; //Almacenará cuantas estan encedidas
            for (int i = 0; i < zone0.size(); ++i ) {
                if(zone0.get(i).getState() == SwitchState.OPEN ) {
                    ++e;
                    if (siren.getState() == 0) siren.play();//Si no esta sonando, la encenderá, pero si lo esta, no lo hará de nuevo
                    /* Esto es por si no funciona la lectura del audio
                {
                    System.out.println("alarma activada");
                }
                 */
                }
            }
            if(e == 0) siren.stop(); //Si no hay ninguna encendida, apagará la alarma
        }
    }
    public String getHeader(){
        return "Central";
    }
    public int getState(){
        return isArmed?1:0;
    }
    private ArrayList<Sensor> zone0;
    private boolean isArmed;
    private Siren siren;
}
