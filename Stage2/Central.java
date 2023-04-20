import java.io.IOException;
import java.util.ArrayList;

public class Central {
    public Central(){
        zone0 = new ArrayList<Sensor>();
        isArmed = false;
        siren = null;
    }
    public void arm() {
        isArmed=true;
    }
    public void disarm() {
        isArmed=false;//apaga la central
        per = false;//desactiva el perimetro

    }

    public void actPer(){
        if(!isArmed) {
            per = true;
        }
    }
    public void setSiren(Siren s) {
        siren =s;
    }
    public void addNewSensor(Sensor s){
        zone0.add(s);
    }
    public void checkZone(){//Chequea que los estados de los sensores
        if(isArmed || per)
        {
            int e = 0; //Almacenará cuantas estan encedidas
            for (int i = 0; i < zone0.size(); ++i ) {
                if(zone0.get(i).getState() == SwitchState.OPEN ) {
                    ++e;
                    if (siren.getState() == 0) {

                            siren.play();//Si no esta sonando, la encenderá, pero si lo esta, no lo hará de nuevo

                        }
                    }
                }
            }
            if(e == 0 && siren.getState() == 1) siren.stop(); //Si no hay ninguna encendida, apagará la alarma
        }
    }

    public String getHeader(){
        return "Central";
    }
    public int getState(){
        if(isArmed || per) return 1;
        return 0;
    }
    public int getStPer(){
        return per?1:0;
    }
    private ArrayList<Sensor> zone0;
    private boolean isArmed;
    private boolean per;//
    private Siren siren;
}
