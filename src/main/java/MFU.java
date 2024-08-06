public class MFU {
    private Object scanMonitor = new Object();
    private Object printMonitor2 = new Object();

    public void scan(int numberOfPages) {
        synchronized (scanMonitor) {
            try {
                for (int i = 1; i < numberOfPages + 1; i++) {
                    Thread.sleep(1000);
                    System.out.println("Отсканировано " + i + "стр");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void print(int numberOfPages) {
        synchronized (printMonitor2) {
            try {
                for (int i = 1; i < numberOfPages + 1; i++) {
                    Thread.sleep(1000);
                    System.out.println("Отпечатано " + i + "стр");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
