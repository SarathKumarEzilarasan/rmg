public interface Car {
    public void common();

    public abstract void own();
}
interface Vehicle {
    public void common();
}
class Tata implements Car, Vehicle {
    public void own() {

    }
    public void common() {

    }
}


