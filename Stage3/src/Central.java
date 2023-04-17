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
    public boolean inArea(Person p) {//me falta terminarlo, pero me acorde que tengo que hacer algo, asi que mañana lo hago
        for (int i = 0; i < zone1.size(); ++i) {
            PIR_Detector pir = (PIR_Detector) zone1.get(i);
            double a = 0;
            double L = p.getLength(pir.getCoordenada_x(),pir.getCoordenada_y());//Se le entrega la posicion de el pir al metodo Len
            if (L <= pir.getRadio()) {//Si su distancia es menor que el radio del cono, significa que PUEDE estar dentro
                double XX = p.getXx() - pir.getCoordenada_x();
                //Esto para calcular el alfa (angulo de la posicion de persona respecto a pir)
                if ( XX > 0) {//Si su posicion x positiva, puede estar en el cuadrante 1 o 4
                    if ((p.getYy() - pir.getCoordenada_y()) < 0) {//Cuadrante 4
                        a = java.lang.Math.toDegrees(java.lang.Math.asin(((XX / L)))) + 270;//Esta en grados hexadecimales
                    } else {//Cuadrante 1
                        a = java.lang.Math.toDegrees(java.lang.Math.acos((XX / L)));//Esta en grados hexadecimales
                    }

                } else {//Si su posicion y positiva, puede estar en el cuadrante 2 o 3
                    if ((p.getYy() - pir.getCoordenada_y()) < 0) {//Cuadrante 3
                        a = java.lang.Math.toDegrees(java.lang.Math.acos((XX / L))) + 180;
                    } else {
                        a = java.lang.Math.toDegrees(java.lang.Math.asin((XX / L))) + 90;
                    }
                }
                //Comprobar si el angulo se encuentra en la zona
                if (a <= (pir.getTheta() + pir.getPhi())) {//Ve si esta mas arriba del cono
                    if(a >=pir.getPhi()){// si esta mas abajo del cono
                        return true;//Retornara verdadero
                    }
                }
            }
        }
        //Esto representa que no se encuntra en la zona
        return false;
    }
    public void checkZone(ArrayList<Person> people){//Chequea que los estados de los sensores, se le entrega las personas que existen
        int e = 0; //Almacenará cuantas estan encedidas
        if(isArmed || per)
        {
            for (int i = 0; i < zone0.size(); ++i ) {
                if (zone0.get(i).getState() == SwitchState.OPEN) {
                    ++e;
                    if (siren.getState() == 0) siren.play();//Si no esta sonando, la encenderá, pero si lo esta, no lo hará de nuevo

                }
                if (!per) {
                    for (int j = 0; j < people.size(); ++j) {//Ira por cada persona
                        if (this.inArea(people.get(j))) {//Comprobara si persona esta en el area de cada pir
                            ++e;//Aumentara la cantidad de alarmas encendidas
                            if (siren.getState() == 0) siren.play();//Si no esta sonando, la encenderá, pero si lo esta, no lo hará de nuevo
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
