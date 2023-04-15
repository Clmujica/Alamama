import java.lang.Math.*;

public class Person {
    public Person(double x, double y){
        xx = x;
        yy = y;
    }
    {
        id = nextId++;
    }

    public double getXx() {
        return xx;
    }
    public double getYy() {
        return yy;
    }
    public int getId() {
        return id;
    }

    public void movePerson(char m){ //acá podemos reutilizar el código para  el stage 4, pasandole ahora como parámetros movePerson(Person p, Character m){...}
        switch(m){
            case '←':
                xx = xx - 0.5;
                break;
            case '↑':
                yy = yy + 0.5;
                break;
            case '→':
                xx = xx + 0.5;
                break;
            case '↓':
                yy = yy - 0.5;
                break;
        }
    }

    public double getLength(Double Ypir){
        return java.lang.Math.sqrt( ( java.lang.Math.pow(this.xx,2)) + java.lang.Math.pow((this.yy - Ypir),2) );
    }
    private double xx;
    private double yy;
    private final int id;
    private static int nextId;
    static {
        nextId = 0;
    }
}
