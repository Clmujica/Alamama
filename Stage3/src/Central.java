import java.io.IOException;
import java.util.ArrayList;

public class Central {
    public Central(){
        zone0 = new ArrayList<Sensor>();
        zone1 = new ArrayList<Sensor>();
        isArmed = false;
        per = false;
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
    public void checkZone(ArrayList<Person> people){//Chequea que los estados de los sensores, se le entrega las personas que existen
        int e = 0; //Almacenará cuantas estan encedidas
        if(isArmed || per) {
            for (int i = 0; i < zone0.size(); ++i ) {
                if (zone0.get(i).getState() == SwitchState.OPEN) {
                    ++e;
                    if (siren.getState() == 0)
                        siren.play();//Si no esta sonando, la encenderá, pero si lo esta, no lo hará de nuevo
                }
            }

                if (!per) {
                    for (int j = 0; j < people.size(); ++j) {//Ira por cada persona
                        for(int k = 0; k < zone1.size(); ++k) {//Ira por cada pir
                            PIR_Detector pir = (PIR_Detector) zone1.get(k);
                            System.out.println("Estamos en el pir " + k);
                            pir.inArea(people.get(j));
                            if(pir.getState() == SwitchState.OPEN ) {
                                System.out.println("El pir"+ k + " esta prendido");
                                if(siren.getState() == 0) siren.play();//prendera la sirena si esta en el área
                            }
                        }
                    }
                }
            }
            if(e == 0 && siren.getState() == 1) siren.stop(); //Si no hay ninguna encendida, apagará la alarma
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
