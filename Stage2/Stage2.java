import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Stage2 {
    public Stage2() {
        doors = new ArrayList<Door>();
        windows = new ArrayList<Window>();
    }
    public void readConfiguration(Scanner in){
        // reading <#_doors> <#_windows> <#_PIRs>
        central = new Central();
        int numDoors = in.nextInt();
        for (int i = 0; i < numDoors; i++) {
            Door d = new Door();
            doors.add(d);
            central.addNewSensor(d.getMagneticSensor());//Se irán agregando los sensores de cada puerta a el array zone0 de central
        }
        int numWindows = in.nextInt();
        for (int i = 0; i < numWindows; i++) {
            Window w = new Window();
            windows.add(w);
            central.addNewSensor(w.getMagneticSensor());//Se irán agregando los sensores de cada ventana a el array zone0 de central
        }
        in.nextLine();
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
                            if(central.getStPer() == 0) central.arm();
                            else{
                                System.out.println("Tiene que estar desarmado para poder volver a armar");
                            }
                            break;
                        case 'p':
                            if(central.getState() == 0) central.actPer();
                            else{
                                System.out.println("Tiene que estar desarmado para poder activar el perimetro");
                            }

                            break;
                        case 'd':
                            central.disarm();
                            break;
                    }
            }
            central.checkZone();//Chequea que se haya abierto algun objeto
        }
    }
    public void printHeader(PrintStream out){// imprime la primera linea de .csv
        out.print("Step");

        for (int i=0; i < doors.size(); i++)
            out.print("\t"+doors.get(i).getHeader());
        for (int i=0; i < windows.size(); i++)
            out.print("\t"+windows.get(i).getHeader());
        /*
        pir
         */
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
        /*
        pir
         */
        out.print("\t");
        out.print(siren.getState());
        out.print("\t");
        out.print("\t");
        out.print(central.getState());

        out.println();
    }
    public static void main(String [] args) throws IOException {
        /*
        if (args.length != 1) {
            System.out.println("Usage: java Stage2 <configurationFile.txt>");
            System.exit(-1);
        }
        */

        //Scanner in = new Scanner(new File(args[0])); // original
        Scanner in = new Scanner(new File("config.txt"));
        //System.out.println("File: " + args[0]);
        System.out.println("File: config.txt");
        Stage2 stage = new Stage2();
        stage.readConfiguration(in);
        stage.executeUserInteraction(new Scanner(System.in), new PrintStream(new File("output.csv")));
    }

    private ArrayList<Door> doors;
    private ArrayList<Window> windows;
    private Central central;
    private Siren siren;
}
