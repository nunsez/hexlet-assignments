package exercise;

class App {

    public static void main(String[] args) {
        // BEGIN
        var list = new SafetyList();
        var thread1 = new ListThread(list);
        var thread2 = new ListThread(list);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException ignored) {}


        System.out.println("List size: " + list.getSize());
        // END
    }
}

