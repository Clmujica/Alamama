public class MagneticSensor extends Sensor {
    public MagneticSensor(){}
    public void moveMagnetAwayFromSwitch() {
        setState(SwitchState.OPEN);//depende de que sea abierto o cerrado
    }
    public void putMagnetNearSwitch() {
        setState(SwitchState.CLOSE);//depende de que sea abierto o cerrado
    }
}
