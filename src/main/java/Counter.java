public class Counter {

    private Object monitor1 = new Object();
    private Object monitor2 = new Object();

    private int value;
    private int value2;

    public int getValue() {
        return value;
    }

    public int getValue2() {
        return value2;
    }

    public void inc() {
        synchronized (monitor1) {
            value++; // Критическая секция
        }


    }

    public void dec() {
        synchronized (monitor1) {
            value--; // Критическая секция
        }
    }

    public void inc2() {
        synchronized (monitor2) {
            value2++; // Критическая секция
        }
    }

    public void dec2() {
        synchronized (monitor2) {
            value2--; // Критическая секция
        }
    }
}
