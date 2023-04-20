public class Sensor {
    public Sensor(){//El estado normal
        this(SwitchState.OPEN);
    }
    public Sensor(SwitchState s){//si se quiere setear un valor
        this.state = s;
    }
    public SwitchState getState(){
        return this.state;
    }
    protected void setState(SwitchState s) {
        this.state = s;
    }
    public String toString(){
        if (state== SwitchState.CLOSE)
            return "1";
        else
            return "0";
    }
    private SwitchState state;
}
