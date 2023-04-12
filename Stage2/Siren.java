import java.io.File;
import java.net.URL;

public class Siren {
    public Siren (String soundFileName){
        try {
            dir = new File(soundFileName).toURI().toURL();
        }
        catch (Exception exc){
            exc.printStackTrace(System.out);
        }
        ...
    }
    public void play(){
        ...
        aWave= new AePlayWave(dir);
        aWave.start();
    }
    public void stop(){
        ...
        aWave.stopSounding();
    }
    public String getHeader() {
        return "Siren";
    }
    public int getState() {
        return ....;
    }
    private URL dir;
    private boolean isSounding;
    private AePlayWave aWave;
}
