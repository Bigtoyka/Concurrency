
public class HomeWork4 {
    static final String A = "A";
    static final String B = "B";
    static final String C = "C";
    static Object monitor = new Object();
    static String next = "A";

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (monitor) {
                    for (int i = 0; i < 5; i++) {
                        while (!next.equals(A)) {
                            try {
                                monitor.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        System.out.print(A);
                        next = B;
                        monitor.notifyAll();
                    }
                }

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (monitor) {
                    for (int i = 0; i < 5; i++) {
                        while (!next.equals(B)) {
                            try {
                                monitor.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        System.out.print(B);
                        next = C;
                        monitor.notifyAll();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (monitor) {
                    for (int i = 0; i < 5; i++) {
                        while (!next.equals(C)) {
                            try {
                                monitor.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        System.out.print(C);
                        next = A;
                        monitor.notifyAll();
                    }
                }
            }
        }).start();
    }
}
