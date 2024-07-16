public class Bankomat {
    double size;

    public Bankomat(double size) {
        this.size = size;
    }

    private final Object monitor = new Object();

    public void getSize(String name, double number) {
        synchronized (monitor) {
            System.out.println(name + " подошел к банкомату");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (number <= this.size) {
                this.size = this.size - number;
                System.out.println(name + " вывел " + number + " рублей");
                System.out.println("В банкомате осталось: " + this.size + " рублей");
            } else {
                System.out.println("В банкомате недостаточно денег для " + name);
            }
        }
    }
}
