import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Stage4 {
    public Stage4() {
        doors = new ArrayList<Door>();
        windows = new ArrayList<Window>();
        pirs = new ArrayList<PIR_Detector>();
        personas = new ArrayList<Person>();
    }
    public void readConfiguration(Scanner in){
        // reading <#_doors> <#_windows> <#_PIRs>
        central = new Central();
        int numDoors = in.nextInt();
        for (int i = 0; i < numDoors; i++) {
            Door d = new Door();
            doors.add(d);
            central.addNewSensor(d.getMagneticSensor(), 0);//Se irán agregando los sensores de cada puerta a el array zone0 de central
        }
        int numWindows = in.nextInt();
        for (int i = 0; i < numWindows; i++) {
            Window w = new Window();
            windows.add(w);
            central.addNewSensor(w.getMagneticSensor(), 0);//Se irán agregando los sensores de cada ventana a el array zone0 de central
        }
        int numPIRs = in.nextInt();
        in.nextLine();
        for (int i = 0; i < numPIRs; i++) {
            PIR_Detector p = new PIR_Detector(in.nextDouble(), in.nextDouble(), in.nextInt(), in.nextInt(), in.nextInt()); //coordenada en x, y, ángulo phi (dirección del cono), ángulo theta (del cono), radio
            pirs.add(p);
            central.addNewSensor(p, 1); //Se agrega el pir a la central
            in.nextLine();
        }
        String soundFile = in.next();
        System.out.println(soundFile);
        siren = new Siren(soundFile);
        central.setSiren(siren);
        in.close();
    }
    public void executeUserInteraction (Scanner in, PrintStream out){//consola
        String command;
        char parameter;
        int i;
        int step=0;
        printHeader(out);
        while (true) {
            printState(step++, out);
            command = in.next();
            if (command.charAt(0)=='x') break;
            switch (command.charAt(0)) {
                case 'c':
                    Person p = new Person(in.nextDouble(), in.nextDouble());
                    personas.add(p);
                    break;
                case 'p':
                    i = Integer.parseInt(command.substring(1));//El valor i
                    parameter = in.next().charAt(0);//El valor de la flecha
                    personas.get(i).movePerson(parameter);
                    break;
                case 'd':
                    i = Integer.parseInt(command.substring(1));
                    parameter = in.next().charAt(0);
                    if (parameter== 'o')
                        doors.get(i).open();
                    else
                        doors.get(i).close();
                    break;
                case 'w':
                    i = Integer.parseInt(command.substring(1));
                    parameter = in.next().charAt(0);
                    if (parameter== 'o')
                        windows.get(i).open();
                    else
                        windows.get(i).close();
                    break;
                case 'k':
                    parameter = in.next().charAt(0);
                    switch (parameter) {
                        case 'a':
                            if(central.getStPer() == 0) {
                                central.arm();
                            }
                            else{
                                System.out.println("Tiene que estar desarmado para poder volver a armar");
                            }
                            break;
                        case 'p':
                            if(central.getState() == 0) {
                                central.actPer();
                            }
                            else{
                                System.out.println("Tiene que estar desarmado para poder activar el perimetro");
                            }
                            break;
                        case 'd':
                            central.disarm();
                            break;
                    }
                    break;
            }
            central.checkZone(personas);//Chequea que se haya abierto algun objeto
        }
    }

    public void printHeader(PrintStream out){// imprime la primera linea de .csv
        out.print("Step");

        for (int i=0; i < doors.size(); i++)
            out.print("\t"+doors.get(i).getHeader() + i);
        for (int i=0; i < windows.size(); i++)
            out.print("\t"+windows.get(i).getHeader() + i);
        /*
        pir
         */
        for (int i = 0; i < pirs.size(); ++i)
            out.print("\t" + pirs.get(i).getHeader()+i);

        out.print("\t");
        out.print(siren.getHeader());
        out.print("\t");
        out.print(central.getHeader());

        out.println();
    }
    public void printState(int step, PrintStream out){// imprime las demas lineas de .csv
        out.print(step);
        out.print("\t");
        for (int i=0; i < doors.size(); i++)
            out.print("\t"+doors.get(i).getState());

        for (int i=0; i < windows.size(); i++)
            out.print("\t"+windows.get(i).getState());
        for(int i = 0; i< pirs.size(); ++i){
            if(pirs.get(i).getState()==SwitchState.OPEN){
                out.print("\t"+0);
            }else{
                out.print("\t"+1);
            }
        }
        out.print("\t");
        out.print(siren.getState());
        out.print("\t");
        out.print("\t");
        out.print(central.getState());

        out.println();
    }
    public static void main(String [] args) throws IOException {

        if (args.length != 1) {
            System.out.println("Usage: java Stage4 <configurationFile.txt>");
            System.exit(-1);
        }


        Scanner in = new Scanner(new File(args[0])); // original
        //Scanner in = new Scanner(new File("config.txt"));
        //System.out.println("File: " + args[0]);
        //System.out.println("File: config.txt");
        Stage4 stage = new Stage4();
        stage.readConfiguration(in);
        stage.executeUserInteraction(new Scanner(System.in), new PrintStream(new File("output.csv")));
    }

    private ArrayList<Door> doors;
    private ArrayList<Window> windows;
    private ArrayList<PIR_Detector> pirs;
    private ArrayList<Person> personas;
    private Central central;
    private Siren siren;
}
