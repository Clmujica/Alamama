import java.io.IOException;
import java.util.ArrayList;

public class Central {
    public Central(){
        zone0 = new ArrayList<Sensor>();
        zone1 = new ArrayList<Sensor>();
        isArmed = false;
        siren = null;
    }
    public void arm() {
        isArmed=true;
    }//Armar to'o????
    public void disarm() {
        isArmed=false;//apaga la central
        per = false;//desactiva el perimetro
    }

    public void setSiren(Siren s) {
        siren =s;
    }
    public void addNewSensor(Sensor s, int n){ 
        if(n==0){
            zone0.add(s);
        }
        else if(n==1){
            zone1.add(s);
        }
        else{
            System.out.println("Zona Inválida");
        }
    }

    public void actPer(){
        if(!isArmed) {
            per = true;
        }
    }
    public void inArea(Person p){//me falta terminarlo, pero me acorde que tengo que hacer algo, asi que mañana lo hago
        for(int i = 0; i < zone1.size(); ++i){
            double L = p.getLength(zone1.get(i).);
        }
        //agregar metodos del pir
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
    private ArrayList<Sensor> zone1;
    private boolean isArmed;
    private boolean per;//
    private Siren siren;
}
