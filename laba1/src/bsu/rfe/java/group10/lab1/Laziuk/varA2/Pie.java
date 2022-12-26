package bsu.rfe.java.group10.lab1.Laziuk.varA2;

public class Pie extends Food {

    private String filling = null;

    public Pie(String filling) {
        super("Pie");
        this.filling = filling;
    }

    public void setFilling(String filling) {
        this.filling = filling;
    }

    public String getFilling() {
        return filling;
    }

    @Override
    public String toString() {
        return super.toString()+ " with '" + filling.toUpperCase() + "' filling";
    }

    @Override
    public void consume() {
        System.out.println(this + " is eaten");
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            if (!(obj instanceof Pie)) return false;
            return filling.equals(((Pie)obj).filling);
        } else return false;
    }

}
