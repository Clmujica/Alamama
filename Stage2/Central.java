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
    public void checkZone(){// queee?????
        if(isArmed)
        {
            for (int i = 0; i < zone0.size(); ++i ) {
                if(zone0.get(i).getState() == SwitchState.OPEN)// {
                    siren.play();
                /*
                    System.out.println("alarma activada");
                }
                
                 */
            }
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
