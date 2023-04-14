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

    public void movePerson(Character m){
        switch(m){
            case (m == '←'):
                xx = xx - 0.5;
                break;
            case (m == '↑'):
                yy = yy + 0.5;
                break;
            case (m == '→'):
                yy = yy + 0.5;
                break;
            case (m == '↓'):
                yy = yy - 0.5;
                break;
        }
    }
    private double xx;
    private double yy;
    private final int id;
    private static int nextId;
    static {
        nextId = 0;
    }
}
