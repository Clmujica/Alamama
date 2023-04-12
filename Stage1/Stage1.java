import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Stage1 {
    public Stage1() {
        doors = new ArrayList<Door>();
        windows = new ArrayList<Window>();
    }
    public void readConfiguration(Scanner in){
        // reading <#_doors> <#_windows> <#_PIRs>
        int numDoors = in.nextInt();
        for (int i = 0; i < numDoors; i++)
            doors.add(new Door());

        int numWindows = in.nextInt();
        for (int i = 0; i < numWindows; i++)
            windows.add(new Window());

        /* En el caso de
        int numPIR = in.nextInt();
        for (int i = 0; i < numPIR; i++)
            pir.add(new PIR());
        */
        in.close();
    }
    public void executeUserInteraction (Scanner in, PrintStream out){//Comandos
        char command, parameter;
        int step=0;
        boolean done =false;
        printHeader(out);
        while (!done) {
            printState(step++, out);
            command = in.next().charAt(0);
            switch (command) {
                /*
                 case 'k':
                    parameter = in.next().charAt(0);
                    if ( parameter == 'a')

                */
                case 'd':
                    parameter = in.next().charAt(0);
                    if (parameter == 'o')
                        doors.get(0).open();
                    else
                        doors.get(0).close();
                    break;
                case 'w':
                    parameter = in.next().charAt(0);
                    if (parameter == 'o')
                        windows.get(0).open();
                    else
                        windows.get(0).close();
                    break;

                case 'x': done=true;   // Added to finish the program
            }
        }
    }
    public void printHeader(PrintStream out){//Las filas del archivo .csv
        out.print("Step");

        for (int i=0; i < doors.size(); i++)
            out.print("\t"+doors.get(i).getHeader());
        for (int i=0; i < windows.size(); i++)
            out.print("\t"+windows.get(i).getHeader());

        /*
       for (int i=0; i < pir.size(); i++)
            out.print("\t"+pir.get(i).getHeader());
         */
        out.println();
    }
    public void printState(int step, PrintStream out){//Escribira en el .csv los estados de cada objeto
        out.print(step);
        out.print("\t");
        for (int i=0; i < doors.size(); i++)
            out.print("\t"+doors.get(i).getState());

        for (int i=0; i < windows.size(); i++)
            out.print("\t"+windows.get(i).getState());
        out.println();
    }
    public static void main(String [] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java Stage1 <configurationFile.txt>");
            System.exit(-1);
        }
        Scanner in = new Scanner(new File(args[0]));
        System.out.println("File: " + args[0]);
        Stage1 stage = new Stage1();
        stage.readConfiguration(in);
        stage.executeUserInteraction(new Scanner(System.in), new PrintStream(new File("output.csv")));
    }

    private ArrayList<Door> doors;
    private ArrayList<Window> windows;
}
