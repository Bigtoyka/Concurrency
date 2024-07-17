public class GoToBankomat {
    public static void main(String[] args) {
        Bankomat bankomat = new Bankomat(10);
        Thread client1 = new Thread(new Runnable() {
            @Override
            public void run() {
                bankomat.getSize("Bob", 2);
            }
        });
        Thread client2 = new Thread(new Runnable() {
            @Override
            public void run() {
                bankomat.getSize("Karolina", 3);
            }
        });
        Thread client3 = new Thread(new Runnable() {
            @Override
            public void run() {
                bankomat.getSize("Daria", 10);
            }
        });
        client1.start();
        client2.start();
        client3.start();

    }
}
