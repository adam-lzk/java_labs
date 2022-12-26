package bsu.rfe.java.group10.lab1.Laziuk.varA2;

public class Apple extends Food {

    private String size;

    public Apple(String size) {
        super("Apple");
        this.size = size;
    }

    public void consume() {
        System.out.println(this + " is eaten");
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            if (!(obj instanceof Apple)) return false; 
            return size.equals(((Apple)obj).size); 
        } else return false;
    }

    public String toString() {
        return super.toString() + " '" + size.toUpperCase() + "' size";
    }

}
