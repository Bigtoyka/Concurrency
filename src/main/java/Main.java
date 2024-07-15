public class Main {
    private static final int size = 50_000_000;
    private static final int half = size / 2;

    public static void main(String[] args) {
        withConcurrency();
        withoutConcurrency();

    }

    public static void withoutConcurrency() {
        float[] array = new float[size];
        for (int i = 0; i < size; i++) {
            array[i] = (float) 1;
        }
        Long before = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            float f = (float) i;
            array[i] = (float) (array[i] * Math.sin(0.2f + f / 5) * Math.cos(0.2f + f / 5) * Math.cos(0.4f + f / 5));
        }
        Long after = System.currentTimeMillis();
        System.out.println("withoutConcurrency: " + (after - before));
    }

    public static void withConcurrency() {
        float[] array = new float[size];
        for (int i = 0; i < size; i++) {
            array[i] = (float) 1;
        }
        Long before = System.currentTimeMillis();
        float[] firstHalf = new float[half];
        float[] secondHalf = new float[half];
        System.arraycopy(array, 0, firstHalf, 0, half);
        System.arraycopy(array, half, secondHalf, 0, half);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < half; i++) {
                    float f = (float) i;
                    firstHalf[i] = (float) (firstHalf[i] * Math.sin(0.2f + f / 5) * Math.cos(0.2f + f / 5) * Math.cos(0.4f + f / 5));
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < half; i++) {
                    float f = (float) i + half;
                    secondHalf[i] = (float) (secondHalf[i] * Math.sin(0.2f + f / 5) * Math.cos(0.2f + f / 5) * Math.cos(0.4f + f / 5));
                }
            }
        });
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.arraycopy(firstHalf, 0, array, 0, half);
        System.arraycopy(secondHalf, 0, array, half, half);
        Long after = System.currentTimeMillis();
        System.out.println("withConcurrency: " + (after - before));
    }
}
